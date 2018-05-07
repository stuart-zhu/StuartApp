package com.stuart.stuartapp.demo.weather.request;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by stuart on 2018/5/7.
 */

public class TokenInterceptor implements Interceptor {

    private String auth;
    public TokenInterceptor(String auth) {
        this.auth = auth;
    }
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();

        Request tokenRequest = originalRequest.newBuilder().header("accept", "application/json")
                .header("Content-Type", "application/json; charset=utf-8")
                .header("Authorization",auth)
                .build();
        return chain.proceed(tokenRequest);
    }
}
