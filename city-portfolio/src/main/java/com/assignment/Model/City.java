package com.assignment.Model;

import java.util.Objects;
import javax.validation.constraints.NotEmpty;

public class City {

    private int cityId;
    private String cityName;
    private String country;
    private float areaCountry;
    private int population;
    private int GDP;
    private String des;

    public City() {
    }

    // Constructor without city ID
    public City(String cityName, String country, float areaCountry, int population, int GDP, String des) {
        super();
        this.cityName = cityName;
        this.country = country;
        this.areaCountry = areaCountry;
        this.population = population;
        this.GDP = GDP;
        this.des = des;
    }

    // Constructor with city ID
    public City(int cityId, String cityName, String country, float areaCountry, int population, int GDP, String des) {
        super();
        this.cityId = cityId;
        this.cityName = cityName;
        this.country = country;
        this.areaCountry = areaCountry;
        this.population = population;
        this.GDP = GDP;
        this.des = des;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    @NotEmpty
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public float getAreaCountry() {
        return areaCountry;
    }

    public void setAreaCountry(float areaCountry) {
        this.areaCountry = areaCountry;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getGDP() {
        return GDP;
    }

    public void setGDP(int GDP) {
        this.GDP = GDP;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof City) && (this.getCityName() != null)
                ? this.getCityName().equals(((City) other).getCityName())
                : (other == this);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCityName());
    }

    @Override
    public String toString() {
        return "City{" +
                "cityId=" + cityId +
                ", cityName='" + cityName + '\'' +
                ", country='" + country + '\'' +
                ", areaCountry=" + areaCountry +
                ", population=" + population +
                ", GDP=" + GDP +
                ", des='" + des + '\'' +
                '}';
    }
}
