/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import entity.Category;
import entity.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entity.ProductDetail;

/**
 *
 * @author Admin
 */
public class ProductDAO extends DBContext {

    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        String sql = "select * from [Products]";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                ));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    public List<ProductDetail> getAllProductDetail(){
    List<ProductDetail> list = new ArrayList<>();
    String sql ="""
                select ProductDetails.*, Products.*
                from ProductDetails
                inner join Products on ProductDetails.pid = Products.idpro""";
    try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            String xsku, xpid, xcolor, xcpu, xcpu_specs, xram, xram_max, xgpu, xgpu2, xstorage, xmonitor,xstatus,xname,xbrand,xdescription,ximg;
            int xprice, xsale;
            ProductDetail x;
            while (rs.next()) {
                xsku = rs.getString("sku");
                xpid = rs.getString("pid");
                xcolor = rs.getString("color");
                xcpu = rs.getString("cpu");
                xcpu_specs = rs.getString("cpu_specs");
                xram = rs.getString("ram");
                xram_max = rs.getString("ram_max");
                xgpu = rs.getString("gpu");
                xgpu2 = rs.getString("gpu2");
                xstorage = rs.getString("storage");
                xmonitor = rs.getString("monitor");
                xstatus = rs.getString("status");
                xname = rs.getString("name");
                xbrand = rs.getString("brand");
                xdescription = rs.getString("description");
                ximg = rs.getString("img");
                xprice = rs.getInt("price");
                xsale = rs.getInt("sale");
                x = new ProductDetail(xsku, xpid, xcolor, xcpu, xcpu_specs, xram, xram_max, xgpu, xgpu2, xstorage, xmonitor, xstatus, xname, xbrand, xdescription, ximg, xprice, xsale);
                list.add(x);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    return list;
    }
    public List<ProductDetail> searchProduct(String xxname){
    List<ProductDetail> list = new ArrayList<>();
    String sql ="""
                select ProductDetails.*, Products.* 
                from ProductDetails 
                inner join Products on ProductDetails.pid = Products.idpro WHERE Products.name like ?""";
    try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" +xxname + "%");
            ResultSet rs = ps.executeQuery();
            String xsku, xpid, xcolor, xcpu, xcpu_specs, xram, xram_max, xgpu, xgpu2, xstorage, xmonitor,xstatus,xname,xbrand,xdescription,ximg;
            int xprice, xsale;
            ProductDetail x;
            while (rs.next()) {
                xsku = rs.getString("sku");
                xpid = rs.getString("pid");
                xcolor = rs.getString("color");
                xcpu = rs.getString("cpu");
                xcpu_specs = rs.getString("cpu_specs");
                xram = rs.getString("ram");
                xram_max = rs.getString("ram_max");
                xgpu = rs.getString("gpu");
                xgpu2 = rs.getString("gpu2");
                xstorage = rs.getString("storage");
                xmonitor = rs.getString("monitor");
                xstatus = rs.getString("status");
                xname = rs.getString("name");
                xbrand = rs.getString("brand");
                xdescription = rs.getString("description");
                ximg = rs.getString("img");
                xprice = rs.getInt("price");
                xsale = rs.getInt("sale");
                x = new ProductDetail(xsku, xpid, xcolor, xcpu, xcpu_specs, xram, xram_max, xgpu, xgpu2, xstorage, xmonitor, xstatus, xname, xbrand, xdescription, ximg, xprice, xsale);
                list.add(x);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    return list;
    }
    public List<ProductDetail> GetProductDetail(String xxsku){
    List<ProductDetail> list = new ArrayList<>();
    String sql ="""
                select ProductDetails.*, Products.* 
                from ProductDetails 
                inner join Products on ProductDetails.pid = Products.idpro WHERE ProductDetails.sku = ?""";
    try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, xxsku);
            ResultSet rs = ps.executeQuery();
            String xsku, xpid, xcolor, xcpu, xcpu_specs, xram, xram_max, xgpu, xgpu2, xstorage, xmonitor,xstatus,xname,xbrand,xdescription,ximg;
            int xprice, xsale;
            ProductDetail x;
            while (rs.next()) {
                xsku = rs.getString("sku");
                xpid = rs.getString("pid");
                xcolor = rs.getString("color");
                xcpu = rs.getString("cpu");
                xcpu_specs = rs.getString("cpu_specs");
                xram = rs.getString("ram");
                xram_max = rs.getString("ram_max");
                xgpu = rs.getString("gpu");
                xgpu2 = rs.getString("gpu2");
                xstorage = rs.getString("storage");
                xmonitor = rs.getString("monitor");
                xstatus = rs.getString("status");
                xname = rs.getString("name");
                xbrand = rs.getString("brand");
                xdescription = rs.getString("description");
                ximg = rs.getString("img");
                xprice = rs.getInt("price");
                xsale = rs.getInt("sale");
                x = new ProductDetail(xsku, xpid, xcolor, xcpu, xcpu_specs, xram, xram_max, xgpu, xgpu2, xstorage, xmonitor, xstatus, xname, xbrand, xdescription, ximg, xprice, xsale);
                list.add(x);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    return list;
    }
}
