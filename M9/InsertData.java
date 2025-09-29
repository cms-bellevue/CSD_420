/*
Clint Scott
Bellevue University
M9 Assignment - InsertData
09/28/2025

This program connects to the databasedb database,
inserts multiple rows into the address table, and
prints confirmation for each row inserted.
 */
import java.sql.*;

public class InsertData {

    Connection con;
    Statement stmt;

    public InsertData() {
        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connection details
            String url = "jdbc:mysql://localhost:3306/databasedb?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
            String user = "student1";
            String password = "pass";

            // Connect
            con = DriverManager.getConnection(url, user, password);
            con.setAutoCommit(false);
            stmt = con.createStatement();
            System.out.println("Connection established.");
        } catch (Exception e) {
            System.out.println("Error connecting to database.");
            e.printStackTrace();
            System.exit(0);
        }

        try {
            // Insert rows (adjust to match table columns in CreateTable.java)
            System.out.println(
                stmt.executeUpdate("INSERT INTO address VALUES(55,'Larry','Rich','1111 Redwing Circle888','Bellevue','NE','68123')") + " row inserted");

            System.out.println(
                stmt.executeUpdate("INSERT INTO address VALUES(1,'Fine','Ruth','1111 Redwing Circle','Bellevue','NE','68123')") + " row inserted");

            System.out.println(
                stmt.executeUpdate("INSERT INTO address VALUES(2,'Howard','Curly','1000 Galvin Road South','Bellevue','NE','68005')") + " row inserted");

            System.out.println(
                stmt.executeUpdate("INSERT INTO address VALUES(3,'Howard','Will','2919 Redwing Circle','Bellevue','NE','68123')") + " row inserted");

            System.out.println(
                stmt.executeUpdate("INSERT INTO address VALUES(4,'Wilson','Larry','1121 Redwing Circle','Bellevue','NE','68124')") + " row inserted");

            System.out.println(
                stmt.executeUpdate("INSERT INTO address VALUES(5,'Johnson','George','1300 Galvin Road South','Bellevue','NE','68006')") + " row inserted");

            System.out.println(
                stmt.executeUpdate("INSERT INTO address VALUES(6,'Long','Matthew','2419 Redwing Circle','Bellevue','NE','68127')") + " row inserted");

            System.out.println(
                stmt.executeUpdate("INSERT INTO address VALUES(44,'Tom','Matthew','1999 Redwing Circle','Bellevue','NE','68123')") + " row inserted");

            // Commit transaction
            con.commit();
            System.out.println("Data Inserted");
        } catch (SQLException e) {
            System.out.println("Insert Data Failed");
            e.printStackTrace();
        }

        try {
            stmt.close();
            con.close();
            System.out.println("Database connections closed.");
        } catch (SQLException e) {
            System.out.println("Connection close failed");
        }
    }

    public static void main(String args[]) {
        new InsertData();
    }
}