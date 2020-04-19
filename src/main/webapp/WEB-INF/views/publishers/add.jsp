<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: rzepecki
  Date: 19.04.2020
  Time: 09:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add publisher</title>
</head>
<body>

<form:form modelAttribute="publishers" method="post">
    <p>
        <form:label path="name">
            Name: <form:input path="name"/>
        </form:label>
    </p>
    <p>
        <form:button>Add</form:button>
    </p>
</form:form>

</body>
</html>
