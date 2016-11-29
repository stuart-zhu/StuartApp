package com.stuart.stuartapp.dao;


import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.stuart.stuartapp.db.StuartProvider;
import com.stuart.stuartapp.entity.SSQ;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by stuart on 2016/11/14.
 */
public class SsqDao {

    private static SsqDao mInstance;

    public static SsqDao getInstance() {
        if (mInstance == null)
            mInstance = new SsqDao();
        return mInstance;
    }


    public List<SSQ> query(Context context, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        if (TextUtils.isEmpty(sortOrder)) {
            sortOrder = "expect DESC";
        }
        Cursor c =  null;
        List<SSQ> list = new ArrayList<>();
        try {
            c = context.getContentResolver().query(StuartProvider.SSQ_URI,projection,selection,selectionArgs,sortOrder);
            while (c != null && c.moveToNext()) {

                String expect = c.getString(c.getColumnIndex("expect"));
                String red = c.getString(c.getColumnIndex("red"));
                String blue = c.getString(c.getColumnIndex("blue"));
                String opentime = c.getString(c.getColumnIndex("opentime"));

                String[] reds = red.split(",");
                SSQ ssq = new SSQ(expect, null, blue, opentime);
                ssq.addRed(reds);

                list.add(ssq);
            }
        } finally {
            if (c != null) c.close();
        }
        return list;
    }

    public Uri insert(Context context, SSQ s) {
       return context.getContentResolver().insert(StuartProvider.SSQ_URI, ssqToValue(s));
    }

    private ContentValues ssqToValue(SSQ s) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("expect", s.getExpect());
        contentValues.put("blue", s.getBlue());
        contentValues.put("opentime", s.getOpenTime());
        StringBuffer red = new StringBuffer();
        for (String str : s.getReds()) {
            red.append(str).append(",");
        }
        if (red.length() > 0) {
            red.delete(red.length() -1 ,red.length());
        }
        contentValues.put("red", red.toString());
        return contentValues;
    }

    public int delete(Context context, String selection, String[] selectionArgs) {
        return context.getContentResolver().delete(StuartProvider.SSQ_URI,selection,selectionArgs);
    }


    public int update(Context context, ContentValues values, String selection, String[] selectionArgs) {
        return context.getContentResolver().update(StuartProvider.SSQ_URI, values, selection, selectionArgs);
    }
}

