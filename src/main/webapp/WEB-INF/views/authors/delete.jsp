
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Usuwanie książki</title>
</head>
<body>
<h2>Delete author - Do you confirm ${book.title}? </h2>
<form:form modelAttribute="author" method="post">
    <form:hidden path="id"/>
    <form:button>Yes</form:button>
</form:form>

<form method="get" action="/forms/authors">
    <button type="submit">No</button>
</form>
</body>
</html>