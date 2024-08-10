<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>User List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f4f4f4;
        }
        .container {
            width: 80%;
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        h2 {
            text-align: center;
        }
        form {
            margin-bottom: 20px;
            text-align: center;
        }
        input[type="text"] {
            padding: 8px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 4px;
            width: 60%;
            max-width: 300px;
        }
        input[type="submit"] {
            padding: 8px 16px;
            font-size: 16px;
            border: none;
            border-radius: 4px;
            background-color: #4CAF50;
            color: white;
            cursor: pointer;
            margin-left: 8px;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 12px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        .error-message {
            color: red;
            text-align: center;
        }
        .success-message {
            color: green;
            text-align: center;
        }
        button[type="submit"] {
            padding: 8px 16px;
            font-size: 16px;
            border: none;
            border-radius: 4px;
            background-color: #f44336;
            color: white;
            cursor: pointer;
        }
        button[type="submit"]:hover {
            background-color: #e53935;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>User List</h2>
        
        <!-- Search form -->
        <form action="searchUser" method="get">
            <input type="text" name="name" placeholder="Search by name" value="${param.name}"/>
            <input type="submit" value="Search"/>
        </form>

        <!-- Display messages -->
        <c:if test="${not empty successMessage}">
            <p class="success-message">${successMessage}</p>
        </c:if>
        <c:if test="${not empty errorMessage}">
            <p class="error-message">${errorMessage}</p>
        </c:if>

        <!-- Users table -->
        <table>
            <tr>
                <th>Username</th>
                <th>Password</th>
                <th>Name</th>
                <th>Gender</th>
                <th>Date of Birth</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Status</th>
                <th>Role</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="user" items="${userList}">
                <tr>
                    <td>${user.username}</td>
                    <td>${user.password}</td>
                    <td>${user.name}</td>
                    <td>
                        <c:choose>
                            <c:when test="${user.gender == '00'}">Female</c:when>
                            <c:otherwise>Male</c:otherwise>
                        </c:choose>
                    </td>
                    <td>${user.dob}</td>
                    <td>${user.email}</td>
                    <td>${user.phone}</td>
                    <td>
                        <c:choose>
                            <c:when test="${user.status == 1}">Active</c:when>
                            <c:otherwise>Inactive</c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${user.role == 0}">Admin</c:when>
                            <c:when test="${user.role == 1}">Employee</c:when>
                            <c:otherwise>Customer</c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <a href="editUser?id=${user.username}">Edit</a> | 
                        <form action="userlist" method="post" style="display:inline;" onsubmit="return confirm('Are you sure you want to delete this user?');">
                            <input type="hidden" name="action" value="delete"/>
                            <input type="hidden" name="username" value="${user.username}"/>
                            <button type="submit">Delete</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
