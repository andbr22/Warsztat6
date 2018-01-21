<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: niblack
  Date: 21.01.18
  Time: 18:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Znalezieni użytkownicy</title>
</head>
<body>
    <c:forEach items="${users}" var="user">
        <a href="/${user.id}">${user.username}</a><br/>
    </c:forEach>
</body>
</html>
