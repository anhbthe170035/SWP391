<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="./assets/style.css"/>
    <title>Cart Checkout</title>
    <style>
        .container {
            margin-top: 50px;
        }
        .column {
            padding: 20px;
        }
        .product-info {
            margin-bottom: 20px;
        }
        .customer-info textarea {
            width: 100%;
            height: 150px;
        }
    </style>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <div class="container">
        <h2 class="text-center">Checking Out</h2>
        <div class="row">
            <!-- Left Column -->
            <div class="col-md-6 column">
                <h4>Selected Items</h4>
                <table class="table">
                    <thead>
                        <tr>
                            <th>Product Name</th>
                            <th>SKU</th>
                            <th>Quantity</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${cartItems}" var="item">
                            <tr>
                                <td>${item.productName}</td>
                                <td>${item.sku}</td>
                                <td>${item.amount}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <div class="mb-3">
                    <h6>Total Price: <span id="totalPrice">${totalPrice}</span></h6>
                    <h6>Total Discounted Price: <span id="totalDiscountedPrice">${totalDiscountedPrice}</span></h6>
                    <h3>Final Price: <span id="finalPrice">${finalPrice}</span></h3>
                </div>
            </div>

            <!-- Right Column -->
            <div class="col-md-6 column customer-info">
                <h4>Customer Info</h4>
                <form id="checkoutForm" action="confirmcheckout" method="post">
                    <div class="form-group">
                        <label for="customerName">Name</label>
                        <input type="text" class="form-control" id="customerName" name="customerName" value="${customerName}" readonly/>
                    </div>
                    <div class="form-group">
                        <label for="customerPhone">Phone</label>
                        <input type="text" class="form-control" id="customerPhone" name="customerPhone" value="${customerPhone}" readonly/>
                    </div>
                    <div class="form-group">
                        <label for="customerEmail">Email</label>
                        <input type="text" class="form-control" id="customerEmail" name="customerEmail" value="${customerEmail}" readonly/>
                    </div>
                    <div class="form-group">
                        <label for="address">To continue, please put your address here</label>
                        <textarea id="address" name="address" class="form-control"></textarea>
                    </div>

                    <!-- Hidden inputs for selected items -->
                    <c:forEach items="${cartItems}" var="item">
                        <input type="hidden" name="selectedItems" value="${item.cdeid}"/>
                    </c:forEach>

                    <!-- Hidden field to submit final price -->
                    <input type="hidden" id="finalPriceInput" name="finalPrice" value="${finalPrice}"/>

                    <button type="button" class="btn btn-secondary" onclick="window.history.back()">Back</button>
                    <button type="submit" class="btn btn-success">Confirm</button>
                </form>
            </div>
        </div>
    </div>
    <jsp:include page="footer.jsp"/>

    <!-- Include JavaScript files -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
    <script src="./assets/js/confirmcheckoutButton.js"></script>
</body>
</html>
