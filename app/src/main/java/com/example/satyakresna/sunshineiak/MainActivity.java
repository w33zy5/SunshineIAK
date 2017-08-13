package com.example.satyakresna.sunshineiak;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.satyakresna.sunshineiak.adapter.forecastAdapter;
import com.example.satyakresna.sunshineiak.model.DailyForecast;
import com.example.satyakresna.sunshineiak.model.DummyForecast;
import com.example.satyakresna.sunshineiak.model.WeatherItem;
import com.example.satyakresna.sunshineiak.utilities.Constant;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    public List<WeatherItem> weatherItemList = new ArrayList<>();
    @BindView(R.id.rv_forecast) RecyclerView mRecyclerView;
    //private RecyclerView mRecyclerView;
    private forecastAdapter mAdapter;
    private Gson gson = new Gson();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mAdapter = new forecastAdapter(weatherItemList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);

        getDataFromAPI("-8.5752", "115.1777", "10", "metric");
    }

    private void populateData() {
        for(int i = 0; i <= 10; i++){
            DummyForecast dummyForecast = new DummyForecast("Sunday", "Sunny", 23, 18, R.drawable.art_clear);
            dummyForecastList.add(dummyForecast);
        }
        mAdapter.notifyDataSetChanged();
    }
}
