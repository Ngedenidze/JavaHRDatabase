package com.example.demo;
/*
  CS 196: Final Project;

  Employee Management System
  Author: Nika Gedenidze
 */


//Imports
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.fxml.Initializable;
import javafx.scene.control.skin.TableHeaderRow;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AppController implements Initializable{

    //Private variables for FXML file

    //Buttons
    @FXML
    private Button buttonInfo;
    @FXML
    private Button goToProfileBTN;
    @FXML
    private Button addNewEmployeeBTN;
    //Labels

    //Tableviews
    @FXML
    private TableView <Employee> tvEmployee;

    //Table Columns
    @FXML
    public TableColumn<Employee,Integer> eIDCol;
    @FXML
    private TableColumn<Employee,String> fnameCol;
    @FXML
    private TableColumn<Employee,String> lnameCol;
    @FXML
    private TableColumn<Employee,String> emailCol;
    @FXML
    private TableColumn<Employee,String> phoneNCol;
    @FXML
    private TableColumn<Employee, Double> salCol;
    @FXML
    private TableColumn<Employee, Integer> jobIDCol;
    @FXML
    private TableColumn<Employee, Integer> managerIDCol;
    @FXML
    private TableColumn<Employee, Integer> depIDCol;

    //TextFields
    @FXML
    private TextField DepIDSearchTF;
    @FXML
    private TextField IDSearchTF;
    @FXML
    private TextField ManIDSearchTF;
    @FXML
    private TextField fNameSearchTF;
    @FXML
    private TextField lNameSearchTF;
    @FXML
    private TextField JobIDSearchTF;


    //Text areas
    @FXML
    private TextArea informationTextArea;




    /**
     * Database connection and bind to the Table view
     */

    //Observable list of Employee class instances
    ObservableList<Employee> employeeSearchObservableList = FXCollections.observableArrayList();

    //On application start
    public void initialize(URL url, ResourceBundle resource){
        //Connection variables
        EmployeeDBC connectNow = new EmployeeDBC();
        Connection connectDBS = connectNow.getConnection();

        //Query to select data from database table
        String connectionQuery = "select employee_id,first_name,last_name,email,phone_number,job_id,salary,manager_id,department_id from employees";

        try{
            //Running this statement
            Statement statement = connectDBS.createStatement();
            ResultSet queryResult = statement.executeQuery(connectionQuery);

            //While query has next result, populate the Observable list
            while (queryResult.next()){
                int queryEmployeeId = queryResult.getInt("employee_id");
                String queryFirstName = queryResult.getString("first_name");
                String queryLastName = queryResult.getString("last_name");
                String queryEmail = queryResult.getString("email");
                String queryPhoneNumber = queryResult.getString("phone_number");
                int queryJobId = queryResult.getInt("job_id");
                double querySalary = queryResult.getDouble("salary");
                int queryManagerId = queryResult.getInt("manager_id");
                int queryDepId = queryResult.getInt("department_id");

                //Populate Employee Observable List
                employeeSearchObservableList.add(new Employee(queryEmployeeId,queryFirstName,queryLastName,queryEmail,
                        queryPhoneNumber,queryJobId,querySalary,queryManagerId,queryDepId));
            }

            //Setting values for cells
            eIDCol.setCellValueFactory(new PropertyValueFactory<>("employee_id"));
            fnameCol.setCellValueFactory(new PropertyValueFactory<>("first_name"));
            lnameCol.setCellValueFactory(new PropertyValueFactory<>("last_name"));
            emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
            phoneNCol.setCellValueFactory(new PropertyValueFactory< >("phone_number"));
            salCol.setCellValueFactory(new PropertyValueFactory<>("salary"));
            jobIDCol.setCellValueFactory(new PropertyValueFactory<>("job_id"));
            managerIDCol.setCellValueFactory(new PropertyValueFactory<>("manager_id"));
            depIDCol.setCellValueFactory(new PropertyValueFactory<>("department_id"));

            //Setting cells in table view
            tvEmployee.setItems(employeeSearchObservableList);
        }   catch (SQLException e){
            //Log for the error
            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE,null, e);
        }


        //Re-ordering of table columns disabled
        tvEmployee.skinProperty().addListener(((obs, oldSkin, newSkin) -> {
            final TableHeaderRow header = (TableHeaderRow) tvEmployee.lookup("TableHeaderRow");
            header.reorderingProperty().addListener((o, oldVal, newVal) -> header.setReordering(false));
        }
        ));

        //Searching
        FilteredList<Employee> filteredData = new FilteredList<>(employeeSearchObservableList, b ->true);

        //Filter for ID search
        IDSearchTF.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Employee -> {
                if (newValue.isEmpty() || newValue.isBlank()) {
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();

                //filtering out by each element of Employee class
                return Employee.getEmployee_id().toString().contains(searchKeyword); //Found a match is first name

            });
        });

        //Filter for first name search
        fNameSearchTF.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(Employee -> {
            if(newValue.isEmpty() || newValue.isBlank()){
                return true;
            }
            String searchKeyword = newValue.toLowerCase();

            //filtering out by each element of Employee class
            return Employee.getFirst_name().toLowerCase().contains(searchKeyword); //Found a match is first name

        }));

        //Filter for last name search
        lNameSearchTF.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(Employee -> {
            if(newValue.isEmpty() || newValue.isBlank()){
                return true;
            }
            String searchKeyword = newValue.toLowerCase();

            //filtering out by each element of Employee class
            return Employee.getLast_name().toLowerCase().contains(searchKeyword); //Found a match is first name

        }));

        //Filter for Job ID search
        JobIDSearchTF.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(Employee -> {
            if(newValue.isEmpty() || newValue.isBlank()){
                return true;
            }
            String searchKeyword = newValue.toLowerCase();

            //filtering out by each element of Employee class
            return Employee.getJob_id().toString().contains(searchKeyword); //Found a match is first name

        }));

        //Filter for Manager ID search
        ManIDSearchTF.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(Employee -> {
            if(newValue.isEmpty() || newValue.isBlank()){
                return true;
            }
            String searchKeyword = newValue.toLowerCase();

            //filtering out by each element of Employee class
            return Employee.getManager_id().toString().contains(searchKeyword); //Found a match is first name

        }));

        //Filter for Department ID search
        DepIDSearchTF.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(Employee -> {
            if(newValue.isEmpty() || newValue.isBlank()){
                return true;
            }
            String searchKeyword = newValue.toLowerCase();

            //filtering out by each element of Employee class
            return Employee.getDepartment_id().toString().contains(searchKeyword); //Found a match is first name

        }));

        //Updating data inside the table with filter

        SortedList<Employee> sortedData = new SortedList<>(filteredData);
        //binding sorted data with tvEmployee
        sortedData.comparatorProperty().bind(tvEmployee.comparatorProperty());

        //Applying filter
        tvEmployee.setItems(sortedData);
    }

    /**
     * FXML file methods
     */
    @FXML
    private void makeTextChange(){

    }

    @FXML
    private void connectButton(ActionEvent event){

    }

    public Employee selectedEmployee;

    @FXML
    public void goToProfile(ActionEvent event) {
        try{
        App.setRoot("profile-page");}
        catch (IOException e){
            e.printStackTrace();
        }
    }


    /**
     * Display the employee information inside the information text are on mouse button click
     */
    @FXML
    private void displayInformation(MouseEvent event){
        selectedEmployee = tvEmployee.getSelectionModel().getSelectedItem();
        informationTextArea.setText(selectedEmployee.toString() + "\n");
    }

    @FXML
    void addEmployee(ActionEvent event) {
        addNewEmployeeBTN.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Parent root;
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("new-employee-tab.fxml"));
                    root = fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setTitle("New Employee");
                    stage.setScene(new Scene(root, 600, 400));
                    stage.show();
                    // Hide this current window (if this is what you want)
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}