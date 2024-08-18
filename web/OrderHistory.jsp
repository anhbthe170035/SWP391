<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Order History</title>
    <style>
        /* CSS nh? tr??c */
    </style>
    <script>
        function setActiveLink() {
            var currentUrl = window.location.href.split('?')[0]; // Remove query parameters if present
            var links = document.querySelectorAll('.menu a');
            links.forEach(function(link) {
                if (link.href === currentUrl) {
                    link.classList.add('active');
                }
            });
        }

        window.onload = function() {
            setActiveLink();
        };
    </script>
</head>
<body>
    <div class="container">
        <h2>Order History</h2>

        <!-- Menu links -->
        <div class="menu">
            <a href="orderListPageUrl">My Orders</a> <!-- C?p nh?t liên k?t ??n trang Order List -->
            <a id="historyLink" href="#">History</a>
        </div>

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
</body>
</html>
