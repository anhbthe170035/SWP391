/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.PasswordResetTokenDAO;
import dao.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class ResetPassword extends HttpServlet {

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
        String token = request.getParameter("token");

        if (token != null && !token.isEmpty()) {
            PasswordResetTokenDAO tokenDAO = new PasswordResetTokenDAO();

            // Check if the token is valid
            if (tokenDAO.isTokenValid(token)) {
                // Get the username associated with the token
                String username = tokenDAO.getUsernameByToken(token);

                if (username != null) {
                    // Forward to the reset password page with the username
                    request.setAttribute("username", username);
                    request.setAttribute("token", token);
                    request.getRequestDispatcher("/resetpass.jsp").forward(request, response);
                } else {
                    // Username not found for the token
                    request.setAttribute("message", "Invalid token.");
                    request.getRequestDispatcher("/result.jsp").forward(request, response);
                }
            } else {
                // Token is either expired or already used
                request.setAttribute("message", "Token is invalid or has expired.");
                request.getRequestDispatcher("/result.jsp").forward(request, response);
            }
        } else {
            // Token parameter is missing
            response.sendRedirect(request.getContextPath() + "/login");
        }
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
        String username = request.getParameter("username");
        String newpass = request.getParameter("newpass");
        String repass = request.getParameter("renewpass");
        String token = request.getParameter("token");
        UserDAO userDAO = new UserDAO();

        if (newpass.equalsIgnoreCase(repass)) {
            PasswordResetTokenDAO tokenDAO = new PasswordResetTokenDAO();

            if (tokenDAO.isTokenValid(token)) {
                if (userDAO.changePass(username, newpass)) {
                    tokenDAO.markTokenAsUsed(token); // Mark the token as used
                    response.sendRedirect(request.getContextPath() + "/resetpass_success.jsp");
                } else {
                    request.setAttribute("errorresetpass", "Failed to change password, try again");
                    request.setAttribute("username", username);
                    request.setAttribute("token", token); // Pass token to the JSP
                    request.getRequestDispatcher("/resetpass.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("errorresetpass", "Token is invalid or expired");
                request.getRequestDispatcher("/resetpass.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("errorresetpass", "Passwords do not match, try again");
            request.setAttribute("username", username);
            request.setAttribute("token", token); // Pass token to the JSP
            request.getRequestDispatcher("/resetpass.jsp").forward(request, response);
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
