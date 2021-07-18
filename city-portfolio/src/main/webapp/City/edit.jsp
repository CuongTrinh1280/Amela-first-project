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
          Edit City
        </h2>
      </caption>
      <c:if test="${city != null}">
        <input type="hidden" name="id" value="<c:out value='${city.cityId}' />"/>
      </c:if>
      <tr>
        <th>City Name:</th>
        <td>
          <input type="text" name="cityName" size="45"
                 value="<c:out value='${city.cityName}' />"
          />
        </td>
      </tr>
      <tr>
        <th>Country:</th>
        <td>
          <input type="text" name="countryName" size="45"
                 value="<c:out value='${city.country}' />"
          />
        </td>
      </tr>
      <tr>
        <th>Area:</th>
        <td>
          <input type="text" name="area" size="15"
                 value="<c:out value='${city.areaCountry}' />"
          />
        </td>
      </tr>
      <tr>
        <th>Population:</th>
        <td>
          <input type="text" name="population" size="45"
                 value="<c:out value='${city.population}' />"
          />
        </td>
      </tr>
      <tr>
        <th>GDP:</th>
        <td>
          <input type="text" name="GDP" size="45"
                 value="<c:out value='${city.GDP}' />"
          />
        </td>
      </tr>
      <tr>
        <th>Description:</th>
        <td>
          <textarea name='des'
                    style="width: 100%;height: 100px;"><c:out value='${city.des}'></c:out>
          </textarea>
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