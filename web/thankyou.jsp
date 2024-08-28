<%-- 
    Document   : thankyou
    Created on : Aug 24, 2024, 9:07:05 AM
    Author     : HMTheBoy154
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="./assets/style.css"/>
    <title>Thank You</title>
    <style>
        .thank-you-container {
            text-align: center;
            margin-top: 50px;
        }
        .thank-you-message {
            font-size: 2.5em;
            font-weight: bold;
            margin-bottom: 10px;
        }
        .thank-you-submessage {
            font-size: 1.2em;
            margin-bottom: 30px;
        }
        .button-container {
            margin-top: 20px;
        }
        .btn-large {
            font-size: 1.5em;
            padding: 10px 20px;
            margin: 10px;
        }
        .btn-home {
            background-color: green;
            color: white;
        }
        .btn-orders {
            background-color: blue;
            color: white;
        }
    </style>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <div class="thank-you-container">
        <div class="thank-you-message">Thank you for your purchase!</div>
        <div class="thank-you-submessage">You can check your order at the Orders page, or you can go back to the Homepage</div>
        <div class="button-container">
            <a href="home" class="btn btn-large btn-home">Home</a>
            <a href="orderUser?userName=${sessionScope.user.username}" class="btn btn-large btn-orders">Orders</a>
        </div>
    </div>
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
</body>
</html>
