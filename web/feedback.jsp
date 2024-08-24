<%-- 
    Document   : feedback
    Created on : Aug 18, 2024, 4:07:00 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Feedback Form</title>
    </head>
    <body>
        <div class="form-container">
            <h1>Send Feedback</h1>
            <h2>Submit your Feedback</h2>
            <form action="feedback" method="post">
                <label for="orderid">Order ID:</label><br>
                <input type="text" id="orderid" name="orderid" required><br><br>

                <label for="sku">SKU:</label><br>
                <input type="text" id="sku" name="sku" required><br><br>

                <label for="star">Star Rating (1-5):</label><br>
                <select id="star" name="star" required>
                    <option value="1">1 Star</option>
                    <option value="2">2 Stars</option>
                    <option value="3">3 Stars</option>
                    <option value="4">4 Stars</option>
                    <option value="5">5 Stars</option>
                </select><br><br>

                <label for="feedback">Your Feedback:</label><br>
                <textarea id="feedback" name="feedback" rows="4" cols="50" required></textarea><br><br>

                <input type="submit" value="Submit">
            </form>
        </div>
    </body>
</html>
