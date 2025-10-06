/*
Clint Scott
CSD 402
Fan Manager – Establishes database connection for fan record management
10/05/2025

This class handles the connection to the MySQL database.
It loads database connection details (URL, user, password) from the **config.properties** file.
Used by other classes to access the fans table for viewing and updating records.
*/

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private static Properties props = new Properties();

    // Static initializer block to load the properties file once
    static {
        try (FileInputStream input = new FileInputStream("config.properties")) {
            props.load(input);
        } catch (IOException ex) {
            // Log the error and exit or throw a runtime exception if the connection is critical
            System.err.println("Error loading config.properties file: " + ex.getMessage());
            // Throw a runtime exception here since the application can't proceed without config
            throw new RuntimeException("Failed to load database configuration.", ex);
        }
    }

    public static Connection getConnection() throws SQLException {
        // The properties file is expected to contain: db.url, db.user, db.password
        return DriverManager.getConnection(
            props.getProperty("db.url"),
            props.getProperty("db.user"),
            props.getProperty("db.password")
        );
    }
}