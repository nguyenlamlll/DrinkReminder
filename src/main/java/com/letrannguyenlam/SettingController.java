package com.letrannguyenlam;

import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.letrannguyenlam.services.TrayService;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class SettingController implements Initializable {
    private double xOffset= 0;
    private double yOffset = 0;


    @FXML
    private JFXToggleButton toggleButton;
    @FXML
    JFXComboBox<String> comboBox ;

    private JFXButton exit;

    private ColorChangeCallback callback;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboBox.getItems().add("1 Glasses");
        comboBox.getItems().add("2 Glasses");
        comboBox.getItems().add("3 Glasses");
        comboBox.getItems().add("4 Glasses");
        comboBox.getItems().add("5 Glasses");
        comboBox.getItems().add("6 Glasses");
        comboBox.getItems().add("7 Glasses");
        comboBox.getItems().add("8 Glasses");
    }

    public void setCallback(ColorChangeCallback callback) {
        this.callback = callback;
    }

    public void start( Stage stage ) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("setting.fxml"));
        stage.initStyle(StageStyle.TRANSPARENT);
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset =  event.getSceneX();
                yOffset =  event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getSceneX() - xOffset);
                stage.setY(event.getScreenY()- yOffset);
            }
        });
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }


}
