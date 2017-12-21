<%--
  Created by IntelliJ IDEA.
  User: nikita
  Date: 19.12.2017
  Time: 23:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit User</title>
</head>
<body>

    <br>
        <p>Edit user:</p>
            <form action="${pageContext.servletContext.contextPath}/edituser" method="post">
                User name : <input type=text name="name"><br/>
                User login : <input type=text name="login"><br/>
                New user login : <input type=text name="newName"><br/>
                New user email: <input type=text name="newLogin"><br/>
                 New user role: <input type=text name="newRole"><br/>
                <input type="submit">
            </form>
    <br>

</body>
</html>
