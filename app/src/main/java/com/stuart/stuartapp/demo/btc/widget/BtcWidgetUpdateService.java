package com.stuart.stuartapp.demo.btc.widget;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

import com.google.gson.Gson;
import com.stuart.stuartapp.R;
import com.stuart.stuartapp.demo.btc.CoinInfo;
import com.stuart.stuartapp.demo.btc.request.BtcRequest;
import com.stuart.stuartapp.demo.weather.request.converter.StringConverterFactory;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class BtcWidgetUpdateService extends Service {

  private Timer timer;
  private TimerTask task;

  private Retrofit mRetrofit;
  private BtcRequest mRequest;

  public BtcWidgetUpdateService() {
    OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
    httpClientBuilder.connectTimeout(3000, TimeUnit.SECONDS);
    String baseUrl = "https://market.jinse.com/api/v1/";

    mRetrofit = new Retrofit.Builder().
        client(httpClientBuilder.build())
//                .addConverterFactory(GsonConverterFactory.create())
        .addConverterFactory(StringConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(baseUrl).
            build();

    mRequest = mRetrofit.create(BtcRequest.class);
  }

  private void getBtc(final Observer subscriber) {


    mRequest.getBtc()
        .subscribeOn(Schedulers.io())
        .unsubscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(subscriber);

  }


  @Override
  public IBinder onBind(Intent intent) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public void onCreate() {
    timer = new Timer();
    task = new TimerTask() {
      @Override
      public void run() {

        ComponentName componentName = new ComponentName(BtcWidgetUpdateService.this, BtcWidget.class);
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.layout_btc_widget);

        AppWidgetManager awm = AppWidgetManager.getInstance(getApplicationContext());
        getBtcus(componentName,awm, remoteViews);
//        awm.updateAppWidget(componentName, remoteViews);

      }
    };
    timer.schedule(task, 0, 3000);
    super.onCreate();
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    timer.cancel();
    task.cancel();
    timer = null;
    task = null;
  }


  private void getBtcus(final ComponentName componentName, final AppWidgetManager appWidgetManager, final RemoteViews remoteViews) {

    getBtc(new Observer() {
      @Override
      public void onSubscribe(Disposable d) {

        Log.i("stuart", "onSubscribe");
      }

      @Override
      public void onNext(Object value) {
        Log.i("stuart", "onNext");
        if (value == null) return;
        //mHandler.obtainMessage(MSG_SHOW_BTC, value).sendToTarget();

        String close = new Gson().fromJson(value.toString(), CoinInfo.class).close;

        int index = close.indexOf(".");
        if (index != -1) {
          index =  index + 3;
          close = close.substring(0, index);
        }
        remoteViews.setTextViewText(R.id.price, close);
        appWidgetManager.updateAppWidget(componentName, remoteViews);

      }

      @Override
      public void onError(Throwable e) {
        Log.i("stuart", "onError");
      }

      @Override
      public void onComplete() {
        Log.i("stuart", "onComplete");
      }
    });
  }
}