<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: niblack
  Date: 19.01.18
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register Form</title>
</head>
<body>
    <form:form modelAttribute="user" method="post">
        Email: <form:input path="email"/><form:errors path="email"/><br/>
        Username: <form:input path="username"/><form:errors path="username"/><br/>
        Password:<form:password path="password"/><form:errors path="password"/> <br/>
        <input type="submit" value="Register"/>
    </form:form>
</body>
</html>
