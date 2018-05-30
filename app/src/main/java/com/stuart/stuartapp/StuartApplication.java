package com.stuart.stuartapp;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.stuart.stuartapp.utils.LogUtil;

/**
 * Created by stuart on 2018/1/4.
 */

public class StuartApplication extends Application{

    private static final String TAG = "StuartApplication";
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        SDKInitializer.initialize(this);
    }

    private static StuartApplication mInstance;

    public static StuartApplication getInstance() {
        if (mInstance == null) {

            LogUtil.i(TAG, "getInstance", "is not init");
        }
        return mInstance;
    }
}
