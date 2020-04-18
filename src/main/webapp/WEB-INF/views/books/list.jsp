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
        <th>Title</th>
        <th>Description</th>
        <th>Rating</th>
        <th>Publisher</th>
        <th>Author</th>
        <th>Action</th>
    </tr>

    <c:forEach items="${books}" var="book" varStatus="stat">
        <tr>
            <td>${stat.count}</td>
            <td>${book.title}</td>
            <td>${book.description}</td>
            <td>${book.rating}</td>
            <td>${book.publisher}</td>
            <td>${book.authors}</td>
            <td>
                <c:url value="/forms/books/edit" var="editURL">
                    <c:param name="id" value="${book.id}"/>
                </c:url>
                <a href="${editURL}"> Edit</a>

                <c:url value="/forms/books/delete" var="deleteURL">
                    <c:param name="id" value="${book.id}"/>
                </c:url>
                <a href="${deleteURL}"> Delete</a>

        </tr>
    </c:forEach>
</table>




</body>
</html>
