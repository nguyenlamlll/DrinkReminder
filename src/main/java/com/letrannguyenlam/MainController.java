package com.letrannguyenlam;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.letrannguyenlam.services.TrayIconDemo;
import com.letrannguyenlam.services.TrayService;
import javafx.animation.FadeTransition;
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
import javafx.util.Duration;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (!Main.isSplashLoaded) {
//            loadSplashScreen();
        }
        loadHome();
        concac();

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

    private void loadHome() {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("menuitems/home.fxml"));
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
                        loadHome();
                        break;
                    case "statistic_btn":
                        break;
                    case "setting_btn":
                        break;
                }
            });
        }
    }

//    public void button250Clicked(MouseEvent mouseEvent) {
//        waterIntakeAmount = waterIntakeCalculator.calculateWaterIntake(70, 22, 30);
//
//        double newProgress = waterBar.getProgress() + (0.250/waterIntakeAmount);
//        waterBar.setProgress(newProgress);
//    }

//    public void createNotification(MouseEvent mouseEvent) {
//        TrayService trayService = new TrayService("Drink Reminder", "Hi there! It's time for water, don't you think?", TrayIcon.MessageType.NONE);
//        try {
//            trayService.displayTray();
//        }
//        catch (AWTException ex) {
//            // TODO: Handle the exception
//        }
//    }

    private void loadSplashScreen() {
        try {
            Main.isSplashLoaded = true;

            StackPane pane = FXMLLoader.load(getClass().getResource(("splash.fxml")));
            root.getChildren().setAll(pane);

            FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), pane);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);

            FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), pane);
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

    private void concac() {
        if (SystemTray.isSupported()) {
            // get the SystemTray instance
            SystemTray tray = SystemTray.getSystemTray();
            // load an image
            Image image = Toolkit.getDefaultToolkit().getImage("trayicon.png");
            // create a action listener to listen for default action executed on the tray icon
            ActionListener listener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            };
            MenuItem exitItem = new MenuItem("Exit");

            // create a popup menu
            PopupMenu popup = new PopupMenu();
            // create menu item for the default action
            MenuItem defaultItem = new MenuItem("Pop up for Drink Reminder");
            defaultItem.addActionListener(listener);
            popup.add(defaultItem);
            popup.add(exitItem);
            /// ... add other items
            // construct a TrayIcon
            final TrayIcon trayIcon = new TrayIcon(image, "Tray Demo", popup);
            // set the TrayIcon properties
            trayIcon.addActionListener(listener);
            exitItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    tray.remove(trayIcon);
                    System.exit(0);
                }
            });

            try {
                tray.add(trayIcon);
            } catch (AWTException e) {
                System.err.println(e);
            }
            // ...
        } else {

        }
// ...
// some time later
// the application state has changed - update the image
//        if (trayIcon != null) {
//            trayIcon.setImage(updatedImage);
//        }
    }

    protected static Image createImage(String path, String description) {
        URL imageURL = TrayIconDemo.class.getResource(path);

        if (imageURL == null) {
            System.err.println("Resource not found: " + path);
            return null;
        } else {
            return (new ImageIcon(imageURL, description)).getImage();
        }
    }

}
