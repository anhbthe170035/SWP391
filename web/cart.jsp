<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="./assets/style.css"/>
    <link rel="stylesheet" href="./assets/fixedreturnbutton.css"/>
    <title>Cart</title>
    <script src="./assets/js/updatecart.js"></script>
    <script src="./assets/js/updateprice.js"></script>
    <style>

    </style>
</head>
<body>
    <jsp:include page="header.jsp"/>

    <div class="container mt-5">
        <h2>Your Cart</h2>
        <form action="cartcheckout" method="post">
            <table class="table">
                <thead>
                    <tr>
                        <th>Select</th>
                        <th>Product</th>
                        <th>SKU</th>
                        <th>Quantity</th>
                        <th>Discount</th>
                        <th>Price</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${cartItems}" var="item">
                        <tr>
                            <td><input type="checkbox" name="selectedItems" value="${item.cdeid}" /></td>
                            <td>${item.productName}</td>
                            <td>${item.sku}</td>
                            <td><input type="number" class="quantity-input" data-cdeid="${item.cdeid}" value="${item.amount}" min="1"/></td>
                            <td class="discount">${item.discount}%</td>
                            <td class="price">${item.price}</td>
                            <td><a href="deleteItem?cdeid=${item.cdeid}" class="btn btn-danger">Delete</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            
            <!-- Display Totals and Check Out Button -->
            <div class="row">
                <div class="col-md-12 text-right">
                    <div class="mb-3">
                        <h6>Total Price: <span id="totalPrice">${totalPrice}</span></h6>
                        <h6>Total Discounted Price: <span id="totalDiscountedPrice">${totalDiscountedPrice}</span></h6>
                        <h3>Final Price: <span id="finalPrice">${finalPrice}</span></h3>
                    </div>
                    <button type="submit" class="btn btn-success">Check Out</button>
                </div>
            </div>
        </form>
    </div>
    <br></br>
    <a href="home" class="btn btn-secondary fixed-bottom-btn">Return to the store page</a>
    <jsp:include page="footer.jsp"/>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
    <script>
        // Print debug message to console
        var debugMessage = '${debugMessage}';
        console.log("DEBUG:", debugMessage);
    </script>
</body>
</html>
