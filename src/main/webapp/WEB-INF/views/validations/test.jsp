<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Błędy walidacji</title>
</head>
<body>

<c:if test="${not errors.isEmpty()}">
    <h2>Błędy walidacji:</h2>
    <ul>
        <c:forEach items="${errors}" var="error">
            <li>Błędna wartość ${error.invalidValue} dla pola ${error.propertyPath} : "${error.message}"</li>
        </c:forEach>
    </ul>
</c:if>

<form>
    <input name="pages"/><c:if test="${not empty errorsMap.pages}">
    <span>${errorsMap.pages}</span>
</c:if>
</form>
</body>
</html>