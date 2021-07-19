<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cities List</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
          crossorigin="anonymous">
</head>

<body>

    <header>
        <nav class="navbar navbar-expand-md navbar-dark" style="background-color: blueviolet">
            <div>
                <a class="navbar-brand"> City Portfolio </a>
            </div>

            <ul class="navbar-nav">
                <li><a href="<%=request.getContextPath()%>/ReadCity?action=list" class="nav-link">City List</a></li>
                <li><a href="<%=request.getContextPath()%>/ReadCity?action=create" class="nav-link">Add New City</a></li>
            </ul>
        </nav>
    </header>
    <br>

    <div align="center">
        <main class="m-3">
            <div class="row col-md-6" style="justify-content: center;">
                <form action="/ReadCity?action=search$cityName='${city.cityName}'" method="post">
                    <div class="form-group has-search">
                        <span class="fa fa-search form-control-feedback"></span>
                        <input type="text" class="form-control" placeholder="Search by city name" name="cityName">
                    </div>

                    <p class="mt-4 text-center">
                        <a>Search city here !</a>
                    </p>
                </form>
            </div>
            <div class="row col-md-6">
                <table class="table table-striped table-bordered table-sm" cellpadding="8">
                    <tr>
                        <th>ID</th>
                        <th>City</th>
                        <th>Country</th>
                        <th>Area</th>
                        <th>Population</th>
                        <th>GDP</th>
                        <th>Description</th>
                        <th>Edit</th>
                        <th>Delete</th>
                        <th>View</th>
                    </tr>

                    <c:forEach items="${cityList}" var="city">
                        <tr>
                            <td>${city.cityId}</td>
                            <td>${city.cityName}</td>
                            <td>${city.country}</td>
                            <td>${city.areaCountry}</td>
                            <td>${city.population}</td>
                            <td>${city.GDP}</td>
                            <td>${city.des}</td>
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

            <div align="center" style="margin-left: 50%; transform: translateX(-15%); justify-content: center;">
                <nav aria-label="Navigation for cities">
                    <ul class="pagination">
                        <c:if test="${currentPage != 1}">
                            <li class="page-item">
                                <a class="page-link" href="ReadCity?recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}">Previous</a>
                            </li>
                        </c:if>

                        <c:forEach begin="1" end="${noOfPages}" var="i">
                            <c:choose>
                                <c:when test="${currentPage eq i}">
                                    <li class="page-item active">
                                        <a class="page-link">
                                                ${i} <span class="sr-only">(current)</span>
                                        </a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item">
                                        <a class="page-link" href="ReadCity?recordsPerPage=${recordsPerPage}&currentPage=${i}">${i}</a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>

                        <c:if test="${currentPage lt noOfPages}">
                            <li class="page-item">
                                <a class="page-link" href="ReadCity?recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}">Next</a>
                            </li>
                        </c:if>
                    </ul>
                </nav>
            </div>
        </main>
    </div>

</body>
</html>