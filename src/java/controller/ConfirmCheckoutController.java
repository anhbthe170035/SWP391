/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CartDAO;
import dao.OrderDAO;
import entity.Cart;
import entity.CartDetails;
import entity.Order;
import entity.OrderDetails;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Calendar;
import java.util.List;
import java.sql.Date;
import java.sql.SQLException;

/**
 *
 * @author HMTheBoy154
 */
public class ConfirmCheckoutController extends HttpServlet {

    private final CartDAO cartDAO = new CartDAO();
    private final OrderDAO orderDAO = new OrderDAO();

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("home").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            User user = (User) request.getSession().getAttribute("user");
            String username = user.getUsername();

            // Retrieve the address from the request
            String address = request.getParameter("address");

            // Create new Order entity
            Order order = new Order();
            order.setOrderdate(new Date(Calendar.getInstance().getTimeInMillis())); // Current date
            order.setTotalprice(Integer.parseInt(request.getParameter("finalPrice")));
            order.setUsername(username);
            order.setStatus(0); // Assuming 0 means 'Pending'
            order.setShipdate(null);
            order.setFromaddress(address);
            order.setToaddress(null);

            // Save the order and get the generated order ID
            int orderId = orderDAO.createOrder(order);

            // Add each chosen product to OrderDetails
            String[] selectedItemIds = request.getParameterValues("selectedItems");
            if (selectedItemIds != null) {
                System.out.println("selectedItems is not null");
                for (String itemId : selectedItemIds) {
                    int cdeid = Integer.parseInt(itemId);
                    CartDetails item = cartDAO.getCartDetailById(cdeid);

                    OrderDetails orderDetails = new OrderDetails();
                    orderDetails.setOrderId(orderId);
                    orderDetails.setSku(item.getSku());
                    orderDetails.setQuantity(item.getAmount());
                    orderDetails.setDiscount(item.getDiscount());
                    orderDetails.setPrice(item.getPrice());

                    // Save the order details
                    orderDAO.createOrderDetails(orderDetails);

                    // Remove item from cart
                    cartDAO.deleteCartItem(cdeid);
                }
            } else {
                System.out.println("selectedItems is null");
            }

            // Redirect to the thank you page
            response.sendRedirect("thankyou.jsp");
        } catch (IOException | NumberFormatException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
