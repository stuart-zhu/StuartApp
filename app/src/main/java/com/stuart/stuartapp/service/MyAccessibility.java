package com.stuart.stuartapp.service;

import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

/**
 * Created by stuart on 2018/3/5.
 */

public class MyAccessibility extends AccessibilityService {

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Log.i("stuart", "onAccessibilityEvent " + event);
        AccessibilityNodeInfo source = event.getSource();
        if (source == null) {
            return;
        }
        Log.i("stuart", "onAccessibilityEvent " + source);
    }


    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        Log.i("stuart", "onServiceConnected ");
    }

    @Override
    public void onInterrupt() {
        Log.i("stuart", "onInterrupt ");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("stuart", "onStartCommand " + startId);
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("stuart", "onUnbind ");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.i("stuart", "onDestroy ");
        super.onDestroy();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("stuart", "Accessibility onCreate");
    }
}
