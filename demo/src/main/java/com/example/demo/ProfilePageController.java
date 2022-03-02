package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.skin.TableHeaderRow;

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
    private Menu homePageBTN;

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





    ObservableList<Job> jobSearchObservableList = FXCollections.observableArrayList();
    ObservableList<Department> departmentSearchObservableList = FXCollections.observableArrayList();



    public void initialize(URL url, ResourceBundle resource){
        EmployeeDBC connectNow = new EmployeeDBC();
        Connection connectDBS = connectNow.getConnection();

        try {

            String connectionQueryDepartment = "SELECT department_id,department_name,departments.location_id,country_id,city,street_address, postal_code\n" +
                    " from departments INNER JOIN locations on departments.location_id = locations.location_id ORDER BY department_id Asc;";
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
        tvLocations.skinProperty().addListener(((obs, oldSkin, newSkin) -> {
            final TableHeaderRow header = (TableHeaderRow) tvJobs.lookup("TableHeaderRow");
            header.reorderingProperty().addListener((o, oldVal, newVal) -> header.setReordering(false));
        }
        ));

        try {
            String connectionQueryJob = "SELECT * FROM jobs";
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
        tvJobs.skinProperty().addListener(((obs, oldSkin, newSkin) -> {
            final TableHeaderRow header = (TableHeaderRow) tvJobs.lookup("TableHeaderRow");
            header.reorderingProperty().addListener((o, oldVal, newVal) -> header.setReordering(false));
        }
        ));



    }

    @FXML
    private void GoToHomePage(ActionEvent event)throws Exception{
        App.setRoot("hello-view");
    }

}
