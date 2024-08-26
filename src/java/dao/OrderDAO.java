package dao;

import context.DBContext;
import entity.Order;
import entity.OrderDetails;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO extends DBContext {

    private static final String SELECT_ORDERS_PAGINATED_SQL
            = "SELECT * FROM Orders ORDER BY orderID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
    private static final String COUNT_TOTAL_ORDERS_SQL
            = "SELECT COUNT(*) FROM Orders";
    private static final String SELECT_PAYMENT_OPTIONS_SQL
            = "SELECT DISTINCT payment_option FROM Cart"; // Cập nhật theo cấu trúc của bảng Cart

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

        try (Connection connection = new DBContext().getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {

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

    public List<Order> getOrdersByUsername(String username) {
        List<Order> orderList = new ArrayList<>();
        String query = "SELECT * FROM Orders WHERE username = ? ORDER BY orderdate DESC";

        try (PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Order order = new Order(
                            rs.getInt("orderid"),
                            rs.getDate("orderdate"),
                            rs.getInt("totalprice"),
                            rs.getString("username"),
                            rs.getInt("status"),
                            rs.getDate("shipdate"),
                            rs.getString("fromaddress"),
                            rs.getString("toaddress")
                    );
                    orderList.add(order);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Consider logging the error or throwing a custom exception
        }

        return orderList;
    }

    // Chuyển đổi ResultSet thành đối tượng Order
    private Order mapResultSetToOrder(ResultSet rs) throws SQLException {
        int id = rs.getInt("orderid");
        java.sql.Date orderDate = rs.getDate("orderdate");
        int totalPrice = rs.getInt("totalprice");
        String username = rs.getString("username");
        int status = rs.getInt("status");
        java.sql.Date shipDate = rs.getDate("shipdate");
        String fromAddress = rs.getString("fromaddress");
        String toAddress = rs.getString("toaddress");
        return new Order(id, orderDate, totalPrice, username, status, shipDate, fromAddress, toAddress);
    }

    public int createOrder(Order order) throws SQLException {
        String sql = "INSERT INTO dbo.Orders (orderdate, totalprice, username, status, shipdate, fromaddress, toaddress) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setDate(1, order.getOrderdate());
            ps.setInt(2, order.getTotalprice());
            ps.setString(3, order.getUsername());
            ps.setInt(4, order.getStatus());
            ps.setDate(5, order.getShipdate());
            ps.setString(6, order.getFromaddress());
            ps.setString(7, order.getToaddress());
            
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1); // Return the generated order ID
                    }
                }
            }
        }
        return -1; // Failure to create order
    }

    public void createOrderDetails(OrderDetails orderDetails) throws SQLException {
        String sql = "INSERT INTO dbo.OrderDetails (orderid, sku, quantity, discount, price) " +
                     "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, orderDetails.getOrderId());
            ps.setString(2, orderDetails.getSku());
            ps.setInt(3, orderDetails.getQuantity());
            ps.setFloat(4, orderDetails.getDiscount());
            ps.setInt(5, orderDetails.getPrice());
            ps.executeUpdate();
        }
    }
}
