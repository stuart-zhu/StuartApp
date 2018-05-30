package com.stuart.stuartapp.demo.weather.request;

import com.stuart.stuartapp.demo.weather.request.converter.StringConverterFactory;

import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;


/**
 * Created by stuart on 2018/5/7.
 */

public class HttpRequest {

    private static final long DEFAULT_TIMEOUT = 10000;
    private Retrofit mRetrofit;

    private static HttpRequest mInstance;

    private WeatherBaseRequest mRequest;

    private HttpRequest() {

    }

    public static HttpRequest getInstance() {
        if (mInstance == null)
            mInstance = new HttpRequest();
        return mInstance;
    }

    private String getAuth() {
        return "APPCODE " + "e35f4af137054c49aabcd10c6996bb0e";
    }


    public void getCity(final Observer subscriber) {

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder().addInterceptor(new TokenInterceptor(getAuth()));
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        String baseUrl = "http://jisutqybmf.market.alicloudapi.com";

        mRetrofit = new Retrofit.Builder().
                client(httpClientBuilder.build())
//                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(StringConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl).
                        build();

        mRequest = mRetrofit.create(WeatherBaseRequest.class);

        mRequest.getCity()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }

    public void getWeather(Observer observer, String city) {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder().addInterceptor(new TokenInterceptor(getAuth()));
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        String baseUrl = "http://jisutqybmf.market.alicloudapi.com";

        mRetrofit = new Retrofit.Builder().
                client(httpClientBuilder.build())
//                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(StringConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).
                        baseUrl(baseUrl).
                        build();

        mRequest = mRetrofit.create(WeatherBaseRequest.class);
//        RequestBody body = buildCityBody(city);
        mRequest.getWeather(city)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    public RequestBody buildCityBody(String city) {
        RequestBody b = new MultipartBody.Builder()
                .addFormDataPart("city", city)
                .build();
        return b;
    }


    private static RequestBody buildMuilteRequest(JSONObject map) {
        MultipartBody.Builder b = new MultipartBody.Builder();
        b.setType(MediaType.parse("multipart/form-data; charset=utf-8"));
//        b.addFormDataPart("reqMsg", map.toString());
//        b.addFormDataPart("sign", MD5.stringMD5(map.toString() + MD5.stringMD5(RestServerDefines.REQUEST_SING)));
        RequestBody body = b.build();
        return body;
    }
}
