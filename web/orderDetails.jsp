<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Order Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            color: #333;
        }

        .container {
            width: 80%;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }

        h2 {
            text-align: center;
            color: #4CAF50;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        th, td {
            padding: 12px;
            text-align: left;
            border: 1px solid #ddd;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        .back-link {
            display: block;
            text-align: center;
            margin-top: 20px;
            font-size: 16px;
            color: #4CAF50;
            text-decoration: none;
        }

        .back-link:hover {
            text-decoration: underline;
        }

        .error-message {
            color: red;
            font-weight: bold;
            text-align: center;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Order Details</h2>

        <c:choose>
            <c:when test="${not empty order}">
                <table>
                    <tr>
                        <th>ID:</th>
                        <td>${order.id}</td>
                    </tr>
                    <tr>
                        <th>Order Date:</th>
                        <td>${order.orderdate}</td>
                    </tr>
                    <tr>
                        <th>Total Price:</th>
                        <td>${order.totalprice}</td>
                    </tr>
                    <tr>
                        <th>Username:</th>
                        <td>${order.username}</td>
                    </tr>
                    <tr>
                        <th>Status:</th>
                        <td>${order.status}</td>
                    </tr>
                    <tr>
                        <th>Ship Date:</th>
                        <td>${order.shipdate}</td>
                    </tr>
                    <tr>
                        <th>From Address:</th>
                        <td>${order.fromaddress}</td>
                    </tr>
                    <tr>
                        <th>To Address:</th>
                        <td>${order.toaddress}</td>
                    </tr>
                    <tr>
                        <th>Product Name:</th>
                        <td>${order.productname}</td>
                    </tr>
                </table>
            </c:when>
            <c:otherwise>
                <p class="error-message">${errorMessage}</p>
            </c:otherwise>
        </c:choose>

        <a href="orders" class="back-link">Back to Order List</a>
    </div>
</body>
</html>
