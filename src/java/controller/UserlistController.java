package controller;

import dao.UserDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import entity.User;

@WebServlet(name = "UserlistController", urlPatterns = {"/userlist", "/searchUser", "/editUser"})
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

        // Handle pagination
        if (request.getParameter("page") != null) {
            try {
                page = Integer.parseInt(request.getParameter("page"));
            } catch (NumberFormatException e) {
                page = 1; // Default to page 1 on error
            }
        }

        switch (action) {
            case "/userlist":
                // Handle request to get the list of users with pagination
                int totalRecords = userDao.getUserCount();
                int offset = (page - 1) * RECORDS_PER_PAGE;
                users = userDao.getUsersByPage(offset, RECORDS_PER_PAGE);
                request.setAttribute("userList", users);
                request.setAttribute("currentPage", page);
                request.setAttribute("totalPages", (int) Math.ceil((double) totalRecords / RECORDS_PER_PAGE));
                break;

            case "/searchUser":
                // Handle search request
                String searchName = request.getParameter("name");
                if (searchName != null && !searchName.trim().isEmpty()) {
                    users = userDao.searchUserByName(searchName);
                    request.setAttribute("userList", users);
                } else {
                    request.setAttribute("errorMessage", "Tên tìm kiếm không hợp lệ.");
                    // Load users with pagination in case of an invalid search
                    int totalRecordsForPagination = userDao.getUserCount();
                    int offsetForPagination = (page - 1) * RECORDS_PER_PAGE;
                    users = userDao.getUsersByPage(offsetForPagination, RECORDS_PER_PAGE);
                    request.setAttribute("userList", users);
                    request.setAttribute("currentPage", page);
                    request.setAttribute("totalPages", (int) Math.ceil((double) totalRecordsForPagination / RECORDS_PER_PAGE));
                }
                break;

            case "/editUser":
                // Handle request to edit user (display edit form)
                String username = request.getParameter("id");
                if (username != null && !username.trim().isEmpty()) {
                    User user = userDao.getUserByUsername(username);
                    request.setAttribute("user", user);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("editUser.jsp");
                    dispatcher.forward(request, response);
                    return;
                } else {
                    request.setAttribute("errorMessage", "Tên người dùng không hợp lệ.");
                }
                break;

            default:
                request.setAttribute("errorMessage", "Hành động không hợp lệ.");
                break;
        }

        // Forward to the JSP page with updated results
        RequestDispatcher dispatcher = request.getRequestDispatcher("userlist.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "delete":
                // Xử lý xóa người dùng
                String username = request.getParameter("username");
                if (username != null && !username.trim().isEmpty()) {
                    boolean isDeleted = userDao.deleteUser(username);
                    if (isDeleted) {
                        request.getSession().setAttribute("successMessage", "Xóa người dùng thành công.");
                    } else {
                        request.getSession().setAttribute("errorMessage", "Xóa người dùng không thành công.");
                    }
                } else {
                    request.getSession().setAttribute("errorMessage", "Tên người dùng không hợp lệ.");
                }
                response.sendRedirect("userlist");
                break;

            case "edit":
                // Xử lý chỉnh sửa người dùng
                String editUsername = request.getParameter("username");
                String password = request.getParameter("password");
                String name = request.getParameter("name");
                String gender = request.getParameter("gender");
                Date dob = Date.valueOf(request.getParameter("dob"));
                String img = request.getParameter("img");
                String email = request.getParameter("email");
                String phone = request.getParameter("phone");
                int status = Integer.parseInt(request.getParameter("status"));
                int role = Integer.parseInt(request.getParameter("role"));

                User user = new User(editUsername, password, name, gender, dob, img, email, phone, status, role);
                boolean success = userDao.editUser(user);

                if (success) {
                    request.getSession().setAttribute("successMessage", "Chỉnh sửa người dùng thành công.");
                    response.sendRedirect("userlist");
                } else {
                    request.setAttribute("errorMessage", "Chỉnh sửa người dùng không thành công.");
                    request.setAttribute("user", user);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("editUser.jsp");
                    dispatcher.forward(request, response);
                }
                break;

            default:
                // Xử lý các yêu cầu POST khác (ví dụ: tìm kiếm)
                doGet(request, response);
                break;
        }
    }
}
