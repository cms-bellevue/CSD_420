/*
Clint Scott
Bellevue University
M9 Assignment - CreateTable
09/28/2025

This program connects to the databasedb database,
drops the address table if it exists, and then
creates a new address table with the required columns.
 */
import java.sql.*;

public class CreateTable {

    Connection con;
    Statement stmt;

    public CreateTable() {
        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connection details
            String url = "jdbc:mysql://localhost:3306/databasedb?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
            String user = "student1";
            String password = "pass";

            // Connect
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            System.out.println("Connection established.");
        } catch (Exception e) {
            System.out.println("Error connecting to database.");
            e.printStackTrace();
            System.exit(0);
        }

        // Try to drop the table (ignore if not exists)
        try {
            stmt.executeUpdate("DROP TABLE address");
            System.out.println("Table address dropped.");
        } catch (SQLException e) {
            System.out.println("Table address did not exist.");
        }

        // Create the address table
        try {
            stmt.executeUpdate(
                "CREATE TABLE address (" +
                "ID INT PRIMARY KEY, " +
                "LASTNAME VARCHAR(40), " +
                "FIRSTNAME VARCHAR(40), " +
                "STREET VARCHAR(40), " +
                "CITY VARCHAR(40), " +
                "STATE VARCHAR(40), " +
                "ZIP VARCHAR(40))"
            );
            System.out.println("Table address created.");
        } catch (SQLException e) {
            System.out.println("Table address creation failed.");
            e.printStackTrace();
        }

        // Clean up
        try {
            stmt.close();
            con.close();
            System.out.println("Database connections closed.");
        } catch (SQLException e) {
            System.out.println("Connection close failed.");
        }
    }

    public static void main(String args[]) {
        new CreateTable();
    }
}