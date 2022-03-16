package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PasswordForgotController implements Initializable {
    @FXML
    private Button confirmButton;

    @FXML
    private TextField emailTextBox;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        confirmButton.setOnAction(event -> {
            try {
                App.setRoot("login-page");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
