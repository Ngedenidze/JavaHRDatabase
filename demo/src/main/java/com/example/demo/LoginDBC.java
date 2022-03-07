package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Connection to 'users' database for login information
 * */
public class LoginDBC {
    public Connection conn;


    /**
     * @return connection to database
     */
    public Connection getConnection(){
        String databaseName = "login_information";
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
