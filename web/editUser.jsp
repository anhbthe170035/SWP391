<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Edit User</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            margin-top: 200px;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f4f4f4;
        }
        .container {
            width: 50%;
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        h2 {
            text-align: center;
        }
        form {
            display: flex;
            flex-direction: column;
            gap: 15px;
        }
        label {
            font-weight: bold;
        }
        input[type="text"], input[type="password"], input[type="date"], input[type="email"], input[type="tel"], select {
            padding: 8px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 4px;
            width: 100%;
        }
        input[type="submit"] {
            padding: 10px 16px;
            font-size: 16px;
            border: none;
            border-radius: 4px;
            background-color: #4CAF50;
            color: white;
            cursor: pointer;
            align-self: center;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        .error-message {
            color: red;
            text-align: center;
        }
        .success-message {
            color: green;
            text-align: center;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Edit User</h2>

        <!-- Display messages -->
        <c:if test="${not empty errorMessage}">
            <p class="error-message">${errorMessage}</p>
        </c:if>

        <!-- Edit user form -->
        <form action="editUser" method="post">
            <input type="hidden" name="action" value="edit" />

            <label for="username">Username</label>
            <input type="text" id="username" name="username" value="${user.username}" readonly/>

            <label for="password">Password</label>
            <input type="password" id="password" name="password" value="${user.password}" required/>

            <label for="name">Name</label>
            <input type="text" id="name" name="name" value="${user.name}" required/>

            <label for="gender">Gender</label>
            <select id="gender" name="gender" required>
                <option value="00" ${user.gender == '00' ? 'selected' : ''}>Female</option>
                <option value="01" ${user.gender == '01' ? 'selected' : ''}>Male</option>
            </select>

            <label for="dob">Date of Birth</label>
            <input type="date" id="dob" name="dob" value="${user.dob}" required/>

            <label for="img">Image URL</label>
            <input type="text" id="img" name="img" value="${user.img}"/>

            <label for="email">Email</label>
            <input type="email" id="email" name="email" value="${user.email}" required/>

            <label for="phone">Phone</label>
            <input type="tel" id="phone" name="phone" value="${user.phone}" required/>

            <label for="status">Status</label>
            <select id="status" name="status" required>
                <option value="1" ${user.status == 1 ? 'selected' : ''}>Active</option>
                <option value="0" ${user.status == 0 ? 'selected' : ''}>Inactive</option>
            </select>

            <label for="role">Role</label>
            <select id="role" name="role" required>
                <option value="0" ${user.role == 0 ? 'selected' : ''}>Admin</option>
                <option value="1" ${user.role == 1 ? 'selected' : ''}>Employee</option>
                <option value="2" ${user.role == 2 ? 'selected' : ''}>Customer</option>
            </select>

            <input type="submit" value="Save Changes"/>
        </form>
    </div>
</body>
</html>
