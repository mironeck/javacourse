<%@ page import="ru.mironenko.models.User" %>
<%@ page import="ru.mironenko.control.DBActions" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title></title>
</head>
<body>

<br/>
<table style="border : lp solid black;" cellpadding="1" cellspacing="1" border="1">
    <tr>
        <th>name</th>
        <th>login</th>
        <th>email</th>
        <th>password</th>
        <th>role</th>
        <th>createDate</th>
    </tr>
    <c:forEach items="${requestScope.users}" var="user">
        <tr>
            <td><c:out value="${user.name}"></c:out></td>
            <td><c:out value="${user.login}"></c:out></td>
            <td><c:out value="${user.email}"></c:out></td>
            <td><c:out value="${user.password}"></c:out></td>
            <td><c:out value="${user.role.role}"></c:out></td>
            <td><c:out value="${user.createDate}"></c:out></td>
        </tr>
    </c:forEach>
</table>



</form>
<br>
<p>Delete user:</p>
<form action="${pageContext.servletContext.contextPath}/edituser" method="post">
    User name : <input type=text name="name">
    User login : <input type=text name="login">
    <input type="submit">
</form>
<br>

</body>
</html>
