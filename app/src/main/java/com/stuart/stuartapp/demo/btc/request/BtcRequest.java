package com.stuart.stuartapp.demo.btc.request;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface BtcRequest {

  @GET("tick/HUOBIPRO:BTCUSDT?unit=usdt")
  Observable<Object> getBtc();
}
