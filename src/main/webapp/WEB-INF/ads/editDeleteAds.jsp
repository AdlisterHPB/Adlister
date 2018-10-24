<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Ad Page" />
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />

<div class="container">
    <h1>This Ad</h1>


    <form action="/editAds" method="post">
        <input type="text" name="title" value="${ad.title}"><br>
        <textarea id="description" name="description" class="form-control">${ad.description}</textarea>
        <input type="submit" value="Update"><input type="submit" value="Delete" formaction="/deleteAds" formmethod="post">
    </form>

</div>


</body>
</html>
