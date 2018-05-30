package com.stuart.stuartapp.demo1.yueche.http.form;

import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by stuart on 2018/5/29.
 */
public class FormTokenInterceptor implements Interceptor {
    public FormTokenInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();



        Request tokenRequest = originalRequest.newBuilder()/*.header("accept", "application/json")*/

                .header("User-Agent",  "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36")

                .build();



        return chain.proceed(tokenRequest);
    }
}
