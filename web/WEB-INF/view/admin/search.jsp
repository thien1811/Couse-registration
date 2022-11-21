<%-- 
    Document   : search
    Created on : Aug 19, 2022, 12:48:44 PM
    Author     : Thien's
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
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
        <title>Login</title>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="<c:url value="/css/style.css"/>" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>
    <body>
        <c:if test="${not empty sublist}">

            <table class="table table-striped">
                <thead>
                    <tr>
                        <th style="text-align: center">No.</th>
                        <th></th>
                        <th style="text-align: center">Name</th>
                        <th style="text-align: center">Description</th>
                        <th style="text-align: center">Tuition Fee</th>
                        <th style="text-align: center">Start Date</th>
                        <th style="text-align: center">End Date</th>
                        <th style="text-align: center">Category</th>
                        <th style="text-align: center">Status</th>
                        <th style="text-align: center">Quantity</th>
                        <th style="text-align: center">Update</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="c" items="${sublist}" varStatus="loop">
                    <form  action="<c:url value="/admin/update.do"/>" enctype="multipart/form-data" method="post">
                        <tr>
                        <input type="hidden" name="cId" value="${c.cId}"/>
                        <input type="hidden" name="page" value="${page}"/>
                        <input type="hidden" name="search" value="${search}"/>
                        <td>${loop.count}</td>
                        <td class="cart-item">
                            <img src="<c:url value='/images/${c.image}'/>" alt=""/><br/>
                            <input type="file" name="file2"/>
                        </td>
                        <td style="font-size: 10pt">
                            <input type="text" name="name" value="${c.name}" required>
                        </td>
                        <td style="font-size: 10pt">
                            <input type="text" name="description" value="${c.description}" required>
                        </td>
                        <td style="text-align: center">
                            <input class="input-field" type="number" name="tuition" value="${c.tuition}" min="100" step="0.5" required>
                        </td>
                        <td style="text-align: right">
                            <input type="date" name="startDate" value="<fmt:formatDate value="${c.startDate}" pattern="yyyy-MM-dd" />">
                        </td>
                        <td style="text-align: right">
                            <input type="date" name="endDate" value="<fmt:formatDate value="${c.endDate}" pattern="yyyy-MM-dd" />">
                        </td>
                        <td style="font-size: 10pt">
                            <select name ="category">
                                <c:forEach var="cate" items="${categoryList}">
                                    <option value="${cate}" ${c.category==cate?'selected':''}>${cate}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td style="font-size: 10pt">
                            <select name ="status">
                                <c:forEach var="status" items="${statusList}">
                                    <option value="${status}" ${c.status==status?'selected':''}>${status}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td style="font-size: 12pt" >
                            <input class="input-field" type="number" name="quantity" value="${c.quantity}" min="0" step="1" required>
                        </td>
                        <td><input type="submit" name="op" value="Update"/></td>
                        </tr>
                    </form>
                </c:forEach>
            </tbody>
        </table>
        <ul class="pager">
            <c:choose>
                <c:when test="${not empty category}">
                    <c:forEach var="page" items="${noOfPage}" varStatus="loop">
                        <li>
                            <a href="<c:url value="/admin/searchByCategory.do?category=${category}&page=${loop.count}&search=${search}"/>">${loop.count}</a>
                        </li>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <c:forEach var="page" items="${noOfPage}" varStatus="loop">
                        <li><a href="<c:url value="/admin/search.do?page=${loop.count}&search=${search}"/>">${loop.count}</a></li>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
        </ul>
    </c:if>
</body>
</html>
