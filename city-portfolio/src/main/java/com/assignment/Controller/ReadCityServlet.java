package com.assignment.Controller;

import com.assignment.DAO.CityDAO;
import com.assignment.Model.City;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

@WebServlet(name = "ReadCityServlet", value = "/ReadCity")
public class ReadCityServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private CityDAO cityDAO;

    public void init() {
        cityDAO = new CityDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    showNewForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteCity(request, response);
                    break;
                case "view":
                    showViewForm(request, response);
                    break;
                case "search":
                    showSearchForm(request, response);
                    break;
                case "list":
                    listCity(request, response);
                    break;
                default:
                    listCities(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    insertCity(request, response);
                    break;
                case "edit":
                    updateCity(request, response);
                    break;
                case "search":
                    searchCity(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void searchCity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String cityName = request.getParameter("cityName");
        City res = cityDAO.searchCity(cityName);

        City city = cityDAO.selectCity(res.getCityId());
        request.setAttribute("city", city);
        RequestDispatcher dispatcher = request.getRequestDispatcher("City/view.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listCities(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        int recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));

        List<City> cities = cityDAO.findCitiesOnePage(currentPage,
                recordsPerPage);

        request.setAttribute("cityList", cities);

        int rows = cityDAO.getNumberOfRows();

        int nOfPages = rows / recordsPerPage;

        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }

        request.setAttribute("noOfPages", nOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);

        RequestDispatcher dispatcher = request.getRequestDispatcher("City/listCities.jsp");

        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listCity(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<City> listCity = cityDAO.selectCities();
        request.setAttribute("listCity", listCity);
        RequestDispatcher dispatcher = request.getRequestDispatcher("City/listCity.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("City/create.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        City existingCity = cityDAO.selectCity(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("City/edit.jsp");
        request.setAttribute("city", existingCity);
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showViewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        City existingCity = cityDAO.selectCity(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("City/view.jsp");
        request.setAttribute("city", existingCity);
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showSearchForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("City/view.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void insertCity(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String cityName = request.getParameter("cityName");
        String country = request.getParameter("countryName");
        float area = Float.parseFloat(request.getParameter("area"));
        int population = Integer.parseInt(request.getParameter("population"));
        int GDP = Integer.parseInt(request.getParameter("GDP"));
        String description = request.getParameter("des");

        City newCity = new City(cityName, country, area, population, GDP, description);

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<City>> constraintViolations = validator.validate(newCity);
        if (!constraintViolations.isEmpty()) {
            String errors = "<ul>";
            for (ConstraintViolation<City> constraintViolation : constraintViolations) {
                errors += "<li>" + constraintViolation.getPropertyPath() + " " + constraintViolation.getMessage()
                        + "</li>";
            }
            errors += "</ul>";

            request.setAttribute("errors", errors);
        } else {
            cityDAO.insertNewCity(newCity);
        }
        request.getRequestDispatcher("City/create.jsp").forward(request, response);
    }

    private void updateCity(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String cityName = request.getParameter("cityName");
        String country = request.getParameter("countryName");
        float area = Float.parseFloat(request.getParameter("area"));
        int population = Integer.parseInt(request.getParameter("population"));
        int GDP = Integer.parseInt(request.getParameter("GDP"));
        String description = request.getParameter("des");

        City update = new City(id, cityName, country, area, population, GDP, description);
        cityDAO.updateCity(update);
        RequestDispatcher dispatcher = request.getRequestDispatcher("City/edit.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteCity(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        cityDAO.deleteCity(id);

        List<City> listCity = cityDAO.selectCities();
        request.setAttribute("listCity", listCity);
        RequestDispatcher dispatcher = request.getRequestDispatcher("City/listCity.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
