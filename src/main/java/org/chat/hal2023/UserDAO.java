package org.chat.hal2023;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *  This class is used to save users in the database.
 */
public class UserDAO {

    public void updateUsername(String oldUsername, String newUsername) {
        String sql = "UPDATE Users SET username = ? WHERE username = ?";

        try (Connection conn = DataBaseUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

             pstmt.setString(1, newUsername);
             pstmt.setString(2, oldUsername);

             pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePassword(String username, String password) {
        String sql = "UPDATE Users SET password = ? WHERE username = ?";

        try (Connection conn = DataBaseUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, password);
            pstmt.setString(2, username);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public void updateEmail(String username, String email) {
        String sql = "UPDATE Users SET email = ? WHERE username = ?";

        try (Connection conn = DataBaseUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

             pstmt.setString(1, email);
             pstmt.setString(2, username);

             pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(User user) {
        String sql = "INSERT INTO Users (username, email, password) VALUES (?, ?, ?)";

         try (Connection conn = DataBaseUtils.getConnection();
              PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, user.getUsername());
                pstmt.setString(2, user.getEmail());
                pstmt.setString(3, user.getPassword());
                pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    boolean validateUser(String username, String password) {
        String sql = "SELECT * FROM Users WHERE username = ? AND password = ?";
        try (Connection conn = DataBaseUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}


