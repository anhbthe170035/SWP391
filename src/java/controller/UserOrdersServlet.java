package controller;

import dao.OrderDAO;
import entity.Order;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserOrdersServlet", urlPatterns = {"/orderUser"})
public class UserOrdersServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String userName = request.getParameter("userName");
        
        if (userName != null && !userName.isEmpty()) {
            OrderDAO orderDAO = new OrderDAO();
            List<Order> userOrders = orderDAO.getOrdersByUsername(userName);
            
            request.setAttribute("userOrders", userOrders);
            request.setAttribute("userName", userName);
            request.getRequestDispatcher("userOrders.jsp").forward(request, response);
        } else {
            response.sendRedirect("login.jsp"); // Redirect to login page if username is not provided
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}