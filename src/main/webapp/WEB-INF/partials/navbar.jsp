<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-lg navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <a class="navbar-brand" href="/ads">Adlister</a>
        </div>
        <ul class="nav  navbar-nav navbar-right">

            <c:choose>
            <c:when test ="${sessionScope.user == null}">
                <li><a href="/login">Login</a></li>
                <li><a href="/register">Register</a></li>
            </c:when>
                <c:otherwise>
                    <li><a href="/ads/create">Create a Ad!</a></li>
                    <li><a href="/logout">Logout</a></li>
                    <li><a href="/updateProfile">Edit Profile</a></li>
                    <li><a href="/profile">Your Ads</a></li>

                </c:otherwise>
            </c:choose>
            <li><form role="search" action="/search" method="GET">
                <input type="search" name="search"/>
                <input type="submit" >
            </form>
            </li>
        </ul>
    </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
