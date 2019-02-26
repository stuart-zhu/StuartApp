package com.stuart.stuartapp.demo.btc.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BtcWidget extends AppWidgetProvider{


  public BtcWidget() {

  }


  @Override
  public void onReceive(Context context, Intent intent) {
    super.onReceive(context, intent);
    Log.i("stuart", "onReceive" + intent.getAction());
  }


  @Override
  public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
    super.onUpdate(context, appWidgetManager, appWidgetIds);
    Intent intent = new Intent(context, BtcWidgetUpdateService.class);
    context.startService(intent);

  }


  @Override
  public void onEnabled(Context context) {
    super.onEnabled(context);
    Intent intent = new Intent(context, BtcWidgetUpdateService.class);
    context.startService(intent);
  }
}
