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
    <title>City Management Application</title>
</head>
<body>
<center>
    <h1>City Management</h1>
    <h2>
        <a href="ReadCity?action=list">List All Cities</a>
    </h2>
</center>
<div align="center">
    <form method="post">
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    View City
                </h2>
            </caption>
            <c:if test="${city != null}">
                <input type="hidden" name="id" value="<c:out value='${city.cityId}' />"/>
            </c:if>
            <tr>
                <td>City Name:</td>
                <td>
                    ${city.cityName}
                </td>
            </tr>
            <tr>
                <td>Country:</td>
                <td>
                    ${city.country}
                </td>
            </tr>
            <tr>
                <td>Area:</td>
                <td>
                    ${city.areaCountry}
                </td>
            </tr>
            <tr>
                <td>Population:</td>
                <td>
                    ${city.population}
                </td>
            </tr>
            <tr>
                <td>GDP:</td>
                <td>
                   ${city.GDP}
                </td>
            </tr>
            <tr>
                <td>Description:</td>
                <td>
                  ${city.des}
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>