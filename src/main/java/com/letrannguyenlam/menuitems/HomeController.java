package com.letrannguyenlam.menuitems;

import com.letrannguyenlam.Main;
import com.letrannguyenlam.SidePanelController;
import com.letrannguyenlam.WaterIntake;
import com.letrannguyenlam.services.TrayService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class HomeController implements Initializable {

    private WaterIntake waterIntakeCalculator = new WaterIntake();
    private double waterIntakeAmount;

    @FXML
    private ProgressBar waterBar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO: Placing this here create a notification each time Home page is loaded.
        timerNotification();
    }

    public void button250Clicked(MouseEvent mouseEvent) {
        waterIntakeAmount = waterIntakeCalculator.calculateWaterIntake(70, 22, 30);

        double newProgress = waterBar.getProgress() + (0.250/waterIntakeAmount);
        waterBar.setProgress(newProgress);
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
