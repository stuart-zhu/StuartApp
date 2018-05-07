package com.stuart.stuartapp.demo.weather.request;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Query;

/**
 * Created by stuart on 2018/5/7.
 */

public interface WeatherBaseRequest {

    @GET("/weather/city")
    Observable<Object> getCity();

//    @GET("http://jisutqybmf.market.alicloudapi.com/weather/query")
    @HTTP(method = "GET", path = "/weather/query",hasBody = false)
    Observable<Object> getWeather(@Query("city") String city);
}
