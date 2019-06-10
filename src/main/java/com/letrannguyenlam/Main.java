package com.letrannguyenlam;

import com.letrannguyenlam.repositories.DrinkRecordRepository;
import com.letrannguyenlam.repositories.JDBCDriver;
import com.letrannguyenlam.repositories.UserRepository;
import com.letrannguyenlam.repositories.models.DrinkRecord;
import com.letrannguyenlam.repositories.models.User;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Date;
import java.net.URL;

public class Main extends Application {

    public static Boolean isSplashLoaded = false;

    // TODO: Implement login and delete this hard-coded number
    public static int currentSignedInUser = 1;
    public static Boolean isLoggedIn = false;

    public static User currentUser;

    private double xOffset = 0, yOffset = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        openLoginForm(primaryStage);
        if (!isLoggedIn) {
            return;
        }

        UserRepository userRepository = new UserRepository();
        currentUser = userRepository.getUser(currentSignedInUser);
        if (currentUser.getWeight() == 0 || currentUser.getHeight() == 0) {
            openMeasureForm(primaryStage);
        }

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("main.fxml"));

//            JDBCDriver db = new JDBCDriver();
//            db.checkConnections();

//            DrinkRecordRepository drinkRecordRepository = new DrinkRecordRepository();
//            drinkRecordRepository.createDrinkRecord(new DrinkRecord(1, 0.1, new Date(2019-1900, 01, 01)));

//            UserRepository userRepository = new UserRepository();
//            userRepository.createUser(new User("lalala", "bababa", "Lala", new Date(2000-1900, 01, 01), 50.5));
//            User user2 = new User("DADADA", "bababa", "Dada", new Date(1999-1900, 01, 01), 41);
//            userRepository.createUser(user2);
//
//            user2.setName("New Name");
//            userRepository.updateUser(user2);

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

    private void openMeasureForm(Stage primaryStage) {
        Stage dialog = new Stage();
        Parent popupTheme = null;
        try {
            popupTheme = FXMLLoader.load(getClass().getResource("measurementform.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (popupTheme != null) {
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initStyle(StageStyle.UNDECORATED);

            popupTheme.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    xOffset = event.getSceneX();
                    yOffset = event.getSceneY();
                }
            });

            popupTheme.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    dialog.setX(event.getScreenX() - xOffset);
                    dialog.setY(event.getScreenY() - yOffset);
                }
            });

            dialog.setScene(new Scene(popupTheme));
            dialog.initOwner(primaryStage);
            dialog.showAndWait();
        }
    }

    private void openLoginForm(Stage primaryStage) {
        Stage dialog = new Stage();
        Parent popupTheme = null;
        try {
            popupTheme = FXMLLoader.load(getClass().getResource("login.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (popupTheme != null) {
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initStyle(StageStyle.DECORATED);

            popupTheme.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    xOffset = event.getSceneX();
                    yOffset = event.getSceneY();
                }
            });

            popupTheme.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    dialog.setX(event.getScreenX() - xOffset);
                    dialog.setY(event.getScreenY() - yOffset);
                }
            });

            // Didn't log in
            dialog.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    isLoggedIn = false;
                }
            });

            dialog.setScene(new Scene(popupTheme));
            dialog.initOwner(primaryStage);
            dialog.showAndWait();
        }
    }
}

