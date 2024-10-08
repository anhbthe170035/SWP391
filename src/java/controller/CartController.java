/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CartDAO;
import entity.Cart;
import entity.CartDetails;
import entity.ProductDetail;
import entity.User;
import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @author HMTheBoy154
 */
public class CartController extends HttpServlet {
    CartDAO cartDAO = new CartDAO();

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
        try {
            User u = (User) request.getSession().getAttribute("user");
            String username = u.getUsername();
            Cart cart = cartDAO.getCartByUsername(username);
            List<CartDetails> cartDetails = cartDAO.getCartDetails(cart.getCartid());

            // Get selected item IDs from request
            String[] selectedItemIds = request.getParameterValues("selectedItems");
            List<CartDetails> selectedCartDetails = new ArrayList<>();

            if (selectedItemIds != null) {
                for (String itemId : selectedItemIds) {
                    int cdeid = Integer.parseInt(itemId);
                    for (CartDetails item : cartDetails) {
                        if (item.getCdeid() == cdeid) {
                            selectedCartDetails.add(item);
                        }
                    }
                }
            } else {
                selectedCartDetails = cartDetails; // If no items are selected, show all items
            }

            // Set attributes for JSP
            request.setAttribute("cartItems", selectedCartDetails);
            request.setAttribute("totalPrice", 0); // Set initial values, will be updated by JavaScript
            request.setAttribute("totalDiscountedPrice", 0); // Set initial values, will be updated by JavaScript
            request.setAttribute("finalPrice", 0); // Set initial values, will be updated by JavaScript

        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("cart.jsp").forward(request, response);
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
        String[] selectedItems = request.getParameterValues("selectedItems");
        if (selectedItems != null) {
            for (String cdeidStr : selectedItems) {
                int cdeid = Integer.parseInt(cdeidStr);
                String quantityParam = request.getParameter("quantity_" + cdeid);
                if (quantityParam != null) {
                    int newAmount = Integer.parseInt(quantityParam);
                    if (newAmount <= 0) {
                        newAmount = 1; // Ensure quantity is at least 1
                    }
                    if (!cartDAO.updateCartItemQuantity(cdeid, newAmount)) {
                        System.out.println("Failed to update quantity for item: " + cdeid);
                    }
                }
            }
        }
        // Redirect to the cart page after updating
        response.sendRedirect("cart");
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
