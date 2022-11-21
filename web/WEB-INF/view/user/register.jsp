<%-- 
    Document   : register
    Created on : Jul 6, 2022, 7:42:14 PM
    Author     : Thien's
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <title>Register</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="<c:url value="/css/site2.css"/>" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
    <div class="container">
        <form  action="<c:url value="/user/register_handler.do"/>"  style="max-width:700px;margin:auto" method="post">
            <h2><span class="glyphicon glyphicon-user"></span> Register</h2>
            <div class="input-container">
                <i class="fa fa-user icon"></i>
                <input class="input-field" type="text" placeholder="Enter Username (6-20 characters)" name="username" value="${username}"  required>
            </div>

            <c:if test="${ not empty err.usernameLength}">
                <font color="red">
                ${err.usernameLength}
                </font>
            </c:if>            
            <c:if test="${ not empty err.usernameIsExisted}">
                <font color="red">
                ${err.usernameIsExisted}
                </font>
            </c:if>

            <div class="input-container">
                <i class="fa fa-key icon"></i>
                <input class="input-field" type="password" placeholder="Enter Password (6-30 characters)" name="password" value=""  required>
            </div> 
            <c:if test="${ not empty err.passwordLength}">
                <font color="red">
                ${err.passwordLength}
                </font>
            </c:if>
            <div class="input-container">
                <i class="fa fa-check icon"></i>
                <input class="input-field" type="password" placeholder="Repeat Your Password" name="rePassword" value="" required>
            </div> 
            <c:if test="${ not empty err.confirmNotMatch}">
                <font color="red">
                ${err.confirmNotMatch}
                </font>
            </c:if>
            <div class="input-container">
                <i class="fa fa-address-card icon"></i>
                <input class="input-field" type="text" placeholder="Enter Your Name (2-50 characters)" name="name" value="${name}" required>
            </div>
            <c:if test="${ not empty err.fullNameLength}">
                <font color="red">
                ${err.fullNameLength}
                </font>
            </c:if>
            <div class="psw">
                <label>
                    &nbsp;
                </label>
                <a href="<c:url value="/user/login.do"/>" >Login</a><br/>
                <a href="<c:url value="/user/register.do"/>">Register</a><br/>
            </div>
            <button type="submit" style="color: white" name="op">Register</button>
        </form>

    </div>
</body>