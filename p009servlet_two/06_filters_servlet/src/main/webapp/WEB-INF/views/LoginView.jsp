<%--
  Created by IntelliJ IDEA.
  User: nikita
  Date: 24.02.2018
  Time: 19:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Login</title>
</head>
<body>
<c:if test="${error != ''}">
    <div style="background-color: red">
        <c:out value="${error}"></c:out>
    </div>
</c:if>
    <p>Login</p>
    <form action="${pageContext.servletContext.contextPath}/singin" method="post" >
        User login : <input type="text" name="login">
        User password : <input type="password" name="password">
        <input type="submit">
    </form>
</body>
</html>
