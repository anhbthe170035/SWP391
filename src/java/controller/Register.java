/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class Register extends HttpServlet {

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
        UserDAO ud = new UserDAO();
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String re_password = request.getParameter("re_password");
        String name = request.getParameter("name");
        int gender = Integer.parseInt(request.getParameter("gender"));
        String email = request.getParameter("email");
        String dob = request.getParameter("date");
        String phone = request.getParameter("phone");
        String img = request.getParameter("img");
        int role = Integer.parseInt(request.getParameter("role"));
        
        if (password.equals(re_password)){
            if (ud.checkAccoutExist(username)){
                request.setAttribute("error", "Username is exist");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
            else if (ud.checkEmailExist(email)){
                request.setAttribute("error", "Email is exist");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
            else {
                ud.signup(username, password, name, gender, email, dob, phone, img, role);
                request.setAttribute("error", "Login to Continue");

                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        }
        
        else{
            request.setAttribute("error", "Password not equal Repeat Password");
            request.getRequestDispatcher("register.jsp").forward(request, response);
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
        processRequest(request, response);
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
