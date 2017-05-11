package com.stuart.stuartapp.weather;

import android.content.Context;

import com.stuart.stuartapp.R;

/**
 * Created by stuart on 2017/5/9.
 */

public class WeatherIconHelper {
    public static int getWeatherIconResId(Context coontext, String weather) {
        if (weather.equals(coontext.getString(R.string.weather_0))) {
            return R.drawable.weather_0;
        } else if (weather.equals(coontext.getString(R.string.weather_1))) {
            return R.drawable.weather_1;
        } else if (weather.equals(coontext.getString(R.string.weather_2))) {
            return R.drawable.weather_2;
        } else if (weather.equals(coontext.getString(R.string.weather_3))) {
            return R.drawable.weather_3;
        } else if (weather.equals(coontext.getString(R.string.weather_4))) {
            return R.drawable.weather_4;
        } else if (weather.equals(coontext.getString(R.string.weather_5))) {
            return R.drawable.weather_5;
        } else if (weather.equals(coontext.getString(R.string.weather_6))) {
            return R.drawable.weather_6;
        } else if (weather.equals(coontext.getString(R.string.weather_7))) {
            return R.drawable.weather_7;
        } else if (weather.equals(coontext.getString(R.string.weather_8))) {
            return R.drawable.weather_8;
        } else if (weather.equals(coontext.getString(R.string.weather_9))) {
            return R.drawable.weather_9;
        } else if (weather.equals(coontext.getString(R.string.weather_10))) {
            return R.drawable.weather_10;
        } else if (weather.equals(coontext.getString(R.string.weather_11))) {
            return R.drawable.weather_11;
        } else if (weather.equals(coontext.getString(R.string.weather_12))) {
            return R.drawable.weather_12;
        } else if (weather.equals(coontext.getString(R.string.weather_13))) {
            return R.drawable.weather_13;
        } else if (weather.equals(coontext.getString(R.string.weather_14))) {
            return R.drawable.weather_14;
        } else if (weather.equals(coontext.getString(R.string.weather_15))) {
            return R.drawable.weather_15;
        } else if (weather.equals(coontext.getString(R.string.weather_16))) {
            return R.drawable.weather_16;
        } else if (weather.equals(coontext.getString(R.string.weather_17))) {
            return R.drawable.weather_17;
        } else if (weather.equals(coontext.getString(R.string.weather_18))) {
            return R.drawable.weather_18;
        } else if (weather.equals(coontext.getString(R.string.weather_19))) {
            return R.drawable.weather_19;
        } else if (weather.equals(coontext.getString(R.string.weather_20))) {
            return R.drawable.weather_20;
        } else if (weather.equals(coontext.getString(R.string.weather_21))) {
            return R.drawable.weather_21;
        } else if (weather.equals(coontext.getString(R.string.weather_22))) {
            return R.drawable.weather_22;
        } else if (weather.equals(coontext.getString(R.string.weather_23))) {
            return R.drawable.weather_23;
        } else if (weather.equals(coontext.getString(R.string.weather_24))) {
            return R.drawable.weather_24;
        } else if (weather.equals(coontext.getString(R.string.weather_25))) {
            return R.drawable.weather_25;
        } else if (weather.equals(coontext.getString(R.string.weather_26))) {
            return R.drawable.weather_26;
        } else if (weather.equals(coontext.getString(R.string.weather_27))) {
            return R.drawable.weather_27;
        } else if (weather.equals(coontext.getString(R.string.weather_28))) {
            return R.drawable.weather_28;
        } else if (weather.equals(coontext.getString(R.string.weather_29))) {
            return R.drawable.weather_29;
        } else if (weather.equals(coontext.getString(R.string.weather_30))) {
            return R.drawable.weather_30;
        } else if (weather.equals(coontext.getString(R.string.weather_31))) {
            return R.drawable.weather_31;
        } else if (weather.equals(coontext.getString(R.string.weather_32))) {
            return R.drawable.weather_32;
        } else if (weather.equals(coontext.getString(R.string.weather_49))) {
            return R.drawable.weather_49;
        } else if (weather.equals(coontext.getString(R.string.weather_53))) {
            return R.drawable.weather_53;
        } else if (weather.equals(coontext.getString(R.string.weather_54))) {
            return R.drawable.weather_54;
        } else if (weather.equals(coontext.getString(R.string.weather_55))) {
            return R.drawable.weather_55;
        } else if (weather.equals(coontext.getString(R.string.weather_56))) {
            return R.drawable.weather_56;
        } else if (weather.equals(coontext.getString(R.string.weather_58))) {
            return R.drawable.weather_58;
        } else if (weather.equals(coontext.getString(R.string.weather_99))) {
            return R.drawable.weather_99;
        } else if (weather.equals(coontext.getString(R.string.weather_301))) {
            return R.drawable.weather_301;
        } else if (weather.equals(coontext.getString(R.string.weather_302))) {
            return R.drawable.weather_302;
        }
        return R.drawable.weather_99;
    }

    public static int getWeatherLevelIcon(String level) {
        if (level.equals("一级"))
            return R.drawable.air_level_1;
        else if (level.equals("二级"))
            return R.drawable.air_level_2;
        else if (level.equals("三级"))
            return R.drawable.air_level_3;
        else if (level.equals("四级"))
            return R.drawable.air_level_4;
        else if (level.equals("五级"))
            return R.drawable.air_level_5;
        else if (level.equals("六级"))
            return R.drawable.air_level_6;
        return 0;
    }
}
