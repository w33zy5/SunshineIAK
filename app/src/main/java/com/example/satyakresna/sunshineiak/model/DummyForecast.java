package com.example.satyakresna.sunshineiak.model;

/**
 * Created by satyakresna on 22-Jul-17.
 */

public class DummyForecast {
    private String day;
    private String weather;
    private int maxTemp;
    private int minTemp;
    private int weatherID;

    public DummyForecast(String day, String weather, int maxTemp, int minTemp, int weatherID) {
        this.day = day;
        this.weather = weather;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.weatherID = weatherID;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public int getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(int maxTemp) {
        this.maxTemp = maxTemp;
    }

    public int getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(int minTemp) {
        this.minTemp = minTemp;
    }

    public int getWeatherID() {
        return weatherID;
    }

    public void setWeatherID(int weatherID) {
        this.weatherID = weatherID;
    }
}
