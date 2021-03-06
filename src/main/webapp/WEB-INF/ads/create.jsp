<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Create a new Ad" />
    </jsp:include>
    <link rel="stylesheet" type="text/css" href="../../css/createAd.css">
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />
<c:set var="title" value="${sessionScope.title != null ? sessionScope.title : ''}" scope="session" />
<c:set var="description" value="${sessionScope.description != null ? sessionScope.description : ''}" scope="session"/>

    <div class="container">
        <h1>Create a new Ad</h1>
        <form action="/ads/create" method="post">
            <div class="form-group">
                <label for="title">Title</label>
                <input required pattern=".*\S+.*" id="title" name="title" class="form-control" type="text" value='<c:out value="${title}" />'>
            </div>
            <div class="form-group">
                <label for="description">Description</label>
                <textarea id="description" name="description" class="form-control" type="text"><c:out value="${description}" /></textarea>
            </div>
            <select name="Category1" id="Category1" required>
                <option value="" selected disabled hidden>Choose here</option>
            <c:forEach var="category" items="${categories}">
                <option value='<c:out value="${category.category}"/>'><c:out value="${category.category}"/></option>
            </c:forEach>
            </select>
            <select name="Category2" id="Category2">
                <option value="" selected disabled hidden>Choose here</option>
                <c:forEach var="category" items="${categories}">
                    <option value='<c:out value="${category.category}"/>'><c:out value="${category.category}"/></option>
                </c:forEach>
            </select>
            <select name="Category3" id="Category3">
                <option value="" selected disabled hidden>Choose here</option>
                <c:forEach var="category" items="${categories}">
                    <option value='<c:out value="${category.category}"/>'><c:out value="${category.category}"/></option>
                </c:forEach>
            </select>
            <input type="submit" class="btn btn-block btn-primary">
        </form>
    </div>
</body>
</html>
