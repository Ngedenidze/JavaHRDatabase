package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class LoginPageController implements Initializable {
    public String salt = "241";

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Button signInButton;

    @FXML
    private TextField usernameTextField;

    @FXML
    private Label invalidLoginLabel;


    @FXML
    void forgotPassword() {
        try {
            App.setRoot("forgot-password-page");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//TODO FIX IMAGES
    public void loginInitialize() {
        Connection connectDBS = LoginDBC.getConnection();

        try {
            String verifyLoginQuery = "select count(1) from users where username='"
                    + usernameTextField.getText()+"' and password = SHA2(CONCAT('"+
                    passwordTextField.getText() +"','241'),256)";

            ResultSet resultSet = LoginDBC.getResultSet(verifyLoginQuery);

            while (resultSet.next()){
                if (resultSet.getInt(1) == 1){

                    App.setRoot("hello-view");

                }
            }

        //

        }   catch (Exception e){
            invalidLoginLabel.setVisible(true);
            e.printStackTrace();
            e.getCause();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void signIn() {
        if (usernameTextField.getText() == null || usernameTextField.getText().trim().isEmpty()
                || passwordTextField.getText() == null || passwordTextField.getText().trim().isEmpty())
            invalidLoginLabel.setVisible(true);
        else {
            loginInitialize();
        }
    }
}
