package com.example.satyakresna.sunshineiak.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.satyakresna.sunshineiak.R;
import com.example.satyakresna.sunshineiak.model.WeatherItem;
import com.example.satyakresna.sunshineiak.utilities.SunshineWeatherUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.attr.data;
import static com.example.satyakresna.sunshineiak.R.string.day;
import static com.example.satyakresna.sunshineiak.R.string.weather;

/**
 * Created by Dewa Agung on 06/08/17.
 */

public class forecastAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final String TAG = forecastAdapter.class.getSimpleName();
    private List<WeatherItem> weatherItemList = new ArrayList<>();

    private static final int VIEW_TODAY = 0;
    private static final int VIEW_OTHER = 1;

    public forecastAdapter(List<WeatherItem> weatherItemList){
        this.weatherItemList = weatherItemList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       if(viewType == VIEW_TODAY){
           Context context = parent.getContext();
           int layoutFromListItem = R.layout.row_today_forecast;
           LayoutInflater inflater = LayoutInflater.from(context);
           boolean shouldAttachToParentImmediately = false;

           View view = inflater.inflate(layoutFromListItem, parent, shouldAttachToParentImmediately);
           TodayViewHolder viewHolder = new TodayViewHolder(view);
           return viewHolder;
       } else{
           Context context = parent.getContext();
           int layoutFromListItem = R.layout.row_forecast_item;
           LayoutInflater inflater = LayoutInflater.from(context);
           boolean shouldAttachToParentImmediately = false;

           View view = inflater.inflate(layoutFromListItem, parent, shouldAttachToParentImmediately);
           ForecastViewHolder viewHolder = new ForecastViewHolder(view);
           return viewHolder;
       }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position) == VIEW_TODAY){
            ((TodayViewHolder) holder).bind(weatherItemList.get(position));
        } else{
            ((ForecastViewHolder) holder).bind(weatherItemList.get(position), position);
        }

    }

    @Override
    public int getItemCount() {
        return weatherItemList.size();
    }

    public int getItemViewType(int position){
        if(position == 0) {
            return VIEW_TODAY;
        } else{
            return VIEW_OTHER;
        }
    }

    public class ForecastViewHolder extends RecyclerView.ViewHolder {
        //Butter Knife
        @BindView(R.id.tv_weather_icon) ImageView weatherIcon;
        @BindView(R.id.tv_day) TextView day;
        @BindView(R.id.tv_weather) TextView weather;
        @BindView(R.id.tv_min_temp) TextView minTemp;
        @BindView(R.id.tv_max_temp) TextView maxTemp;

        /*ImageView weatherIcon;
        TextView day;
        TextView weather;
        TextView minTemp;
        TextView maxTemp;*/

        public ForecastViewHolder(View itemView){
            super(itemView);
            //Butter Knife
            ButterKnife.bind(this, itemView);
            /*weatherIcon = (ImageView) itemView.findViewById(R.id.tv_weather_icon);
            day = (TextView) itemView.findViewById(R.id.tv_day);
            weather = (TextView) itemView.findViewById(R.id.tv_weather);
            minTemp = (TextView) itemView.findViewById(R.id.tv_min_temp);
            maxTemp = (TextView) itemView.findViewById(R.id.tv_max_temp);*/
        }

        public void bind(WeatherItem data, final int position){
            weatherIcon.setImageResource(
                    SunshineWeatherUtils.getSmallArtResourceIdForWeatherCondition(
                            data.getWeather().get(0).getId()
                    )
            );
            day.setText(data.getReadableDateTime(position));
            weather.setText(data.getWeather().get(0).getDescription());
            minTemp.setText(data.getTemp().getResolvedTemp(data.getTemp().getMin()));
            maxTemp.setText(data.getTemp().getResolvedTemp(data.getTemp().getMax()));
        }
    }

    public class TodayViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_weather_icon_today) ImageView weatherIconToday;
        @BindView(R.id.tv_day_today) TextView dayToday;
        @BindView(R.id.tv_weather_today) TextView weatherToday;
        @BindView(R.id.tv_min_temp_today) TextView minTempToday;
        @BindView(R.id.tv_max_temp_today) TextView maxTempToday;

        public TodayViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(WeatherItem data){
            weatherIconToday.setImageResource(
                    SunshineWeatherUtils.getSmallArtResourceIdForWeatherCondition(
                            data.getWeather().get(0).getId()
                    )
            );
            dayToday.setText(data.getTodayReadableTime());
            weatherToday.setText(data.getWeather().get(0).getDescription());
            minTempToday.setText(data.getTemp().getResolvedTemp(data.getTemp().getMin()));
            maxTempToday.setText(data.getTemp().getResolvedTemp(data.getTemp().getMax()));
        }
    }
}
