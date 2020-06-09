<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 6/7/2020
  Time: 10:36 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<a href="/index.jsp"> Back to index</a>
<fieldset>
    <legend>Register</legend>
<c:if test='${requestScope["message"] != null}'>
    <h3 style="color: blue">${requestScope["message"]}</h3>
</c:if>
    <c:if test='${requestScope["error"] != null}'>
        <h3 style="color: red">${requestScope["error"]}</h3>
    </c:if>

    <form method="post">
        Name <br/>
        <input type="text" name="username"/><br/>
        PassWord <br/>
        <input type="password" name="password"/><br/>
        First Name <br/>
        <input type="text" name="firstName"/><br/>
        Last Name <br/>
        <input type="text" name="lastName"/><br/>
        Email <br/>
        <input type="text" name="email"/><br/>
        Phone Number <br/>
        <input type="text" name="phoneNumber"/><br/>
        Address <br>
        <input type="text" name="address"/> <br>
        <input type="submit">
    </form>

</fieldset>
</body>
</html>
