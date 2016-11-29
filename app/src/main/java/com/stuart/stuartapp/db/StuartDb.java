package com.stuart.stuartapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by stuart on 2016/11/14.
 */
public class StuartDb extends SQLiteOpenHelper {

    public StuartDb(Context context) {
        super(context, "stuart", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_SSQ);
    }

    private static final String SQL_CREATE_SSQ = "" +
            "CREATE TABLE ssq ("
            +"_id INTEGER PRIMARY KEY, "
            +"expect TEXT unique,"
            +"red TEXT,"
            +"blue TEXT,"
            +"opentime TEXT)"
            ;
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
