<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%--
  Created by IntelliJ IDEA.
  User: rzepecki
  Date: 19.04.2020
  Time: 12:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Validation</title>
</head>
<body>
<c:forEach items="${validations}" var="validation" varStatus="stat">
    ${validation.propertyPath}: ${validation.message}
</c:forEach>

</body>
</html>
