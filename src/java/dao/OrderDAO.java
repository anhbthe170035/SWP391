package dao;

import entity.Order;
import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO extends context.DBContext {

    private static final String SELECT_ORDERS_PAGINATED_SQL
            = "SELECT * FROM Orders ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
    private static final String COUNT_TOTAL_ORDERS_SQL
            = "SELECT COUNT(*) FROM Orders";
    private static final String SELECT_PAYMENT_OPTIONS_SQL
            = "SELECT DISTINCT payment_option FROM Cart"; // Cập nhật theo cấu trúc của bảng Cart

    // Lấy danh sách đơn hàng với phân trang
    public List<Order> getOrders(int pageNumber, int pageSize) {
        List<Order> list = new ArrayList<>();
        int offset = (pageNumber - 1) * pageSize;

        try (PreparedStatement ps = connection.prepareStatement(SELECT_ORDERS_PAGINATED_SQL)) {
            ps.setInt(1, offset);
            ps.setInt(2, pageSize);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Order order = mapResultSetToOrder(rs);
                    list.add(order);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Cân nhắc sử dụng logging
        }
        return list;
    }

    // Lấy tổng số đơn hàng
    public int getTotalOrders() {
        int totalOrders = 0;

        try (PreparedStatement ps = connection.prepareStatement(COUNT_TOTAL_ORDERS_SQL); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                totalOrders = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Cân nhắc sử dụng logging
        }
        return totalOrders;
    }

    // Lấy các tùy chọn thanh toán từ bảng Cart
    public List<String> getPaymentOptions() {
        List<String> paymentOptions = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(SELECT_PAYMENT_OPTIONS_SQL); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                paymentOptions.add(rs.getString("payment_option")); // Thay đổi theo cấu trúc của bảng Cart
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Cân nhắc sử dụng logging
        }

        return paymentOptions;
    }

    // Lấy thông tin đơn hàng theo ID
    public Order getOrderById(int id) {
        Order order = null;
        String query = "SELECT * FROM Orders WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    order = mapResultSetToOrder(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Cân nhắc sử dụng logging
        }

        return order;
    }

    // Chuyển đổi ResultSet thành đối tượng Order
    private Order mapResultSetToOrder(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        java.sql.Date orderDate = rs.getDate("orderdate");
        double totalPrice = rs.getDouble("totalprice");
        String username = rs.getString("username");
        int status = rs.getInt("status");
        java.sql.Date shipDate = rs.getDate("shipdate");
        String fromAddress = rs.getString("fromaddress");
        String toAddress = rs.getString("toaddress");
        String productName = rs.getString("productname"); // Thêm trường productname

        return new Order(id, orderDate, (int) totalPrice, username, status, shipDate, fromAddress, toAddress, productName);
    }
}
