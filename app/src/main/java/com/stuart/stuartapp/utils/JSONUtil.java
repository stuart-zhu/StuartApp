package com.stuart.stuartapp.utils;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by stuart on 2018/4/26.
 */

public class JSONUtil {


    public static JSONObject getNewJsonObject(String json) {
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        try {
            JSONObject object = new JSONObject(json);
            return object;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getString(JSONObject object, String key) {
        String value = "";
        if (object == null) {
            return value;
        }
        try {
            if (object.has(key)) {
                value = object.getString(key);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if("null".equals(value)){
            value = "";
        }
        return value;
    }

    public static int getInt(JSONObject object, String key, int defaultValue) {
        int value = defaultValue;
        if (object == null) {
            return value;
        }
        try {
            if (object.has(key)) {
                value = object.getInt(key);
            }
        } catch (JSONException e) {

            e.printStackTrace();
        }
        return value;
    }

    public static double getDouble(JSONObject object, String key, double defaultValue) {
        double value = defaultValue;
        if (object == null) {
            return value;
        }
        try {
            if (object.has(key)) {
                value = object.getDouble(key);
            }
        } catch (JSONException e) {

            e.printStackTrace();
        }
        return value;
    }

    public static boolean getBoolean(JSONObject object, String key, boolean defaultValue) {
        boolean value = defaultValue;
        if (object == null) {
            return value;
        }
        try {
            if (object.has(key)) {
                value = object.getBoolean(key);
            }
        } catch (JSONException e) {

            e.printStackTrace();
        }
        return value;
    }

    public static JSONArray getJSONArray(JSONObject object, String key) {
        JSONArray value = new JSONArray();
        if (object == null) {
            return value;
        }
        try {
            if (object.has(key)) {
                value = object.getJSONArray(key);
            }
        } catch (JSONException e) {

            e.printStackTrace();
        }
        return value;
    }

    public static JSONObject getJSONObjectFromJSONArray(JSONArray array, int index) {
        JSONObject value = new JSONObject();
        try {
            if (array != null && array.length() > index) {
                value = array.getJSONObject(index);
            }
        } catch (JSONException e) {

            e.printStackTrace();
        }
        return value;
    }

    public static JSONArray getJSONArrayFromJSONArray(JSONArray array, int index) {
        JSONArray value = new JSONArray();
        try {
            if (array != null && array.length() > index) {
                value = array.getJSONArray(index);
            }
        } catch (JSONException e) {

            e.printStackTrace();
        }
        return value;
    }

    public static JSONObject getJSONObject(JSONObject object, String key) {
        JSONObject value = new JSONObject();
        if (object == null) {
            return value;
        }
        try {
            if (object.has(key)) {
                value = object.getJSONObject(key);
            }
        } catch (JSONException e) {

            e.printStackTrace();
        }
        return value;
    }

    public static JSONArray newJSONArray(String jsonStr) {
        try {
            JSONArray jsonArray = new JSONArray(jsonStr);
            return jsonArray;
        } catch (JSONException e) {

            e.printStackTrace();
        }
        return new JSONArray();
    }
}
