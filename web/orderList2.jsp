<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Order List</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 20px;
                display: flex;
                justify-content: center;
                align-items: center;
                min-height: 100vh;
                background-color: #f4f4f4;
            }
            .container {
                width: 95%; /* Increased width to accommodate new column */
                background: white;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0,0,0,0.1);
            }
            h2 {
                text-align: center;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }
            th, td {
                border: 1px solid #ddd;
                padding: 12px;
                text-align: left;
            }
            th {
                background-color: #f2f2f2;
            }
            tr:nth-child(even) {
                background-color: #f9f9f9;
            }
            .pagination {
                text-align: center;
                margin-top: 20px;
            }
            .pagination a {
                color: black;
                padding: 8px 16px;
                text-decoration: none;
                transition: background-color .3s;
                border: 1px solid #ddd;
                margin: 0 4px;
            }
            .pagination a.active {
                background-color: #4CAF50;
                color: white;
                border: 1px solid #4CAF50;
            }
            .pagination a:hover:not(.active) {
                background-color: #ddd;
            }
            .view-details {
                background-color: #4CAF50;
                color: white;
                padding: 6px 10px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                border-radius: 4px;
                transition: background-color 0.3s;
            }
            .view-details:hover {
                background-color: #45a049;
            }
            .back-button {
                position: fixed;
                top: 20px;
                right: 20px;
                z-index: 1000;
            }
            .back-button a {
                padding: 8px 16px;
                font-size: 16px;
                border: none;
                border-radius: 4px;
                background-color: #2196F3;
                color: white;
                text-decoration: none;
            }
            .back-button a:hover {
                background-color: #0b79d0;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2>Order List</h2>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Order Date</th>
                        <th>Total Price</th>
                        <th>Username</th>
                        <th>Status</th>
                        <th>Ship Date</th>
                        <th>From Address</th>
                        <th>To Address</th>
                        <th>Action</th> <!-- New column for View Details -->
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="order" items="${orderList}">
                        <tr>
                            <td>${order.id}</td>
                            <td>${order.orderdate}</td>
                            <td>${order.totalprice}</td>
                            <td>${order.username}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${order.status == 0}">Pending</c:when>
                                    <c:when test="${order.status == 1}">Processing</c:when>
                                    <c:when test="${order.status == 2}">Shipped</c:when>
                                    <c:when test="${order.status == 3}">Delivered</c:when>
                                    <c:otherwise>Unknown</c:otherwise>
                                </c:choose>
                            </td>
                            <td>${order.shipdate}</td>
                            <td>${order.fromaddress}</td>
                            <td>${order.toaddress}</td>
                            <td>
                                <a href="viewOrderDetails?orderId=${order.id}" class="view-details">View Details</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div class="pagination">
                <c:forEach begin="1" end="${totalPages}" var="i">
                    <a href="orderListAdmin?page=${i}" class="${i == currentPage ? 'active' : ''}">${i}</a>
                </c:forEach>
                <div class="back-button">
                    <a href="home">Back to Homepage</a>
                </div>

            </div>
        </div>
    </body>
</html>