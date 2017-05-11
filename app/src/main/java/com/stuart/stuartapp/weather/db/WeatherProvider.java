package com.stuart.stuartapp.weather.db;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

/**
 * Created by stuart on 2017/5/8.
 */

public class WeatherProvider extends ContentProvider{

    private WeatherDb mDb;

    private static final String AUTHORITY = "com.stuart.stuartapp.weather";
    private static final int CODE_CITY = 12;

    public static Uri WEATHER_CITY_URI = Uri.parse("content://" + AUTHORITY+"/" +"city");
    static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        uriMatcher.addURI(AUTHORITY,"city",CODE_CITY);
    }
    @Override
    public boolean onCreate() {
        mDb = new WeatherDb(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase sqlite = mDb.getReadableDatabase();
        String table = null;
        switch (uriMatcher.match(uri)) {
            case CODE_CITY:
                table = "city";
                break;
        }
        if (!TextUtils.isEmpty(table)) {
           return sqlite.query(table, projection, selection, selectionArgs, null, null,sortOrder);
        }
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        SQLiteDatabase sqlite = mDb.getWritableDatabase();
        String table = null;
        switch (uriMatcher.match(uri)) {
            case CODE_CITY:
                table = "city";
                break;
        }
        if (!TextUtils.isEmpty(table)) {
           long id =  sqlite.insert(table, null, values);
            return Uri.parse(uri.toString()+"/"+id);
        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
            SQLiteDatabase sqlite = mDb.getWritableDatabase();
            String table = null;
            switch (uriMatcher.match(uri)) {
                case CODE_CITY:
                    table = "city";
                    break;
            }
            if (!TextUtils.isEmpty(table)) {
                return sqlite.delete(table, selection, selectionArgs );
            }

        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
            SQLiteDatabase sqlite = mDb.getWritableDatabase();
            String table = null;
            switch (uriMatcher.match(uri)) {
                case CODE_CITY:
                    table = "city";
                    break;
            }
            if (!TextUtils.isEmpty(table)) {
                return sqlite.update(table, values, selection, selectionArgs);
            }

        return 0;
    }
}
