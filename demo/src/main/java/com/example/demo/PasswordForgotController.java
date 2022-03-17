package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PasswordForgotController implements Initializable {
    @FXML
    private Button confirmButton;

    @FXML
    private Label securityQuestionLabel;

    String securityQeustion = "What is the meaning of Molere?";
    @FXML
    private TextField textBox;
    String securityQuestionAnswer = "Grinding";



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        securityQuestionLabel.setText(securityQeustion);
        confirmButton.setOnAction(event -> {
            try {
                if (securityQuestionAnswer.equals(textBox.getText())) {
                    Alerts.throwInfoAlert("Password", "Password for admin2:","admin");
                    App.setRoot("login-page");
                } else {
                    Alerts.errorInfoAlert("Wrong answer", "Your answer is wrong", "Try again!");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
