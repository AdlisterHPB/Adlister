<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Viewing All The Ads" />
    </jsp:include>
    <link rel="stylesheet" type="text/css" href="css/allAds.css">
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />

<div class="container">
    <h1>Here Are all the ads!</h1>

    <c:forEach var="ad" items="${ads}">
        <div class="col-md-6">
            <h5>Categories</h5>
            <p><c:forEach var="category" items="${ad.getCategories()}" varStatus="i">
                <c:out value="${category.category.concat(!i.last ? ', ': '')}" />
            </c:forEach></p>
            <h2><a href=<%="/adPage?id="%>${ad.getId()}>${ad.title}</a></h2>
            <p><c:out value="${ad.description}"/></p>
        </div>
    </c:forEach>

</div>




</body>
</html>
