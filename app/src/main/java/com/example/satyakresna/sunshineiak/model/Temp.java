package com.example.satyakresna.sunshineiak.model;

/**
 * Created by Dewa Agung on 06/08/17.
 */

public class Temp {
    private Double min;
    private Double max;

    public Double getMin(){
        return min;
    }

    public void setMin(Double min){
         this.min = min;
    }

    public Double getMax(){
        return max;
    }

    public void setMax(Double max){
        this.max = max;
    }

    public String getResolvedTemp(double temp){
        int result = (int) temp;
        return result + "\u00b0";
    }
}
