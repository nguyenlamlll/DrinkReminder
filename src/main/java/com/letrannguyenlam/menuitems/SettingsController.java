package com.letrannguyenlam.menuitems;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import com.letrannguyenlam.Main;
import com.letrannguyenlam.logic.UserLogic;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.awt.geom.Arc2D;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {
    @FXML
    private JFXTextField weightTextField;

    @FXML
    private JFXTextField heightTextField;

    @FXML
    private Text personalMetricsSaveStatus;

    private UserLogic userLogic;
    public SettingsController() {
        userLogic = new UserLogic();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getPersonalMetrics();
        bindValidators();
    }

    private void bindValidators() {
        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage("You must input this field");
        NumberValidator numberValidator = new NumberValidator();
        numberValidator.setMessage("Input must be an integer number");

        weightTextField.getValidators().addAll(validator, numberValidator);

        weightTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue) {
                    weightTextField.validate();
                }
            }
        });
    }

    private void getPersonalMetrics() {
        var currentUser = userLogic.getUser(Main.currentSignedInUser);
        weightTextField.setText(Double.toString(currentUser.getWeight()));
        heightTextField.setText(Double.toString(currentUser.getHeight()));
    }

    public void onSavePersonalMetricsButtonClick(MouseEvent mouseEvent) {
        userLogic.updateUser(
                Main.currentSignedInUser,
                Double.parseDouble(weightTextField.getText()),
                Double.parseDouble(heightTextField.getText()));
        personalMetricsSaveStatus.setText("Success!");
    }
}
