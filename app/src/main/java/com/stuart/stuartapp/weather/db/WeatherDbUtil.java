package com.stuart.stuartapp.weather.db;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.util.Log;

import com.stuart.stuartapp.weather.entity.CityInfo;

import java.util.ArrayList;
import java.util.List;

import static com.stuart.stuartapp.weather.db.WeatherProvider.WEATHER_CITY_URI;

/**
 * Created by stuart on 2017/5/10.
 */

public class WeatherDbUtil {

  /*  public static List<CityInfo> getCityInfo(Context context, String city) {
        Cursor c = null;
        if (TextUtils.isEmpty(city)) city = "";
        List<CityInfo> citys = new ArrayList<>();
        try {
            c = context.getContentResolver().query(WEATHER_CITY_URI, null, "city like \'%" + city + "%\'", null, null);
            if (c != null)
                while (c.moveToNext()) {
                    CityInfo ci = new CityInfo();
                    ci.setCity(c.getString(c.getColumnIndex("city")));
                    ci.setCityCode(c.getString(c.getColumnIndex("cityCode")));
                    ci.setCityId(c.getInt(c.getColumnIndex("cityId")));
                    ci.setId(c.getInt(c.getColumnIndex("id")));
                    ci.setParentId(c.getInt(c.getColumnIndex("parentId")));
                    CityInfo cP = getCityInfo(context, ci.getParentId());
                    if (cP != null) {
                        ci.setCityInfoP(cP);
                        CityInfo cPP = getCityInfo(context, cP.getParentId());
                        if (cPP != null) ci.setCityInfoPp(cPP);
                    }


                    citys.add(ci);
                }
        } finally {
            if (c != null) c.close();
        }
        return citys;

    }*/


    public static List<CityInfo> getCityInfo(Context context, String city) {

        if (TextUtils.isEmpty(city)) city = "";
        Cursor c = null;
        List<CityInfo> citys = new ArrayList<>();
        try {
            c = context.getContentResolver().query(WEATHER_CITY_URI, null,
                    "(city like \'%" + city + "%\'" +
                            " OR " +
                            "cityP like \'%" + city + "%\'" +
                            " OR " +
                            "cityP like \'%" + city + "%\')"
                    + " AND id > 34"

                    , null, "parentId ASC");
            Log.i("stuart", "cursor.getCount() = " + c.getCount());
            if (c != null)
                while (c.moveToNext()) {

                    CityInfo ci = new CityInfo();
                    ci.setCity(c.getString(c.getColumnIndex("city")));
                    ci.setCityCode(c.getString(c.getColumnIndex("cityCode")));
                    ci.setCityId(c.getInt(c.getColumnIndex("cityId")));
                    ci.setId(c.getInt(c.getColumnIndex("id")));
                    ci.setParentId(c.getInt(c.getColumnIndex("parentId")));
                    ci.setCityP(c.getString(c.getColumnIndex("cityP")));
                    ci.setCityPP(c.getString(c.getColumnIndex("cityPP")));
                    citys.add(ci);
                }

        } finally {
            if (c != null) c.close();
        }
        return citys;



     /* ci.setCity(c.getString(c.getColumnIndex("city")));
      ci.setCityCode(c.getString(c.getColumnIndex("cityCode")));
      ci.setCityId(c.getInt(c.getColumnIndex("cityId")));
      ci.setId(c.getInt(c.getColumnIndex("id")));
      ci.setParentId(c.getInt(c.getColumnIndex("parentId")));
*/
    }

    public static CityInfo getCityInfo(Context context, int cityId) {
        Cursor c = null;


        try {
            c = context.getContentResolver().query(WEATHER_CITY_URI, null, "cityId = " + cityId, null, null);
            if (c != null)
                while (c.moveToNext()) {
                    CityInfo ci = new CityInfo();
                    ci.setCity(c.getString(c.getColumnIndex("city")));
                    ci.setCityCode(c.getString(c.getColumnIndex("cityCode")));
                    ci.setCityId(c.getInt(c.getColumnIndex("cityId")));
                    ci.setId(c.getInt(c.getColumnIndex("id")));
                    ci.setParentId(c.getInt(c.getColumnIndex("parentId")));
                    return ci;
                }
        } finally {
            if (c != null) c.close();
        }
        return null;
    }
}
