package com.example.satyakresna.sunshineiak;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.satyakresna.sunshineiak.model.WeatherItem;
import com.example.satyakresna.sunshineiak.utilities.SunshineWeatherUtils;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dewa Agung on 13/08/17.
 */

public class DetailActivity extends AppCompatActivity {

    public static final String TAG = DetailActivity.class.getSimpleName();

    private String jsonData;
    private int position;
    private WeatherItem weatherItem;
    private Gson gson = new Gson();

    @BindView(R.id.iv_weather_icon_today) ImageView weatherIconDetail;
    @BindView(R.id.tv_day_detail) TextView dayDetail;
    @BindView(R.id.tv_weather_detail) TextView weatherDetail;
    @BindView(R.id.tv_max_temp_detail) TextView maxTempDetail;
    @BindView(R.id.tv_min_temp_detail) TextView minTempDetail;
    @BindView(R.id.tv_humidity_detail) TextView humidityDetail;
    @BindView(R.id.tv_pressure_detail) TextView pressureDetail;
    @BindView(R.id.tv_wind_detail) TextView windDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        jsonData = getIntent().getStringExtra("data");
        position = getIntent().getIntExtra("position", 0);

        if(jsonData != null){
            weatherItem = gson.fromJson(jsonData, WeatherItem.class);
            bindData();
        } else {
            Log.e(TAG, "Data is null");
        }
    }

    private void bindData() {
        if(position == 0){
            dayDetail.setText(weatherItem.getTodayReadableTime());
        } else {
            dayDetail.setText(weatherItem.getReadableDateTime(position));
        }

        weatherIconDetail.setImageResource(
                SunshineWeatherUtils.getSmallArtResourceIdForWeatherCondition(
                        weatherItem.getWeather().get(0).getId()
                )
        );
        maxTempDetail.setText(weatherItem.getTemp().getResolvedTemp(weatherItem.getTemp().getMax()));
        minTempDetail.setText(weatherItem.getTemp().getResolvedTemp(weatherItem.getTemp().getMin()));
        weatherDetail.setText(weatherItem.getWeather().get(0).getDescription());
        humidityDetail.setText(weatherItem.getReadableHumidity());
        windDetail.setText(weatherItem.getReadableHumidity());
        pressureDetail.setText(weatherItem.getReadablePressure());
    }
}
