package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entity.User;
import controller.Encryption;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UserDAO extends context.DBContext {

    // Get all users
    public List<User> getAllUser() {
        List<User> list = new ArrayList<>();
        String query = "SELECT TOP (1000) [username], [password], [name], [gender], [dob], [img], [email], [phone], [status], [role] FROM [SWP391].[dbo].[Users]";


        try (PreparedStatement st = connection.prepareStatement(query); ResultSet rs = st.executeQuery()) {
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

        try ( PreparedStatement st = connection.prepareStatement(query)) {
            st.setString(1, "%" + name + "%");

            try ( ResultSet rs = st.executeQuery()) {
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
            System.out.println("Error deleting user: " + e.getMessage());
            return false;
        }
    }

    // Get users by page
    public List<User> getUsersByPage(int offset, int limit) {
        List<User> users = new ArrayList<>();
        String sql = "SELECT [username], [password], [name], [gender], [dob], [img], [email], [phone], [status], [role] "
                + "FROM [SWP391].[dbo].[Users] ORDER BY [username] OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try ( PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, offset);
            ps.setInt(2, limit);
            try ( ResultSet rs = ps.executeQuery()) {
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

        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean editUser(User user) {
        if (user == null || user.getUsername() == null) {
            throw new IllegalArgumentException("User or username cannot be null");
        }

        // Define the SQL update query to only modify status and role
        String query = "UPDATE [SWP391].[dbo].[Users] SET [status] = ?, [role] = ? WHERE [username] = ?";

        try (PreparedStatement st = connection.prepareStatement(query)) {
            // Set parameters for status, role, and username
            st.setInt(1, user.getStatus());
            st.setInt(2, user.getRole());
            st.setString(3, user.getUsername());

            // Execute the update and return whether rows were affected
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

        try ( PreparedStatement st = connection.prepareStatement(query)) {
            st.setString(1, username);

            try ( ResultSet rs = st.executeQuery()) {
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
            ps.setString(2, Encryption.MD5Encryption(password));
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

    // Get role based on Username
    public int getRoleByUsername(String username) {
        int role = -1; // Default value if user is not found or an error occurs
        String sql = "SELECT role FROM Users WHERE username = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            // Set the username parameter in the query
            pstmt.setString(1, username);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    role = rs.getInt("role");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions as appropriate
        }

        return role;
    }

    // Get username based on email
    public String getUsernamebyEmail(String email) {
        String username = null;
        String query = "SELECT username FROM Users WHERE email = ?";

        try ( PreparedStatement st = connection.prepareStatement(query)) {
            st.setString(1, email);

            try ( ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    username = rs.getString("username");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return username;
    }

    // Method to change password
    public boolean changePass(String username, String newPassword) {
        String query = "UPDATE [SWP391].[dbo].[Users] SET [password] = ? WHERE [username] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, Encryption.MD5Encryption(newPassword));
            st.setString(2, username);
            int rowsAffected = st.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Add new user
    public boolean addUser(User user) {
        if (user == null || user.getUsername() == null) {
            throw new IllegalArgumentException("User hoặc username không thể null");
        }

        String query = "INSERT INTO [dbo].[Users] "
                + "([username], [password], [name], [gender], [dob], [email], [phone], [status], [role]) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, 1, ?)";

        try ( PreparedStatement st = connection.prepareStatement(query)) {

            st.setString(1, user.getUsername());
            st.setString(2, Encryption.MD5Encryption(user.getPassword()));
            st.setString(3, user.getName());
            st.setString(4, user.getGender());
            st.setDate(5, user.getDob() != null ? new java.sql.Date(user.getDob().getTime()) : null);
            st.setString(6, user.getEmail());
            st.setString(7, user.getPhone());
            st.setInt(8, user.getRole());

            int rowsAffected = st.executeUpdate();
            System.out.println(1);
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(2);
            return false;
        }
    }

    public boolean signup(String username, String password, String name, String gender, String dob, String email, String phone, int role)  {
        String query = "INSERT INTO [dbo].[Users] "
                + "([username], [password], [name], [gender], [dob], [email], [phone], [role]) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, username);
            st.setString(2, Encryption.MD5Encryption(password));
            st.setString(3, name);
            st.setString(4, gender);
            java.sql.Date sqlDob = null;
            if (dob != null && !dob.isEmpty()) {
                try {
                    java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(dob);
                    sqlDob = new java.sql.Date(utilDate.getTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                    return false;
                }
            }
            st.setDate(5, sqlDob);
            st.setString(6, email);
            st.setString(7, phone);
            st.setInt(8, role);

            int rowsAffected = st.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean checkAccoutExist(String username) {
        String query = "select * from [dbo].[Users] where username = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
        }

        return false;
    }

    public boolean checkEmailExist(String email) {
        String query = "select * from [dbo].[Users] where email = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
        }

        return false;
    }

    public boolean updateUserProfile(User user) {
        String sql = "UPDATE Users SET password = ?, name = ?, gender = ?, dob = ?, img = ?, email = ?, phone = ? WHERE username = ?";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getPassword());
            ps.setString(2, user.getName());
            ps.setString(3, user.getGender());
            ps.setDate(4, user.getDob());
            ps.setString(5, user.getImg());
            ps.setString(6, user.getEmail());
            ps.setString(7, user.getPhone());
            ps.setString(8, user.getUsername());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
