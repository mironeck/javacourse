<%--
  Created by IntelliJ IDEA.
  User: nikita
  Date: 02.02.2018
  Time: 23:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AdminView</title>
</head>
<body>
<table style="border : lp solid black;" cellpadding="1" cellspacing="1" border="1">
    <tr>
        <th>name</th>
        <th>login</th>
        <th>email</th>
        <th>password</th>
        <th>createDate</th>
        <th>roleId</th>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td><c:out value="${user.name}"></c:out></td>
            <td><c:out value="${user.login}"></c:out></td>
            <td><c:out value="${user.email}"></c:out></td>
            <td><c:out value="${user.password}"></c:out></td>
            <td><c:out value="${user.createDate}"></c:out></td>
            <td><c:out value="${user.roleId}"></c:out></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
