package com.stuart.stuartapp.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by lenovo on 2016/11/8.
 */
public class ToastUtil {
    private Toast mPreToast;
    private Toast mToast;

    private static ToastUtil mInstance;

    private static Context c;
    private ToastUtil() {}
    public static ToastUtil getInstance(Context context) {
        if (mInstance == null)
            mInstance = new ToastUtil();
        c = context;
        return mInstance;
    }

    public void show(String text) {
        if (mPreToast != null) mPreToast.cancel();
        mToast =  Toast.makeText(c, text, Toast.LENGTH_SHORT);

        mToast.show();
        mPreToast = mToast;
    }

    public void show(int resId) {
        show(c.getString(resId));
    }
}
