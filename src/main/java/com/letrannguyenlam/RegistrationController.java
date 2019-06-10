package com.letrannguyenlam;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.letrannguyenlam.logic.UserLogic;
import com.letrannguyenlam.repositories.models.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class RegistrationController implements Initializable {
    @FXML
    private JFXButton cancelButton;

    @FXML
    private JFXTextField usernameTextField;

    @FXML
    private JFXPasswordField passwordTextField;

    @FXML
    private JFXTextField nameTextField;

    @FXML
    private DatePicker birthdayPicker;

    @FXML
    private Text statusText;

    private UserLogic userLogic;
    public RegistrationController() {
        userLogic = new UserLogic();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void cancelButtonClick(MouseEvent mouseEvent) {
        Stage popup = (Stage) cancelButton.getScene().getWindow();
        popup.close();
    }

    public void registerButtonClick(MouseEvent mouseEvent) {
        User user = new User(
                usernameTextField.getText(),
                passwordTextField.getText(),
                nameTextField.getText(),
                java.sql.Date.valueOf(birthdayPicker.getValue()),
                0, 0, 0);
        userLogic.createUser(user);

        statusText.setText("New user is created successfully!");
        usernameTextField.setText("");
        passwordTextField.setText("");
        nameTextField.setText("");
        birthdayPicker.setValue(null);
    }
}
