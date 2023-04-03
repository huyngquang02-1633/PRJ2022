<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Index</title>
        <%
            String path = request.getContextPath();
        %>
        <link href="<%=path%>/css/style.css" rel="stylesheet"/>
    </head>
    <body>
        <div id="container">
            <div id="header">
                <div id="logo">
                    <c:url value="/home" var="logo"></c:url>
                    <c:url value="/shopping/order" var="cart"></c:url>
                    <c:url value="/account/signin" var="signin"></c:url>
                    <c:url value="/account/signup" var="signup"></c:url>
                    <c:url value="/account/profile" var="profile"></c:url>
                    <c:url value="/account/signin" var="signout"></c:url>

                        <a href="${logo}"><img src="<%=path%>/img/logo.png"/></a>

                </div>

                <div id="banner">
                    <c:if test="${ordermsg!=null}">
                        <p style="color: green">${ordermsg}</p>
                    </c:if>
                    <c:if test="${updatemsg!=null}">
                        <p style="color: green">${updatemsg}</p>
                    </c:if>

                    <ul>
                        <form action="<%=path%>/search" method="post">
                            <input type="text" name="txt" value="${txt}"/>
                            <input type="submit" value="Search"/>
                        </form>
                        <li><a href="${cart}">Cart: ${sessionScope.size}</a></li>
                            <c:if test="${sessionScope.AccSession==null}">
                            <li><a href="${signin}">SignIn</a></li>
                            <li><a href="${signup}">SignUp</a></li>
                            </c:if>
                            <c:if test="${sessionScope.AccSession!=null}">
                            <li><a href="${profile}">Profile</a></li>
                            <li><a href="${signout}">SignOut</a></li>
                            </c:if>
                    </ul>
                </div>
            </div>
            <div id="content">