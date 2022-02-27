package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

public class NewEmployeeController {

    @FXML
    private Label employeeID;

    private int changingID;

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
        changingID = Integer.parseInt(employeeID.getText());
        EmployeeDBC dbc = new EmployeeDBC();
        Connection conn = dbc.getConnection();
        String insertQuery = "insert into employees (employee_id, first_name, last_name, email," +
                "phone_number, hire_date, job_id, salary, manager_id, department_id)" +
                " values(" + changingID + ", '" + newEmployeeFName.getText() + "', '"
                + newEmployeeLName.getText() + "', '" + newEmployeeEmail.getText() + "', '" +
                newEmployeePNumber.getText() + "', '" + Date.valueOf(newEmployeeHireDate.getText())
                + "', " + Integer.parseInt(newEmployeeJID.getText())+ "," +
                Double.parseDouble(newEmployeeSalary.getText() )+ "," + Integer.getInteger(newEmployeeMID.getText()) + "," + Integer.getInteger(newEmployeeDepID.getText()) + ")";
//
//
//
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate(insertQuery);
            changingID++;
           } catch (Exception ex){
            ex.printStackTrace();
            ex.getCause();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Wrong input!");
            alert.setHeaderText("Wrong input type in text fields!");
            alert.setContentText("One or more inputs have wrong type! Try again!");

            alert.showAndWait();
        }
        employeeID.setText(String.valueOf(changingID));
    }

    @FXML
    void CancelOperation(ActionEvent event) {

    }

}
