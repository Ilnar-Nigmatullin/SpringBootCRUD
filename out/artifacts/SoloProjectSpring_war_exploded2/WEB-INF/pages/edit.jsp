<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Edit</title>
</head>
<body>
<form action="/update" method="post">
    <input type="hidden" name="id" value="${user.id}">
    <label for="userName">Name</label>
    <input type="text" name="userName" id="userName">
    <label for="age">Age</label>
    <input type="text" name="age" id="age">
    <label for="password">Password</label>
    <input type="password" name="password" id="password">
    <input type="submit" value="Edit user">
</form>
</body>
</html>