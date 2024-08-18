/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.OrderService;
import entity.Order;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/orderDetails")
public class OrderDetailsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private OrderService orderService = new OrderService(); // Initialize your service here

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        int id = Integer.parseInt(idParam); // Convert to integer

        Order order = orderService.getOrderById(id);

        if (order != null) {
            request.setAttribute("order", order);
        } else {
            request.setAttribute("errorMessage", "Order not found.");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("orderDetails.jsp");
        dispatcher.forward(request, response);
    }
}
