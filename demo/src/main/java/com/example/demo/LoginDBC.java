package com.example.demo;

import java.sql.*;

/**
 * Connection to 'users' database for login information
 * */
public class LoginDBC {
    public static Connection conn;


    /**
     * @return connection to database
     */
    public static Connection getConnection(){
        String databaseName = "employeedatabase";
        String dbPassword = "Sabae_123!";
        String dbUsername = "root1";
        String url = "jdbc:mysql://localhost:3306/"  +  databaseName;
       
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url,dbUsername,dbPassword);

        }catch (Exception e){
            e.printStackTrace();
        }
        return conn;
    }
    public static ResultSet getResultSet(String Query) throws SQLException {
        conn = LoginDBC.getConnection();
        Statement stm = conn.createStatement();
        return stm.executeQuery(Query);
    }
}
