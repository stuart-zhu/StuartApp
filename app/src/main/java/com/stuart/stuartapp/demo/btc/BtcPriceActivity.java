package com.stuart.stuartapp.demo.btc;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;
import com.stuart.stuartapp.BaseActivity;
import com.stuart.stuartapp.demo.btc.request.BtcRequest;
import com.stuart.stuartapp.demo.weather.request.converter.StringConverterFactory;
import com.stuart.stuartapp.utils.ToastUtil;

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class BtcPriceActivity extends BaseActivity {

  private Retrofit mRetrofit;
  private BtcRequest mRequest;
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    init();
    mHandler.sendEmptyMessage(MSG_GET_BTC);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    mHandler.sendEmptyMessage(MSG_GET_BTC);
    mHandler.sendEmptyMessage(MSG_SHOW_BTC);
  }

  private void getBtc() {
    getBtc(new Observer() {
      @Override
      public void onSubscribe(Disposable d) {

        Log.i("stuart", "onSubscribe");
      }

      @Override
      public void onNext(Object value) {
        Log.i("stuart", "onNext");
        if (value == null) return;
        mHandler.obtainMessage(MSG_SHOW_BTC, value).sendToTarget();
      }

      @Override
      public void onError(Throwable e) {
        Log.i("stuart", "onError");
        getBtc();
      }

      @Override
      public void onComplete() {
        Log.i("stuart", "onComplete");
      }
    });
  }

  private static final int MSG_GET_BTC = 1;
  private static final int MSG_SHOW_BTC = 2;

  private Handler mHandler = new Handler() {
    @Override
    public void handleMessage(Message msg) {
      switch (msg.what) {
        case MSG_GET_BTC:
          getBtc();
          break;
        case MSG_SHOW_BTC:
          String s = (String) msg.obj;
          handleMsg(s);
          sendEmptyMessageDelayed(MSG_GET_BTC, 5000);
          break;
      }



    }
  };


  private void handleMsg(String s) {
    /**
     * {
     "ticker" : "HUOBIPRO:BTCUSDT",  // Ticker名称
     "exchangeName" : "HuobiPro",   // 交易所名称
     "base" : "BTC",   // 交易币种
     "currency" : "USDT",  // 兑换币种
     "symbol" : "BTCUSDT",   // 交易对儿标识
     "high" : 61512.12713304667350,    // 最高价
     "open" : 58970.63530801362825,   // 开盘价
     "close" : 59065.33243911680550,   // 收盘价（最新价）
     "low" : 57920.51289144851775,   // 最低价
     "vol" : 22373.79282497,   // 成交量
     "degree" : 0.16058400,   // 涨跌幅
     "dateTime" : 1524630942000   // 更新时间戳（微妙）
     }
     */
    CoinInfo coinInfo = new Gson().fromJson(s, CoinInfo.class);


    DecimalFormat df = new DecimalFormat("#.00");
    String ss = df.format(coinInfo.close);
    ToastUtil.showMessage(ss);
  }


  private void init() {
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
}
