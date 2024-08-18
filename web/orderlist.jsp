<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Order List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            color: #333;
        }

        h2 {
            text-align: center;
            margin-top: 20px;
            color: #4CAF50;
        }

        .container {
            width: 90%;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }

        table {
            width: 100%;
            margin: 20px 0;
            border-collapse: collapse;
        }

        table, th, td {
            border: 1px solid #ddd;
        }

        th, td {
            padding: 12px;
            text-align: left;
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

        .pagination {
            text-align: center;
            margin: 20px 0;
            display: flex;
            justify-content: center;
            align-items: center;
            gap: 15px;
        }

        .pagination a {
            display: inline-block;
            padding: 10px 15px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }

        .pagination a:hover {
            background-color: #45a049;
        }

        .page-info {
            font-size: 16px;
            margin: 0 10px;
            font-weight: bold;
        }

        .total-price {
            font-size: 18px;
            font-weight: bold;
            margin-top: 20px;
            text-align: right;
        }

        .submit-form {
            text-align: right;
            margin-top: 20px;
        }

        .submit-form button {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }

        .submit-form button:hover {
            background-color: #45a049;
        }
    </style>
    <script>
        function calculateTotal() {
            var total = 0;
            var checkboxes = document.querySelectorAll('input[name="orderId"]:checked');
            checkboxes.forEach(function(checkbox) {
                var price = parseFloat(checkbox.getAttribute('data-price'));
                total += price;
            });
            document.getElementById('totalPrice').innerText = 'Total Price: $' + total.toFixed(2);
        }
    </script>
</head>
<body>
    <div class="container">
        <h2>Order List</h2>

        <table>
            <thead>
                <tr>
                    <th>Select</th>
                    <th>ID</th>
                    <th>Order Date</th>
                    <th>Total Price</th>
                    <th>Username</th>
                    <th>Status</th>
                    <th>Ship Date</th>
                    <th>From Address</th>
                    <th>To Address</th>
                    <th>Product Name</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="order" items="${orderList}">
                    <tr>
                        <td><input type="checkbox" name="orderId" data-price="${order.totalprice}" onchange="calculateTotal()"></td>
                        <td>${order.id}</td>
                        <td>${order.orderdate}</td>
                        <td>${order.totalprice}</td>
                        <td>${order.username}</td>
                        <td>${order.status}</td>
                        <td>${order.shipdate}</td>
                        <td>${order.fromaddress}</td>
                        <td>${order.toaddress}</td>
                        <td>${order.productname}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="totalPrice" class="total-price">Total Price: $0.00</div>

        <div class="submit-form">
            <form action="processPayment" method="post">
                <input type="hidden" name="orderIds" id="orderIds">
                <button type="submit" onclick="setOrderIds()">Submit</button>
            </form>
        </div>

        <div class="pagination">
            <c:if test="${currentPage > 1}">
                <a href="?page=${currentPage - 1}">Previous</a>
            </c:if>
            <span class="page-info">Page ${currentPage} of ${totalPages}</span>
            <c:if test="${currentPage < totalPages}">
                <a href="?page=${currentPage + 1}">Next</a>
            </c:if>
        </div>
    </div>

    <script>
        function setOrderIds() {
            var selectedIds = [];
            var checkboxes = document.querySelectorAll('input[name="orderId"]:checked');
            checkboxes.forEach(function(checkbox) {
                selectedIds.push(checkbox.closest('tr').children[1].textContent.trim());
            });
            document.getElementById('orderIds').value = selectedIds.join(',');
        }
    </script>
</body>
</html>
