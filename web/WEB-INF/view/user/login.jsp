<%-- 
    Document   : login
    Created on : Aug 19, 2022, 11:33:51 AM
    Author     : Thien's
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="<c:url value="/css/site2.css"/>" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>
    <body>
        <div class="container">
            <form  action="<c:url value="/user/login_handler.do"/>"  style="max-width:500px;margin:auto" method="post">
                <h2><span class="glyphicon glyphicon-lock"></span> Login</h2>
                <div class="input-container">
                    <i class="fa fa-user icon"></i>
                    <input class="input-field" type="text" placeholder="Enter Username" name="username" value="${username}" required>
                </div>

                <div class="input-container">
                    <i class="fa fa-key icon"></i>
                    <input class="input-field" type="password" placeholder="Enter Password" name="password" required>
                </div> 
                <div class="psw">
                    <label>
                        <input  type="checkbox" checked="checked" name="remember"> Remember me
                    </label>
                    <a href="<c:url value="/user/login.do"/>" >Login</a><br/>
                    <a href="<c:url value="/user/register.do"/>">Register</a><br/>
                </div>
                <i>${message}</i>
                <button  type="submit" style="color: white" >Login</button>
            </form>
                
        </div>
    </body>
</html>