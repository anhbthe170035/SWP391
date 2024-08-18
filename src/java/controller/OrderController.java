package controller;

import dao.OrderDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import entity.Order;

@WebServlet("/orders") // Đường dẫn để truy cập vào controller này
public class OrderController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private OrderDAO orderDAO;

    public void init() {
        orderDAO = new OrderDAO(); // Khởi tạo đối tượng OrderDAO
    }

    // Xử lý yêu cầu GET từ người dùng
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy thông tin phân trang từ yêu cầu HTTP, mặc định là trang 1 và kích thước trang 10
        int pageNumber = 1;
        int pageSize = 10;

        String pageParam = request.getParameter("page");
        if (pageParam != null && !pageParam.isEmpty()) {
            try {
                pageNumber = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                pageNumber = 1; // Nếu không thể chuyển đổi, mặc định là trang 1
            }
        }

        // Lấy danh sách đơn hàng với phân trang
        List<Order> orderList = orderDAO.getOrders(pageNumber, pageSize);

        // Lấy tổng số đơn hàng để tính tổng số trang
        int totalOrders = orderDAO.getTotalOrders();
        int totalPages = (int) Math.ceil((double) totalOrders / pageSize);

        // Lấy các tùy chọn thanh toán
        List<String> paymentOptions = orderDAO.getPaymentOptions();

        // Đặt danh sách đơn hàng, thông tin phân trang và các tùy chọn thanh toán vào request attribute
        request.setAttribute("orderList", orderList);
        request.setAttribute("currentPage", pageNumber);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("paymentOptions", paymentOptions);

        // Chuyển tiếp yêu cầu và phản hồi tới trang hiển thị danh sách đơn hàng (JSP)
        RequestDispatcher dispatcher = request.getRequestDispatcher("orderlist.jsp");
        dispatcher.forward(request, response);
        
    }

    // Xử lý yêu cầu POST từ người dùng (nếu cần)
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response); // Mặc định sẽ gọi lại phương thức doGet nếu có yêu cầu POST
    }
}
