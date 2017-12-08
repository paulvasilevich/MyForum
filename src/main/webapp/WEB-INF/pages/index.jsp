<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%--<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>--%>
<%--<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login page</title>
</head>
<body>
<span style="float: right">
    <a href="?lang=en">en</a>
    |
    <a href="?lang=ru">ru</a>
</span>
<div id="loginWindow">
<div>
    <%--<form action="/login" method="post">--%>
        <%--<input type="text" name="name">--%>
        <%--<input type="text" name="lastName">--%>
        <%--<input type="submit" value="Login">--%>
    <%--</form>--%>
<form:form action="/login" method="post" modelAttribute="userDTO">
    <%--<form:label path="login" />--%>
    <div><spring:message code="login" var="loginLabel"/>
        ${loginLabel}
        <form:input path="login" autocomplete="true"/></div>
    <div><form:errors path="login"/> </div>

    <div><spring:message code="password" var="passwordLabel"/>
        ${passwordLabel}
        <form:password path="password" autocomplete="true"/></div>
    <div><form:errors path="password"/> </div>

    <spring:message code="button.enter" var="enterLabel"/>
    <input type="submit" value="${enterLabel}" />
</form:form>
</div>
<div>
<form:form action="/registration" method="get">
    <spring:message code="button.registration" var="registrLabel"/>
    <input type="submit" value="${registrLabel}">
</form:form>
</div>
</div>
</body>
</html>
