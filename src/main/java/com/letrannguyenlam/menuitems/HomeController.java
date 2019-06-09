package com.letrannguyenlam.menuitems;

import com.letrannguyenlam.Main;
import com.letrannguyenlam.SidePanelController;
import com.letrannguyenlam.WaterIntake;
import com.letrannguyenlam.logic.DrinkRecordLogic;
import com.letrannguyenlam.repositories.models.DrinkRecord;
import com.letrannguyenlam.services.TrayService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

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


    private DrinkRecordLogic drinkRecordLogic;
    public HomeController() {
        drinkRecordLogic = new DrinkRecordLogic();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO: Placing this here create a notification each time Home page is loaded.
        timerNotification();

        double currentProgress = drinkRecordLogic.getCurrentProgress(2);
        waterBar.setProgress(currentProgress);
    }

    public void drinkButtonClicked(MouseEvent mouseEvent) {
        Button button = (Button)mouseEvent.getSource();
        double currentProgress = 0;
        switch (button.getId()){
            case "button50":
                drinkRecordLogic.createDrinkRecord(new DrinkRecord(2, 0.05));
                currentProgress = drinkRecordLogic.getCurrentProgress(2);
                waterBar.setProgress(currentProgress);
                break;
            case "button100":
                drinkRecordLogic.createDrinkRecord(new DrinkRecord(2, 0.1));
                currentProgress = drinkRecordLogic.getCurrentProgress(2);
                waterBar.setProgress(currentProgress);
                break;
            case "button250":
                drinkRecordLogic.createDrinkRecord(new DrinkRecord(2, 0.25));
                currentProgress = drinkRecordLogic.getCurrentProgress(2);
                waterBar.setProgress(currentProgress);
                break;
            case "button350":
                drinkRecordLogic.createDrinkRecord(new DrinkRecord(2, 0.35));
                currentProgress = drinkRecordLogic.getCurrentProgress(2);
                waterBar.setProgress(currentProgress);
                break;
        }
    }

    public void createNotification() {
        TrayService trayService = new TrayService("Drink Reminder", "Hi there! It's time for water, don't you think?", TrayIcon.MessageType.NONE);
        try {
            trayService.displayTray();
        }
        catch (AWTException ex) {
            // TODO: Handle the exception
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
}
