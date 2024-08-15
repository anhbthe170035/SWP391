<%-- 
    Document   : resetpass_success
    Created on : Aug 15, 2024, 10:42:38 AM
    Author     : hmtheboy154
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Password Changed</title>
    <meta http-equiv="refresh" content="3;url=<%= request.getContextPath() + "/login" %>">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <div class="alert alert-success mt-5">
            <h3>Password changed successfully!</h3>
            <p>Redirecting back to the login screen in a few seconds...</p>
        </div>
    </div>
</body>
</html>
