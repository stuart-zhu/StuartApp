package com.stuart.stuartapp.demo.jktk;

import android.util.Log;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.stuart.stuartapp.demo.jktk.entity.JKTK;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stuart on 2017/12/1.
 */

public class JKTKUtils {

    /***
     * pagenum	STRING	必选	当前页数
     pagesize	STRING	必选	每页数量 默认1
     sort	STRING	必选	排序方式 正常排序normal 随机排序rand 默认normal
     subject	STRING	必选	科目类别 1为科目一 4为科目四 默认1
     type	STRING	必选	题目类型 分为A1,A3,B1,A2,B2,C1,C2,C3,D,E,F 默认C1
     */

    public interface OnLoadJKTKListener {
        public void onJKTKLoad(List<JKTK> list);
        public void onLoadJKTLFaile(String msg);
        public void onJKTKLoadStart();
    }
    public static void loadJktk(String pageNum, String pageSize, String sort, String subject, String type, final OnLoadJKTKListener listener) {

        String url = "http://jisujiakao.market.alicloudapi.com/driverexam/query";
        HttpUtils utils = new HttpUtils();
        RequestParams rp = new RequestParams();
        rp.addHeader("Authorization", "APPCODE " + "e35f4af137054c49aabcd10c6996bb0e");
        rp.addQueryStringParameter("pagenum", pageNum);
        rp.addQueryStringParameter("pagesize", pageSize);
        rp.addQueryStringParameter("sort", sort);
        rp.addQueryStringParameter("subject", subject);
        rp.addQueryStringParameter("type", type);
        if (listener != null) listener.onJKTKLoadStart();

        utils.send(HttpRequest.HttpMethod.GET, url, rp, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Log.i("stuart", "onSuccess（" +responseInfo.result);
                try {
                    JSONObject jo = new JSONObject(responseInfo.result);
                    int status = jo.getInt("status");
                    if (status == 0) {
                        JSONObject jsonO = jo.getJSONObject("result");
                        JSONArray list = jsonO.getJSONArray("list");
                        List<JKTK> jktks = new ArrayList<>();
                        for (int i = 0; i < list.length(); i++) {
                            JSONObject jsonObject = list.getJSONObject(i);
                            JKTK jk = new JKTK();

                            jk.setSubject(jsonO.getInt("subject"));
                            jk.setQuestion(jsonObject.getString("question"));
                            jk.setOption1(jsonObject.getString("option1"));
                            jk.setOption2(jsonObject.getString("option2"));
                            jk.setOption3(jsonObject.getString("option3"));
                            jk.setOption4(jsonObject.getString("option4"));
                            jk.setAnswer(jsonObject.getString("answer"));
                            jk.setExplain(jsonObject.getString("explain"));
                            jk.setPic(jsonObject.getString("pic"));
                            jk.setType(jsonO.getString("type"));
                            jk.setChapter(jsonObject.getString("chapter"));
                            Log.i("stuart", "jk = " + jk);
                            jktks.add(jk);
                        }
                        if (listener != null) listener.onJKTKLoad(jktks);
                    }
                    
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Log.i("stuart", "onFailure(" + s +"," + e.getLocalizedMessage());
                if (listener != null) listener.onLoadJKTLFaile(s);
            }
        });


    }
    }

