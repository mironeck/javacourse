<%@ page import="ru.mironenko.model.User" %>
<%@ page import="ru.mironenko.control.UserStore" %><%--
  Created by IntelliJ IDEA.
  User: nikita
  Date: 22.02.2018
  Time: 22:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>MVC_JSTL</title>
</head>
<body>

    <table style="border : lp solid black;" cellpadding="1" cellspacing="1" border="1">
        <tr>
            <th>Name</th>
            <th>Login</th>
            <th>Email</th>
            <th>Create Date</th>
        </tr>
        <c:forEach items="${users}" var="user">
            <tr>
                <td><c:out value="${user.name}"></c:out></td>
                <td><c:out value="${user.login}"></c:out></td>
                <td><c:out value="${user.email}"></c:out></td>
                <td><c:out value="${user.createDate}"></c:out></td>
            </tr>
        </c:forEach>
    </table>

    <p>Create new user</p>
    <form action="${pageContext.servletContext.contextPath}/createuser" method="post" >
        User name : <input type="text" name="name">
        User login : <input type="text" name="login">
        User email : <input type="text" name="email">
        <input type="submit">
    </form>

    <br/>
    <p>Edit user</p>
    <form action="${pageContext.servletContext.contextPath}/edituser" method="post" >
        User name : <input type="text" name="name">
        User login : <input type="text" name="login">
        User new login : <input type="text" name="newLogin">
        User new email : <input type="text" name="newEmail">
        <input type="submit">
    </form>

    <br/>
    <p>Delete user</p>
    <form action="${pageContext.servletContext.contextPath}/deleteuser" method="post" >
        User name : <input type="text" name="name">
        User login : <input type="text" name="login">
        <input type="submit">
    </form>

    <br/>
</body>
</html>
