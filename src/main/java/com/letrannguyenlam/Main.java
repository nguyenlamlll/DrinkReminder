package com.letrannguyenlam;

import com.letrannguyenlam.repositories.DrinkRecordRepository;
import com.letrannguyenlam.repositories.JDBCDriver;
import com.letrannguyenlam.repositories.UserRepository;
import com.letrannguyenlam.repositories.models.DrinkRecord;
import com.letrannguyenlam.repositories.models.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;

public class Main extends Application {

    public static Boolean isSplashLoaded = false;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("statistics.fxml"));

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
    }
}

