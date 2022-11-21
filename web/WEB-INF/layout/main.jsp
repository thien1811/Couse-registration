<%-- 
    Document   : main
    Created on : Jul 6, 2022, 10:14:23 AM
    Author     : ThienHoang 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <!--        <title>Home Page</title>-->
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="<c:url value="/css/site.css"/>" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    </head>
    <body>

        <div class="container-fluid text-center">

            <div class="row" >

                <nav class="navbar navbar-inverse bg-primary" role="navigation" style="padding-top: 20px;">
                    <div class="container-fluid">
                        <!--Brand and toggle get grouped for better mobile display--> 
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                                <span class="sr-only">Toggle navigation</span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </button>
                        </div>


                        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                            <div class="text-center col-md-4" >
                                <ul class="nav navbar-nav" >
                                    <li style="margin-left: 30px; margin-right: 30px">
                                        <a href="<c:url value="#"/>" style="color: white">Category</a>
                                        <c:choose>
                                            <c:when test="${isAdmin}">

                                                <ul>
                                                    <li><a class="link" href="<c:url value="/admin/searchByCategory.do?category=Piano"/>">Piano</a></li>
                                                    <li><a class="link" href="<c:url value="/admin/searchByCategory.do?category=Guitar"/>">Guitar</a></li>
                                                    <li><a class="link" href="<c:url value="/admin/searchByCategory.do?category=Drawing"/>">Drawing</a></li>
                                                </ul>
                                            </c:when>
                                            <c:otherwise>

                                                <ul>
                                                    <li><a class="link" href="<c:url value="/course/searchByCategory.do?category=Piano"/>">Piano</a></li>
                                                    <li><a class="link" href="<c:url value="/course/searchByCategory.do?category=Guitar"/>">Guitar</a></li>
                                                    <li><a class="link" href="<c:url value="/course/searchByCategory.do?category=Drawing"/>">Drawing</a></li>
                                                </ul>
                                            </c:otherwise>
                                        </c:choose>
                                    </li>
                                    <c:if test="${isAdmin}">
                                        <li style="margin-left: 30px; margin-right: 30px">
                                            <a href="<c:url value="/admin/create.do"/>" style="color: white">Create</a>
                                        </li>
                                    </c:if>
                                </ul>
                            </div>

                            <div class="col-md-4">  
                                <c:choose>
                                    <c:when test="${isAdmin}">
                                        <form class="example  text-center" style="color: black; margin-left: 50px" action="<c:url value="/admin/search.do" />" method="post">
                                            <input type="text" placeholder="Enter Course's Name" name="search" value="${search}"/>
                                            <input type="hidden"  name="page" value="1"/>
                                            <button type="submit"><i class="fa fa-search"></i></button>
                                        </form>
                                    </c:when>
                                    <c:otherwise>
                                        <form class="example  text-center" style="color: black; margin-left: 50px" action="<c:url value="/course/search.do" />" method="post">
                                            <input type="text" placeholder="Enter Course's Name" name="search" value="${search}"/>
                                            <input type="hidden"  name="page" value="1"/>
                                            <button type="submit"><i class="fa fa-search"></i></button>
                                        </form>
                                    </c:otherwise>
                                </c:choose>
                            </div>

                            <div  class="col-md-4 ">
                                <c:if test="${!isAdmin}">
                                    <a class="text-center"  href="<c:url value="/cart/view.do"/>" style="text-align: center;position: relative;">
                                        <i class="fa fa-shopping-cart" style="font-size:30px; "></i>
                                        <sup style="position: absolute;font-size: 10pt; top: 15px; right: 0px;color: red"><strong>${count}</strong></sup>
                                    </a>
                                </c:if>
                                <c:choose>
                                    <c:when test="${not empty username}">
                                        <ul class="login">
                                            <li>
                                                <a class="link" href="<c:url value="/user/login.do"/>">Welcome, ${username}</a>
                                                <ul>
                                                    <li><a class="link" href="<c:url value="/user/logout.do"/>">Logout</a></li>
                                                </ul>
                                            </li>
                                        </ul>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="<c:url value="/user/login.do"/>">
                                            <i class="fa fa-user" style="font-size:30px"></i>
                                        </a>
                                    </c:otherwise>
                                </c:choose>
                            </div>

                        </div> <!-- /.navbar-collapse -->
                    </div>  <!--/.container-fluid--> 
                </nav>

            </div>
        </div>

        <!--Content-->
        <div class="container-fluid text-center row ">
            <div class="col" style="min-height: 500px">
                <jsp:include page="/WEB-INF/view/${controller}/${action}.jsp"/>
            </div>
        </div>

        <div class="text-center row " style="margin-top: 100px;">
            <div class="footer ">
                <div class="col-md-4 " >
                </div>

                <div class="text-center  col-md-4" style="" >
                    <h5>&copy; Copyright 2022.</h5>
                </div>

                <div class="col-md-4" >
                </div>

            </div>
        </div>

    </body>
</html>

