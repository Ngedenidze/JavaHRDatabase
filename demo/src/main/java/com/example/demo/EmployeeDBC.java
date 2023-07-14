package com.example.demo;
import java.sql.*;

/*
    Database Connection with new_schema(Employee information)
 */
public class EmployeeDBC {


    public static Connection conn;

    /*
        returns connection with database
     */
    public static Connection getConnection() {
        String databaseName = "employeedatabase";
        String dbPassword = "Sabae_123!";
        String dbUsername = "root1";
        String url = "jdbc:mysql://localhost:3306/" + databaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, dbUsername, dbPassword);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static ResultSet getResultSet(String Query) throws SQLException {
        conn = EmployeeDBC.getConnection();
        Statement stm = conn.createStatement();
        return stm.executeQuery(Query);
    }

    public static void updateSelect(String query) throws SQLException {
        conn = EmployeeDBC.getConnection();
        Statement stm = conn.createStatement();
        int countOfUpdate = stm.executeUpdate(query);
    }
}
