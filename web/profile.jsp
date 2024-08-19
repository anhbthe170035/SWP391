<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>User Profile</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            background-color: #e9ecef;
            height: 100vh;
        }

        .profile-container {
            background-color: #ffffff;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            max-width: 900px;
            width: 100%;
            display: flex;
            gap: 30px;
            align-items: start;
            border: 1px solid #dee2e6;
        }

        .profile-image {
            width: 180px;
            height: 180px;
            object-fit: cover;
            border-radius: 50%;
            border: 4px solid #f1f1f1;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }

        .profile-details {
            flex: 1;
            border-left: 2px solid #dee2e6;
            padding-left: 30px;
            display: flex;
            flex-direction: column;
        }

        .profile-details h1 {
            margin-bottom: 20px;
            font-size: 24px;
            color: #343a40;
        }

        .profile-detail {
            display: flex;
            justify-content: space-between;
            margin: 10px 0;
            font-size: 16px;
            color: #495057;
            padding: 5px 0;
            border-bottom: 1px solid #dee2e6;
        }

        .profile-detail strong {
            text-align: left;
            width: 150px;
            color: #212529;
        }

        .profile-detail span {
            text-align: right;
            flex-grow: 1;
            color: #495057;
        }

        .profile-detail:last-child {
            border-bottom: none;
        }

        .back-button {
            margin-top: 20px;
            padding: 10px 20px;
            font-size: 16px;
            border: none;
            border-radius: 4px;
            background-color: #007bff;
            color: white;
            cursor: pointer;
            text-decoration: none;
            display: inline-block;
            text-align: center;
            transition: background-color 0.3s;
        }

        .back-button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="profile-container">
        <!-- Profile Image -->
        <img class="profile-image" src="${user.img}" alt="Profile Image" />

        <!-- Profile Details -->
        <div class="profile-details">
            <h1>User Profile</h1>

            <div class="profile-detail">
                <strong>Username:</strong>
                <span>${user.username}</span>
            </div>
            <div class="profile-detail">
                <strong>Name:</strong>
                <span>${user.name}</span>
            </div>
            <div class="profile-detail">
                <strong>Email:</strong>
                <span>${user.email}</span>
            </div>
            <div class="profile-detail">
                <strong>Gender:</strong>
                <span>
                    <c:choose>
                        <c:when test="${user.gender == '00'}">Female</c:when>
                        <c:otherwise>Male</c:otherwise>
                    </c:choose>
                </span>
            </div>
            <div class="profile-detail">
                <strong>Date of Birth:</strong>
                <span>${user.dob}</span>
            </div>
            <div class="profile-detail">
                <strong>Phone:</strong>
                <span>${user.phone}</span>
            </div>
            <div class="profile-detail">
                <strong>Status:</strong>
                <span>
                    <c:choose>
                        <c:when test="${user.status == 1}">Active</c:when>
                        <c:otherwise>Inactive</c:otherwise>
                    </c:choose>
                </span>
            </div>
            <div class="profile-detail">
                <strong>Role:</strong>
                <span>
                    <c:choose>
                        <c:when test="${user.role == 0}">Admin</c:when>
                        <c:when test="${user.role == 1}">Employee</c:when>
                        <c:otherwise>Customer</c:otherwise>
                    </c:choose>
                </span>
            </div>

            <!-- Back Button -->
            <a href="userlist" class="back-button">Back to User List</a>
        </div>
    </div>
</body>
</html>
