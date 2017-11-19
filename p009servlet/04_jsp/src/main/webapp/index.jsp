<%@ page import="ru.mironenko.models.User" %>
<%@ page import="ru.mironenko.controls.DBActions" %><%--
  Created by IntelliJ IDEA.
  User: nikita
  Date: 25.09.2017
  Time: 22:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>jsp</title>
</head>
<body>

    <table style="border : lp solid black;" cellpadding="1" cellspacing="1" border="1">
        <tr>
            <th>name</th>
            <th>login</th>
            <th>email</th>
            <th>create Date</th>
        </tr>
        <% for(User user : DBActions.getInstance().getUserList()) { %>
        <tr>
            <td><%=user.getName()%></td>
            <td><%=user.getLogin()%></td>
            <td><%=user.getEmail()%></td>
            <td><%=user.getCreateDate()%></td>
        </tr>
        <% } %>
    </table>

    <p>Create new user</p>
    <form action="<%=request.getContextPath()%>/createuser" method="post">
        User name : <input type="text" name="name">
        User login : <input type="text" name="login">
        User email : <input type="text" name="email">
        <input type="submit">
    </form>

    <br/>
    <p>Edit user</p>
    <form action="<%=request.getContextPath()%>/edituser" method="post">
        User name : <input type="text" name="name">
        User login : <input type="text" name="login">
        User new name : <input type="text" name="newName">
        User new login : <input type="text" name="newEmail">
        <input type="submit">
    </form>

    <br/>
    <p>Delete user</p>
    <form action="<%=request.getContextPath()%>/deleteuser" method="post">
        User name : <input type="text" name="name">
        User login : <input type="text" name="email">
        <input type="submit">
    </form>

    <br/>

</body>
</html>
