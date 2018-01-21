<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: niblack
  Date: 21.01.18
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Comments</title>
</head>
<body>
    <table border="2px">
        <tr>
            <td>${tweet.user.username}</td>
            <td>${tweet.created}</td>
            <td>${tweet.text}</td>
        </tr>
    </table>
    <table border="2px">
        <c:forEach items="${comments}" var="com">
            <tr>
                <td>${com.user.username}</td>
                <td>${com.created}</td>
                <td>${com.text}</td>
            </tr>
        </c:forEach>
        <form:form modelAttribute="comment" method="post">
            <tr>
                <td></td>
                <td><input type="submit" value="Add Comment"/></td>
                <td><form:input path="text"/></td>
            </tr>
        </form:form>
    </table>
</body>
</html>
