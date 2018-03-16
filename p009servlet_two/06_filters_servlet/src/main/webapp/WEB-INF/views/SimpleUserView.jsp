<%--
  Created by IntelliJ IDEA.
  User: nikita
  Date: 13.03.2018
  Time: 23:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Simple User View</title>
</head>
<body>


        <p>Edit user</p>
        Your current name : "${user.name}"<br>
        Your current login: "${user.login}"<br>

        <form action="${pageContext.servletContext.contextPath}/editsimpleuser" method="post" >
            <input name="name" type="hidden" value="${user.name}"><br>
            <input name="login" type="hidden" value="${user.login}"><br>
            <input name="password" type="hidden" value="${user.password}"><br>
            Enter your new login : <input type="text" name="newLogin"><br>
            Enter your new email : <input type="text" name="newEmail"><br>
            <input type="submit">
        </form>

        <table style="border : lp solid black;" cellpadding="1" cellspacing="1" border="1">
            <tr>
                <th>Name</th>
                <th>Login</th>
                <th>Email</th>
                <th>Password</th>
                <th>Create Date</th>
                <th>Role</th>
            </tr>

            <tr>
                <td><c:out value="${user.name}"></c:out></td>
                <td><c:out value="${user.login}"></c:out></td>
                <td><c:out value="${user.email}"></c:out></td>
                <td><c:out value="${user.password}"></c:out></td>
                <td><c:out value="${user.createDate}"></c:out></td>
                <td><c:out value="${user.role}"></c:out></td>
            </tr>
            <br/>
        </table>

        <p><a href="${pageContext.servletContext.contextPath}/signin">LOG OUT</a>
</body>
</html>
