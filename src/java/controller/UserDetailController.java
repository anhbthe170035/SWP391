package controller;

import dao.UserDAO;
import entity.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UserDetailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        UserDAO dao = new UserDAO();
        User user = dao.getUserByUsername(username);

        if (user == null) {
            response.sendRedirect("userlist"); // Nếu không tìm thấy user thì quay lại danh sách
        } else {
            request.setAttribute("user", user);
            request.getRequestDispatcher("profile.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Handles user profile details retrieval";
    }
}
