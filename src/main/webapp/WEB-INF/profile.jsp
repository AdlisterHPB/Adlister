<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Your Profile" />
    </jsp:include>
    <link rel="stylesheet" type="text/css" href="css/styleprofile.css">

</head>
<body>
    <jsp:include page="../WEB-INF/partials/navbar.jsp" />

    <div class="container div-custom">
        <h1>Welcome, <c:out value="${sessionScope.user.username}"/>!</h1>
        <hr>
            <h1>Here are all your ads!</h1>

            <c:forEach var="ad" items="${ads}">
                <div class="col-md-6">
                    <h2><a href=<%="/adPage?id="%>${ad.getId()}><c:out value="${ad.title}"/></a></h2>
                    <hr>
                    <p><c:out value="${ad.description}"/></p>
                    <c:forEach var="category" items="${ad.getCategories()}" varStatus="i">
                        <c:out value="${category.category.concat(!i.last ? ', ': '')}" />
                    </c:forEach>
                </div>
            </c:forEach>
        </div>


</body>
</html>
