<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: niblack
  Date: 19.01.18
  Time: 15:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>I hear you want to tweet</h1>
    <form:form method="post" modelAttribute="tweet">
        <form:input path="text"/><form:errors path="text"/> <br/>
        <input type="submit" name="Add Tweet" value="Add Tweet"/>
    </form:form>
    <hr/>
    <table border="2px">
        <c:forEach items="${tweets}" var="tw">
            <tr>
                <td>${tw.created}</td>
                <td><a href="/comment/${tw.id}">${tw.text}</a></td>
            </tr>
        </c:forEach>
    </table>
    <hr/>
    <form method="post" action="/user/find">
    Find user:<input name="name"><input type="submit" value="Find">
    </form>
</body>
</html>
