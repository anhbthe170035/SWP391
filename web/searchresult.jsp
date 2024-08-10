<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- 
    Trang JSP hiển thị kết quả tìm kiếm người dùng theo tên.
    
    @version 1.0
    @since 2024-08-10
--%>
<html>
<head>
    <title>Search Results</title>
</head>
<body>
    <h2>Search Results</h2>

    <a href="userlist">Back to User List</a>
    
    <c:if test="${not empty errorMessage}">
        <p style="color: red;">${errorMessage}</p>
    </c:if>
    
    <c:if test="${not empty userList}">
        <table border="1">
            <tr>
                <th>Username</th>
                <th>Password</th>
                <th>Name</th>
                <th>Gender</th>
                <th>Date of Birth</th>
                <th>Image</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Status</th>
                <th>Role</th>
            </tr>
            <c:forEach var="user" items="${userList}">
                <tr>
                    <td>${user.username}</td>
                    <td>${user.password}</td>
                    <td>${user.name}</td>
                    <td>${user.gender}</td>
                    <td>${user.dob}</td>
                    <td><img src="${user.img}" alt="User Image" width="50" height="50"/></td>
                    <td>${user.email}</td>
                    <td>${user.phone}</td>
                    <td>${user.status}</td>
                    <td>${user.role}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    
    <c:if test="${empty userList}">
        <p>No users found.</p>
    </c:if>
</body>
</html>
