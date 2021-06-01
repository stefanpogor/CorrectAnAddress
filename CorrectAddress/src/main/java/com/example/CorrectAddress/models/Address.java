package com.example.CorrectAddress.models;

public class Address {

    private String countryName;
    private String stateName;
    private String cityName;

    public Address() {
    }

    public Address(String countryName, String stateName, String cityName) {
        this.countryName = countryName;
        this.stateName = stateName;
        this.cityName = cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return "Adresa este: " + countryName + ',' +
                ' ' + stateName + ',' +
                ' ' + cityName;
    }
}
