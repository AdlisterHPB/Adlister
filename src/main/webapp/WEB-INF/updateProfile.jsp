<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Update Profile" />
    </jsp:include>
</head>
<body>
<jsp:include page="../WEB-INF/partials/navbar.jsp" />

<div class="container">
    <form action="/updateProfile" method="post">
        <input type="email" name="email" value="${sessionScope.user.email}"><br>
        <input type="text" name="username" value="${sessionScope.user.username}"><br>
        <input type="password" name="oldPassword" placeholder="Old Password"><br>
        <input type="password" name="newPassword" placeholder="New Password"><br>
        <input type="password" name="confirmNewPassword" placeholder="Confirm New Password"><br>
        <input type="submit" value="Update">
    </form>
</div>


</body>
</html>
