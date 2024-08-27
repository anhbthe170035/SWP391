/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CartDAO;
import entity.Cart;
import entity.CartDetails;
import entity.User;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author HMTheBoy154
 */
public class AddCartController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddCartController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddCartController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
        User u = (User) request.getSession().getAttribute("user");
        String username = u.getUsername();
        String sku = request.getParameter("sku");
        String pid = request.getParameter("pid");

        CartDAO cartDAO = new CartDAO();
        Cart cart = cartDAO.getCartByUsername(username);

        if (cart == null) {
            // Create a new cart if it doesn't exist
            cart = new Cart();
            cart.setUsername(username);
            cartDAO.createCart(cart); // You need to implement this method in CartDAO
        }

        int cartId = cart.getCartid();
        CartDetails cartDetails = cartDAO.getCartDetailBySkuAndCartId(sku, cartId);

        if (cartDetails == null) {
            // Add new cart detail if it doesn't exist
            cartDetails = new CartDetails();
            cartDetails.setCartid(cartId);
            cartDetails.setSku(sku);
            cartDetails.setAmount(1); // Initial amount
            cartDAO.addCartDetail(cartDetails); // You need to implement this method in CartDAO
        } else {
            // Update existing cart detail
            cartDetails.setAmount(cartDetails.getAmount() + 1);
            cartDAO.updateCartDetailQuantity(cartDetails.getCdeid(), cartDetails.getAmount());
        }

        // Redirect to home
        HttpSession session = request.getSession();
        session.setAttribute("cartMessage", "Item added to cart successfully!");
        response.sendRedirect("home");
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
        processRequest(request, response);
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
