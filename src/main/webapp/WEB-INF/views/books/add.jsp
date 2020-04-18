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
        Description: <form:input path="description"/>
    </p>
    <p>
        Rating: <form:input path="rating" type="number"
    min="0" max="5" step="0.1"/>
    </p>
    <p>
        Publisher: <form:select path="publisher" items="${publishers}" itemValue="id" itemLabel="name"/>
    </p>
    <p>
        <form:button>Send</form:button>
    </p>
</form:form>

</body>
</html>
