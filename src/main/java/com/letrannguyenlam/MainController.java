package com.letrannguyenlam;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.letrannguyenlam.services.TrayService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable{
    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private VBox drawerVbox;

    @FXML
    private ProgressBar waterBar;

    private WaterIntake waterIntakeCalculator = new WaterIntake();
    private double waterIntakeAmount;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        if (!Launcher.isSplashLoaded) {
//            loadSplashScreen();
//        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("sidepanel.fxml"));
            VBox box = loader.load();
            SidePanelController controller = loader.getController();

            drawer.setSidePane(box);
            bindEventToBox(box);
        } catch (IOException ex) {
            //Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
        transition.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();

            if (drawer.isOpened()) {
                drawerVbox.setPrefWidth(80);
                drawer.close();
            } else {
                drawerVbox.setPrefWidth(200);
                drawer.open();
            }
        });
    }

    private void bindEventToBox(VBox box){
        for (Node node : box.getChildren()) {
            node.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                switch (node.getId()){
                    case "home_btn" :
                    {
                        TrayService trayService = new TrayService("Test", "Clicked in home button", TrayIcon.MessageType.WARNING);
                        try {
                            trayService.displayTray();
                        }
                        catch (AWTException ex) {
                            // TODO: Handle the exception
                        }
                    }
                }
            });
        }
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

//    private void loadSplashScreen() {
//        try {
//            Launcher.isSplashLoaded = true;
//
//            StackPane pane = FXMLLoader.load(getClass().getResource(("/genuinecoder/splash/splash.fxml")));
//            root.getChildren().setAll(pane);
//
//            FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), pane);
//            fadeIn.setFromValue(0);
//            fadeIn.setToValue(1);
//            fadeIn.setCycleCount(1);
//
//            FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), pane);
//            fadeOut.setFromValue(1);
//            fadeOut.setToValue(0);
//            fadeOut.setCycleCount(1);
//
//            fadeIn.play();
//
//            fadeIn.setOnFinished((e) -> {
//                fadeOut.play();
//            });
//
//            fadeOut.setOnFinished((e) -> {
//                try {
//                    AnchorPane parentContent = FXMLLoader.load(getClass().getResource(("/genuinecoder/main/main.fxml")));
//                    root.getChildren().setAll(parentContent);
//                } catch (IOException ex) {
//                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            });
//
//        } catch (IOException ex) {
//            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

}
