package com.example.satyakresna.sunshineiak.model;

/**
 * Created by Dewa Agung on 06/08/17.
 */

public class Weather {
    private int id;
    private String main;
    private String description;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getMain(){
        return main;
    }

    public void setMain(String main){
        this.main = main;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

}