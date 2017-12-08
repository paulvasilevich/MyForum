<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: white
  Date: 19.10.17
  Time: 13:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
</head>
<body>
<spring:message code="name" var="nameLabel"/>
<spring:message code="lastName" var="lastnNameLabel"/>
<spring:message code="login" var="loginLabel"/>
<spring:message code="password" var="passwordLabel"/>
<spring:message code="email" var="emailLabel"/>
<spring:message code="age" var="ageLabel"/>
<spring:message code="info" var="infoLabel"/>
<spring:message code="rating" var="ratingLabel"/>

<b> ${nameLabel}:</b> ${profile.firstName} <br/>
<b> ${lastnNameLabel}:</b> ${profile.lastName}<br/>
<b> ${emailLabel}:</b> ${profile.email}<br/>
<b> ${ageLabel}:
    <c:if test="${profile.age gt 0}">
</b> ${age.age}
</c:if> <br/>
<b> ${infoLabel}:</b> ${profile.info}<br/>
<b> ${ratingLabel}:</b> ${profile.rating}<br/>
<form:form method="get" action="/profileEdit">
    <input type="submit" value="Edit profile">
</form:form>




</body>
</html>
