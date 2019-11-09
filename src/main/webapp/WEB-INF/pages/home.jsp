<%--
  Created by IntelliJ IDEA.
  User: aaq-9
  Date: 15.10.2019
  Time: 19:47
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserHome</title>
</head>
<body>
<sec:authorize access="hasRole('ROLE_USER')">
    <a>Hello User!</a>
    <form action="/logout" method="post">
        <input type="submit" value="Sign Out"/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</sec:authorize>
<sec:authorize access="hasRole('ROLE_ADMIN')">
    <form action="/admin" method="get" style="float: left">
        <input type="submit" value="Admin Page"/>
    </form>
</sec:authorize>
</body>
</html>
