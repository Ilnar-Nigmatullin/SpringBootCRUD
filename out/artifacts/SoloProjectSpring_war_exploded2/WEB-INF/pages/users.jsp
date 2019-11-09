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
            <th>age</th>
            <th>password</th>
        </tr>
        <c:forEach items="${userList}" var="users">
            <tr>
                <td>${users.getId()}</td>
                <td>${users.getUserName()}</td>
                <td>${users.getAge()}</td>
                <td>${users.getPassword()}</td>
                <td>
                    <form action="/update/${users.id}" method="get">
                        <input type="submit" value="изменить" style="float:left;">
                    </form>
                </td>
                <td>
                    <form action="/delete/${users.id}" method="get">
                        <input type="hidden" name="id" value="${users.getId()}">
                        <input type="submit" value="удалить" style="float:left;">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <%--<c:url value="/create" var="create"/>--%>
    <form action="/create" method="get">
        <input type="submit" value="Добавить пользователя">
    </form>
</sec:authorize>
</body>
</html>