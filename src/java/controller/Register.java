/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.UserDAO;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        String gender = request.getParameter("gender");
        String email = request.getParameter("email");
        String dobString = request.getParameter("date");
        String phone = request.getParameter("phone");
        int role = Integer.parseInt(request.getParameter("role"));
        
        java.sql.Date dob = null;
        if (dobString != null && !dobString.isEmpty()) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date utilDate = sdf.parse(dobString);
                dob = new java.sql.Date(utilDate.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
                request.setAttribute("error", "Invalid date format.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return;
            }
        }
        
        User user = new User();
        user.setUsername(username);
        user.setPassword(hashPassword(password)); // Ensure passwords are hashed
        user.setName(name);
        user.setGender(gender);
        user.setDob(dob);
        user.setEmail(email);
        user.setPhone(phone);
        user.setRole(role);
        
        UserDAO userDAO = new UserDAO();
        
        if (password.equals(re_password)){
            if (userDAO.checkAccoutExist(username)){
                request.setAttribute("error", "Username is exist");
                request.getRequestDispatcher("signup.jsp").forward(request, response);
            }
            else if (userDAO.checkEmailExist(email)){
                request.setAttribute("error", "Email is exist");
                request.getRequestDispatcher("signup.jsp").forward(request, response);
            }
            else {
                userDAO.addUser(user);
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
    
    private String hashPassword(String password) {
        // Implement your password hashing here (e.g., using BCrypt)
        return password; // Placeholder - replace with actual hashing logic
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
