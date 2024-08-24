package dao;

import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class PasswordResetTokenDAO extends DBContext {

    // Save a new token in the database
    public void saveToken(String username, String token, Timestamp expired) {
        String query = "INSERT INTO dbo.PasswordResetToken (username, token, expired, used) VALUES (?, ?, ?, ?)";
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setString(1, username);
            st.setString(2, token);
            st.setTimestamp(3, expired);
            st.setBoolean(4, false); // Set used to false initially
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Check if the token is valid
    public boolean isTokenValid(String token) {
        String query = "SELECT expired, used FROM dbo.PasswordResetToken WHERE token = ?";
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setString(1, token);
            var rs = st.executeQuery();
            if (rs.next()) {
                Timestamp expired = rs.getTimestamp("expired");
                boolean used = rs.getBoolean("used");
                return !used && expired.after(new Timestamp(System.currentTimeMillis()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Mark the token as used
    public void markTokenAsUsed(String token) {
        String query = "UPDATE dbo.PasswordResetToken SET used = 1 WHERE token = ?";
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setString(1, token);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get the username associated with a token
    public String getUsernameByToken(String token) {
        String query = "SELECT username FROM dbo.PasswordResetToken WHERE token = ?";
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setString(1, token);
            var rs = st.executeQuery();
            if (rs.next()) {
                return rs.getString("username");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
