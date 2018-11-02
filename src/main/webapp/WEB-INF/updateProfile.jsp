<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/updateProfile.css">
    <jsp:include page="../WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Update Profile" />
    </jsp:include>
</head>
<body>
<jsp:include page="../WEB-INF/partials/navbar.jsp" />

<div class="container div-custom">
    <form class="form-custom" action="/updateProfile" method="post">
        <input required pattern=".*\S+.*" type="email" name="email" value='<c:out value="${sessionScope.user.email}"/>'><br>
        <input required pattern=".*\S+.*" type="text" name="username" value='<c:out value="${sessionScope.user.username}"/>'><br>
        <input type="password" name="oldPassword" placeholder="Old Password"><br>
        <input type="password" name="newPassword" placeholder="New Password"><br>
        <input type="password" name="confirmNewPassword" placeholder="Confirm New Password"><br>
        <input type="submit" value="Update">
    </form>
</div>


</body>
</html>
