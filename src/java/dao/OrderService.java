/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package dao;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.OrderDAO;
import entity.Order;

/**
 *
 * @author Nhat Anh
 */
@WebServlet(name = "OrderService", urlPatterns = {"/OrderService"})
public class OrderService extends HttpServlet {

    private OrderDAO orderDAO = new OrderDAO(); // Initialize your DAO here

    public Order getOrderById(int id) {
        return orderDAO.getOrderById(id);
    }
}
