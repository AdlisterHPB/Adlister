<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <input required pattern=".*\S+.*" type="text" name="title" value='<c:out value="${ad.title}"/>'><br>
        <textarea id="description" name="description" class="form-control"><c:out value="${ad.description}"/></textarea>
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
        <input type="submit" value="Update"><input type="submit" value="Delete" formaction="/deleteAds" formmethod="post">
    </form>

</div>


</body>
</html>
