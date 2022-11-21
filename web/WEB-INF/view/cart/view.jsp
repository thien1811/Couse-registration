<%-- 
    Document   : cartview
    Created on : Jul 4, 2022, 5:16:19 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<c:set var="locale" value="vi_VN" scope="page"/>
<fmt:setLocale value="vi_VN"/>
<html>
    <head>
        <title> Cart</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <link href="<c:url value="/css/style.css"/>" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    </head>
    <body>
        <div class="card container">
            <div class="card-header">
                <h4>
                    Your Cart
                </h4> 
            </div>
            <div class="form-group">
                <table class="table">
                    <thead>
                        <tr>
                            <th >No.</th>
                            <th >Course's Name</th>
                            <th >Tuition Fee</th>
                            <th  style="text-align: center;">Amount</th>
                            <th >Total</th>
                            <th ></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="total" value="0"/>
                        <c:forEach var="cart" items="${list}" varStatus="loop">
                            <tr>
                                <td style="text-align: left;font-size: 15pt ">${loop.count}</td>
                                <td style="text-align: left;font-size: 20px">${cart.name}</td>
                                <td style="text-align: left; font-size: 15pt;" ><fmt:formatNumber value="${cart.tuition}" type="currency" pattern="#,###.000 đ"/></td>
                                <form action="<c:url value='/cart/update.do'/>">
                                    <td style="text-align: center;font-size: 15pt">
                                        <input style="width: 15%" type="number" name="quantity" value="${cart.quantity}" min="1" step="1" >
                                        <input type="hidden" name="name" value="${cart.name}">   
                                        <input type="hidden" name="tuition" value="${cart.tuition}">   
                                        <input type="hidden" name="cId" value="${cart.cId}">   
                                        <button type="submit">Update</button>
                                    </td>
                                </form>
                            <td style="text-align: left; font-size: 15pt;" ><fmt:formatNumber value="${cart.quantity * cart.tuition}" type="currency" pattern="#,###.000 đ"/></td>
                            <td >
                                <a href="<c:url value="/cart/delete.do?cId=${cart.cId}"/>">Delete</a>
                            </td>
                            </tr>
                            <c:if test="${cId == cart.cId}">
                                <tr>
                                    <td  colspan="4"  style="text-align: right;font-size: 15pt">Are you sure to remove this item from your cart?</td>
                                    <td><a href="<c:url value="/cart/delete_handler.do?cId=${cart.cId}"/>">Yes</a></td>
                                    <td><a href="<c:url value="/cart/view.do"/>">No</a></td>
                                </tr>
                            </c:if>
                        <c:set var="total" value="${total + cart.quantity * cart.tuition}"/>
                    </c:forEach>
                    <tr>
                        <th colspan="5" style="text-align: right; font-size: 15pt;" >Total Fee:</th>
                        <th style="text-align: center; font-size: 15pt;" ><fmt:formatNumber value="${total}" type="currency" pattern="#,###.000 đ"/></th>
                    </tr>
                    </tbody>
                </table>
            </div>
            <c:choose>
                <c:when test="${not empty username}">
                    <form action="<c:url value='/cart/checkout.do'/>">
                        <select name="payment">
                            <option selected>Cash Payment</option>
                            <option>Pay online via Paypal</option>
                        </select>
                        <button class="cart-btn" type="submit" name="total" value="${total}">Check out</button>  
                    </form>
                </c:when>
                <c:otherwise>
                    <form action="<c:url value='/cart/information.do'/>">
                        <table class="table table-striped info" style="text-align: right;">
                            <tr>
                                <td><Strong>Name:</Strong></td>
                                <td>
                                    <input type="text" name="name" value="" style="float: left" required>
                                </td>
                            </tr>
                            <tr>
                                <td><Strong>Phone:</Strong></td>
                                <td>
                                    <input type="tel" name="phone" value="" style="float: left" required>
                                </td>
                            </tr>
                            <tr>
                                <td><Strong>Email:</Strong></td>
                                <td>
                                    <input type="email" name="email" value="" style="float: left" required>
                                </td>
                            </tr>
                        </table>
                        <select name="payment">
                            <option selected>Cash Payment</option>
                            <option>Pay online via Paypal</option>
                        </select>
                        <button class="cart-btn" type="submit" name="total" value="${total}">Check out</button>  
                    </form>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>
