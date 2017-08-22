package com.stuart.stuartapp.news;

import android.support.annotation.Nullable;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.stuart.stuartapp.utils.LogUtil;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by stuart on 2017/8/22.
 */

public class NewsUtils {

    private static final String TAG = "NewsUtils";

    /**
     * {"uniquekey":"610ecdcc4e6ef67c3a57c83e1d194af0","title":"四川庞统叔父隐居地 10颗明代佛头被盗"
     * ,
     * "date":"2017-08-22 08:39","category":"头条","author_name":"封面新闻",
     * "url":"http:\/\/mini.eastday.com\/mobile\/170822083936207.html",
     * "thumbnail_pic_s":"http:\/\/05.imgmini.eastday.com\/mobile\/20170822\/20170822083936_22599d876050907b1ea6806da85e185f_4_mwpm_03200403.jpg",
     * "thumbnail_pic_s02":"http:\/\/05.imgmini.eastday.com\/mobile\/20170822\/20170822083936_22599d876050907b1ea6806da85e185f_2_mwpm_03200403.jpg",
     * "thumbnail_pic_s03":"http:\/\/05.imgmini.eastday.com\/mobile\/20170822\/20170822083936_22599d876050907b1ea6806da85e185f_3_mwpm_03200403.jpg"}
     */

    public interface onLoadData {
        void onLoadSuccess(List<News> news);

        void onLoadFaile(String msg);
    }

    public static void loadNews(final onLoadData l) {


        String uri = "http://toutiao-ali.juheapi.com/toutiao/index";

        HttpUtils utils = new HttpUtils();
        RequestParams rp = new RequestParams();
        rp.addHeader("Host", "toutiao-ali.juheapi.com");
        rp.addHeader("X-Ca-Timestamp", "" + System.currentTimeMillis());
        rp.addHeader("gateway_channel", "http");
        rp.addHeader("X-Ca-Request-Mode", "debug");
        rp.addHeader("X-Ca-Key", "23807577");
        rp.addHeader("X-Ca-Stage", "RELEASE");
        rp.addHeader("Content-Type", "application/json; charset=utf-8");
        rp.addHeader("Authorization", "APPCODE e35f4af137054c49aabcd10c6996bb0e");
        utils.send(HttpRequest.HttpMethod.GET, uri, rp, new RequestCallBack() {
            @Override
            public void onSuccess(ResponseInfo responseInfo) {
                LogUtil.i(TAG, "onSuccess", responseInfo.result + "");
                List<News> news = new ArrayList<News>();
                try {
                    JSONObject jj = new JSONObject(responseInfo.result.toString());
                    JSONObject result = jj.getJSONObject("result");
                    int stat = result.getInt("stat");
                    if (stat == 1) {
                        JSONArray data = result.getJSONArray("data");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject jo = data.getJSONObject(i);
                            News n = new News();

                            /**
                             * {"uniquekey":"610ecdcc4e6ef67c3a57c83e1d194af0","title":"四川庞统叔父隐居地 10颗明代佛头被盗"
                             * ,
                             * "date":"2017-08-22 08:39","category":"头条","author_name":"封面新闻",
                             * "url":"http:\/\/mini.eastday.com\/mobile\/170822083936207.html",
                             * "thumbnail_pic_s":"http:\/\/05.imgmini.eastday.com\/mobile\/20170822\/20170822083936_22599d876050907b1ea6806da85e185f_4_mwpm_03200403.jpg",
                             * "thumbnail_pic_s02":"http:\/\/05.imgmini.eastday.com\/mobile\/20170822\/20170822083936_22599d876050907b1ea6806da85e185f_2_mwpm_03200403.jpg",
                             * "thumbnail_pic_s03":"http:\/\/05.imgmini.eastday.com\/mobile\/20170822\/20170822083936_22599d876050907b1ea6806da85e185f_3_mwpm_03200403.jpg"}
                             */
                            n.setUniquekey(jo.getString("uniquekey"));
                            n.setTitle(jo.getString("title"));
                            n.setDate(getTime(jo.getString("date")));
                            n.setCategory(jo.getString("category"));
                            n.setAuthor_name(jo.getString("author_name"));
                            n.setUrl(jo.getString("url"));
                            String thumbnail_pic_s = "thumbnail_pic_s";
                            for (int j = 0; j < 10; j++) {
                                if (j == 0) {
                                    if (!jo.isNull(thumbnail_pic_s)) {
                                        n.addThumbnail_pic_s(jo.getString(thumbnail_pic_s));
                                    }
                                } else {
                                    if (!jo.isNull(thumbnail_pic_s +"0" + j)) {
                                        n.addThumbnail_pic_s(jo.getString(thumbnail_pic_s +"0" + j));
                                    }
                                }
                            }

                            news.add(n);

                        }
                        if (l != null) l.onLoadSuccess(news);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {

                LogUtil.e(TAG, "loadNews", e.getLocalizedMessage());
                if (l != null) l.onLoadFaile(e.getLocalizedMessage());
            }
        });


    }

    private static long getTime(String t) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            return fmt.parse(t).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
