/*
 Clint Scott
 CSD 402
 Fan Manager – Data Access Object for fan records
 10/05/2025
 
 This class contains methods for retrieving and updating fan records.
 - getFanById() retrieves a fan record by ID
 - updateFan() updates an existing fan record in the database
 */

import java.sql.*;

public class FanDAO {

    // Retrieves a fan record by ID
    public Fan getFanById(int id) {
        String sql = "SELECT * FROM fans WHERE ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Fan(
                    rs.getInt("ID"),
                    rs.getString("firstname"),
                    rs.getString("lastname"),
                    rs.getString("favoriteteam")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving fan: " + e.getMessage());
        }
        return null;
    }

    // Updates an existing fan record
    public boolean updateFan(Fan fan) {
        String sql = "UPDATE fans SET firstname=?, lastname=?, favoriteteam=? WHERE ID=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, fan.getFirstname());
            stmt.setString(2, fan.getLastname());
            stmt.setString(3, fan.getFavoriteteam());
            stmt.setInt(4, fan.getId());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            System.out.println("Error updating fan: " + e.getMessage());
        }
        return false;
    }
}