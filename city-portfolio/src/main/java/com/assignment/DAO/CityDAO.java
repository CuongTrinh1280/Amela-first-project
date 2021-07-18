package com.assignment.DAO;

import com.assignment.Model.City;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

public class CityDAO implements ICityDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3307/city_portfolio?useUnicode=yes&characterEncoding=UTF-8";
    private String jdbcUsername = "root";
    private String jdbcPassword = "cuongthan1280";

    private static final String INSERT_CITIES_SQL = "INSERT INTO city_portfolio.countries"
            + " (city, country, area, population, GDP, des) VALUES "
            + " (?, ?, ?, ?, ?, ?);";
    private static final String SELECT_CITY_BY_ID = "SELECT * FROM city_portfolio.countries WHERE id = ?;";
    private static final String SELECT_ALL_CITIES = "SELECT * FROM city_portfolio.countries;";
    private static final String DELETE_CITY_SQL = "DELETE FROM city_portfolio.countries WHERE id = ?;";
    private static final String UPDATE_CITY_SQL =
            "UPDATE city_portfolio.countries SET city = ?, country = ?, area = ?, population = ?, GDP = ?, des = ? WHERE id = ?;";
    private static final String SELECT_RECORDS_PER_PAGE = "SELECT * FROM city_portfolio.countries LIMIT ?, ?;";

    public CityDAO() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void insertNewCity(City city) throws SQLException {
        System.out.println(INSERT_CITIES_SQL);

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CITIES_SQL);)
        {
            preparedStatement.setString(1, city.getCityName());
            preparedStatement.setString(2, city.getCountry());
            preparedStatement.setFloat(3, city.getAreaCountry());
            preparedStatement.setInt(4, city.getPopulation());
            preparedStatement.setInt(5, city.getGDP());
            preparedStatement.setString(6, city.getDes());

            System.out.println(preparedStatement);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public City selectCity(int id) {
        City city = null;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CITY_BY_ID);)
        {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);

            ResultSet rs =  preparedStatement.executeQuery();

            while (rs.next()) {
                String cityName = rs.getString("city");
                String countryName = rs.getString("country");
                float area = rs.getFloat("area");
                int population = rs.getInt("population");
                int GDP = rs.getInt("GDP");
                String des = rs.getString("des");

                city = new City(id, cityName, countryName, area, population, GDP, des);
            }

        } catch (SQLException e) {
            printSQLException(e);
        }
        return city;
    }

    @Override
    public List<City> selectCities() {
        List<City> cities = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CITIES);)
        {
            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String cityName = rs.getString("city");
                String countryName = rs.getString("country");
                float area = rs.getFloat("area");
                int population = rs.getInt("population");
                int GDP = rs.getInt("GDP");
                String des = rs.getString("des");

                cities.add(new City(id, cityName, countryName, area, population, GDP, des));
            }

        } catch (SQLException e) {
            printSQLException(e);
        }
        return cities;
    }

    @Override
    public List<City> findCitiesOnePage(int currentPage, int numOfRecords) {
        List<City> cities = new ArrayList<>();
        int start = currentPage * numOfRecords - numOfRecords;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_RECORDS_PER_PAGE);) {
//            SimpleDriverDataSource ds = new SimpleDriverDataSource();
//            ds.setDriver(new com.mysql.jdbc.Driver());
//            ds.setUrl(jdbcURL);
//            ds.setUsername(jdbcUsername);
//            ds.setPassword(jdbcPassword);

//            JdbcTemplate jtm = new JdbcTemplate(ds);
//            countries = jtm.query(sql, new Object[] {start, numOfRecords},
//                    new BeanPropertyRowMapper<>(City.class));
            System.out.println(preparedStatement);
            preparedStatement.setInt(1, start);
            preparedStatement.setInt(2, numOfRecords);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String cityName = rs.getString("city");
                String countryName = rs.getString("country");
                float area = rs.getFloat("area");
                int population = rs.getInt("population");
                int GDP = rs.getInt("GDP");
                String des = rs.getString("des");

                cities.add(new City(id, cityName, countryName, area, population, GDP, des));
            }
        } catch(SQLException e){
            Logger.getLogger(CityDAO.class.getName()).log(Level.SEVERE,
                    null, e);
        }
        return cities;
    }

    @Override
    public int getNumberOfRows() {
        int numOfRows = 0;

        try {
            String sql = "SELECT COUNT(id) FROM countries";

            SimpleDriverDataSource ds = new SimpleDriverDataSource();
            ds.setDriver(new com.mysql.cj.jdbc.Driver());
            ds.setUrl(jdbcURL);
            ds.setUsername(jdbcUsername);
            ds.setPassword(jdbcPassword);

            JdbcTemplate jtm = new JdbcTemplate(ds);
            numOfRows = jtm.queryForObject(sql, Integer.class);

        } catch (SQLException e) {
            Logger.getLogger(CityDAO.class.getName()).log(Level.SEVERE,
                    null, e);
        }
        return numOfRows;
    }

    @Override
    public boolean deleteCity(int id) throws SQLException {
        boolean rowDeleted;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CITY_SQL);)
        {
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    @Override
    public boolean updateCity(City city) throws SQLException {
        boolean rowUpdated;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CITY_SQL);)
        {
            preparedStatement.setString(1, city.getCityName());
            preparedStatement.setString(2, city.getCountry());
            preparedStatement.setFloat(3, city.getAreaCountry());
            preparedStatement.setInt(4, city.getPopulation());
            preparedStatement.setInt(5, city.getGDP());
            preparedStatement.setString(6, city.getDes());
            preparedStatement.setInt(7, city.getCityId());

            rowUpdated = preparedStatement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    @Override
    public City searchCity(String cityName) throws SQLException {
        List<City> resultFilter = selectCities().stream()
                .filter(city -> city.getCityName().equals(cityName))
                .collect(Collectors.toList());
        return resultFilter.stream().findAny().orElse(null);
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
