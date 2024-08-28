/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CartDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import entity.Cart;
import entity.CartDetails;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author HMTheBoy154
 */
public class CartCheckoutController extends HttpServlet {

    private CartDAO cartDAO = new CartDAO();

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
        //Return to home if directly access the page
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
            User u = (User) request.getSession().getAttribute("user");
            String username = u.getUsername();
            Cart cart = cartDAO.getCartByUsername(username);
            List<CartDetails> cartDetails = cartDAO.getCartDetails(cart.getCartid());

            // Get selected item IDs from hidden inputs
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
                request.getRequestDispatcher("home").forward(request, response);
                return;
            }

            // Set attributes for JSP
            request.setAttribute("cartItems", selectedCartDetails);

            // Calculate totals
            int totalPrice = 0;
            int totalDiscountedPrice = 0;
            int finalPrice = 0;
            for (CartDetails item : selectedCartDetails) {
                int quantity = item.getAmount();
                int price = item.getPrice();
                int discount = item.getDiscount();
                
                // The below is actually these code to calculate the final price
                // but due to different rounding compare the cart price, we switch to BigDecimal & RoundingMode
                // int itemPrice = price * quantity;
                // int discountAmount = (price * (100 - discount)) / 100;
                // int itemDiscountedPrice = (itemPrice - discountAmount * quantity);
                BigDecimal priceBD = new BigDecimal(price);
                BigDecimal quantityBD = new BigDecimal(quantity);
                BigDecimal discountBD = new BigDecimal(discount);                
                BigDecimal itemPrice = priceBD.multiply(quantityBD).setScale(0, RoundingMode.HALF_UP);
                BigDecimal discountAmount = priceBD.multiply(BigDecimal.valueOf(100 - discount)).divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);
                BigDecimal itemDiscountedPrice = itemPrice.subtract(discountAmount.multiply(quantityBD)).setScale(0, RoundingMode.HALF_UP);

                totalPrice += itemPrice.intValue();
                totalDiscountedPrice += itemDiscountedPrice.intValue();
            }
            finalPrice = totalPrice - totalDiscountedPrice;

            request.setAttribute("totalPrice", totalPrice);
            request.setAttribute("totalDiscountedPrice", totalDiscountedPrice);
            request.setAttribute("finalPrice", finalPrice);

            // Set customer info
            request.setAttribute("customerName", u.getName());
            request.setAttribute("customerPhone", u.getPhone());
            request.setAttribute("customerEmail", u.getEmail());

            // Forward to checkout JSP
            request.getRequestDispatcher("cartcheckout.jsp").forward(request, response);
        } catch (Exception e) {
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
