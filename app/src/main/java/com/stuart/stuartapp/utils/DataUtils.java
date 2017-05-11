package com.stuart.stuartapp.utils;

import android.util.Log;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.stuart.stuartapp.callback.GetSSQListener;
import com.stuart.stuartapp.entity.SSQ;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by stuart on 2016/11/14.
 */
public class DataUtils {

    private static final String TAG = "DataUtils";
    /**
     * 开彩网API开放平台(免费接口)提供基础的彩票开奖信息，包括开奖期号、开奖号码、开奖时间等内容。开彩网API开放平台暂时只提供基于web的访问方式，格式支持xml与json两种格式，以后会陆续推出基于Socket、HttpPush与WebService的调用方式。开彩网API开放平台彩票json接口支持以callback作为回调参数的jsonp调用方式。
     免费接口网址	http://f.apiplus.cn/[彩票代码]-[返回行数].[返回格式]
     参数名称	是否必填	参数说明	参数演示
     [彩票代码]	必填	详见彩票列表	重庆时时彩：cqssc
     双色球：ssq
     [返回格式]	必填	返回的数据格式，xml或json	xml,json
     [返回行数]	选填	不填时默认返回5行数据，如果需要返回不同的行数时填写，范围从1行到50行，
     [返回行数]参数前面的"-"符号是连接符，当采用默认值时请移除。	1,5,14,22,50等
     免费接口例程	http://f.apiplus.cn/cqssc.xml (重庆时时彩xml格式5行)
     http://f.apiplus.cn/cqssc.json (重庆时时彩json格式5行utf-8编码)
     http://f.apiplus.cn/cqssc-10.json (重庆时时彩json格式10行utf-8编码)
     注：所有参数不区别大小写
     */

















   // {"rows":7,"code":"ssq","info":"免费接口随机延迟3-6分钟，实时接口请访问opencai.net或QQ:23081452(注明彩票或API)",
    // "data":[{"expect":"2016133","opencode":"15,16,21,22,27,33+15","opentime":"2016-11-13 21:20:40","opentimestamp":1479043240},{"expect":"2016132","opencode":"05,08,13,19,27,28+07","opentime":"2016-11-10 21:20:40","opentimestamp":1478784040},{"expect":"2016131","opencode":"04,10,18,19,25,27+02","opentime":"2016-11-08 21:20:40","opentimestamp":1478611240},{"expect":"2016130","opencode":"03,17,21,23,27,28+01","opentime":"2016-11-06 21:20:40","opentimestamp":1478438440},{"expect":"2016129","opencode":"05,06,08,21,31,33+14","opentime":"2016-11-03 21:20:40","opentimestamp":1478179240},{"expect":"2016128","opencode":"04,09,11,17,26,27+13","opentime":"2016-11-01 21:20:40","opentimestamp":1478006440},{"expect":"2016127","opencode":"07,12,17,26,29,31+16","opentime":"2016-10-30 21:20:40","opentimestamp":1477833640}]}
    public static void getSsq(int line, final GetSSQListener l) {
//
/**
        String uri = "http://f.apiplus.cn/ssq-"+line+".json";

        uri = "http://baidu.lecai.com/api/hao123/new_lottery_all.php";
        HttpUtils u = new HttpUtils();
        u.send(HttpRequest.HttpMethod.GET, uri, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {

               String res =  new String(responseInfo.result);
                System.out.print(res);
                Log.i("stuart","result :" + res + ")");
                try
                {
                    JSONObject jo = new JSONObject(responseInfo.result);



                }catch (JSONException e) {

                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Log.i("stuart","");
            }
        });
*/


        /*HttpUtils utils = new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, uri, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                LogUtil.i(TAG,"getSsq", "onSuccess" );
                try {
                    JSONObject jo = new JSONObject(responseInfo.result);
                    JSONArray ja = jo.getJSONArray("data");
                    List<SSQ> list = new ArrayList<>();
                    for (int i = 0 ;i < ja.length() ;i ++) {
                        Object  j = ja.get(i);
                        JSONObject jj = new JSONObject(String.valueOf(j));
                        SSQ s = new SSQ();
                        s.setExpect(jj.getString("expect"));
                        s.setOpenTime(jj.getString("opentime"));
                        String openCode = jj.getString("opencode");
                        String[] balls =  openCode.split("\\+");
                        s.setBlue(balls[1]);
                        String[] red = balls[0].split(",");
                        s.addRed(red);
                        list.add(s);
                    }
                    if (l != null) {
                        l.onGetSuccess(list);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                LogUtil.e(TAG,"getSsq", "onFailure  " + e.getMessage() );
                if (l != null) l.onGetFaile(s);
            }
        });*/
    }


    private static String decode(String s) {

        StringBuffer sb = new StringBuffer();
        Pattern p = Pattern.compile("(?i)\\\\u([\\da-f]{4})");
        Matcher m = p.matcher(s);
        while (m.find()) {
            m.appendReplacement(sb,
                    Character.toString((char) Integer.parseInt(m.group(1), 16)));
        }
        m.appendTail(sb);
        return m.toString();
    }
}
