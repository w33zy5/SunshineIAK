package com.example.satyakresna.sunshineiak;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by Dewa Agung on 18/08/17.
 */

public class App extends Application {
    @Override
    public void onCreate(){
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
