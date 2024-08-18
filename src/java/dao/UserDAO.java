package dao;

import entity.User;
import controller.Encryption;
import context.DBContext;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    // Lấy tất cả người dùng
    public List<User> getAllUser() {
        List<User> list = new ArrayList<>();
        String query = "SELECT TOP (1000) [username], [password], [name], [gender], [dob], [img], [email], [phone], [status], [role] FROM [SWP391].[dbo].[Users]";

        try (Connection connection = DBContext.getConnection();
             PreparedStatement st = connection.prepareStatement(query);
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

    // Tìm kiếm người dùng theo tên
    public List<User> searchUserByName(String name) {
        List<User> list = new ArrayList<>();
        String query = "SELECT [username], [password], [name], [gender], [dob], [img], [email], [phone], [status], [role] "
                + "FROM [SWP391].[dbo].[Users] "
                + "WHERE [name] LIKE ?";

        try (Connection connection = DBContext.getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {

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

    // Xóa người dùng
    public boolean deleteUser(String username) {
        String query = "DELETE FROM [SWP391].[dbo].[Users] WHERE [username] = ?";

        try (Connection connection = DBContext.getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {

            st.setString(1, username);
            int rowsAffected = st.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Lấy người dùng theo trang
    public List<User> getUsersByPage(int offset, int limit) {
        List<User> users = new ArrayList<>();
        String sql = "SELECT [username], [password], [name], [gender], [dob], [img], [email], [phone], [status], [role] "
                + "FROM [SWP391].[dbo].[Users] ORDER BY [username] OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try (Connection connection = DBContext.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

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

    // Lấy tổng số người dùng
    public int getUserCount() {
        String sql = "SELECT COUNT(*) FROM [SWP391].[dbo].[Users]";
        try (Connection connection = DBContext.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Chỉnh sửa người dùng
    public boolean editUser(User user) {
        if (user == null || user.getUsername() == null) {
            throw new IllegalArgumentException("User hoặc username không thể null");
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

        try (Connection connection = DBContext.getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {

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

    // Lấy người dùng theo username
    public User getUserByUsername(String username) {
        User user = null;
        String query = "SELECT [username], [password], [name], [gender], [dob], [img], [email], [phone], [status], [role] "
                + "FROM [SWP391].[dbo].[Users] "
                + "WHERE [username] = ?";

        try (Connection connection = DBContext.getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {

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

    // Phương thức hỗ trợ để ánh xạ ResultSet thành đối tượng User
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

    // Kiểm tra đăng nhập người dùng
    public User checkLogin(String username, String password) {
        String sql = "SELECT * FROM [Users] WHERE [username] = ? AND [password] = ?";
        try (Connection connection = DBContext.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, Encryption.MD5Encryption(password));

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToUser(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Lấy vai trò dựa trên Username
    public int getRoleByUsername(String username) {
        int role = -1; // Giá trị mặc định nếu không tìm thấy người dùng hoặc xảy ra lỗi
        String sql = "SELECT role FROM Users WHERE username = ?";
        try (Connection connection = DBContext.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, username);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    role = rs.getInt("role");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    // Lấy username dựa trên email
    public String getUsernamebyEmail(String email) {
        String username = null;
        String query = "SELECT username FROM Users WHERE email = ?";

        try (Connection connection = DBContext.getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {

            st.setString(1, email);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    username = rs.getString("username");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return username;
    }

    // Phương thức thay đổi mật khẩu
    public boolean changePass(String username, String newPassword) {
        String query = "UPDATE [SWP391].[dbo].[Users] SET [password] = ? WHERE [username] = ?";
        try (Connection connection = DBContext.getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {

            st.setString(1, Encryption.MD5Encryption(newPassword));
            st.setString(2, username);
            int rowsAffected = st.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Thêm người dùng mới
    public boolean addUser(User user) {
        if (user == null || user.getUsername() == null) {
            throw new IllegalArgumentException("User hoặc username không thể null");
        }

        String query = "INSERT INTO [SWP391].[dbo].[Users] "
                + "([username], [password], [name], [gender], [dob], [img], [email], [phone], [status], [role]) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBContext.getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {

            st.setString(1, user.getUsername());
            st.setString(2, Encryption.MD5Encryption(user.getPassword())); // Mã hóa mật khẩu trước khi lưu
            st.setString(3, user.getName());
            st.setString(4, user.getGender());
            st.setDate(5, user.getDob() != null ? new java.sql.Date(user.getDob().getTime()) : null);
            st.setString(6, user.getImg());
            st.setString(7, user.getEmail());
            st.setString(8, user.getPhone());
            st.setInt(9, user.getStatus());
            st.setInt(10, user.getRole());

            int rowsAffected = st.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
