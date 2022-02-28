package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

public class NewEmployeeController {

    @FXML
    private Label employeeID;

    @FXML
    private Button cancelBtn;

    @FXML
    private Label name;

    @FXML
    private TextField newEmployeeEmail;

    @FXML
    private TextField newEmployeeFName;

    @FXML
    private TextField newEmployeeHireDate;

    @FXML
    private TextField newEmployeeJID;

    @FXML
    private TextField newEmployeeLName;

    @FXML
    private TextField newEmployeeMID;

    @FXML
    private TextField newEmployeePNumber;

    @FXML
    private TextField newEmployeeSalary;

    @FXML
    private TextField newEmployeeDepID;

    @FXML
    void AddEmployeeToSystem(ActionEvent event) {
        EmployeeDBC dbc = new EmployeeDBC();
        Connection conn = dbc.getConnection();

//
//
//
        try {
            String insertQuery = "insert into employees (first_name, last_name, email," +
                    "phone_number, hire_date, job_id, salary, manager_id, department_id)" +
                    " values('" + newEmployeeFName.getText() + "', '"
                    + newEmployeeLName.getText() + "', '" + newEmployeeEmail.getText() + "', '" +
                    newEmployeePNumber.getText() + "', '" + Date.valueOf(newEmployeeHireDate.getText())
                    + "', " + Integer.parseInt(newEmployeeJID.getText())+ "," +
                    Double.parseDouble(newEmployeeSalary.getText() )+ "," + Integer.getInteger(newEmployeeMID.getText())
                    + "," + Integer.getInteger(newEmployeeDepID.getText()) + ")";
            Statement statement = conn.createStatement();
            statement.executeUpdate(insertQuery);

           } catch (Exception ex){
            ex.printStackTrace();
            ex.getCause();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Wrong input!");
            alert.setHeaderText("Wrong input type in text fields!");
            alert.setContentText("One or more inputs have wrong type! Try again!");

            alert.showAndWait();
        }
        String idQuery = "Select * from employees where first_name ='" + newEmployeeFName.getText() + "' and last_name='" + newEmployeeLName.getText() + "';";
        try {
            Statement statement = conn.createStatement();
            ResultSet queryResult = statement.executeQuery(idQuery);
            while (queryResult.next()){
            employeeID.setText(String.valueOf(queryResult.getInt("employee_id")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            ex.getCause();

        }
    }

    @FXML
    void CancelOperation(ActionEvent event) {

            Stage stage = (Stage) employeeID.getScene().getWindow();
            stage.close();

    }

}
