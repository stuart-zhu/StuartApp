package com.stuart.stuartapp.demo.weather;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.stuart.stuartapp.demo.weather.entity.WeatherInfo;
import com.stuart.stuartapp.demo.weather.request.WeatherBaseRequest;
import com.stuart.stuartapp.utils.Contants;
import com.stuart.stuartapp.demo.weather.entity.Aqi;
import com.stuart.stuartapp.demo.weather.entity.CityInfo;
import com.stuart.stuartapp.demo.weather.entity.DailyWeather;
import com.stuart.stuartapp.demo.weather.entity.HourWeather;
import com.stuart.stuartapp.demo.weather.entity.IndexInfo;
import com.stuart.stuartapp.utils.LogUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.stuart.stuartapp.demo.weather.db.WeatherProvider.WEATHER_CITY_URI;


/**
 * Created by stuart on 2017/4/13.
 */

public class WeatherDateUtils {

    private static final String TAG = "WeatherDataUtils";

    private static final String BASE_URL = "http://webservice.36wu.com/weatherService.asmx/";


    public interface OnCityGetListener {
        public void onCityGetStart();
        public void onCityGetFinish();
    }
    public static void getAllCityInfo(final Context c, final OnCityGetListener l) {


        Observer<Object> observer = new Observer<Object>() {


            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(final Object o) {

                LogUtil.i(TAG, "getCity -> onNext", o.toString());

                new Thread(){
                    @Override
                    public void run() {
                        try {
                            JSONObject jo = new JSONObject(String.valueOf(o));
                            Log.i("stuart", "responseInfo.result = " + o.toString());
                            if (jo.getInt("status") == 0 && jo.getString("msg").equals("ok")) {
                                if (l != null) l.onCityGetStart();
                                JSONArray jsonArray = jo.getJSONArray("result");
                                Map<String,CityInfo> maps = new LinkedHashMap<String, CityInfo>();
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject o = jsonArray.getJSONObject(i);
                                    CityInfo info = new CityInfo();
                                    info.setCityId(o.getInt("cityid"));
                                    info.setParentId(o.getInt("parentid"));
                                    info.setCityCode(o.getString("citycode"));
                                    info.setCity(o.getString("city"));

                                    maps.put(info.getCityId()+"", info);
                                }
                                Set<String> keys = maps.keySet();
                                for (String key : keys) {
                                    CityInfo info = maps.get(key);
                                    CityInfo cityInfo = maps.get(info.getParentId() + "");
                                    if (cityInfo != null) {
                                        info.setCityP(cityInfo.getCity());
                                        info.setParentPId(cityInfo.getParentId());
                                        cityInfo = maps.get(cityInfo.getParentId() + "");
                                        if (cityInfo != null) info.setCityPP(cityInfo.getCity());
                                    }
                                    ContentValues cv = new ContentValues();
                                    cv.put("cityId", info.getCityId());
                                    cv.put("parentId", info.getParentId());
                                    cv.put("cityCode", info.getCityCode());
                                    cv.put("city", info.getCity());
                                    cv.put("parentPid",info.getParentPId());
                                    cv.put("cityPP", info.getCityPP());
                                    cv.put("cityP", info.getCityP());

                                    c.getContentResolver().insert(WEATHER_CITY_URI, cv);
                                }



                                if (l != null) l.onCityGetFinish();
                                Contants.setCiityAlready(c, true);
                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();

            }
        };
        com.stuart.stuartapp.demo.weather.request.HttpRequest.getInstance().getCity(observer);
    }

    public static void getWeather(Context context, String city, final onWeatherGetListener l) {
        /*String uri = "http://jisutqybmf.market.alicloudapi.com/weather/query";
        HttpUtils utils = new HttpUtils();
        RequestParams rp = new RequestParams();
        rp.addHeader("Authorization", "APPCODE " + "e35f4af137054c49aabcd10c6996bb0e");
        rp.addQueryStringParameter("city", city);
        utils.send(HttpRequest.HttpMethod.GET, uri, rp, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {

                try {
                    JSONObject
                            jo = new JSONObject(responseInfo.result);
                    if (jo.getInt("status") == 0 && jo.getString("msg").equals("ok")) {

                        JSONObject j = jo.getJSONObject("result");
                        WeatherInfo info = new WeatherInfo();
                        info.setCity(j.getString("city"));
                        info.setCityId(j.getInt("cityid"));
                        info.setCityCode(j.getString("citycode"));
                        info.setDate(j.getString("date"));
                        info.setWeek(j.getString("week"));
                        info.setWeather(j.getString("weather"));
                        info.setTemp(j.getString("temp"));
                        info.setTemphigh(j.getString("temphigh"));
                        info.setTemplow(j.getString("templow"));
                        info.setHumidity(j.getString("humidity"));
                        info.setPressure(j.getString("pressure"));
                        info.setWindspeed(j.getString("windspeed"));
                        info.setWinddirect(j.getString("winddirect"));
                        info.setWindpower(j.getString("windpower"));
                        info.setUpdatetime(j.getString("updatetime"));

                        JSONArray jIndex = j.getJSONArray("index");
                        for (int i = 0 ; i < jIndex.length(); i++) {
                            try {

                            JSONObject ji = jIndex.getJSONObject(i);
                            IndexInfo index = new IndexInfo(ji.getString("iname"), ji.getString("ivalue"), ji.getString("detail"));
                            info.addIndex(index);
                            } catch (JSONException e) {

                            }
                        }

                        JSONObject aqiJ = j.getJSONObject("aqi");
                        Aqi aqi = new Aqi();
                        aqi.setSo2(aqiJ.getString("so2"));
                        aqi.setSo224(aqiJ.getString("so224"));
                        aqi.setNo2(aqiJ.getString("no2"));
                        aqi.setNo224(aqiJ.getString("no224"));
                        aqi.setCo(aqiJ.getString("co"));
                        aqi.setCo24(aqiJ.getString("co24"));
                        aqi.setO3(aqiJ.getString("o3"));
                        aqi.setO38(aqiJ.getString("o38"));
                        aqi.setO324(aqiJ.getString("o324"));
                        aqi.setPm10(aqiJ.getString("pm10"));
                        aqi.setPm1024(aqiJ.getString("pm1024"));
                        aqi.setPm2_5(aqiJ.getString("pm2_5"));
                        aqi.setPm2_524(aqiJ.getString("pm2_524"));
                        aqi.setIno2(aqiJ.getString("ino2"));
                        aqi.setIco(aqiJ.getString("ico"));
                        aqi.setIo3(aqiJ.getString("io3"));
                        aqi.setIo38(aqiJ.getString("io38"));
                        aqi.setIpm10(aqiJ.getString("ipm10"));
                        aqi.setIpm2_5(aqiJ.getString("ipm2_5"));
                        aqi.setAqi(aqiJ.getString("aqi"));
                        aqi.setPrimarypollutant(aqiJ.getString("primarypollutant"));
                        aqi.setQuality(aqiJ.getString("quality"));
                        aqi.setTimepoint(aqiJ.getString("timepoint"));

                        JSONObject aqiinfo = aqiJ.getJSONObject("aqiinfo");
                        aqi.setLevel(aqiinfo.getString("level"));
                        aqi.setColor(aqiinfo.getString("color"));
                        aqi.setAffect(aqiinfo.getString("affect"));

                        info.setAqi(aqi);
                        
                        JSONArray dailyJ = j.getJSONArray("daily");
                        for (int i = 0; i < dailyJ.length(); i++) {
                            JSONObject jsonObject = dailyJ.getJSONObject(i);
                            DailyWeather dw = new DailyWeather();
                            dw.setDate(jsonObject.getString("date"));
                            dw.setWeek(jsonObject.getString("week"));
                            dw.setSunrise(jsonObject.getString("sunrise"));
                            dw.setSunset(jsonObject.getString("sunset"));
                            JSONObject night = jsonObject.getJSONObject("night");
                            dw.setNight_weather(night.getString("weather"));
                            dw.setTemplow(night.getString("templow"));
                            dw.setNight_winddirect(night.getString("winddirect"));
                            dw.setNight_windpower(night.getString("windpower"));
                            JSONObject day = jsonObject.getJSONObject("day");
                            dw.setDay_weather(day.getString("weather"));
                            dw.setTempHigh(day.getString("temphigh"));
                            dw.setDay_winddirect(day.getString("winddirect"));
                            dw.setDay_windpower(day.getString("windpower"));
                            info.addDailyWeather(dw);
                        }

                        JSONArray hourly = j.getJSONArray("hourly");

                        //time":"18:00","weather":"多云","temp":"28","img":"1"},
                        for (int i = 0 ; i < hourly.length() ; i++) {
                            JSONObject jsonObject = hourly.getJSONObject(i);
                            HourWeather hw = new HourWeather();
                            hw.setTemp(jsonObject.getString("temp"));
                            hw.setTime(jsonObject.getString("time"));
                            hw.setWeather(jsonObject.getString("weather"));
                            info.addHourWeather(hw);
                        }

                        l.onWeatherGet(info);
                        Log.i("stuart", "info = " + info.toString());


                    } else {
                        l.onWeatherGetFailure("status = " + jo.getInt("status") +",msg = "+ jo.getString("msg"));
                    }
                } catch (JSONException e) {
                    Log.e("stuart", "",e);
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(HttpException e, String s) {
                l.onWeatherGetFailure(e.getLocalizedMessage());
            }
        });
*/
        Observer<Object> sub = new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object value) {
                try {
                    JSONObject
                            jo = new JSONObject(value.toString());
                    if (jo.getInt("status") == 0 && jo.getString("msg").equals("ok")) {

                        JSONObject j = jo.getJSONObject("result");
                        WeatherInfo info = new WeatherInfo();
                        info.setCity(j.getString("city"));
                        info.setCityId(j.getInt("cityid"));
                        info.setCityCode(j.getString("citycode"));
                        info.setDate(j.getString("date"));
                        info.setWeek(j.getString("week"));
                        info.setWeather(j.getString("weather"));
                        info.setTemp(j.getString("temp"));
                        info.setTemphigh(j.getString("temphigh"));
                        info.setTemplow(j.getString("templow"));
                        info.setHumidity(j.getString("humidity"));
                        info.setPressure(j.getString("pressure"));
                        info.setWindspeed(j.getString("windspeed"));
                        info.setWinddirect(j.getString("winddirect"));
                        info.setWindpower(j.getString("windpower"));
                        info.setUpdatetime(j.getString("updatetime"));

                        JSONArray jIndex = j.getJSONArray("index");
                        for (int i = 0 ; i < jIndex.length(); i++) {
                            try {

                                JSONObject ji = jIndex.getJSONObject(i);
                                IndexInfo index = new IndexInfo(ji.getString("iname"), ji.getString("ivalue"), ji.getString("detail"));
                                info.addIndex(index);
                            } catch (JSONException e) {

                            }
                        }

                        JSONObject aqiJ = j.getJSONObject("aqi");
                        Aqi aqi = new Aqi();
                        aqi.setSo2(aqiJ.getString("so2"));
                        aqi.setSo224(aqiJ.getString("so224"));
                        aqi.setNo2(aqiJ.getString("no2"));
                        aqi.setNo224(aqiJ.getString("no224"));
                        aqi.setCo(aqiJ.getString("co"));
                        aqi.setCo24(aqiJ.getString("co24"));
                        aqi.setO3(aqiJ.getString("o3"));
                        aqi.setO38(aqiJ.getString("o38"));
                        aqi.setO324(aqiJ.getString("o324"));
                        aqi.setPm10(aqiJ.getString("pm10"));
                        aqi.setPm1024(aqiJ.getString("pm1024"));
                        aqi.setPm2_5(aqiJ.getString("pm2_5"));
                        aqi.setPm2_524(aqiJ.getString("pm2_524"));
                        aqi.setIno2(aqiJ.getString("ino2"));
                        aqi.setIco(aqiJ.getString("ico"));
                        aqi.setIo3(aqiJ.getString("io3"));
                        aqi.setIo38(aqiJ.getString("io38"));
                        aqi.setIpm10(aqiJ.getString("ipm10"));
                        aqi.setIpm2_5(aqiJ.getString("ipm2_5"));
                        aqi.setAqi(aqiJ.getString("aqi"));
                        aqi.setPrimarypollutant(aqiJ.getString("primarypollutant"));
                        aqi.setQuality(aqiJ.getString("quality"));
                        aqi.setTimepoint(aqiJ.getString("timepoint"));

                        JSONObject aqiinfo = aqiJ.getJSONObject("aqiinfo");
                        aqi.setLevel(aqiinfo.getString("level"));
                        aqi.setColor(aqiinfo.getString("color"));
                        aqi.setAffect(aqiinfo.getString("affect"));

                        info.setAqi(aqi);

                        JSONArray dailyJ = j.getJSONArray("daily");
                        for (int i = 0; i < dailyJ.length(); i++) {
                            JSONObject jsonObject = dailyJ.getJSONObject(i);
                            DailyWeather dw = new DailyWeather();
                            dw.setDate(jsonObject.getString("date"));
                            dw.setWeek(jsonObject.getString("week"));
                            dw.setSunrise(jsonObject.getString("sunrise"));
                            dw.setSunset(jsonObject.getString("sunset"));
                            JSONObject night = jsonObject.getJSONObject("night");
                            dw.setNight_weather(night.getString("weather"));
                            dw.setTemplow(night.getString("templow"));
                            dw.setNight_winddirect(night.getString("winddirect"));
                            dw.setNight_windpower(night.getString("windpower"));
                            JSONObject day = jsonObject.getJSONObject("day");
                            dw.setDay_weather(day.getString("weather"));
                            dw.setTempHigh(day.getString("temphigh"));
                            dw.setDay_winddirect(day.getString("winddirect"));
                            dw.setDay_windpower(day.getString("windpower"));
                            info.addDailyWeather(dw);
                        }

                        JSONArray hourly = j.getJSONArray("hourly");

                        //time":"18:00","weather":"多云","temp":"28","img":"1"},
                        for (int i = 0 ; i < hourly.length() ; i++) {
                            JSONObject jsonObject = hourly.getJSONObject(i);
                            HourWeather hw = new HourWeather();
                            hw.setTemp(jsonObject.getString("temp"));
                            hw.setTime(jsonObject.getString("time"));
                            hw.setWeather(jsonObject.getString("weather"));
                            info.addHourWeather(hw);
                        }

                        l.onWeatherGet(info);
                        Log.i("stuart", "info = " + info.toString());


                    } else {
                        l.onWeatherGetFailure("status = " + jo.getInt("status") +",msg = "+ jo.getString("msg"));
                    }
                } catch (JSONException e) {
                    Log.e("stuart", "",e);
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable e) {

                LogUtil.e(TAG, "getWeather onError", e.getLocalizedMessage());
            }

            @Override
            public void onComplete() {

            }
        };
        com.stuart.stuartapp.demo.weather.request.HttpRequest.getInstance().getWeather(sub, city);
    }

    public interface onWeatherGetListener {
        public void onWeatherGet(WeatherInfo info);

        public void onWeatherGetFailure(String msg);
    }
    /**
     * "status":"0","msg":"ok","result":{"city":"海淀区","cityid":"501","citycode":"101010200","date":"2017-05-08",
     * "week":"星期一","weather":"多云","temp":"30","temphigh":"31","templow":"15","img":"1","humidity":"16",
     * "pressure":"1005","windspeed":"4.0","winddirect":"西南风","windpower":"3级",
     * "updatetime":"2017-05-08 15:54:55","
     * index":[{"iname":"空调指数","ivalue":"部分时间开启","detail":"天气热，到中午的时候您将会感到有点热，因此建议在午后较热时开启制冷空调。"},
     * {"iname":"运动指数","ivalue":"较适宜","detail":"天气较好，但因风力稍强，户外可选择对风力要求不高的运动，推荐您进行室内运动。"},
     * {"iname":"紫外线指数","ivalue":"中等","detail":"属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。"},
     * {"iname":"感冒指数","ivalue":"少发","detail":"各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。"},
     * {"iname":"洗车指数","ivalue":"较适宜","detail":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"},{
     * "iname":"空气污染扩散指数","index":"良","detail":"气象条件有利于空气污染物稀释、扩散和清除，可在室外正常活动。"},
     * {"iname":"穿衣指数","ivalue":"热","detail":"天气热，建议着短裙、短裤、短薄外套、T恤等夏季服装。"}]
     * ,"aqi":{"so2":"7","so224":"7","no2":"34","no224":"49","co":"0.420","co24":"0.430","o3":"169","o38":"97","o324":"101","pm10":"79",
     * "pm1024":"100","pm2_5":"52","pm2_524":"42","iso2":"3","ino2":"17","ico":"5","io3":"64","io38":"51","ipm10":"59","ipm2_5":"71",
     * "aqi":"71","primarypollutant":"PM2.5","quality":"良","timepoint":"2017-05-08 15:00:00",
     * "aqiinfo":{"level":"二级","color":"#FFFF00",
     * "affect":"空气质量可接受，但某些污染物可能对极少数异常敏感人群健康有较弱影响","measure":"极少数异常敏感人群应减少户外活动"}},
     * "daily":[{"date":"2017-05-08","week":"星期一","sunrise":"05:07","sunset":"19:15",
     * "night":{"weather":"多云","templow":"15","img":"1","winddirect":"南风","windpower":"微风"},
     * "day":{"weather":"多云","temphigh":"31","img":"1","winddirect":"南风","windpower":"3-4 级"}},
     * {"date":"2017-05-09","week":"星期二","sunrise":"05:06","sunset":"19:16","night":{"weather":"阵雨","templow":"14","img":"3","winddirect":"东风","windpower":"微风"},
     * "day":{"weather":"多云","temphigh":"29","img":"1","winddirect":"南风","windpower":"3-4 级"}},{
     * "date":"2017-05-10","week":"星期三","sunrise":"05:05","sunset":"19:17","night":{"weather":"多云","templow":"14","img":"1","winddirect":"南风","windpower":"微风"},
     * "day":{"weather":"阴","temphigh":"30","img":"2","winddirect":"南风","windpower":"微风"}},
     * {"date":"2017-05-11","week":"星期四","sunrise":"05:04","sunset":"19:18","night":{"weather":"多云","templow":"16","img":"1","winddirect":"南风","windpower":"微风"},
     * "day":{"weather":"晴","temphigh":"33","img":"0","winddirect":"北风","windpower":"3-4 级"}},
     * {"date":"2017-05-12","week":"星期五","sunrise":"05:03","sunset":"19:19","night":{"weather":"多云","templow":"16","img":"1","winddirect":"西北风","windpower":"4-5 级"}
     * ,"day":{"weather":"多云","temphigh":"31","img":"1","winddirect":"西南风","windpower":"微风"}},
     * {"date":"2017-05-13","week":"星期六","sunrise":"07:30","sunset":"19:30","night":{"weather":"晴","templow":"14","img":"0","winddirect":"西北风","windpower":"4级"},
     * day":{"weather":"晴","temphigh":"28","img":"0","winddirect":"西北风","windpower":"4级"}},
     * {"date":"2017-05-14","week":"星期日","sunrise":"07:30","sunset":"19:30","night":{"weather":"晴","templow":"14","img":"0","winddirect":"西北风","windpower":"4级"},
     * "day":{"weather":"晴","temphigh":"28","img":"0","winddirect":"西北风","windpower":"4级"}}],
     * "hourly":[{"time":"17:00","weather":"多云","temp":"29","img":"1"},
     * {"time":"18:00","weather":"多云","temp":"28","img":"1"},
     * {"time":"19:00","weather":"多云","temp":"26","img":"1"},
     * {"time":"20:00","weather":"多云","temp":"24","img":"1"},
     * {"time":"21:00","weather":"多云","temp":"23","img":"1"},
     * {"time":"22:00","weather":"多云","temp":"23","img":"1"},
     * {"time":"23:00","weather":"多云","temp":"21","img":"1"},
     * {"time":"0:00","weather":"多云","temp":"20","img":"1"},
     * {"time":"1:00","weather":"多云","temp":"19","img":"1"},
     * {"time":"2:00","weather":"多云","temp":"18","img":"1"},
     * {"time":"3:00","weather":"多云","temp":"16","img":"1"},
     * {"time":"4:00","weather":"多云","temp":"14","img":"1"},
     * {"time":"5:00","weather":"晴","temp":"14","img":"0"},
     * {"time":"6:00","weather":"晴","temp":"16","img":"0"},
     * {"time":"7:00","weather":"晴","temp":"18","img":"0"},
     * {"time":"8:00","weather":"晴","temp":"21","img":"0"},
     * {"time":"9:00","weather":"晴","temp":"22","img":"0"},
     * {"time":"10:00","weather":"晴","temp":"24","img":"0"},
     * {"time":"11:00","weather":"晴","temp":"26","img":"0"},
     * {"time":"12:00","weather":"晴","temp":"27","img":"0"},
     * {"time":"13:00","weather":"晴","temp":"28","img":"0"},
     * {"time":"14:00","weather":"晴","temp":"28","img":"0"},
     * {"time":"15:00","weather":"晴","temp":"29","img":"0"},
     * {"time":"16:00","weather":"晴","temp":"28","img":"0"}]}}
     */

}
