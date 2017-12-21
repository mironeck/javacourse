<%--
  Created by IntelliJ IDEA.
  User: nikita
  Date: 21.12.2017
  Time: 23:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete User</title>
</head>
<body>

    <br>
        <p>Delete user:</p>
        <form action="${pageContext.servletContext.contextPath}/deleteuser" method="post">
            User name : <input type=text name="name"><br/>
            User login : <input type=text name="login"><br/>
            <input type="submit">
    </form>
    <br>

</body>
</html>
