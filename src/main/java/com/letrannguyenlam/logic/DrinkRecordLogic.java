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
        java.util.Date today = new java.util.Date(System.currentTimeMillis());
        today.setHours(0);
        today.setMinutes(0);
        today.setSeconds(0);


        double currentSumAmmount = this.getWaterAmountDrankToday(userId);


        WaterIntake waterIntakeCalculator = new WaterIntake();
        User currentUser = userRepository.getUser(userId);
        double waterIntakeAmount = waterIntakeCalculator.calculateWaterIntake(
                currentUser.getWeight(),
                today.getYear() - currentUser.getDateOfBirth().getYear(), 30);
        return currentSumAmmount / waterIntakeAmount;
    }

    public double getAmountLeftOfToday(int userId) {
        double waterIntakeAmount = this.getWaterIntakeAmount(userId);
        double waterAmountDrank = this.getWaterAmountDrankToday(userId);
        double amountLeft = waterIntakeAmount - waterAmountDrank;
        return amountLeft;
    }

    public double getWaterIntakeAmount(int userId) {
        java.util.Date today = new java.util.Date(System.currentTimeMillis());
        today.setHours(0);
        today.setMinutes(0);
        today.setSeconds(0);

        WaterIntake waterIntakeCalculator = new WaterIntake();
        User currentUser = userRepository.getUser(userId);
        double waterIntakeAmount = waterIntakeCalculator.calculateWaterIntake(
                currentUser.getWeight(),
                today.getYear() - currentUser.getDateOfBirth().getYear(), 30);
        return waterIntakeAmount;
    }

    public double getWaterAmountDrankToday(int userId) {
        LinkedList<DrinkRecord> drinkRecords = drinkRecordRepository.getDrinkRecords(userId);

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

        double currentSumAmmount = 0;
        for(DrinkRecord record: drinkRecords) {
            currentSumAmmount += record.getAmount();
        }
        return currentSumAmmount;
    }
}
