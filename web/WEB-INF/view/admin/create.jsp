<%-- 
    Document   : create
    Created on : Aug 21, 2022, 11:58:55 PM
    Author     : Thien's
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="<c:url value="/css/site2.css"/>" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>
    <body>
        <div class="container">

            <form action="<c:url value="/admin/create_handler.do"/>" enctype="multipart/form-data" method="post">
                <h2>Create New Course</h2>
                <div class="input-container">
                    Choose Image:
                    <input type="file" name="file2" required/>
                </div>
                <div class="input-container">
                    <input class="input-field" type="text" name="name" value="" placeholder="Enter Course's Name" required>
                </div>
                <div class="input-container">
                    <input class="input-field" type="text" name="description" value="" placeholder="Enter Course's Description" required>
                </div>
                <div class="input-container">
                    <input class="input-field" style="width: 20%" class="input-field" type="number" name="tuition" value="" min="100" step="0.5"
                          placeholder="Enter Tuition Fee (.000 đ)" required>
                </div>
                <div class="input-container">
                    <input class="input-field" style="width: 20%" type="date" name="startDate"  required>
                </div>
                <div class="input-container">
                    <input class="input-field" style="width: 20%" type="date" name="endDate"  required>
                </div>
                <div class="input-container">
                    <select name ="category" >
                        <c:forEach var="cate" items="${categoryList}">
                            <option value="${cate}">${cate}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="input-container">
                    <select name ="status">
                        <c:forEach var="status" items="${statusList}">
                            <option value="${status}" ${status == 'active'?'selected':''}>${status}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="input-container">
                    <input class="input-field" style="width: 12%" type="number" name="quantity" value="" min="0" step="1" placeholder="Enter Quantity" required>
                </div>
                <button type="submit" style="color: white" name="op">Create</button>
            </form>
        </div>
    </body>
</html>
