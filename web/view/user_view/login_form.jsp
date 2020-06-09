<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 6/7/2020
  Time: 2:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<a href="/index.jsp">Back to home</a>
<fieldset>
    <c:if test='${requestScope["error"] != null}'>
        <h3 style="color: red">${requestScope["error"]}</h3>
    </c:if>
    <legend>Login</legend>
    <form method="post">
        User Name <br/>
        <input type="text" name="username"/> <br>
        Password <br/>
        <input type="password" name="password"> <br>
        <input type="submit">
    </form>
</fieldset>
</body>
</html>
