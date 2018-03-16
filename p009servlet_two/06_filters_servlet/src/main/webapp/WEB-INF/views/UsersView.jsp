<%--
  Created by IntelliJ IDEA.
  User: nikita
  Date: 24.02.2018
  Time: 19:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Filters</title>
</head>
<body>

    <table style="border : lp solid black;" cellpadding="1" cellspacing="1" border="1">
        <tr>
            <th>Name</th>
            <th>Login</th>
            <th>Email</th>
            <th>Password</th>
            <th>Create Date</th>
            <th>Role</th>
        </tr>
        <c:forEach items="${users}" var="user">
            <tr>
                <td><c:out value="${user.name}"></c:out></td>
                <td><c:out value="${user.login}"></c:out></td>
                <td><c:out value="${user.email}"></c:out></td>
                <td><c:out value="${user.password}"></c:out></td>
                <td><c:out value="${user.createDate}"></c:out></td>
                <td><c:out value="${user.role}"></c:out></td>
            </tr>
        </c:forEach>
    </table>

    <p>Create new user</p>
    <form action="${pageContext.servletContext.contextPath}/createuser" method="post" >
        User name : <input type="text" name="name"><br>
        User login : <input type="text" name="login"><br>
        User password : <input type="text" name="password"><br>
        User email : <input type="text" name="email"><br>
        role:<br>
        <select name="role" size="1">
            <c:forEach items="${roles}" var = "role">
                <option value = "${role.id}"> <c:out value="${role.name}"></c:out></option>
            </c:forEach>
        </select>
        <br>
        <input type="submit">
    </form>

    <br/>
    <p>Edit user</p>
    <form action="${pageContext.servletContext.contextPath}/edituser" method="post" >
        User name : <input type="text" name="name"><br>
        User login : <input type="text" name="login"><br>
        User new login : <input type="text" name="newLogin"><br>
        User new email : <input type="text" name="newEmail"><br>
        <input type="submit">
    </form>

    <br/>
    <p>Delete user</p>
    <form action="${pageContext.servletContext.contextPath}/deleteuser" method="post" >
        User name : <input type="text" name="name"><br>
        User login : <input type="text" name="login"><br>
        <input type="submit">
    </form>

    <br/>

    <p><a href="${pageContext.servletContext.contextPath}/createrole">CREATE NEW ROLE</a>
    <p><a href="${pageContext.servletContext.contextPath}/editrole">EDIT ROLE</a>
    <p><a href="${pageContext.servletContext.contextPath}/deleterole">DELETE ROLE</a>
    <br/>
    <p><a href="${pageContext.servletContext.contextPath}/signin">LOG OUT</a>
</body>
</html>
