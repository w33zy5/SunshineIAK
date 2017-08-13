package com.example.satyakresna.sunshineiak.model;

import java.util.List;
/**
 * Created by Dewa Agung on 06/08/17.
 */

public class DailyForecast {
    private City city;
    private List<WeatherItem> list;

    public City getCity(){
        return city;
    }

    public void setCity(City city){
        this.city = city;
    }

    public List<WeatherItem> getList(){
        return list;
    }

    public void setList(List<WeatherItem> list){
        this.list = list;
    }


}
