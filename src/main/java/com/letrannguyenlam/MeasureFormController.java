package com.letrannguyenlam;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import com.letrannguyenlam.repositories.UserRepository;
import com.letrannguyenlam.repositories.models.User;
import com.letrannguyenlam.services.TrayService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

import javax.sound.sampled.FloatControl;
import java.awt.*;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class MeasureFormController implements Initializable {

    public static final double KGTOPOUND = 2.20462262;
    public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##");
    private UserRepository userRepository = new UserRepository();

    @FXML
    private Slider height_slider;

    @FXML
    private JFXTextField weight_txt;

    @FXML
    private Label pound_lbl;

    @FXML
    private JFXButton submit_btn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bindEvent();
    }

    private void bindEvent() {
        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage("You must input this field");
        NumberValidator numberValidator = new NumberValidator();
        numberValidator.setMessage("Input must be an integer number");

        weight_txt.getValidators().addAll(validator, numberValidator);

        weight_txt.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue) {
                    weight_txt.validate();
                }
            }
        });

        weight_txt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                double value = 1997;
                try
                {
                   value = Double.parseDouble(newValue);
                }catch (NumberFormatException e) {

                }

                if (value != 1997) {
                    pound_lbl.setText(String.valueOf(DECIMAL_FORMAT.format(value*KGTOPOUND)));
                }

                if (newValue == null || newValue.isEmpty()) {
                    pound_lbl.setText("");
                }
            }
        });

        submit_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (weight_txt.validate()) {
                    TrayService trayService = new TrayService("Update Value", "height: "+DECIMAL_FORMAT.format(height_slider.getValue()) +"\nweight: "+weight_txt.getText(), TrayIcon.MessageType.NONE);
                    try {
                        trayService.displayTray();
                    }
                    catch (AWTException ex) {
                        // TODO: Handle the exception
                    }
                    User updatedUser = Main.currentUser;
                    updatedUser.setHeight(height_slider.getValue());
                    updatedUser.setWeight(Double.parseDouble(weight_txt.getText()));
                    userRepository.updateUser(updatedUser);
                    Stage popup = (Stage) submit_btn.getScene().getWindow();
                    popup.close();

                }
            }
        });
    }
}
