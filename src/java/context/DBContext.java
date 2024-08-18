package context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Lớp DBContext để quản lý kết nối cơ sở dữ liệu.
 */
public class DBContext {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=SWP391";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "123";

    // Tải driver JDBC
    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // Xử lý khi không tìm thấy driver
        }
    }

    /**
     * Lấy kết nối tới cơ sở dữ liệu.
     * 
     * @return Đối tượng Connection
     * @throws SQLException nếu xảy ra lỗi khi truy cập cơ sở dữ liệu
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
