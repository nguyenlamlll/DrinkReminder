package com.letrannguyenlam.logic;

import com.letrannguyenlam.WaterIntake;
import com.letrannguyenlam.repositories.DrinkRecordRepository;
import com.letrannguyenlam.repositories.UserRepository;
import com.letrannguyenlam.repositories.models.DrinkRecord;
import com.letrannguyenlam.repositories.models.User;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.LinkedList;

public class DrinkRecordLogic {
    private DrinkRecordRepository drinkRecordRepository;
    private UserRepository userRepository;
    public DrinkRecordLogic() {
        this.drinkRecordRepository = new DrinkRecordRepository();
        this.userRepository = new UserRepository();
    }

    public void createDrinkRecord(DrinkRecord record) {
        // Set time of the record to NOW.
        record.setTimeTaken(new Timestamp(System.currentTimeMillis()));
        drinkRecordRepository.createDrinkRecord(record);
    }

    public double getCurrentProgress(int userId) {
        LinkedList<DrinkRecord> drinkRecords = drinkRecordRepository.getDrinkRecords(userId);

//        Calendar today = Calendar.getInstance();
//        Calendar tomorrow = Calendar.getInstance();
//        tomorrow.set(Calendar.DAY_OF_MONTH, today.get(Calendar.DAY_OF_MONTH) + 1);
        java.util.Date today = new java.util.Date(System.currentTimeMillis());
        today.setHours(0);
        today.setMinutes(0);
        today.setSeconds(0);

        for(int i = 0; i < drinkRecords.size(); i++) {
            java.util.Date timeTaken = new java.util.Date(drinkRecords.get(i).getTimeTaken().getTime());
            if (timeTaken.getDay() != today.getDay() || timeTaken.getMonth() != today.getMonth() || timeTaken.getYear() != today.getYear()) {
                drinkRecords.remove(i);
            }
        }
//        drinkRecords.removeIf(d ->
//                d.getTimeTaken().before(today.getTime()) || d.getTimeTaken().after(tomorrow.getTime())
//        );

        double currentSumAmmount = 0;
        for(DrinkRecord record: drinkRecords) {
            currentSumAmmount += record.getAmount();
        }

        WaterIntake waterIntakeCalculator = new WaterIntake();
        User currentUser = userRepository.getUser(2);
        double waterIntakeAmount = waterIntakeCalculator.calculateWaterIntake(
                currentUser.getWeight(),
                today.getYear() - currentUser.getDateOfBirth().getYear(), 30);
        return currentSumAmmount / waterIntakeAmount;
    }
}
