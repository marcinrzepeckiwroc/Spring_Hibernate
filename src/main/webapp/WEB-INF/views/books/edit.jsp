<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: rzepecki
  Date: 18.04.2020
  Time: 16:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit book</title>
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
        Rating: <form:input path="rating" type="number" min="0" max="9.9" step="0.1"/>
    </p>
    <p>
        Publisher: <form:select path="publisher" items="${publishers}" itemValue="id" itemLabel="name"/>
    </p>
    <p>
        Author: <form:select path="authors" items="${authors}" itemValue="id" itemLabel="fullName" multiple="true"/>
    </p>
    <p>
        <form:hidden path="id"/>
        <form:button>Doddaj</form:button>
    </p>
</form:form>

</body>
</html>
