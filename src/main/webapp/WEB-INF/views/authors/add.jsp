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
    <title>Add Author</title>
</head>
<body>

<form:form modelAttribute="authors" method="post">
    <p>
        <form:label path="firstName">
            FirstName: <form:input path="firstName"/>
        </form:label>
    </p>
    <p>
        <form:label path="lastName">
            LastName: <form:textarea path="lastName"/>
        </form:label>
    </p>
    <p>
        <form:button>Doddaj</form:button>
    </p>
</form:form>

</body>
</html>
