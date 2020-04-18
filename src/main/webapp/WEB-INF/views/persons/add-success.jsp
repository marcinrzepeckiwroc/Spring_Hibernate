<%--
  Created by IntelliJ IDEA.
  User: rzepecki
  Date: 18.04.2020
  Time: 11:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Success</title>
</head>
<body>
<h1>Saved person!</h1>
<p>
    <b> Id: ${modelPerson.id}</b><br/>
     Login: ${modelPerson.login}<br/>
     Email: ${modelPerson.email}<br/>
</p>

<a href="/persons/add">Add new person!</a>

</body>
</html>
