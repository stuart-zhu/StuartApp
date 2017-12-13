package com.stuart.stuartapp.demo1.haoma;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by stuart on 2017/12/12.
 */

public class HaoMaUtils {

    public interface CallBack {
        public void onLoadFailed(String msg);
        public void onLoadSuccess(String msg);
    }
    public static void loadGSD(String number, final CallBack mCallback) {
        String url = "http://api04.aliyun.venuscn.com/mobile";
        HttpUtils httpUtils = new HttpUtils();
        RequestParams rp = new RequestParams();

        rp.addHeader("Authorization", "APPCODE e35f4af137054c49aabcd10c6996bb0e");
        rp.addBodyParameter("mobile", number);
        httpUtils.send(HttpRequest.HttpMethod.GET, url, rp, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {

                String x = (String) responseInfo.result;
                try {
                    JSONObject jo = new JSONObject(x);/*
                    {"ret":200,"msg":"success","errorMsg":null,"data":
                    {"result":
                    {"types":"中国移动 GSM","lng":"116.407526","city":"北京",
                    "num":1520152,"isp":"移动","area_code":"110100","city_code":"010",
                    "prov":"北京","zip_code":"100000","lat":"39.904030"}}}*/
                    JSONObject data = jo.getJSONObject("data");
                    data = data.getJSONObject("result");
                    String city = data.getString("prov");
                    String isp = data.getString("isp");
                    if (mCallback != null) mCallback.onLoadSuccess(city +isp);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(HttpException e, String s) {
                if (mCallback != null) mCallback.onLoadSuccess(s);
            }
        });
    }
}
