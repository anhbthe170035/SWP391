/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.UserDAO;
import dao.CustomerDAO;
import dao.StaffDAO;
import entity.User;
import entity.Customer;
import entity.Staff;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
@WebServlet(name = "Login", urlPatterns = {"/login"})
public class Login extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Login</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Login at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("login.jsp").forward(request, response);
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
        String username = request.getParameter("email");
        String password = request.getParameter("password");
        UserDAO ud = new UserDAO();
        CustomerDAO cd = new CustomerDAO();
        PrintWriter out = response.getWriter();
        User u = ud.checkLogin(username, password);
        HttpSession session = request.getSession();
        Cookie user = new Cookie("username", username);
        Cookie pass = new Cookie("cpass", password);
        if (u == null) {
            String erro = "Email and passworld is not correct";
            request.setAttribute("erro", erro);
            user.setMaxAge(0);
            pass.setMaxAge(0);
            response.addCookie(user);
            response.addCookie(pass);
            request.getRequestDispatcher("login.jsp").forward(request, response);

        } else {
            response.addCookie(user);
            response.addCookie(pass);
            switch (u.getRole()) {
                case 1: // Mentee
                    Customer customer = cd.getCustomerByName(u.getName());
                    session.setAttribute("customer", customer);
                    response.sendRedirect("home");
                    break;
                case 2: // Mentor 
                    StaffDAO sd = new StaffDAO();
                    Staff staff = sd.getStaffByName(u.getName());
                    int status = u.getStatus();
                    if ("Block".equals(status)) {
                        String error = "The Account is blocked!";
                        request.setAttribute("error", error);
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    } else {
                        session.setAttribute("staff", staff);
                        response.sendRedirect("HomeStaff");
                    }
                    break;

                case 0: // Admin
                    out.print("Admin");
                    break;
                default: // Manager or other roles
                    out.print("Manager");
                    break;
            }
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
