<%@ page import="ru.mironenko.model.User" %>
<%@ page import="ru.mironenko.control.UserStore" %><%--
  Created by IntelliJ IDEA.
  User: nikita
  Date: 20.02.2018
  Time: 22:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP</title>
</head>
<body>

    <table style="border : lp solid black;" cellpadding="1" cellspacing="1" border="1">
        <tr>
            <th>Name</th>
            <th>Login</th>
            <th>Email</th>
            <th>Create Date</th>
        </tr>
        <% for(User user : UserStore.getInstance().getUserList()) { %>
            <tr>
                <td><%=user.getName()%></td>
                <td><%=user.getLogin()%></td>
                <td><%=user.getEmail()%></td>
                <td><%=user.getCreateDate()%></td>
            </tr>
        <% } %>
    </table>

    <p>Create new user</p>
    <form action="<%=request.getContextPath()%>/createuser" method="post" >
        User name : <input type="text" name="name">
        User login : <input type="text" name="login">
        User email : <input type="text" name="email">
        <input type="submit">
    </form>

    <br/>
    <p>Edit user</p>
    <form action="<%=request.getContextPath()%>/edituser" method="post" >
        User name : <input type="text" name="name">
        User login : <input type="text" name="login">
        User new login : <input type="text" name="newLogin">
        User new email : <input type="text" name="newEmail">
        <input type="submit">
    </form>

    <br/>
    <p>Delete user</p>
    <form action="<%=request.getContextPath()%>/deleteuser" method="post" >
        User name : <input type="text" name="name">
        User login : <input type="text" name="login">
        <input type="submit">
    </form>

    <br/>
</body>
</html>
