package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entity.User;
import java.sql.Connection;

public class UserDAO extends context.DBContext {

    // Get all users
    public List<User> getAllUser() {
        List<User> list = new ArrayList<>();
        String query = "SELECT TOP (1000) [username], [password], [name], [gender], [dob], [img], [email], [phone], [status], [role] FROM [SWP391].[dbo].[Users]";

        try (PreparedStatement st = connection.prepareStatement(query);
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                User user = mapResultSetToUser(rs);
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Search users by name
    public List<User> searchUserByName(String name) {
        List<User> list = new ArrayList<>();
        String query = "SELECT [username], [password], [name], [gender], [dob], [img], [email], [phone], [status], [role] "
                + "FROM [SWP391].[dbo].[Users] "
                + "WHERE [name] LIKE ?";

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setString(1, "%" + name + "%");

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    User user = mapResultSetToUser(rs);
                    list.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Delete a user
    public boolean deleteUser(String username) {
        String query = "DELETE FROM [SWP391].[dbo].[Users] WHERE [username] = ?";

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setString(1, username);
            int rowsAffected = st.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get users by page
    public List<User> getUsersByPage(int offset, int limit) {
        List<User> users = new ArrayList<>();
        String sql = "SELECT [username], [password], [name], [gender], [dob], [img], [email], [phone], [status], [role] "
                + "FROM [SWP391].[dbo].[Users] ORDER BY [username] OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, offset);
            ps.setInt(2, limit);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    User user = mapResultSetToUser(rs);
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // Get the total count of users
    public int getUserCount() {
        String sql = "SELECT COUNT(*) FROM [SWP391].[dbo].[Users]";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Edit a user
    public boolean editUser(User user) {
        if (user == null || user.getUsername() == null) {
            throw new IllegalArgumentException("User or username cannot be null");
        }

        String query = "UPDATE [SWP391].[dbo].[Users] SET "
                + "[password] = ?, "
                + "[name] = ?, "
                + "[gender] = ?, "
                + "[dob] = ?, "
                + "[img] = ?, "
                + "[email] = ?, "
                + "[phone] = ?, "
                + "[status] = ?, "
                + "[role] = ? "
                + "WHERE [username] = ?";

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setString(1, user.getPassword());
            st.setString(2, user.getName());
            st.setString(3, user.getGender());
            st.setDate(4, user.getDob() != null ? new java.sql.Date(user.getDob().getTime()) : null);
            st.setString(5, user.getImg());
            st.setString(6, user.getEmail());
            st.setString(7, user.getPhone());
            st.setInt(8, user.getStatus());
            st.setInt(9, user.getRole());
            st.setString(10, user.getUsername());

            int rowsAffected = st.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get a user by username
    public User getUserByUsername(String username) {
        User user = null;
        String query = "SELECT [username], [password], [name], [gender], [dob], [img], [email], [phone], [status], [role] "
                + "FROM [SWP391].[dbo].[Users] "
                + "WHERE [username] = ?";

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setString(1, username);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    user = mapResultSetToUser(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    // Helper method to map ResultSet to User object
    private User mapResultSetToUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setName(rs.getString("name"));
        user.setGender(rs.getString("gender"));
        user.setDob(rs.getDate("dob"));
        user.setImg(rs.getString("img"));
        user.setEmail(rs.getString("email"));
        user.setPhone(rs.getString("phone"));
        user.setStatus(rs.getInt("status"));
        user.setRole(rs.getInt("role"));
        return user;
    }
    
    // Check user login
    public User checkLogin(String username, String password) {
        String sql = "select * from [Users] where [username] = ? and [password] = ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDate(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getInt(9),
                        rs.getInt(10)
                );
                return user;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
}
