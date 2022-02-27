package com.example.demo;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

public class EmployeeDBC {

    public Connection conn;

    public Connection getConnection(){
        String databaseName = "new_schema";
        String dbPassword = "Thegafield_123";
        String dbUsername = "root";
        String url = "jdbc:mysql://localhost:3400/"  +  databaseName;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url,dbUsername,dbPassword);

        }catch (Exception e){
            e.printStackTrace();
        }
        return conn;
    }
}
