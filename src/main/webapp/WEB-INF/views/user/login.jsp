<%--
  Created by IntelliJ IDEA.
  User: niblack
  Date: 19.01.18
  Time: 16:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <form method="post">
        Email:<input name="email"><br/>
        Password:<input type="password" name="password"><br/>
        <input type="submit" value="Log-in"/>
    </form>
    <a href="/user/register">Register</a>
</body>
</html>
