package dao;

import entity.OrderDetails;
import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailsDAO extends DBContext {

    public OrderDetailsDAO() {
    }
    

    public List<OrderDetails> getOrderDetailsByOrderId(int orderId) {
        List<OrderDetails> orderDetailsList = new ArrayList<>();
        String query = "SELECT * FROM OrderDetails WHERE orderid = ?";

        try ( PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, orderId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    OrderDetails orderDetails = new OrderDetails(
                            rs.getInt("orderid"),
                            rs.getString("sku"),
                            rs.getInt("quantity"),
                            rs.getFloat("discount"),
                            rs.getInt("price")
                    );
                    orderDetailsList.add(orderDetails);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Consider logging the error or throwing a custom exception
        }

        return orderDetailsList;
    }

    public boolean addOrderDetail(OrderDetails orderDetail) {
        String query = "INSERT INTO OrderDetails (orderid, sku, quantity, discount, price) VALUES (?, ?, ?, ?, ?)";
        try ( PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, orderDetail.getOrderId());
            ps.setString(2, orderDetail.getSku());
            ps.setInt(3, orderDetail.getQuantity());
            ps.setFloat(4, orderDetail.getDiscount());
            ps.setInt(5, orderDetail.getPrice());

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            // Consider logging the error or throwing a custom exception
            return false;
        }
    }

    public boolean updateOrderDetail(OrderDetails orderDetail) {
        String query = "UPDATE OrderDetails SET quantity = ?, discount = ?, price = ? WHERE orderid = ? AND sku = ?";

        try ( PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, orderDetail.getQuantity());
            ps.setFloat(2, orderDetail.getDiscount());
            ps.setInt(3, orderDetail.getPrice());
            ps.setInt(4, orderDetail.getOrderId());
            ps.setString(5, orderDetail.getSku());

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            // Consider logging the error or throwing a custom exception
            return false;
        }
    }

    public boolean deleteOrderDetail(int orderId, String sku) {
        String query = "DELETE FROM OrderDetails WHERE orderid = ? AND sku = ?";

        try ( PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, orderId);
            ps.setString(2, sku);

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            // Consider logging the error or throwing a custom exception
            return false;
        }
    }
}
