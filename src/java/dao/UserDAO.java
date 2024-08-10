package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entity.User;

public class UserDAO extends context.DBContext {

    public List<User> getAllUser() {
        List<User> list = new ArrayList<>();
        String query = "SELECT TOP (1000) [username], [password], [name], [gender], [dob], [img], [email], [phone], [status], [role] FROM [SWP391].[dbo].[Users]";
        
        try (PreparedStatement st = connection.prepareStatement(query)) {
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    User p = new User();
                    p.setUsername(rs.getString("username"));
                    p.setPassword(rs.getString("password"));
                    p.setName(rs.getString("name"));
                    p.setGender(rs.getString("gender"));
                    p.setDob(rs.getDate("dob"));
                    p.setImg(rs.getString("img"));
                    p.setEmail(rs.getString("email"));
                    p.setPhone(rs.getString("phone"));
                    p.setStatus(rs.getInt("status"));
                    p.setRole(rs.getInt("role"));
                    
                    list.add(p);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<User> searchUserByName(String name) {
        List<User> list = new ArrayList<>();
        String query = "SELECT [username], [password], [name], [gender], [dob], [img], [email], [phone], [status], [role] " +
                       "FROM [SWP391].[dbo].[Users] " +
                       "WHERE [name] LIKE ?";
        
        try (PreparedStatement st = connection.prepareStatement(query)) {
            // Set parameter with wildcards for partial matching
            st.setString(1, "%" + name + "%");
            
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    User p = new User();
                    p.setUsername(rs.getString("username"));
                    p.setPassword(rs.getString("password"));
                    p.setName(rs.getString("name"));
                    p.setGender(rs.getString("gender"));
                    p.setDob(rs.getDate("dob"));
                    p.setImg(rs.getString("img"));
                    p.setEmail(rs.getString("email"));
                    p.setPhone(rs.getString("phone"));
                    p.setStatus(rs.getInt("status"));
                    p.setRole(rs.getInt("role"));
                    
                    list.add(p);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean deleteUser(String username) {
        String query = "DELETE FROM [SWP391].[dbo].[Users] WHERE [username] = ?";
        
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setString(1, username);
            int rowsAffected = st.executeUpdate();
            
            // Return true if a user was deleted, false otherwise
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<User> getUsersByPage(int offset, int limit) {
        List<User> users = new ArrayList<>();
        String sql = "SELECT [username], [password], [name], [gender], [dob], [img], [email], [phone], [status], [role] " +
                     "FROM [SWP391].[dbo].[Users] ORDER BY [username] OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, offset);
            ps.setInt(2, limit);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
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
                
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public int getUserCount() {
        String sql = "SELECT COUNT(*) FROM [SWP391].[dbo].[Users]";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args) {
        UserDAO udao = new UserDAO();
        
        // Test getAllUser
        List<User> users = udao.getAllUser();
        for (User user : users) {
            System.out.println(user);
        }
        
        // Test searchUserByName
        List<User> searchResults = udao.searchUserByName("John");
        for (User user : searchResults) {
            System.out.println(user);
        }
    }
}
