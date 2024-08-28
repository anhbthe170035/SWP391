/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import entity.Cart;
import entity.CartDetails;
import entity.ProductDetail;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDAO extends DBContext {

    public CartDAO() {
        super();
    }

    public Cart getCartByUsername(String username) {
        Cart cart = null;
        String sql = "SELECT * FROM dbo.Carts WHERE username = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cart = new Cart();
                cart.setCartid(rs.getInt("cartid"));
                cart.setUsername(rs.getString("username"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cart;
    }

    public List<CartDetails> getCartDetails(int cartid) {
        List<CartDetails> detailsList = new ArrayList<>();
        String sql = "SELECT cd.cdeid, cd.cartid, cd.sku, cd.amount, pd.price, pd.sale, " +
                     "p.name AS productName, pd.color, pd.cpu, pd.ram, pd.storage, pd.monitor " +
                     "FROM dbo.CartDetails cd " +
                     "JOIN dbo.ProductDetails pd ON cd.sku = pd.sku " +
                     "JOIN dbo.Products p ON pd.pid = p.pid " +
                     "WHERE cd.cartid = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, cartid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CartDetails details = new CartDetails();
                details.setCdeid(rs.getInt("cdeid"));
                details.setCartid(rs.getInt("cartid"));
                details.setSku(rs.getString("sku"));
                details.setAmount(rs.getInt("amount"));
                
                // Concatenate all product details into a single productName field
                String productName = rs.getString("productName") + ", " +
                                     rs.getString("color") + ", " +
                                     rs.getString("cpu") + ", " +
                                     rs.getString("ram") + " RAM, " +
                                     rs.getString("storage") + ", " +
                                     rs.getString("monitor");
                details.setProductName(productName);

                details.setDiscount(rs.getInt("sale"));
                details.setPrice(rs.getInt("price"));
                
                detailsList.add(details);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return detailsList;
    }

    public void deleteCartItem(int cdeid) {
        String sql = "DELETE FROM dbo.CartDetails WHERE cdeid = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, cdeid);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    // Method to update quantity of a cart item
    public boolean updateCartItemQuantity(int cdeid, int newAmount) {
        String sql = "UPDATE dbo.CartDetails SET amount = ? WHERE cdeid = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, newAmount);
            ps.setInt(2, cdeid);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Updates the quantity of a specific CartDetail identified by cdeid.
     *
     * @param cdeid The ID of the CartDetail to update.
     * @param quantity The new quantity to set.
     * @return true if the update was successful, false otherwise.
     */
    public boolean updateCartDetailQuantity(int cdeid, int quantity) {
        String sql = "UPDATE dbo.CartDetails SET amount = ? WHERE cdeid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, quantity);
            stmt.setInt(2, cdeid);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public CartDetails getCartDetailById(int cdeid) {
        CartDetails details = null;
        String sql = "SELECT cd.cdeid, cd.cartid, cd.sku, cd.amount, pd.price, pd.sale, p.name AS productName " +
                     "FROM dbo.CartDetails cd " +
                     "JOIN dbo.ProductDetails pd ON cd.sku = pd.sku " +
                     "JOIN dbo.Products p ON pd.pid = p.pid " +
                     "WHERE cd.cdeid = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, cdeid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                details = new CartDetails();
                details.setCdeid(rs.getInt("cdeid"));
                details.setCartid(rs.getInt("cartid"));
                details.setSku(rs.getString("sku"));
                details.setAmount(rs.getInt("amount"));
                details.setPrice(rs.getInt("price"));
                details.setDiscount(rs.getInt("sale"));
    
                // Concatenate all product details into a single productName field
                String productName = rs.getString("productName");
                details.setProductName(productName);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return details;
    }    

    public void createCart(String username) {
        String sql = "INSERT INTO dbo.Carts (username) VALUES (?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void addCartDetail(CartDetails cartDetails) {
        String sql = "INSERT INTO dbo.CartDetails (cartid, sku, amount) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, cartDetails.getCartid());
            ps.setString(2, cartDetails.getSku());
            ps.setInt(3, cartDetails.getAmount());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public CartDetails getCartDetailBySkuAndCartId(String sku, int cartid) {
        CartDetails details = null;
        String sql = "SELECT * FROM dbo.CartDetails WHERE sku = ? AND cartid = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, sku);
            ps.setInt(2, cartid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                details = new CartDetails();
                details.setCdeid(rs.getInt("cdeid"));
                details.setCartid(rs.getInt("cartid"));
                details.setSku(rs.getString("sku"));
                details.setAmount(rs.getInt("amount"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return details;
    }
}
