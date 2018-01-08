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

<form action="${pageContext.servletContext.contextPath}/" method="post">
    Name : <input type="text" name="name"><br/>
    Login : <input type="text" name="login"><br/>
    Email : <input type="text" name="email"><br/>
    Password : <input type="text" name="password"><br/>
    Role : <input type="text" name="role"><br/>
    <input type="submit">
</form>
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
            <td>
                <form action="${pageContext.servletContext.contextPath}/edituser" method="post">
                    <input type="image" value="edit user"
                    <%--style="background-image: url(http://localhost:8089/images/edit.jpg) ; border: solid 0px #000000; width: 100px; height: 40px;" />--%>
                    src="http://localhost:8089/images/edit.jpg" WIDTH="120"  HEIGHT="30"
                           BORDER="0" ALT="SUBMIT!">
                </form>
            </td>
            <td>
                <form action="${pageContext.servletContext.contextPath}/deleteuser" method="post">
                    <input type="submit" value="delete user"
                           <%--style="background-image: url(edit.jpg) ; border: solid 0px #000000; width: 100px; height: 40px;" />--%>
                           src="http://localhost:8089/images/delete.jpg" WIDTH="120"  HEIGHT="30"
                           BORDER="0" ALT="SUBMIT!">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
