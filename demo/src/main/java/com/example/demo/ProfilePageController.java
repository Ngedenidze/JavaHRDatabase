package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.skin.TableHeaderRow;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProfilePageController implements Initializable {


    @FXML
    private TextField EmailTF;

    @FXML
    private TextField EmployeeIDTF;

    @FXML
    private TextField EmployeeNameTF;


    @FXML
    private TextField LastNameTF;

    @FXML
    private TextField PhoneNumberTF;

    @FXML
    private Button deleteEmployee;

    @FXML
    private Button editDepID;

    @FXML
    private Button editEmail;


    @FXML
    private Button editEmpLastName;

    @FXML
    private Button editEmpName;

    @FXML
    private Button editJobID;

    @FXML
    private Button editPhoneNumber;

    @FXML
    private Menu homePageBTN;

    @FXML
    private Button uploadPicture;

    @FXML
    private TableView<Department> tvLocations;

    @FXML
    private TableColumn<Department, Integer> locationIDCol;

    @FXML
    private TableColumn<Department, String> postalCodeCol;

    @FXML
    private TableColumn<Department, String> streetAdCol;

    @FXML
    private TableColumn<Department, String> cityCol;

    @FXML
    private TableColumn<Department, String> countryCol;

    @FXML
    private TableColumn<Department, Integer> depIDCol;

    @FXML
    private TableColumn<Department, String> depNameCol;

    @FXML
    private Label labelOne;

    @FXML
    private TableColumn<Job, Integer> jobIDCol;

    @FXML
    private TableColumn<Job, String> jobTitleCol;

    @FXML
    private TableColumn<Job, Double> maxSalaryCol;

    @FXML
    private TableColumn<Job, Double> minSalaryCol;

    @FXML
    private TableView<Job> tvJobs;

    @FXML
    private ChoiceBox<Integer> choiceBoxDepID;

    @FXML
    private ChoiceBox<Integer> choiceBoxJobID;




    ObservableList<Job> jobSearchObservableList = FXCollections.observableArrayList();
    ObservableList<Department> departmentSearchObservableList = FXCollections.observableArrayList();
    ObservableList<Integer> departmentIDObservableList = FXCollections.observableArrayList();
    ObservableList<Integer> jobIDObservableList = FXCollections.observableArrayList();

    @FXML
    void onActionDepartmentID(ActionEvent event) {
        editDepID.setVisible(true);
    }

    public void initialize(URL url, ResourceBundle resource){
        /*
            Update Text fields after choosing the Employee
         */

        Employee selectedEmployee =  AppController.displayInformation();


        EmployeeNameTF.setText(selectedEmployee.getFirst_name());
        LastNameTF.setText(selectedEmployee.getLast_name());
        EmployeeIDTF.setText(selectedEmployee.getEmployee_id().toString());
        PhoneNumberTF.setText(selectedEmployee.getPhone_number());
        EmailTF.setText(selectedEmployee.getEmail());


        choiceBoxDepID.setValue(selectedEmployee.getDepartment_id());
        choiceBoxJobID.setValue(selectedEmployee.getJob_id());

        Connection connectDBS = EmployeeDBC.getConnection();

        try {

            String connectionQueryDepartment = "SELECT department_id,department_name,departments.location_id," +
                    "country_id,city,street_address, postal_code\n" +
                    " from departments INNER JOIN locations on departments.location_id = locations.location_id ORDER BY department_id Asc;";
            String distinctDepartmentID = "Select distinct department_id from departments order by department_id asc";
            //Running this statement
            Statement statement = connectDBS.createStatement();
            ResultSet queryResult = statement.executeQuery(connectionQueryDepartment);

            //While query has next result, populate the Observable list
            while (queryResult.next()) {
                int queryDepId = queryResult.getInt("department_id");
                String queryDepName = queryResult.getString("department_name");
                int queryLocationID = queryResult.getInt("location_id");
                String queryCountryID = queryResult.getString("country_id");
                String queryCityID = queryResult.getString("city");
                String queryStrAdId = queryResult.getString("street_address");
                String queryPostalCode = queryResult.getString("postal_code");


                //Populate Employee Observable List
                departmentSearchObservableList.add(new Department(queryDepId,queryDepName,queryLocationID,queryCountryID,queryCityID,queryStrAdId
                        ,queryPostalCode));



            }
            queryResult = statement.executeQuery(distinctDepartmentID);
            while (queryResult.next()) {
                int queryDepId = queryResult.getInt("department_id");
                departmentIDObservableList.add(queryDepId);
            }

            choiceBoxDepID.setItems(departmentIDObservableList);

            depIDCol.setCellValueFactory(new PropertyValueFactory<>("department_id"));
            depNameCol.setCellValueFactory(new PropertyValueFactory<>("department_name"));
            locationIDCol.setCellValueFactory(new PropertyValueFactory<>("location_id"));
            countryCol.setCellValueFactory(new PropertyValueFactory<>("country_id"));
            cityCol.setCellValueFactory(new PropertyValueFactory<>("city"));
            streetAdCol.setCellValueFactory(new PropertyValueFactory<>("street_address"));
            postalCodeCol.setCellValueFactory(new PropertyValueFactory<>("postal_code"));
            tvLocations.setItems(departmentSearchObservableList);


            }   catch (SQLException e){
        //Log for the error
        Logger.getLogger(AppController.class.getName()).log(Level.SEVERE,null, e);
        }

        try {
            String connectionQueryJob = "SELECT * FROM jobs";
            String distinctJobID = "Select distinct job_id from jobs order by job_id asc";
            //Running this statement
            Statement statement = connectDBS.createStatement();
            ResultSet queryResult = statement.executeQuery(connectionQueryJob);

            //While query has next result, populate the Observable list
            while (queryResult.next()) {
                int queryJobId = queryResult.getInt("job_id");
                String queryJobTitle = queryResult.getString("job_title");
                double queryMinSalary = queryResult.getDouble("min_salary");
                double queryMaxSalary = queryResult.getDouble("max_salary");

                //Populate Employee Observable List
                jobSearchObservableList.add(new Job(queryJobId,queryJobTitle,queryMinSalary,queryMaxSalary));

            }
            queryResult = statement.executeQuery(distinctJobID);

            //While query has next result, populate the Observable list
            while (queryResult.next()) {
                int queryJobId = queryResult.getInt("job_id");
                jobIDObservableList.add(queryJobId);
            }
            choiceBoxJobID.setItems(jobIDObservableList);




            jobIDCol.setCellValueFactory(new PropertyValueFactory<>("job_id"));
            jobTitleCol.setCellValueFactory(new PropertyValueFactory<>("job_title"));
            minSalaryCol.setCellValueFactory(new PropertyValueFactory<>("min_salary"));
            maxSalaryCol.setCellValueFactory(new PropertyValueFactory<>("max_salary"));

            tvJobs.setItems(jobSearchObservableList);

        }   catch (SQLException e){
            //Log for the error
            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE,null, e);
        }
        tvJobs.skinProperty().addListener(((obs, oldSkin, newSkin) -> {
            final TableHeaderRow header = (TableHeaderRow) tvJobs.lookup("TableHeaderRow");
            header.reorderingProperty().addListener((o, oldVal, newVal) -> header.setReordering(false));
        }
        ));

        /*
            Edit  Buttons
         */
            editEmpName.setOnAction(event -> {
                if (editEmpName.getText().contains("Edit")) {
                    EmployeeNameTF.setEditable(true);
                    editEmpName.setText("Confirm");
                } else{
                    try {
                        String updateStatement = "UPDATE employees " +
                                                 "SET first_name = '"+ EmployeeNameTF.getText() +"'" +
                                                 "WHERE employee_id="+ EmployeeIDTF.getText();
                        Statement statement = connectDBS.createStatement();
                        statement.executeUpdate(updateStatement);
                        EmployeeNameTF.setEditable(false);
                        editEmpName.setText("Edit");
                        Alerts.throwInfoAlert("Employee Update","Employee information updated!","");

                    }  catch (Exception io){
                        Alerts.errorInfoAlert("Wrong input!","Wrong input type in text fields!","One or more inputs have wrong type! Try again!");
                        io.printStackTrace();
                    }

                }

            });

        editEmpLastName.setOnAction(event -> {
            if (editEmpLastName.getText().contains("Edit")) {
                LastNameTF.setEditable(true);
                editEmpLastName.setText("Confirm");
            } else{
                try {
                    String updateStatement = "UPDATE employees " +
                            "SET last_name = '"+ LastNameTF.getText() +"'" +
                            "WHERE employee_id="+ EmployeeIDTF.getText();
                    Statement statement = connectDBS.createStatement();
                    statement.executeUpdate(updateStatement);
                    LastNameTF.setEditable(false);
                    editEmpLastName.setText("Edit");
                    Alerts.throwInfoAlert("Employee Update","Employee information updated!","");

                }  catch (Exception io){
                    Alerts.errorInfoAlert("Wrong input!","Wrong input type in text fields!","One or more inputs have wrong type! Try again!");
                    io.printStackTrace();
                }

            }

        });
        editPhoneNumber.setOnAction(event -> {
            if (editPhoneNumber.getText().contains("Edit")) {
                PhoneNumberTF.setEditable(true);
                editPhoneNumber.setText("Confirm");
            } else{
                try {
                    String updateStatement = "UPDATE employees " +
                            "SET phone_number = '"+ PhoneNumberTF.getText() +"'" +
                            "WHERE employee_id="+ EmployeeIDTF.getText();
                    Statement statement = connectDBS.createStatement();
                    statement.executeUpdate(updateStatement);
                    PhoneNumberTF.setEditable(false);
                    editPhoneNumber.setText("Edit");
                    Alerts.throwInfoAlert("Employee Update","Employee information updated!","");

                }  catch (Exception io){
                    Alerts.errorInfoAlert("Wrong input!","Wrong input type in text fields!","One or more inputs have wrong type! Try again!");
                    io.printStackTrace();
                }

            }

        });
        editEmail.setOnAction(event -> {
            if (editEmail.getText().contains("Edit")) {
                EmailTF.setEditable(true);
                editEmail.setText("Confirm");
            } else{
                try {
                    String updateStatement = "UPDATE employees " +
                            "SET email = '"+ EmailTF.getText() +"'" +
                            "WHERE employee_id="+ EmployeeIDTF.getText();
                    Statement statement = connectDBS.createStatement();
                    statement.executeUpdate(updateStatement);
                    EmailTF.setEditable(false);
                    editEmail.setText("Edit");
                    Alerts.throwInfoAlert("Employee Update","Employee information updated!","");

                }  catch (Exception io){
                    Alerts.errorInfoAlert("Wrong input!","Wrong input type in text fields!","One or more inputs have wrong type! Try again!");
                    io.printStackTrace();
                }

            }

        });

        /*
        needs editing
         */
        editDepID.setOnAction(event -> {


        });
        editJobID.setOnAction(event -> {

        });
        /*
            finish editing
         */
        /*
            END OF EDIT BUTTONS
         */



    }

    @FXML
    private void GoToHomePage(ActionEvent event)throws Exception{
        App.setRoot("hello-view");
    }

    @FXML
    void deleteEmployee(ActionEvent event) {
        Connection conn = EmployeeDBC.getConnection();


        try {
            String queryDelete= "DELETE FROM employees where employee_id=" + Integer.parseInt(EmployeeIDTF.getText());
            Statement  statement = conn.createStatement();
            statement.executeUpdate(queryDelete);

        } catch (SQLException e) {
           Alerts.errorInfoAlert("You selected the Manager!",
                            "Manager cannot be deleted!",
                            "You are trying to delete manager, try choosing different employee!");
        }


        try {
            App.setRoot("hello-view");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
