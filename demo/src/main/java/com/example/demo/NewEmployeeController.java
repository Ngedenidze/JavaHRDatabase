package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * controller class for new employee tab
 */
public class NewEmployeeController implements Initializable {
    ObservableList<String> officeChoseChooseObsList= FXCollections.observableArrayList();
    ObservableList<Integer> officeJobIdObsList= FXCollections.observableArrayList();
    ObservableList<Integer> ManagerIdObsList= FXCollections.observableArrayList();
    @FXML
    private ChoiceBox<String> jobIDChoBox;

    @FXML
    private ChoiceBox<Integer> managerIDChoBox;

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
    void AddEmployeeToSystem(ActionEvent event) throws SQLException {
       EmployeeDBC.getConnection();




       int departmentId = 1;
       String managerQuery = "Select department_id from employees where employee_id=" + managerIDChoBox.getValue();
       ResultSet rs = EmployeeDBC.getResultSet(managerQuery);
       while (rs.next()){
           departmentId=  rs.getInt(1);
       }

//
//
//
        try {
            String insertQuery = "insert into employees (first_name, last_name, email," +
                    "phone_number, hire_date, job_title, salary, manager_id, department_id,job_id)" +
                    " values('" + newEmployeeFName.getText() + "', '"
                    + newEmployeeLName.getText() + "', '" + newEmployeeEmail.getText() + "', '" +
                    newEmployeePNumber.getText() + "', current_date()"
                    + ", '" + jobIDChoBox.getValue() + "'," +
                    Double.parseDouble(newEmployeeSalary.getText() )+ "," + managerIDChoBox.getValue()
                    + "," + departmentId + ", " + officeJobIdObsList.get(officeChoseChooseObsList.indexOf(jobIDChoBox.getValue()))+")";

            EmployeeDBC.updateSelect(insertQuery);
            Alerts.throwInfoAlert("Employee added!", "","Employee Added to the system.");
            Stage stage = (Stage) newEmployeeFName.getScene().getWindow();
            stage.close();
            App.setRoot("hello-view");
           } catch (Exception ex){
            ex.printStackTrace();
            ex.getCause();
            Alerts.errorInfoAlert("Wrong input!","Wrong input type in text fields!","One or more inputs have wrong type! Try again!");
        }

    }

    @FXML
    void CancelOperation(ActionEvent event) {
        Stage stage = (Stage) newEmployeeFName.getScene().getWindow();
            stage.close();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        String jobQuery= """
                select
                distinct job_title, job_id
                from jobs""";
        ResultSet result;
        try {
            result = EmployeeDBC.getResultSet(jobQuery);
            while (result.next()){
                 officeJobIdObsList.add(result.getInt(2));
                officeChoseChooseObsList.add(result.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        jobIDChoBox.setItems(officeChoseChooseObsList);


        String managerQuery= """
                select
                distinct manager_id
                from employees""";

        try {
            result = EmployeeDBC.getResultSet(managerQuery);
            while (result.next()){
                ManagerIdObsList.add(result.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // ManagerIdObsList.remove(0);
        managerIDChoBox.setItems(ManagerIdObsList);


    }
}
