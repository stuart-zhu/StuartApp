package com.stuart.stuartapp.utils;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class ViewProvider {

  public static LinearLayout getLinearLayout(Context context) {
    LinearLayout ll = new LinearLayout(context);

    ll.setOrientation(LinearLayout.VERTICAL);
    ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DpUtil.dp2px(context, 50));

    ll.setLayoutParams(layoutParams);
    return ll;
  }

  public static RelativeLayout getRealtiveLayout(Context context) {
    RelativeLayout ll = new RelativeLayout(context);

    ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DpUtil.dp2px(context, 50));

    ll.setLayoutParams(layoutParams);
    return ll;
  }
}
