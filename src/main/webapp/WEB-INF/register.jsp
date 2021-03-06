<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <jsp:include page="partials/head.jsp">
        <jsp:param name="title" value="Register For Our Site!" />
    </jsp:include>
    <link rel="stylesheet" type="text/css" href="css/register.css">
</head>
<body>

    <c:set var="username" value="${sessionScope.username != null ? sessionScope.username : ''}" scope="session" />
    <c:set var="email" value="${sessionScope.email != null ? sessionScope.email : ''}" scope="session"/>


    <jsp:include page="partials/navbar.jsp" />
    <div class="container">
        <h1>Please fill in your information.</h1>
        <form action="/register" method="post">
            <div class="form-group">
                <label for="username">Username</label>
                <input required pattern=".*\S+.*" id="username" name="username" class="form-control" type="text" value='<c:out value="${username}" />'>
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input required pattern=".*\S+.*" id="email" name="email" class="form-control" type="email" value='<c:out value="${email}" />'>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input required pattern=".*\S+.*" id="password" name="password" class="form-control" type="password">
            </div>
            <div class="form-group">
                <label for="confirm_password">Confirm Password</label>
                <input required pattern=".*\S+.*" id="confirm_password" name="confirm_password" class="form-control" type="password">
            </div>
            <input type="submit" class="btn btn-custom btn-block" id="sbumitForLogin">
        </form>
    </div>
</body>
</html>
