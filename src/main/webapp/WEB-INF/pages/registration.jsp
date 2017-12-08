<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  UserEntity: white
  Date: 19.10.17
  Time: 13:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration page</title>
</head>
<body>
<span style="float: right">
    <a href="?lang=en">en</a>
    |
    <a href="?lang=ru">ru</a>
</span>
<div>
    <form:form method="post" action="/registration" modelAttribute="userEntity">
        <div><spring:message code="firstName" var="firstNameLabel"/></div>
        <div>${firstNameLabel}</div>
        <div> <form:input path="name" /></div>
        <div><form:errors path="name"/></div>

        <div><spring:message code="lastName" var="lastNameLabel"/></div>
        <div>${lastNameLabel}</div>
        <div> <form:input path="lastName"/> </div>
        <div><form:errors path="lastName"/></div>

        <div> <spring:message code="login" var="loginLabel"/>
        <div>${loginLabel}</div>
        <div><form:input path="login"/></div>
        <div><form:errors path="login"/></div></div>

        <div><spring:message code="email" var="emailLabel"/>
        <div>${emailLabel}</div>
        <div>  <form:input path="email"/></div>
        <div><form:errors path="email"/></div></div>

        <div>   <spring:message code="password" var="passwordLabel"/>
        <div>${passwordLabel}</div>
        <div><form:input path="password"/></div>
        <div><form:errors path="password" /></div>

        <div> <spring:message code="button.confirm" var="confirmLabel"/>
            <input type="submit" value="${confirmLabel}"></div>
    </form:form>
</div>
<div>
    <form:form method="get" action="/">
        <spring:message code="button.cancel" var="cancelLabel"/>
        <input type="submit" value=${cancelLabel}>
    </form:form>
</div>
</body>
</html>
