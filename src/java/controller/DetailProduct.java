package controller;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import dao.ProductDAO;
import entity.Product;
import java.util.*;
import entity.ProductDetail;

public class DetailProduct extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        ProductDAO u = new ProductDAO();
        String sn;
        sn = request.getParameter("sku");
        List<ProductDetail> lst = u.getProductDetail(sn);
        request.setAttribute("lst", lst);
        request.getRequestDispatcher("productdetail.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

    }
}

