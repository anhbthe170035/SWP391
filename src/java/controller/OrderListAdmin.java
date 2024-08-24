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

@WebServlet(name = "OrderListServlet", urlPatterns = {"/orderListAdmin"})
public class OrderListAdmin extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        OrderDAO orderDAO = new OrderDAO();
        
        int page = 1;
        int pageSize = 10;
        if(request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        
        List<Order> orderList = orderDAO.getOrders(page, pageSize);
        int totalOrders = orderDAO.getTotalOrders();
        int totalPages = (int) Math.ceil((double) totalOrders / pageSize);
        
        request.setAttribute("orderList", orderList);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", page);
        
        request.getRequestDispatcher("orderList2.jsp").forward(request, response);
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