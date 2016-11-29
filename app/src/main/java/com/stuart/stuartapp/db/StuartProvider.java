package com.stuart.stuartapp.db;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.stuart.stuartapp.utils.LogUtil;

/**
 * Created by stuart on 2016/11/14.
 */
public class StuartProvider extends ContentProvider{

    private static final String TAG = "StuartProvider";
    private StuartDb mDb;

    private static final String AUTHORITY = "com.stuart.stuartapp";

    public static final Uri SSQ_URI = Uri.parse("content://" + AUTHORITY +"/ssq");

    private static final UriMatcher mUriMatcher;

    static final int SSQ = 1;
    static {
        mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mUriMatcher.addURI(AUTHORITY, "ssq", SSQ);
    }

    @Override
    public boolean onCreate() {

        mDb = new StuartDb(getContext());
        LogUtil.i(TAG, "onCreate","");
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        LogUtil.i(TAG, "query", "uri :" + uri);
        SQLiteDatabase readableDatabase = mDb.getReadableDatabase();
        String table = null;
        switch (mUriMatcher.match(uri)) {
            case SSQ:
                table = "ssq";
                break;
        }
        if (TextUtils.isEmpty(table)) {
            throw new SQLiteException("The uri " + uri +" is not suitable");
        }
        return  readableDatabase.query(table, projection, selection, selectionArgs, null , null, sortOrder);
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        LogUtil.i(TAG, "insert", "uri :" + uri);
        SQLiteDatabase db = mDb.getWritableDatabase();
        String table = null;
        switch (mUriMatcher.match(uri)) {
            case SSQ:
                table = "ssq";
                break;
        }
        if (TextUtils.isEmpty(table)) {
            throw new SQLiteException("The uri " + uri +" is not suitable");
        }
        long insert = db.insert(table, null, values);
        if (insert>0)
        {
            Uri _uri = ContentUris.withAppendedId(SSQ_URI, insert);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }
        return null;
        //throw new SQLiteException("Failed to insert row into " + uri);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        LogUtil.i(TAG, "delete", "uri :" + uri);
        SQLiteDatabase db = mDb.getWritableDatabase();
        String table = null;
        switch (mUriMatcher.match(uri)) {
            case SSQ:
                table = "ssq";
                break;
        }
        if (TextUtils.isEmpty(table)) {
            throw new SQLiteException("The uri " + uri +" is not suitable");
        }
        return db.delete(table, selection, selectionArgs);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        LogUtil.i(TAG, "update", "uri :" + uri);
        SQLiteDatabase db = mDb.getWritableDatabase();
        String table = null;
        switch (mUriMatcher.match(uri)) {
            case SSQ:
                table = "ssq";
                break;
        }
        if (TextUtils.isEmpty(table)) {
            throw new SQLiteException("The uri " + uri +" is not suitable");
        }
       return db.update(table, values, selection, selectionArgs);
    }
}
