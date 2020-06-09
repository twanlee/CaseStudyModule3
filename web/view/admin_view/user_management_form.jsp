<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 6/7/2020
  Time: 8:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User Management</title>
</head>
<body>
<fieldset>
    <legend>User management</legend>
    <table border="1">
        <tr>
            <th>User ID</th>
            <th>Username</th>
            <th>Password</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Address</th>
            <th>Email</th>
            <th>Phone Number</th>
            <th style="color: red">Position</th>
            <th colspan="3">Action</th>
        </tr>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.getUserId()}</td>
                <td>${user.getUserName()}</td>
                <td>${user.getPassword()}</td>
                <td>${user.getFirstName()}</td>
                <td>${user.getLastName()}</td>
                <td>${user.getAddress()}</td>
                <td>${user.getEmail()}</td>
                <td>${user.getPhoneNumber()}</td>
                <td>${user.getPermission()}</td>
                <td><a href="/command?action=edit&user=${user.getUserName()}">Edit User</a></td>
                <td><a href="/command?action=delete&id=${user.getUserId()}">Delete User</a></td>
                <td><a href="/command?action=appoint&id=${user.getUserId()}">Appoint to staff</a></td>
            </tr>
        </c:forEach>
    </table>
</fieldset>
</body>
</html>
