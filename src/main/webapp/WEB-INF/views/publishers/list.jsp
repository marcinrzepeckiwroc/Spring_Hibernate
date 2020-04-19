<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Table</title>
</head>
<body>
<p>
    <a href="/forms/publishers/add">Add book</a>
</p>

<table>
    <tr>
        <th>Lp.</th>
        <th>Name</th>
        <th>Action</th>
    </tr>

    <c:forEach items="${publishers}" var="publisher" varStatus="stat">
        <tr>
            <td>${stat.count}</td>
            <td>${publisher.name}</td>
            <td>
                <c:url value="/forms/publishers/edit" var="editURL">
                    <c:param name="id" value="${publisher.id}"/>
                </c:url>
                <a href="${editURL}"> Edit</a>

                <c:url value="/forms/publishers/delete" var="deleteURL">
                    <c:param name="id" value="${publisher.id}"/>
                </c:url>
                <a href="${deleteURL}"> Delete</a>

        </tr>
    </c:forEach>
</table>




</body>
</html>
