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
                <h2>Add New City</h2>
            </caption>
            <tr>
                <th>City Name:</th>
                <td>
                    <input type="text" name="cityName" id="cityName" size="45"/>
                </td>
            </tr>
            <tr>
                <th>Country:</th>
                <td>
                    <input type="text" name="countryName" id="countryName" size="45"/>
                </td>
            </tr>
            <tr>
                <th>Area:</th>
                <td>
                    <input type="text" name="area" id="area" size="15"/>
                </td>
            </tr><tr>
                <th>Population:</th>
                <td>
                    <input type="text" name="population" id="population" size="15"/>
                </td>
            </tr><tr>
                <th>GDP:</th>
                <td>
                    <input type="text" name="GDP" id="GDP" size="15"/>
                </td>
            </tr><tr>
                <th>Description:</th>
                <td>
                    <input type="text" name="des" id="des" size="15"/>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save"/>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>