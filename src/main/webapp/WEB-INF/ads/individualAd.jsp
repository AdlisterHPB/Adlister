<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: brittany
  Date: 10/22/18
  Time: 12:25 PM
  To change this template use File | Settings | File Templates.
--%>
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


        <div class="col-md-6">
            <h2>${ad.title}</h2>
            <p>${ad.description}</p>

            <c:forEach var="category" items="${ad.getCategories()}" varStatus="i">
                <c:out value="${category.category.concat(!i.last ? ', ': '')}" />
            </c:forEach>
            <c:if test="${sessionScope.user != null && sessionScope.user.id == ad.userId}">
                <a href=<%="/editAds?id="%>${ad.id}><button>Edit</button></a>
            </c:if>
        </div>

</div>


</body>
</html>
