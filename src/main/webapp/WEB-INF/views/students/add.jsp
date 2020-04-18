<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add student</title>
</head>
<body>

<form:form method="post" modelAttribute="modelStudent" action="/students/add">
    <form:label path="firstName">First Name: </form:label>
    <form:input path="firstName"/><br/>
    <form:label path="lastName">Last Name: </form:label>
    <form:input path="lastName"/><br/>
    <form:label path="lastName">Gender: </form:label>
    Male: <form:radiobutton path="gender" value="M"/><br/>
    Female: <form:radiobutton path="gender" value="F"/><br/>
    <form:label path="country">Country: </form:label>
    <form:select path="country" items="${countryItems}" /><br/>
    <form:label path="notes">Notes: </form:label>
    <form:textarea path="notes" cols="3" rows="3"/><br/>
    <form:label path="mailingList">Mailing: </form:label>
    <form:checkbox path="mailingList"/><br/>
    <form:label path="programmingSkills">Programing skills: </form:label>
    <form:select path="programmingSkills" items="${programingItems}" multiple="true" /><br/>
    <form:label path="hobbies">Hobbies: </form:label>
    <form:checkboxes path="hobbies" items="${hobbiesItems}"/><br/>

    <form:button>Send</form:button>
</form:form>
</body>