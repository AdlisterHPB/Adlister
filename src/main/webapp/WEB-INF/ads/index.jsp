<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Viewing All The Ads" />
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />

<div class="container">
    <h1>Here Are all the ads!</h1>

    <c:forEach var="ad" items="${ads}">
        <div class="col-md-6">
            <h2><a href=<%="/adPage?id="%>${ad.getId()}>${ad.title}</a></h2>
            <p>${ad.description}</p>
        </div>
    </c:forEach>
</div>

<div class="container">
    <h1>Here Are all the ads!</h1>

    <c:forEach var="joiner" items="${joiners}">
        <div class="col-md-6">
           <p>${joiner.getAd_id()}</p>
            <p>${joiner.getCategory_id()}</p>
        </div>
    </c:forEach>
</div>
<div class="container">
    <h1>Here Are all the ads!</h1>

    <c:forEach var="category" items="${categories}">
        <div class="col-md-6">
            <p>${category.category}</p>

        </div>
    </c:forEach>
</div>



</body>
</html>
