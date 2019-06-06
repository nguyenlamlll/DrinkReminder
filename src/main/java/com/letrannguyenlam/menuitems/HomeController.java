package com.letrannguyenlam.menuitems;

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

public class HomeController implements Initializable {

    private WaterIntake waterIntakeCalculator = new WaterIntake();
    private double waterIntakeAmount;

    @FXML
    private ProgressBar waterBar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void button250Clicked(MouseEvent mouseEvent) {
        waterIntakeAmount = waterIntakeCalculator.calculateWaterIntake(70, 22, 30);

        double newProgress = waterBar.getProgress() + (0.250/waterIntakeAmount);
        waterBar.setProgress(newProgress);
    }

    public void createNotification(MouseEvent mouseEvent) {
        TrayService trayService = new TrayService("Drink Reminder", "Hi there! It's time for water, don't you think?", TrayIcon.MessageType.NONE);
        try {
            trayService.displayTray();
        }
        catch (AWTException ex) {
            // TODO: Handle the exception
        }
    }
}
