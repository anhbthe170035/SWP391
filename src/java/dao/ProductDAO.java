/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import entity.Category;
import entity.Product;
import entity.ProductDetail;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ProductDAO extends DBContext {

    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM dbo.Products";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(
                        rs.getString("pid"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("cid"),
                        rs.getInt("brandid")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<ProductDetail> getAllProductDetail() {
        List<ProductDetail> list = new ArrayList<>();
        String sql = """
            SELECT ProductDetails.*, Products.*, Brand.name AS brandname
            FROM dbo.ProductDetails
            INNER JOIN dbo.Products ON ProductDetails.pid = Products.pid
            INNER JOIN dbo.Brand ON Products.brandid = Brand.brandid
        """;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ProductDetail(
                        rs.getString("sku"),
                        rs.getString("pid"),
                        rs.getString("color"),
                        rs.getString("cpu"),
                        rs.getString("cpu_specs"),
                        rs.getString("ram"),
                        rs.getString("ram_max"),
                        rs.getString("gpu"),
                        rs.getString("storage"),
                        rs.getString("monitor"),
                        rs.getString("status"),
                        rs.getString("name"),
                        rs.getString("brandname"),
                        rs.getString("description"),
                        rs.getString("img"),
                        rs.getInt("price"),
                        rs.getInt("sale"),
                        rs.getBoolean("enable")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<ProductDetail> searchProduct(String xxname) {
        List<ProductDetail> list = new ArrayList<>();
        String sql = """
            SELECT ProductDetails.*, Products.*, Brand.name AS brandname
            FROM dbo.ProductDetails
            INNER JOIN dbo.Products ON ProductDetails.pid = Products.pid
            INNER JOIN dbo.Brand ON Products.brandid = Brand.brandid
            WHERE Products.name LIKE ?
        """;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + xxname + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ProductDetail(
                        rs.getString("sku"),
                        rs.getString("pid"),
                        rs.getString("color"),
                        rs.getString("cpu"),
                        rs.getString("cpu_specs"),
                        rs.getString("ram"),
                        rs.getString("ram_max"),
                        rs.getString("gpu"),
                        rs.getString("storage"),
                        rs.getString("monitor"),
                        rs.getString("status"),
                        rs.getString("name"),
                        rs.getString("brandname"),
                        rs.getString("description"),
                        rs.getString("img"),
                        rs.getInt("price"),
                        rs.getInt("sale"),
                        rs.getBoolean("enable")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<ProductDetail> getProductDetail(String xxsku) {
        List<ProductDetail> list = new ArrayList<>();
        String sql = """
            SELECT ProductDetails.*, Products.*, Brand.name AS brandname
            FROM dbo.ProductDetails
            INNER JOIN dbo.Products ON ProductDetails.pid = Products.pid
            INNER JOIN dbo.Brand ON Products.brandid = Brand.brandid
            WHERE ProductDetails.sku = ?
        """;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, xxsku);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ProductDetail(
                        rs.getString("sku"),
                        rs.getString("pid"),
                        rs.getString("color"),
                        rs.getString("cpu"),
                        rs.getString("cpu_specs"),
                        rs.getString("ram"),
                        rs.getString("ram_max"),
                        rs.getString("gpu"),
                        rs.getString("storage"),
                        rs.getString("monitor"),
                        rs.getString("status"),
                        rs.getString("name"),
                        rs.getString("brandname"),
                        rs.getString("description"),
                        rs.getString("img"),
                        rs.getInt("price"),
                        rs.getInt("sale"),
                        rs.getBoolean("enable")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
 
    public List<String> getSKUsByProductId(String pid) {
        List<String> skus = new ArrayList<>();
        String sql = "SELECT sku FROM dbo.ProductDetails WHERE pid = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, pid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                skus.add(rs.getString("sku"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return skus;
    }
    
}
