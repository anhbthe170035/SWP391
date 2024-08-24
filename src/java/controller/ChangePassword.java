package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import dao.UserDAO;
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
public class ChangePassword extends HttpServlet {

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
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null) {
            request.setAttribute("username", user.getUsername());
            request.getRequestDispatcher("/changepass.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
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
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        String currentPass = request.getParameter("currentpass");
        String newPass = request.getParameter("newpass");
        String reNewPass = request.getParameter("renewpass");
        String username = request.getParameter("username");

        UserDAO userDAO = new UserDAO();
        String currentHashedPass = Encryption.MD5Encryption(currentPass);

        // Check if current password is correct
        if (!userDAO.isPasswordCorrect(username, currentHashedPass)) {
            request.setAttribute("errorchangepass", "Current password is incorrect.");
            request.setAttribute("username", username);
            request.getRequestDispatcher("/changepass.jsp").forward(request, response);
            return;
        }

        // Check if new passwords match
        if (newPass.equals(reNewPass)) {
            userDAO.changePass(username, newPass);
            response.sendRedirect(request.getContextPath() + "/resetpass_success.jsp");
        } else {
            request.setAttribute("errorchangepass", "New passwords do not match.");
            request.setAttribute("username", username);
            request.getRequestDispatcher("/changepass.jsp").forward(request, response);
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
