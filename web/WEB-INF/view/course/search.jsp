<%-- 
    Document   : search
    Created on : Aug 19, 2022, 12:48:44 PM
    Author     : Thien's
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Search</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="<c:url value="/css/style.css"/>" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>
    <body>
        <c:if test="${not empty sublist}">

            <div class="container">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th></th>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Tuition Fee</th>
                            <th>Start Date</th>
                            <th>End Date</th>
                            <th>Category</th>
                            <th>Status</th>
                            <th>Quantity</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="c" items="${sublist}" varStatus="loop">
                            <tr>
                                <td>${loop.count}</td>
                                <td class="cart-item">
                                    <img src="<c:url value='/images/${c.image}'/>" alt=""/>
                                </td>
                                <td style="font-size: 10pt">${c.name}</td>
                                <td style="font-size: 10pt">${c.description}</td>
                                <td style="text-align: right"><fmt:formatNumber value="${c.tuition}" pattern="#,###.000 đ"/></td>
                                <td style="text-align: right"><fmt:formatDate value="${c.startDate}" pattern="dd-MM-yyyy"/></td>
                                <td style="text-align: right"><fmt:formatDate value="${c.endDate}" pattern="dd-MM-yyyy"/></td>
                                <td style="font-size: 10pt">${c.category}</td>
                                <td style="font-size: 10pt">${c.status}</td>
                                <td style="font-size: 10pt">${c.quantity}</td>
                                <td style="font-size: 10pt">
                                    <form action="<c:url value='/cart/add.do'/>">
                                        <input type="hidden" name="name" value="${c.name}">   
                                        <input type="hidden" name="search" value="${search}">   
                                        <input type="hidden" name="page" value="${page}">   
                                        <input type="hidden" name="tuition" value="${c.tuition}">   
                                        <input type="hidden" name="cId" value="${c.cId}">   
                                        <input type="submit" class="cart-btn" value="Add to cart">
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <ul class="pager">
                    <c:choose>
                        <c:when test="${not empty category}">
                            <c:forEach var="page" items="${noOfPage}" varStatus="loop">
                                <li>
                                    <a href="<c:url value="/course/searchByCategory.do?category=${category}&page=${loop.count}&search=${search}"/>">${loop.count}</a>
                                </li>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="page" items="${noOfPage}" varStatus="loop">
                                <li><a href="<c:url value="/course/search.do?page=${loop.count}&search=${search}"/>">${loop.count}</a></li>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </c:if>
    </body>
</html>
