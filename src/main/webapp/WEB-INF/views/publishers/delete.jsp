
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Publisher delete</title>
</head>
<body>
<h2>Delete publisher - Do you confirm ${book.title}? </h2>
<form:form modelAttribute="publisher" method="post">
    <form:hidden path="id"/>
    <form:button>Yes</form:button>
</form:form>

<form method="get" action="/forms/publishers">
    <button type="submit">No</button>
</form>
</body>
</html>