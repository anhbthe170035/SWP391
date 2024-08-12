<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>User Profile</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            display: flex;
            justify-content: center;
            align-items: center;
            background-color: #f4f4f4;
            height: 100vh;
        }
        .profile-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            max-width: 400px;
            width: 100%;
        }
        h1 {
            text-align: center;
            margin-bottom: 20px;
        }
        p {
            margin: 10px 0;
            font-size: 16px;
        }
        p strong {
            display: inline-block;
            width: 120px;
        }
    </style>
</head>
<body>
    <div class="profile-container">
        <h1>User Profile</h1>
        <p><strong>Username:</strong> ${user.username}</p>
        <p><strong>Name:</strong> ${user.name}</p>
        <p><strong>Email:</strong> ${user.email}</p>
        <p><strong>Gender:</strong> 
            <c:choose>
                <c:when test="${user.gender == '00'}">Female</c:when>
                <c:otherwise>Male</c:otherwise>
            </c:choose>
        </p>
        <p><strong>Date of Birth:</strong> ${user.dob}</p>
        <p><strong>Phone:</strong> ${user.phone}</p>
        <p><strong>Status:</strong> 
            <c:choose>
                <c:when test="${user.status == 1}">Active</c:when>
                <c:otherwise>Inactive</c:otherwise>
            </c:choose>
        </p>
        <p><strong>Role:</strong> 
            <c:choose>
                <c:when test="${user.role == 0}">Admin</c:when>
                <c:when test="${user.role == 1}">Employee</c:when>
                <c:otherwise>Customer</c:otherwise>
            </c:choose>
        </p>
    </div>
</body>
</html>
