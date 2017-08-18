package com.example.satyakresna.sunshineiak.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.satyakresna.sunshineiak.model.City;
import com.example.satyakresna.sunshineiak.model.DailyForecast;
import com.example.satyakresna.sunshineiak.model.Temp;
import com.example.satyakresna.sunshineiak.model.Weather;
import com.example.satyakresna.sunshineiak.model.WeatherItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dewa Agung on 18/08/17.
 */

public class ForecastDBHelper {

    private static final String TAG = ForecastDBHelper.class.getSimpleName();
    private static final String DB_NAME = "sunshine.db";
    private static final int DB_VERSION = 1;

    public ForecastDBHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        final String CREATE_DB_SQL = "CREATE TABLE "+ ForecastContract.ForecastEntry.TABLE_NAME + " ("
                + ForecastContract.ForecastEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ForecastContract.ForecastEntry.COLUMN_CITY_NAME + " TEXT, "
                + ForecastContract.ForecastEntry.COLUMN_EPOCH_TIME + " INTEGER, "
                + ForecastContract.ForecastEntry.COLUMN_MAX_TEMP + " REAL, "
                + ForecastContract.ForecastEntry.COLUMN_MIN_TEMP + " REAL, "
                + ForecastContract.ForecastEntry.COLUMN_HUMIDITY + " INTEGER, "
                + ForecastContract.ForecastEntry.COLUMN_PRESSURE + " REAL, "
                + ForecastContract.ForecastEntry.COLUMN_WEATHER_ID + " INTEGER, "
                + ForecastContract.ForecastEntry.COLUMN_WEATHER_MAIN + " TEXT, "
                + ForecastContract.ForecastEntry.COLUMN_WEATHER_DESC + " TEXT, "
                + ForecastContract.ForecastEntry.COLUMN_WIND_SPEED + " REAL, "
                + ForecastContract.ForecastEntry.COLUMN_TIMESTAMP + " DATETIME DEFAULT (DATETIME(CURRENT_TIMESTAMP, 'LOCALTIME')));";
        db.execSQL(CREATE_DB_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXIST "+ ForecastContract.ForecastEntry.TABLE_NAME);
        onCreate(db);
    }

    public void saveForecast(City city, WeatherItem data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ForecastContract.ForecastEntry.COLUMN_CITY_NAME, city.getName());
        values.put(ForecastContract.ForecastEntry.COLUMN_EPOCH_TIME, data.getDt());
        values.put(ForecastContract.ForecastEntry.COLUMN_MAX_TEMP, data.getTemp().getMax());
        values.put(ForecastContract.ForecastEntry.COLUMN_MIN_TEMP, data.getTemp().getMin());
        values.put(ForecastContract.ForecastEntry.COLUMN_PRESSURE, data.getPressure());
        values.put(ForecastContract.ForecastEntry.COLUMN_HUMIDITY, data.getHumidity());
        values.put(ForecastContract.ForecastEntry.COLUMN_WEATHER_ID, data.getWeather().get(0).getId());
        values.put(ForecastContract.ForecastEntry.COLUMN_WEATHER_DESC, data.getWeather().get(0).getDescription());
        values.put(ForecastContract.ForecastEntry.COLUMN_WIND_SPEED, data.getSpeed());

        long result = db.insert(ForecastContract.ForecastEntry.TABLE_NAME, null, values);
        Log.i(TAG, "saveForecast result -> " + result);
        db.close();
    }

    public DailyForecast getSavedForecast(String city){
        SQLiteDatabase db = this.getReadableDatabase();
        List<WeatherItem> weatherItems = new ArrayList<>();

        //Create city, which will be added to DailyForecast
        City resultCity = new City();
        resultCity.setName(city);

        //Create DailyForecast, which will be added to DailyForecast
        DailyForecast result = new DailyForecast();
        //Set city to DailyForecast
        result.setCity(resultCity);
        //Set 0-sized list for WeatherItem
        result.setList(weatherItems);

        Cursor cursor = db.query(ForecastContract.ForecastEntry.TABLE_NAME, null, ForecastContract.ForecastEntry.COLUMN_CITY_NAME + "=?",
                new String[]{city},
                null,
                null,
                null,
                null);
        int total = cursor.getCount();

        if(total > 0){
            if(cursor.moveToFirst()){
                do{
                    //Prepare model
                    //Create list weather, which is only one item Inside
                    List<Weather> weatherList = new ArrayList<>();
                    //Create the weather object, will be inserted to list aboce
                    Weather weather = new Weather();
                    //Add the Weather Object above to list
                    weatherList.add(weather);

                    //Create Temp Object
                    Temp temp = new Temp();

                    //Getting all data from cursor and set it to WeatherItem
                    item.setDt(get.Int(cursor.getColumnIndex(ForecastContract.ForecastEntry.COLUMN_EPOCH_TIME)));
                    item.getWeather().get(0).setId(cursor.getInt(cursor.getColumnIndex(ForecastContract.ForecastEntry.COLUMN_WEATHER_ID)));
                    item.getWeather().get(0).setDescription(cursor.getString(cursor.getColumnIndex(ForecastContract.ForecastEntry.COLUMN_WEATHER_DESC)));
                    item.getTemp().setMax(cursor.getDouble(cursor.getColumnIndex(ForecastContract.ForecastEntry.COLUMN_MAX_TEMP)));
                    item.getTemp().setMin(cursor.getDouble(cursor.getColumnIndex(ForecastContract.ForecastEntry.COLUMN_MIN_TEMP)));
                    item.setHumidity(cursor.getInt(cursor.getColumnIndex(ForecastContract.ForecastEntry.COLUMN_HUMIDITY)));
                    item.setPressure(cursor.getInt(cursor.getColumnIndex(ForecastContract.ForecastEntry.COLUMN_PRESSURE)));
                    item.setSpeed(cursor.getInt(cursor.getColumnIndex(ForecastContract.ForecastEntry.COLUMN_WIND_SPEED)));

                    //Finally, add WeatherItem to DailyForecast
                    result.getList().add(item);
                }while(cursor.moveToNext());
            }
        } else {
            //Data Not Found
            Log.w(TAG, "getSaveForecast not found any data!");
        }
        cursor.close();
        db.close();
        Log.d(TAG, "result -> "+result.toString());
        return result;
    }

    public boolean isDataAlreadyExist(String city){
        SQLiteDatabase db = this.getReadableDatabase();

        final String sql = "SELECT * FROM "
                + ForecastContract.ForecastEntry.TABLE_NAME
                + " WHERE "
                + ForecastContract.ForecastEntry.COLUMN_CITY_NAME
                + " LIKE '%" + city + "%' ;";
        Cursor cursor = db.rawQuery(sql, null);

        int total = cursor.getCount();
        Log.d(TAG, "Total data already exist -> "+total);
        cursor.close();
        db.close();
        return total > 0;
    }

    public void deleteForUpdate(String city){
        SQLiteDatabase db = this.getWritableDatabase();
        final String sql = "DELETE FROM "
                + ForecastContract.ForecastEntry.TABLE_NAME
                + " WHERE "
                + ForecastContract.ForecastEntry.COLUMN_CITY_NAME
                + " LIKE '%" + city + "%' ;";
        db.execSQL(sql);
        db.close();
    }
}
