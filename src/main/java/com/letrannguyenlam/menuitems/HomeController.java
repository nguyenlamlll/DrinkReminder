package com.letrannguyenlam.menuitems;

import com.letrannguyenlam.Main;
import com.letrannguyenlam.WaterIntake;
import com.letrannguyenlam.logic.DrinkRecordLogic;
import com.letrannguyenlam.logic.UserLogic;
import com.letrannguyenlam.repositories.models.DrinkRecord;
import com.letrannguyenlam.services.TrayService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;

import java.awt.TrayIcon;
import java.awt.AWTException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class HomeController implements Initializable {

    private WaterIntake waterIntakeCalculator = new WaterIntake();
    private double waterIntakeAmount;

    @FXML
    private ProgressBar waterBar;

    @FXML
    private Label homeLabel_01;
    @FXML
    private Label homeLabel_02;


    private DrinkRecordLogic drinkRecordLogic;
    private UserLogic userLogic;
    public HomeController() {
        drinkRecordLogic = new DrinkRecordLogic();
        userLogic = new UserLogic();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        double currentProgress = drinkRecordLogic.getCurrentProgress(Main.currentSignedInUser);
        waterBar.setProgress(currentProgress);

        this.refreshHomeLabel();
    }

    public void drinkButtonClicked(MouseEvent mouseEvent) {
        Button button = (Button)mouseEvent.getSource();
        double currentProgress = 0;
        switch (button.getId()){
            case "button50":
                drinkRecordLogic.createDrinkRecord(new DrinkRecord(Main.currentSignedInUser, 0.05));
                currentProgress = drinkRecordLogic.getCurrentProgress(Main.currentSignedInUser);
                waterBar.setProgress(currentProgress);
                break;
            case "button100":
                drinkRecordLogic.createDrinkRecord(new DrinkRecord(Main.currentSignedInUser, 0.1));
                currentProgress = drinkRecordLogic.getCurrentProgress(Main.currentSignedInUser);
                waterBar.setProgress(currentProgress);
                break;
            case "button250":
                drinkRecordLogic.createDrinkRecord(new DrinkRecord(Main.currentSignedInUser, 0.25));
                currentProgress = drinkRecordLogic.getCurrentProgress(Main.currentSignedInUser);
                waterBar.setProgress(currentProgress);
                break;
            case "button350":
                drinkRecordLogic.createDrinkRecord(new DrinkRecord(Main.currentSignedInUser, 0.35));
                currentProgress = drinkRecordLogic.getCurrentProgress(Main.currentSignedInUser);
                waterBar.setProgress(currentProgress);
                break;
        }

        refreshPage();
    }

    private void refreshPage() {
        this.refreshHomeLabel();
    }

    private void refreshHomeLabel() {
        String name = userLogic.getUser(Main.currentSignedInUser).getName();
        double amountLeftValue = drinkRecordLogic.getAmountLeftOfToday(Main.currentSignedInUser);
        String amountLeft = String.format("%.3f", amountLeftValue);
        if (amountLeftValue > 0.0) {
            homeLabel_01.setText("Hi " + name + ". Let's drink water! You're doing well. ");
            homeLabel_02.setText(amountLeft + " more liters to go.");
        } else {
            homeLabel_01.setText("Congrats! You've met today's requirement. But it's always good to have some more.");
            homeLabel_02.setText("But it's always good to have some more.");
        }

    }

    public void createNotification() {
        TrayService trayService = new TrayService("Drink Reminder", "Hi there! It's time for water, don't you think?", TrayIcon.MessageType.NONE);
        try {
            trayService.displayTray();
        }
        catch (AWTException ex) {
            // TODO: Handle the exception
            ex.printStackTrace();
        }
    }

    public void timerNotification() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                createNotification();
            }
        };

        //how long between the reminder
        long delay = 1800000L;
        Timer timer = new Timer("Timer");
        timer.schedule(timerTask, 0, delay);
    }

    public void remind10MinutesClick(MouseEvent mouseEvent) {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                createNotification();
            }
        };
        long delay = 600000L;
        Timer timer = new Timer("Timer");
        timer.schedule(timerTask, delay);
    }


    public void remind30MinutesClick(MouseEvent mouseEvent) {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                createNotification();
            }
        };
        long delay = 1800000L;
        Timer timer = new Timer("Timer");
        timer.schedule(timerTask, delay);
    }

    public void remind15SecondsClick(MouseEvent mouseEvent) {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                createNotification();
            }
        };
        long delay = 15000L;
        Timer timer = new Timer("Timer");
        timer.schedule(timerTask, delay);
    }
}
