package com.assignment.DAO;

import com.assignment.Model.City;

import java.sql.SQLException;
import java.util.List;

public interface ICityDAO {

    // Create new city
    void insertNewCity(City city) throws SQLException;

    // Select and view city
    City selectCity(int id);

    // Select all cities
    List<City> selectCities();

    // Select list of cities
    List<City> findCitiesOnePage(int currentPage, int numOfRecords);

    // Get number of rows
    int getNumberOfRows();

    // Delete a city
    boolean deleteCity(int id) throws SQLException;

    // Update an existed city
    boolean updateCity(City city) throws SQLException;

    // Search city by name
    City searchCity(String cityName) throws SQLException;
}
