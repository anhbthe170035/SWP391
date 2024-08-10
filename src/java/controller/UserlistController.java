package controller;

import dao.UserDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import entity.User;

@WebServlet(name = "UserlistController", urlPatterns = {"/userlist", "/searchUser"})
public class UserlistController extends HttpServlet {

    private UserDAO userDao;
    private static final int RECORDS_PER_PAGE = 10;

    @Override
    public void init() throws ServletException {
        // Initialize UserDAO
        userDao = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        List<User> users;
        int page = 1;

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        if ("/userlist".equals(action)) {
            // Handle request to get the list of users with pagination
            int totalRecords = userDao.getUserCount();
            int offset = (page - 1) * RECORDS_PER_PAGE;

            users = userDao.getUsersByPage(offset, RECORDS_PER_PAGE);
            request.setAttribute("userList", users);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", (int) Math.ceil((double) totalRecords / RECORDS_PER_PAGE));

        } else if ("/searchUser".equals(action)) {
            // Handle search request
            String searchName = request.getParameter("name");

            if (searchName != null && !searchName.trim().isEmpty()) {
                users = userDao.searchUserByName(searchName);
                request.setAttribute("userList", users);
            } else {
                request.setAttribute("errorMessage", "Tên tìm kiếm không hợp lệ.");
                // Load users with pagination in case of an invalid search
                int totalRecords = userDao.getUserCount();
                int offset = (page - 1) * RECORDS_PER_PAGE;

                users = userDao.getUsersByPage(offset, RECORDS_PER_PAGE);
                request.setAttribute("userList", users);
                request.setAttribute("currentPage", page);
                request.setAttribute("totalPages", (int) Math.ceil((double) totalRecords / RECORDS_PER_PAGE));
            }
        }

        // Forward to the JSP page with updated results
        RequestDispatcher dispatcher = request.getRequestDispatcher("userlist.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            String username = request.getParameter("username");

            if (username != null && !username.trim().isEmpty()) {
                boolean isDeleted = userDao.deleteUser(username);

                if (isDeleted) {
                    // Set success message
                    request.getSession().setAttribute("successMessage", "User deleted successfully.");
                } else {
                    // Set error message
                    request.getSession().setAttribute("errorMessage", "User deletion failed.");
                }
            } else {
                // Set error message for invalid username
                request.getSession().setAttribute("errorMessage", "Invalid username.");
            }

            // Redirect to the user list page
            response.sendRedirect("userlist");
        } else {
            // Handle other POST requests (e.g., search)
            doGet(request, response);
        }
    }
}
