<%--
  Created by IntelliJ IDEA.
  User: aaq-9
  Date: 15.10.2019
  Time: 19:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Spring Security Example </title>
</head>
<body>
<form action="/login" method="post">
    <div><label> User Name : <input type="text" name="username"/> </label></div>
    <div><label> Password: <input type="password" name="password"/> </label></div>
    <div><input type="submit" value="Sign In"/></div>
    <input type="hidden" name="${_csrf.parameterName}"
           value="${_csrf.token}" />
</form>
</body>
</html>