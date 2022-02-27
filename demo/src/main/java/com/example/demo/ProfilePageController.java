package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.demo.App.setRoot;

public class ProfilePageController implements Initializable {
    @FXML
    public Label labelOne;

    @FXML
    private Menu homePageBTN;

    @FXML
    private TableColumn<Department, Integer> depIDCol;

    @FXML
    private TableColumn<Department, String> depNameCol;


    @FXML
    private TableColumn<Job, Integer> jobIDCol;

    @FXML
    private TableColumn<Job, String> jobTitleCol;

    @FXML
    private TableColumn<Job, Double> maxSalaryCol;

    @FXML
    private TableColumn<Job, Double> minSalaryCol;

    @FXML
    private TableView<Department> tvDepartments;

    @FXML
    private TableView<Job> tvJobs;



    private String informationName;

    ObservableList<Job> jobSearchObservableList = FXCollections.observableArrayList();
    ObservableList<Department> departmentSearchObservableList = FXCollections.observableArrayList();

    public void getInformationFromTextField(String name){
        informationName = name;
    }

    public void initialize(URL url, ResourceBundle resource){
        EmployeeDBC connectNow = new EmployeeDBC();
        Connection connectDBS = connectNow.getConnection();
        String connectionQueryJob = "SELECT * FROM jobs";
        String connectionQueryDepartment = "SELECT department_id,department_name FROM departments";


        try {
            //Running this statement
            Statement statement = connectDBS.createStatement();
            ResultSet queryResult = statement.executeQuery(connectionQueryDepartment);

            //While query has next result, populate the Observable list
            while (queryResult.next()) {
                int queryDepId = queryResult.getInt("department_id");
                String queryDepName = queryResult.getString("department_name");
                //Populate Employee Observable List
                departmentSearchObservableList.add(new Department(queryDepId,queryDepName));

            }


            depIDCol.setCellValueFactory(new PropertyValueFactory<>("department_id"));
            depNameCol.setCellValueFactory(new PropertyValueFactory<>("department_name"));
            tvDepartments.setItems(departmentSearchObservableList);

            }   catch (SQLException e){
        //Log for the error
        Logger.getLogger(AppController.class.getName()).log(Level.SEVERE,null, e);
        }

        try {
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


            jobIDCol.setCellValueFactory(new PropertyValueFactory<>("job_id"));
            jobTitleCol.setCellValueFactory(new PropertyValueFactory<>("job_title"));
            minSalaryCol.setCellValueFactory(new PropertyValueFactory<>("min_salary"));
            maxSalaryCol.setCellValueFactory(new PropertyValueFactory<>("max_salary"));

            tvJobs.setItems(jobSearchObservableList);

        }   catch (SQLException e){
            //Log for the error
            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE,null, e);
        }



    }

    @FXML
    private void GoToHomePage(ActionEvent event)throws Exception{
        setRoot("hello-view");
    }

}
