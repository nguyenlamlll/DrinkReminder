package com.letrannguyenlam;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

public class Main extends Application {

    public static Boolean isSplashLoaded = false;

    public Boolean firstLoaded = true;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("main.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

        eventBinder(primaryStage);
    }

    private void eventBinder(Stage primaryStage) {
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.setImplicitExit(false);
                event.consume();
                primaryStage.hide();
            }
        });
        minimizeToSystemTray(primaryStage);

        if (firstLoaded) {
            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(primaryStage);
            VBox dialogVbox = new VBox(20);
            dialogVbox.getChildren().add(new Text("This is a Dialog"));
            Scene dialogScene = new Scene(dialogVbox, 300, 200);
            dialog.setScene(dialogScene);
            dialog.show();
            firstLoaded = false;
        }
    }

    private void minimizeToSystemTray(Stage primaryStage) {
        if (SystemTray.isSupported()) {
            // get the SystemTray instance
            SystemTray tray = SystemTray.getSystemTray();
            // load an image
            URL resource = getClass().getResource("images/trayicon.png");
            Image image = Toolkit.getDefaultToolkit().getImage(resource);
            // create a action listener to listen for default action executed on the tray icon
            ActionListener listener = e -> {
                Platform.runLater(() -> primaryStage.show());
            };
            MenuItem exitItem = new MenuItem("Exit");

            // create a popup menu
            PopupMenu popup = new PopupMenu();
            // create menu item for the default action
            MenuItem defaultItem = new MenuItem("ReOpen");
            defaultItem.addActionListener(listener);
            popup.add(defaultItem);
            popup.add(exitItem);

            final TrayIcon trayIcon = new TrayIcon(image, "Tray Demo", popup);

            trayIcon.addActionListener(listener);
//            trayIcon.addMouseListener(mouseListener);
            exitItem.addActionListener(e -> {
                tray.remove(trayIcon);
                System.exit(0);
            });

            try {
                tray.add(trayIcon);
            } catch (AWTException e) {
                System.err.println(e);
            }
        } else {

        }
    }
}

