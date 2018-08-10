package com.stuart.stuartapp.utils;

import android.content.Context;

public class DpUtil {
  public static int dp2px(Context c,  float dpValue) {
    final float scale = c.getResources().getDisplayMetrics().density;
    return (int) (dpValue * scale + 0.5f);
  }

  public static int px2dp(Context c,float pxValue) {
    float density =  c.getResources().getDisplayMetrics().density;
    return (int) (pxValue / density+ 0.5f);
  }
}
