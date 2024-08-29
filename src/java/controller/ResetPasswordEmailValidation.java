/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.PasswordResetTokenDAO;
import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author HMTheBoy154
 */
public class ResetPasswordEmailValidation extends HttpServlet {

    private static final int TOKEN_LENGTH = 8;

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
            out.println("<title>Servlet ResetPasswordEmailValidation</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ResetPasswordEmailValidation at " + request.getContextPath() + "</h1>");
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
        String email = request.getParameter("email");
        String message = "";
        UserDAO userDAO = new UserDAO();
        PasswordResetTokenDAO tokenDAO = new PasswordResetTokenDAO();

        // Check if the email exists
        String username = userDAO.getUsernamebyEmail(email);

        if (username != null) {
            // Generate a token
            SecureRandom random = new SecureRandom();
            byte[] tokenBytes = new byte[TOKEN_LENGTH];
            random.nextBytes(tokenBytes);
            String token = Encryption.MD5Encryption(username + Base64.getEncoder().encodeToString(tokenBytes));

            // Store the token in the database
            Timestamp expireTime = new Timestamp(System.currentTimeMillis() + 10 * 60 * 1000); // 10 minutes from now
            tokenDAO.saveToken(username, token, expireTime);

            // Generate the reset link
            String resetPasswordURL = request.getContextPath() + "/resetpwd?token=" + token;

            // Send the email
            boolean emailSent = sendEmail(email, resetPasswordURL);

            if (emailSent) {
                message = "Password reset instructions have been sent to your email address.";
            } else {
                message = "Failed to send password reset instructions. Please try again later.";
            }
        } else {
            message = "Sorry, we can't find your account.";
        }

        // Set the message as a request attribute
        request.setAttribute("message", message);

        // Forward to the result JSP page
        request.getRequestDispatcher("/result.jsp").forward(request, response);
    }

    private boolean sendEmail(String recipient, String resetPasswordURL) {
        final String username = "youremail@address.com";
        final String password = "thisisforthepassword";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject("Password Reset Request");
            message.setText("To reset your password, please click the link below:\n" + resetPasswordURL);

            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
