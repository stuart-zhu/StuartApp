package com.stuart.stuartapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by stuart on 2017/5/8.
 */

public class Contants {

    public static final String PREF_CITY_ALREADY = "city is ready";

    public static final String PREF_CITY_LIST = "city list";

    public static boolean isCityAlready(Context c) {
        SharedPreferences sp = c.getSharedPreferences("stuartapp", Context.MODE_PRIVATE);
        return sp.getBoolean(PREF_CITY_ALREADY, false);
    }
    public static void setCiityAlready(Context c, boolean ready) {
        SharedPreferences sp = c.getSharedPreferences("stuartapp", Context.MODE_PRIVATE);
        sp.edit().putBoolean(PREF_CITY_ALREADY, ready).commit();
    }

    public static Set<String> getCityList(Context c) {
        SharedPreferences sp = c.getSharedPreferences("stuartapp", Context.MODE_PRIVATE);
        return sp.getStringSet(PREF_CITY_LIST,new LinkedHashSet<String>());
    }

    public static void addCityList(Context c, String city) {
        Set<String> cityList = getCityList(c);
        cityList.add(city);
        SharedPreferences sp = c.getSharedPreferences("stuartapp", Context.MODE_PRIVATE);
        sp.edit().putStringSet(PREF_CITY_LIST, cityList).commit();
    }

    public static void removeCityList(Context c, String city) {
        Set<String> cityList = getCityList(c);
        cityList.remove(city);
        SharedPreferences sp = c.getSharedPreferences("stuartapp", Context.MODE_PRIVATE);
        sp.edit().putStringSet(PREF_CITY_LIST, cityList).commit();
    }
}
