package com.stuart.stuartapp;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

/**
 * Created by stuart on 2018/1/4.
 */

public class StuartApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(this);
    }
}
