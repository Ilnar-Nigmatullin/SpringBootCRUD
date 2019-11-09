<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>AllUsers</title>
</head>
<body>

<h2>AllUsers</h2>
<sec:authorize access="hasRole('ROLE_ADMIN')">
    <table border="3">
        <tr>
            <th>id</th>
            <th>name</th>
            <th>password</th>
            <th>action</th>
            <th>action</th>
            <th style="right: 500px">role</th>
        </tr>
        <c:forEach items="${userList}" var="users">

            <tr>
                <td>${users.getId()}</td>
                <td>${users.getUserName()}</td>
                <td>${users.getPassword()}</td>
                <td>
                    <form action="/update/${users.id}" method="get">
                        <input type="submit" value="изменить" style="float:left;">
                    </form>
                </td>
                <td style="float: right;">
                    <form action="/delete/${users.id}" method="get">
                        <input type="hidden" name="id" value="${users.getId()}">
                        <input type="submit" value="удалить" style="float:left;">
                    </form>
                </td>
                <c:forEach items="${users.getRoles()}" var="roles">
                    <td>
                            ${roles.getName()}
                    </td>
                </c:forEach>

            </tr>
        </c:forEach>
    </table>
    <form action="/create" method="get">
        <input type="submit" value="Добавить пользователя">
    </form>
    <form action="/logout" method="post" style="float: left">
        <input type="submit" value="Sign Out"/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
    <button>
        <a href="http://localhost:8080/home">User Page</a>
    </button>
</sec:authorize>
</body>
</html>