<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dodawanie osoby</title>
</head>
<body>
<form method="post">
    <label for="login">Login: </label>
    <input id="login" name="login" type="text"/><br/>
    <label for="password">Hasło: </label>
    <input id="password" name="password" type="password"/><br/>
    <label for="email">Email: </label>
    <input id="email" name="email" type="text"/><br/>
    <button type="submit">Wyślij</button>
</form>

<h2>Different spring approach</h2>

<form:form method="post" modelAttribute="modelPerson" action="/persons/add-bind">
    <form:label path="login">Login: </form:label>
    <form:input path="login"/><br/>
    <form:label path="email">Email: </form:label>
    <form:input path="email"/><br/>
    <form:label path="password">Password: </form:label>
    <form:password path="password"/><br/>

    <form:button>Send</form:button>
</form:form>
</body>