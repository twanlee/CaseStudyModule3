<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 6/8/2020
  Time: 4:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
<a href="/user_management">Back</a>

<c:if test='${requestScope["message"] != null}'>
    <h3 style="color: red">${requestScope["message"]}</h3>
</c:if>



<form method="post">
    <input type="hidden" name="id" value="<c:out value="${user.userId}"/>"/>
    Name <br/>
    <input type="text" name="username" value="<c:out value="${user.userName}"/>"/><br/>
    PassWord <br/>
    <input type="password" name="password" value="<c:out value="${user.password}"/>"/><br/>
    First Name <br/>
    <input type="text" name="firstName" value="<c:out value="${user.firstName}"/>"/><br/>
    Last Name <br/>
    <input type="text" name="lastName" value="<c:out value="${user.lastName}"/>"/><br/>
    Email <br/>
    <input type="text" name="email" value="<c:out value="${user.email}"/>" /><br/>
    Phone Number <br/>
    <input type="text" name="phoneNumber" value="<c:out value="${user.phoneNumber}"/>"/><br/>
    Address <br>
    <input type="text" name="address" value="<c:out value="${user.address}"/>"/> <br>
    <input type="submit">
</form>
</body>
</html>
