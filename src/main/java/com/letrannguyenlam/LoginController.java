package com.letrannguyenlam;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.letrannguyenlam.Main;
import com.letrannguyenlam.logic.UserLogic;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private AnchorPane loginPane;

    @FXML
    private JFXButton loginButton;

    @FXML
    private  JFXButton registerButton;

    @FXML
    private JFXTextField usernameTextField;

    @FXML
    private JFXPasswordField passwordTextField;

    @FXML
    private Text statusText;

    private UserLogic userLogic;
    public LoginController() {
        userLogic = new UserLogic();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void registerButtonClicked(MouseEvent mouseEvent) {
        openRegistrationForm((Stage)loginButton.getScene().getWindow());
    }
    private double xOffset = 0, yOffset = 0;
    private void openRegistrationForm(Stage primaryStage) {
//        try {
//            AnchorPane form = FXMLLoader.load(getClass().getResource("registration.fxml"));
//            loginPane.getChildren().setAll(form);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        Stage dialog = new Stage();
        Parent popupTheme = null;
        try {
            popupTheme = FXMLLoader.load(getClass().getResource("registration.fxml"));
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

    public void loginButtonClick(MouseEvent mouseEvent) {
        var username = usernameTextField.getText();
        var password = passwordTextField.getText();
        if (userLogic.verifyUser(username, password)) {
            Main.currentSignedInUser = userLogic.getUser(username).getId();
            Main.isLoggedIn = true;
            Stage popup = (Stage) loginButton.getScene().getWindow();
            popup.close();

        } else {
            Main.isLoggedIn = false;
            statusText.setText("Username or password is wrong.");
        }

    }
}
