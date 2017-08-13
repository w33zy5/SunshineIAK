package com.example.satyakresna.sunshineiak.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Dewa Agung on 06/08/17.
 */

public class WeatherItem {
    private long dt;
    private Temp temp;
    private double pressure;
    private int humidity;
    private double speed;
    private List<Weather> weather;

    public long getDt(){
        return dt;
    }

    public void setDt(long dt){
        this.dt = dt;
    }

    public Temp getTemp(){
        return temp;
    }

    public void setTemp(Temp temp){
        this.temp = temp;
    }

    public double getPressure(){
        return pressure;
    }

    public void setPressure(double pressure){
        this.pressure = pressure;
    }

    public int getHumidity(){
        return humidity;
    }

    public void setHumidity(int humidity){
        this.humidity = humidity;
    }

    public double getSpeed(){
        return speed;
    }

    public void setSpeed(double speed){
        this.speed = speed;
    }

    public List<Weather> getWeather(){
        return weather;
    }

    public void setWeather(List<Weather> weather){
        this.weather = weather;
    }

    public String getReadableDateTime(int position){
        if(position == 1){
            return "Tommorow";
        } else{
            Date date = new Date(this.dt * 1000L);
            DateFormat dateFormat = new SimpleDateFormat("EEEE");
            return dateFormat.format(date);
        }
    }

    public String getTodayReadableTime(){
        Date date = new Date(dt * 1000L);
        DateFormat dateFormat = new SimpleDateFormat("MMM dd");
        return "Today, "+dateFormat.format(date);
    }
}
