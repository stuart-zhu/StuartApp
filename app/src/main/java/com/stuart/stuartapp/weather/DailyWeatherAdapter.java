package com.stuart.stuartapp.weather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.stuart.stuartapp.R;
import com.stuart.stuartapp.weather.entity.DailyWeather;

import java.util.List;

/**
 * Created by stuart on 2017/5/10.
 */

public class DailyWeatherAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<DailyWeather> list;
    private Context mContext;
    public DailyWeatherAdapter(Context context, List<DailyWeather> list) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
        this.list = list;
        if (list.size() > 0) {
            // 去掉当天的天气情况
            list.remove(0);
        }
    }
    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public DailyWeather getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder vh;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_daily_weather, null);
            vh = new ViewHolder(convertView);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        DailyWeather item = getItem(position);
        vh.ivWeather.setImageResource(WeatherIconHelper.getWeatherIconResId(mContext, item.getDay_weather()));
        vh.tvWeek.setText(item.getWeek());
        vh.tvWeather.setText(item.getDay_weather());
        vh.tvTemp.setText(item.getTemplow() +"°"+"/" + item.getTempHigh()+"°");
        return convertView;
    }

    private class ViewHolder {
        private ImageView ivWeather;
        private TextView tvWeek;
        private TextView tvWeather;
        private TextView tvTemp;

        public ViewHolder(View v) {
            ivWeather = (ImageView) v.findViewById(R.id.icon_weather);
            tvWeek = (TextView) v.findViewById(R.id.week);
            tvWeather = (TextView) v.findViewById(R.id.weather);
            tvTemp = (TextView) v.findViewById(R.id.temp);
            v.setTag(this);
        }
    }
}
