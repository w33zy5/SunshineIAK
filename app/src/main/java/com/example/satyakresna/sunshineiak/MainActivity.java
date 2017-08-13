package com.example.satyakresna.sunshineiak;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
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
    private DividerItemDecoration mDividerItemDecoration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mAdapter = new forecastAdapter(weatherItemList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mDividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(), layoutManager.getOrientation());
        mRecyclerView.addItemDecoration(mDividerItemDecoration);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);

        ActionBar toolbar = getSupportActionBar();

        getDataFromAPI("-8.5752", "115.1777", "10", "metric");
    }

    //finally did it dude
    private void getDataFromAPI(String lat, String lon, String cnt, String units){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = Constant.URL_API + Constant.PARAM_DAILY +
                Constant.PARAM_LAT + lat + "&" + Constant.PARAM_LON + lon + "&" +
                Constant.PARAM_CNT + cnt + "&" + Constant.PARAM_UNIT + units + "&" +
                Constant.PARAM_API_KEY + Constant.API_KEY;
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response){
                        try{
                            DailyForecast dailyForecast = gson.fromJson(response, DailyForecast.class);
                            for(WeatherItem item : dailyForecast.getList()){
                                weatherItemList.add(item);
                            }
                            mAdapter.notifyDataSetChanged();
                        } catch (Exception e){
                            Log.e(TAG, e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        if(error != null){
                            Log.e(TAG, error.getMessage());
                        } else{
                            Log.e(TAG, "Something error happened!");
                        }
                    }
                }
        );
        requestQueue.add(stringRequest);
    }
}
