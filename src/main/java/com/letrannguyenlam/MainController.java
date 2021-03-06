package com.letrannguyenlam;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.letrannguyenlam.services.TrayService;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainController implements Initializable {
    @FXML
    private AnchorPane root;

    @FXML
    private AnchorPane content_pan;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private VBox drawerVbox;

    @FXML
    private VBox iconOnlyVBox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (!Main.isSplashLoaded) {
            loadSplashScreen();
        }
        loadHome();
        createRandomDrinkReminder();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("sidepanel.fxml"));
            VBox box = loader.load();
            SidePanelController controller = loader.getController();

            drawer.setSidePane(box);
            bindEventToBox(box);
            bindEventToBox(iconOnlyVBox);
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
                iconOnlyVBox.setVisible(true);
            } else {
                drawerVbox.setPrefWidth(200);
                drawer.open();
                iconOnlyVBox.setVisible(false);
            }
        });
    }

    private void loadHome() {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("menuitems/home.fxml"));
            content_pan.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadStatistics() {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("menuitems/statistics.fxml"));
            content_pan.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadSettings() {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("menuitems/settings.fxml"));
            content_pan.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void bindEventToBox(VBox box) {
        for (Node node : box.getChildren()) {
            node.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                switch (node.getId()) {
                    case "home_btn":
                    case "homeIconButton":
                        loadHome();
                        break;
                    case "statistics_btn":
                    case "statisticsIconButton":
                        loadStatistics();
                        break;
                    case "setting_btn":
                    case "settingsIconButton":
                        loadSettings();
                        break;
                    case "exit_btn":
                    case "exitIconButton":
                        SystemTray tray = SystemTray.getSystemTray();
                        for (TrayIcon icon : tray.getTrayIcons()) {
                            tray.remove(icon);
                        }
                        System.exit(0);
                        break;
                    case "minimize_btn":
                    case "minimizeIconButton":
                        Main.minimizeWindow();
                        break;
                }
            });
        }
    }

    private void loadSplashScreen() {
        try {
            Main.isSplashLoaded = true;

            StackPane pane = FXMLLoader.load(getClass().getResource(("splash.fxml")));
            root.getChildren().setAll(pane);

            FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), pane);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);

            FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), pane);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setCycleCount(1);

            fadeIn.play();

            fadeIn.setOnFinished((e) -> {
                fadeOut.play();
            });

            fadeOut.setOnFinished((e) -> {
                try {
                    AnchorPane parentContent = FXMLLoader.load(getClass().getResource(("main.fxml")));
                    root.getChildren().setAll(parentContent);
                } catch (IOException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private long myLong = randomLong(30000L, 600000L);

    public void createRandomDrinkReminder() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                TrayService trayService = new TrayService(
                        "Drink Reminder",
                        "Hi there! It's time for water, don't you think?",
                        TrayIcon.MessageType.NONE);
                try {
                    trayService.displayTray();
                } catch (AWTException ex) {
                    // TODO: Handle the exception
                    ex.printStackTrace();
                }
                myLong = randomLong(30000L, 600000L);
            }
        };
        Timer timer = new Timer("Timer");
        timer.schedule(timerTask, myLong, myLong);
    }

    private long randomLong(long leftLimit, long rightLimit) {
        long generatedLong = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
        return generatedLong;
    }
}
