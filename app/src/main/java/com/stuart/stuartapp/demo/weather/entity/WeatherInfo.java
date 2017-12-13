package com.stuart.stuartapp.demo.weather.entity;

/**
 * Created by stuart on 2017/5/8.
 */

import java.util.ArrayList;
import java.util.List;

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
 * {"time":"18:00","weather":"多云","temp":"28","img":"1"},{"time":"19:00","weather":"多云","temp":"26","img":"1"},{"time":"20:00","weather":"多云","temp":"24","img":"1"},
 * {"time":"21:00","weather":"多云","temp":"23","img":"1"},{"time":"22:00","weather":"多云","temp":"23","img":"1"},{"time":"23:00","weather":"多云","temp":"21","img":"1"},
 * {"time":"0:00","weather":"多云","temp":"20","img":"1"},{"time":"1:00","weather":"多云","temp":"19","img":"1"},{"time":"2:00","weather":"多云","temp":"18","img":"1"},
 * {"time":"3:00","weather":"多云","temp":"16","img":"1"},{"time":"4:00","weather":"多云","temp":"14","img":"1"},{"time":"5:00","weather":"晴","temp":"14","img":"0"},
 * {"time":"6:00","weather":"晴","temp":"16","img":"0"},{"time":"7:00","weather":"晴","temp":"18","img":"0"},{"time":"8:00","weather":"晴","temp":"21","img":"0"},
 * {"time":"9:00","weather":"晴","temp":"22","img":"0"},{"time":"10:00","weather":"晴","temp":"24","img":"0"},{"time":"11:00","weather":"晴","temp":"26","img":"0"},
 * {"time":"12:00","weather":"晴","temp":"27","img":"0"},{"time":"13:00","weather":"晴","temp":"28","img":"0"},{"time":"14:00","weather":"晴","temp":"28","img":"0"},
 * {"time":"15:00","weather":"晴","temp":"29","img":"0"},{"time":"16:00","weather":"晴","temp":"28","img":"0"}]}}
 */
public class WeatherInfo {
    private int id;
    private String city;
    private int cityId;
    private String cityCode;
    private String date;
    private String week;
    private String weather;
    private String temp;
    private String temphigh;
    private String templow;
    private String humidity;// 湿度
    private String pressure; // 气压
    private String windspeed; // 风速
    private String winddirect; // 风向
    private String windpower; // 风力
    private String updatetime;

    private List<IndexInfo> indexs; // 空气指数

    private List<DailyWeather> dailyWeathers;
    private List<HourWeather> hourWeathers;
    private Aqi mAqi;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getTemphigh() {
        return temphigh;
    }

    public void setTemphigh(String temphigh) {
        this.temphigh = temphigh;
    }

    public String getTemplow() {
        return templow;
    }

    public void setTemplow(String templow) {
        this.templow = templow;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getWindspeed() {
        return windspeed;
    }

    public void setWindspeed(String windspeed) {
        this.windspeed = windspeed;
    }

    public String getWinddirect() {
        return winddirect;
    }

    public void setWinddirect(String winddirect) {
        this.winddirect = winddirect;
    }

    public String getWindpower() {
        return windpower;
    }

    public void setWindpower(String windpower) {
        this.windpower = windpower;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public List<IndexInfo> getIndexs() {
        return indexs;
    }

    public void addIndex(IndexInfo info) {
        if (indexs == null) indexs = new ArrayList<>();
        indexs.add(info);
    }

    public void setIndexs(List<IndexInfo> indexs) {
        this.indexs = indexs;
    }

    public Aqi getAqi() {
        return mAqi;
    }

    public void setAqi(Aqi mAqi) {
        this.mAqi = mAqi;
    }


    public List<DailyWeather> getDailyWeathers() {
        return dailyWeathers;
    }

    public void addDailyWeather(DailyWeather dw) {
        if (dailyWeathers == null) dailyWeathers = new ArrayList<>();
        dailyWeathers.add(dw);
    }
    public void setDailyWeathers(List<DailyWeather> dailyWeathers) {
        this.dailyWeathers = dailyWeathers;
    }

    public List<HourWeather> getHourWeathers() {
        return hourWeathers;
    }

    public void addHourWeather(HourWeather hw) {
        if (hourWeathers == null) hourWeathers = new ArrayList<>();
        hourWeathers.add(hw);
    }
    public void setHourWeathers(List<HourWeather> hourWeathers) {
        this.hourWeathers = hourWeathers;
    }

    @Override
    public String toString() {
        return "WeatherInfo{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", cityId=" + cityId +
                ", cityCode='" + cityCode + '\'' +
                ", date='" + date + '\'' +
                ", week='" + week + '\'' +
                ", weather='" + weather + '\'' +
                ", temp=" + temp +
                ", temphigh=" + temphigh +
                ", templow=" + templow +
                ", humidity=" + humidity +
                ", pressure=" + pressure +
                ", windspeed='" + windspeed + '\'' +
                ", winddirect='" + winddirect + '\'' +
                ", windpower='" + windpower + '\'' +
                ", updatetime='" + updatetime + '\'' +
                ", mAqi=" + mAqi +
                ", indexs=" + indexs +
                ", dailyWeathers=" + dailyWeathers +
                ", hourWeathers=" + hourWeathers +

                '}';
    }
}

