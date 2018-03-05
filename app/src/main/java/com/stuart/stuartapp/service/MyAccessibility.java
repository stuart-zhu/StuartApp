package com.stuart.stuartapp.service;

import android.accessibilityservice.AccessibilityService;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

/**
 * Created by stuart on 2018/3/5.
 */

public class MyAccessibility extends AccessibilityService{

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
    public void onInterrupt() {

    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("stuart", "Accessibility onCreate");
    }
}
