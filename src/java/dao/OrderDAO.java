package dao;

import entity.Order;
import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    private static final String SELECT_ORDERS_PAGINATED_SQL = "SELECT * FROM Orders ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
    private static final String COUNT_TOTAL_ORDERS_SQL = "SELECT COUNT(*) FROM Orders";
    private static final String SELECT_PAYMENT_OPTIONS_SQL = "SELECT DISTINCT payment_option FROM Cart"; // Cập nhật theo cấu trúc của bảng Cart

    // Lấy danh sách đơn hàng với phân trang
    public List<Order> getOrders(int pageNumber, int pageSize) {
        List<Order> list = new ArrayList<>();
        int offset = (pageNumber - 1) * pageSize;

        try (Connection connection = DBContext.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_ORDERS_PAGINATED_SQL)) {
            ps.setInt(1, offset);
            ps.setInt(2, pageSize);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Order order = mapResultSetToOrder(rs);
                    list.add(order);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý lỗi
        }
        return list;
    }

    // Lấy tổng số đơn hàng
    public int getTotalOrders() {
        int totalOrders = 0;

        try (Connection connection = DBContext.getConnection();
             PreparedStatement ps = connection.prepareStatement(COUNT_TOTAL_ORDERS_SQL);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                totalOrders = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý lỗi
        }
        return totalOrders;
    }

    // Lấy các tùy chọn thanh toán từ bảng Cart
    public List<String> getPaymentOptions() {
        List<String> paymentOptions = new ArrayList<>();

        try (Connection connection = DBContext.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_PAYMENT_OPTIONS_SQL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                paymentOptions.add(rs.getString("payment_option")); // Thay đổi theo cấu trúc của bảng Cart
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý lỗi
        }

        return paymentOptions;
    }

    private Order mapResultSetToOrder(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        java.sql.Date orderdate = rs.getDate("orderdate");
        int totalprice = rs.getInt("totalprice");
        String username = rs.getString("username");
        int status = rs.getInt("status");
        java.sql.Date shipdate = rs.getDate("shipdate");
        String fromaddress = rs.getString("fromaddress");
        String toaddress = rs.getString("toaddress");
        String productname = rs.getString("productname"); // Thêm trường productname

        return new Order(id, orderdate, totalprice, username, status, shipdate, fromaddress, toaddress, productname);
    }
}
