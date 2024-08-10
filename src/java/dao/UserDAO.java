package dao;

import jakarta.servlet.annotation.WebServlet;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entity.User;

@WebServlet(name = "UserlistDAO", urlPatterns = {"/UserlistDAO"})
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
