<%@ page import="ru.mironenko.model.User" %>
<%@ page import="ru.mironenko.control.DBActions" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title></title>
</head>
<body>

    <form action="${pageContext.servletContext.contextPath}/" method="post">
        Name : <input type="text" name="name"><br/>
        Login : <input type="text" name="login"><br/>
        Email : <input type="text" name="email"><br/>
        <input type="submit">
    </form>
    <br/>
    <table style="border : lp solid black;" cellpadding="1" cellspacing="1" border="1">
            <tr>
                <th>name</th>
                <th>login</th>
                <th>email</th>
                <th>createDate</th>
            </tr>
        <c:forEach items="${requestScope.users}" var="user">
            <tr>
                <td><c:out value="${user.name}"></c:out></td>
                <td><c:out value="${user.login}"></c:out></td>
                <td><c:out value="${user.email}"></c:out></td>
                <td><c:out value="${user.createDate}"></c:out></td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>
