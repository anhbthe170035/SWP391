package controller;

import dao.UserDAO;
import entity.User;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/addUser")
public class AddUserController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve parameters
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String dobString = request.getParameter("dob");
        String img = request.getParameter("img");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        int status = Integer.parseInt(request.getParameter("status"));
        int role = Integer.parseInt(request.getParameter("role"));

        // Convert date of birth
        java.sql.Date dob = null;
        if (dobString != null && !dobString.isEmpty()) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date utilDate = sdf.parse(dobString);
                dob = new java.sql.Date(utilDate.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
                request.setAttribute("error", "Invalid date format.");
                request.getRequestDispatcher("addUserForm.jsp").forward(request, response);
                return;
            }
        }

        // Create user object
        User user = new User();
        user.setUsername(username);
        user.setPassword(hashPassword(password)); // Ensure passwords are hashed
        user.setName(name);
        user.setGender(gender);
        user.setDob(dob);
        user.setImg(img);
        user.setEmail(email);
        user.setPhone(phone);
        user.setStatus(status);
        user.setRole(role);

        // Add user to database
        UserDAO userDAO = new UserDAO();
        boolean success = userDAO.addUser(user);

        // Handle result
        if (success) {
            response.sendRedirect("userList.jsp");
        } else {
            request.setAttribute("error", "Failed to add user. Please try again.");
            request.getRequestDispatcher("addUserForm.jsp").forward(request, response);
        }
    }

    private String hashPassword(String password) {
        // Implement your password hashing here (e.g., using BCrypt)
        return password; // Placeholder - replace with actual hashing logic
    }
}
