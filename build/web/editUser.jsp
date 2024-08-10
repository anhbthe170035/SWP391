<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Edit User</title>
    <style>
        /* Your CSS styles here */
    </style>
</head>
<body>
    <h2>Edit User</h2>
    <form action="EditUserServlet" method="post">
        <input type="hidden" name="id" value="${user.id}"/>
        <label>Name:</label>
        <input type="text" name="name" value="${user.name}" required/>
        <br/>
        <label>Gender:</label>
        <select name="gender">
            <option value="00" ${user.gender == '00' ? 'selected' : ''}>Female</option>
            <option value="01" ${user.gender == '01' ? 'selected' : ''}>Male</option>
        </select>
        <br/>
        <label>Status:</label>
        <select name="status">
            <option value="1" ${user.status == 1 ? 'selected' : ''}>Active</option>
            <option value="0" ${user.status == 0 ? 'selected' : ''}>Inactive</option>
        </select>
        <br/>
        <input type="submit" value="Update"/>
    </form>
    <a href="userList">Back to User List</a>
</body>
</html>
