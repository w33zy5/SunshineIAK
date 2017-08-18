package com.example.satyakresna.sunshineiak.database;

import android.provider.BaseColumns;

/**
 * Created by Dewa Agung on 18/08/17.
 */

public class ForecastContract {
    public static final class ForecastEntry implements BaseColumns {
        //Table name
        public static final String TABLE_NAME = "forecast";

        //City detail
        public static final String COLUMN_CITY_NAME = "city_name";

        //Forecast Detail
        public static final String COLUMN_EPOCH_TIME = "epoch_time";
        public static final String COLUMN_MAX_TEMP = "max_temperature";
        public static final String COLUMN_MIN_TEMP = "min_temperature";
        public static final String COLUMN_PRESSURE = "pressure";
        public static final String COLUMN_HUMIDITY = "humidity";
        public static final String COLUMN_WEATHER_ID = "weather_id";
        public static final String COLUMN_WEATHER_MAIN = "weather_main";
        public static final String COLUMN_WEATHER_DESC = "weather_description";
        public static final String COLUMN_WIND_SPEED = "wind_speed";

        //Additional info
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }
}
