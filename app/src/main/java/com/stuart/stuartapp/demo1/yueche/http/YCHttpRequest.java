package com.stuart.stuartapp.demo1.yueche.http;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by stuart on 2018/5/29.
 */
public interface YCHttpRequest {

    @POST(YueContants.LOGIN_URL)
    Observable<ResponseBody> login(@Body RequestBody p);

    @POST(YueContants.SET_LOGIN_STATUS)
    Observable<ResponseBody> setLoginStatus(@Body RequestBody p);

    @POST(YueContants.QUERY_MSG_URL)
    Observable<ResponseBody> queryMsg(@Body RequestBody p);

    @POST(YueContants.YC_URL)
    Observable<ResponseBody> yueChe(@Body RequestBody p);
}
