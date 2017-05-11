package com.stuart.stuartapp.weather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by stuart on 2017/5/8.
 */

public class WeatherDb extends SQLiteOpenHelper {
    public WeatherDb(Context context) {
        super(context, "weather", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(TABLE_CITY_CREATE);
    }

    private static String TABLE_CITY_CREATE = "CREATE TABLE city("
            +"id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "cityId INTEGER, parentId TEXT,"
            + "cityCode LONG, city TEXT, cityP Text,parentPid TEXT, cityPP TEXT );"
            ;
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
