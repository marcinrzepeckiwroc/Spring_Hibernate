<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Table</title>
</head>
<body>
<p>
    <a href="/forms/books/add">Add book</a>
</p>

<table>
    <tr>
        <th>Lp.</th>
        <th>FirstName</th>
        <th>LastName</th>
        <th>Action</th>
    </tr>

    <c:forEach items="${authors}" var="author" varStatus="stat">
        <tr>
            <td>${stat.count}</td>
            <td>${author.firstName}</td>
            <td>${author.lastName}</td>
            <td>
                <c:url value="/forms/authors/edit" var="editURL">
                    <c:param name="id" value="${author.id}"/>
                </c:url>
                <a href="${editURL}"> Edit</a>

                <c:url value="/forms/authors/delete" var="deleteURL">
                    <c:param name="id" value="${author.id}"/>
                </c:url>
                <a href="${deleteURL}"> Delete</a>

        </tr>
    </c:forEach>
</table>




</body>
</html>
