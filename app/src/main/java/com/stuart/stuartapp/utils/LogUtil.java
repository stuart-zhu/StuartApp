package com.stuart.stuartapp.utils;

import android.util.Log;

/**
 * Created by lenovo on 2016/11/14.
 */
public class LogUtil {

    private static final boolean DEBUG = true;

    private static final String BASE_TAG = "stuart";

    public static void e(String Tag, String method, String msg) {
        if (DEBUG)
            Log.e(BASE_TAG, Tag + " - " + method + "  >>>  " + msg);
    }

    public static void i(String Tag, String method, String msg) {
        if (DEBUG)
            Log.i(BASE_TAG, Tag + " - " + method + "  >>>  " + msg);
    }
}
