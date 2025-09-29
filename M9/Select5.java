/*
Clint Scott
Bellevue University
M9 Assignment - Select5
09/28/2025
 
This program connects to the databasedb database,
runs a SELECT query, and prints the results.
*/
import java.sql.*;

public class Select5 {

    public static void main(String args[]) {

        try {
            // Load the MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connection details
            String url = "jdbc:mysql://localhost:3306/databasedb?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
            String user = "student1";
            String password = "pass";

            // Establish the connection
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connection established - now executing a select");

            // Create and execute the query
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM address");

            // Display results
            System.out.println("Received Results:");
            int i = rs.getMetaData().getColumnCount();

            while (rs.next()) {
                for (int x = 1; x <= i; ++x) {
                    System.out.print(rs.getString(x) + " ");
                }
                System.out.println();
            }

            // Cleanup
            rs.close();
            stmt.close();
            con.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}