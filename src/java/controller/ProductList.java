package controller;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import dao.ProductDAO;
import entity.Product;
import java.util.*;

public class ProductList extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        ProductDAO u = new ProductDAO();
        List<Product> lst = u.getAllProduct();
        request.setAttribute("lst", lst);
        request.getRequestDispatcher("productlist.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

    }
}

