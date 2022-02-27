package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.w3c.dom.events.MouseEvent;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
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
    void forgotPassword(ActionEvent event) {

    }

    @FXML
    void signIn(MouseEvent event) throws NoSuchAlgorithmException, NoSuchProviderException {
        loginInitialize();

    }


    public void loginInitialize() throws NoSuchAlgorithmException, NoSuchProviderException {


        LoginDBC connectNow = new LoginDBC();
        Connection connectDBS = connectNow.getConnection();
        String verifyLoginQuery = "select count(1) from users where username='"
                + usernameTextField.getText()+"' and password = SHA2(CONCAT('"+
            passwordTextField.getText() +"','241'),256)";
        try {
            Statement statement = connectDBS.createStatement();
            ResultSet resultSet = statement.executeQuery(verifyLoginQuery);

            while (resultSet.next()){
                if (resultSet.getInt(1) == 1){
                    App.setRoot("hello-view");
                }else {
                    invalidLoginLabel.setVisible(true);
                }
            }

        //

        }   catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void signIn(ActionEvent event) throws NoSuchAlgorithmException, NoSuchProviderException {
        if (usernameTextField.getText() == null || usernameTextField.getText().trim().isEmpty()
                || passwordTextField.getText() == null || passwordTextField.getText().trim().isEmpty())
            invalidLoginLabel.setVisible(true);
        else {
            loginInitialize();
        }
    }
}