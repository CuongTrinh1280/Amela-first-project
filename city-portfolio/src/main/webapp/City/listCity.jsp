<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cities List</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
          crossorigin="anonymous">
</head>
<head>
    <title>City Portfolio Management Application</title>
</head>
<body>
<center>
    <h1>City Management</h1>
    <h2>
        <a href="/ReadCity?action=create">Add New City</a>
    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of City</h2></caption>
        <tr>
            <th>ID</th>
            <th>City Name</th>
            <th>Country</th>
            <th>Area</th>
            <th>Population</th>
            <th>GDP</th>
            <th>Description</th>
            <th>Edit</th>
            <th>Delete</th>
            <th>View</th>
        </tr>
        <c:forEach var="city" items="${listCity}">
            <tr>
                <td>
                        <c:out value="${city.cityId}"/>
                </td>
                <td><c:out value="${city.cityName}"/></td>
                <td><c:out value="${city.country}"/></td>
                <td><c:out value="${city.areaCountry}"/></td>
                <td><c:out value="${city.population}"/></td>
                <td><c:out value="${city.GDP}"/></td>
                <td><c:out value="${city.des}"/></td>
                <td>
                    <a href="/ReadCity?action=edit&id=${city.cityId}">Edit</a>
                </td>
                <td>
                    <a href="/ReadCity?action=delete&id=${city.cityId}">Delete</a>
                </td>
                <td>
                    <a href="/ReadCity?action=view&id=${city.cityId}">View</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>