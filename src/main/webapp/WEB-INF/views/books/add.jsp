<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: rzepecki
  Date: 18.04.2020
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add book</title>
</head>
<body>

<form:form modelAttribute="book" method="post">
    <p>
        Title: <form:input path="title"/>
    </p>
    <p>
        Description: <form:textarea path="description"/>
    </p>
    <p>
        Raiting: <form:input path="rating" type="number" min="0" max="9.9" step="0.1"/>
        <form:errors path="rating"/>
    </p>
    <p>
        Pages: <form:input path="pages" type="text"/>
        <form:errors path="pages"/>
    </p>
    <p>
        Publisher: <form:select path="publisher" items="${publishers}" itemValue="id" itemLabel="name"/>
        <form:errors path="publisher"/>
    </p>
    <p>
        Author: <form:select path="authors" items="${authors}" itemValue="id" itemLabel="fullName" multiple="true"/>
        <form:errors path="authors"/>
    </p>
    <p>
        <form:button>Doddaj</form:button>
    </p>
</form:form>

</body>
</html>
