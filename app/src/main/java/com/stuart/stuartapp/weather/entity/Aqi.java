package com.stuart.stuartapp.weather.entity;

/**
 * Created by stuart on 2017/5/8.
 */

/**aqi":{"so2":"7","so224":"7","no2":"34","no224":"49","co":"0.420","co24":"0.430","o3":"169","o38":"97","o324":"101","pm10":"79",
 * "pm1024":"100","pm2_5":"52","pm2_524":"42","iso2":"3","ino2":"17","ico":"5","io3":"64","io38":"51","ipm10":"59","ipm2_5":"71",
 * "aqi":"71","primarypollutant":"PM2.5","quality":"良","timepoint":"2017-05-08 15:00:00",
 * "aqiinfo":{"level":"二级","color":"#FFFF00",
 * "affect":"空气质量可接受，但某些污染物可能对极少数异常敏感人群健康有较弱影响","measure":"极少数异常敏感人群应减少户外活动"}},*/
// 空气质量指数
public class Aqi {
    String so2; //	二氧化硫1小时平均
    String so224	;  //	二氧化硫24小时平均
    String no2	;  //	二氧化氮1小时平均
    String no224	;  //	二氧化氮24小时平均
    String co	;  //	一氧化碳1小时平均 mg/m3
    String co24	;  //	一氧化碳24小时平均 mg/m3
    String o3	;  //	臭氧1小时平均
    String o38	;  //	臭氧8小时平均
    String o324	;  //	臭氧24小时平均
    String pm10	;  //	PM10 1小时平均
    String pm1024	;  //	PM10 24小时平均
    String pm2_5	;  //	PM2.5 1小时平均
    String pm2_524	;  //	PM2.5 24小时平均
    String iso2	;  //	二氧化硫指数
    String ino2	;  //	二氧化氮指数
    String ico	;  //	一氧化碳指数
    String io3	;  //	臭氧指数
    String io38	;  //	臭氧8小时指数
    String ipm10	;  //	PM10指数
    String ipm2_5	;  //	PM2.5指数
    String aqi	;  //	AQI指数
    String primarypollutant	;  //	首要污染物
    String quality	;  //	空气质量指数类别，有“优、良、轻度污染、中度污染、重度污染、严重污染”6类
    String timepoint	;  //	发布时间
    String aqiinfo	;  //	AQI指数信息
    String level	;  //	等级
    String color	;  //	指数颜色值
    String affect	;  //	对健康的影响

    public String getSo2() {
        return so2;
    }

    public void setSo2(String so2) {
        this.so2 = so2;
    }

    public String getSo224() {
        return so224;
    }

    public void setSo224(String so224) {
        this.so224 = so224;
    }

    public String getNo2() {
        return no2;
    }

    public void setNo2(String no2) {
        this.no2 = no2;
    }

    public String getNo224() {
        return no224;
    }

    public void setNo224(String no224) {
        this.no224 = no224;
    }

    public String getCo() {
        return co;
    }

    public void setCo(String co) {
        this.co = co;
    }

    public String getCo24() {
        return co24;
    }

    public void setCo24(String co24) {
        this.co24 = co24;
    }

    public String getO3() {
        return o3;
    }

    public void setO3(String o3) {
        this.o3 = o3;
    }

    public String getO38() {
        return o38;
    }

    public void setO38(String o38) {
        this.o38 = o38;
    }

    public String getO324() {
        return o324;
    }

    public void setO324(String o324) {
        this.o324 = o324;
    }

    public String getPm10() {
        return pm10;
    }

    public void setPm10(String pm10) {
        this.pm10 = pm10;
    }

    public String getPm1024() {
        return pm1024;
    }

    public void setPm1024(String pm1024) {
        this.pm1024 = pm1024;
    }

    public String getPm2_5() {
        return pm2_5;
    }

    public void setPm2_5(String pm2_5) {
        this.pm2_5 = pm2_5;
    }

    public String getPm2_524() {
        return pm2_524;
    }

    public void setPm2_524(String pm2_524) {
        this.pm2_524 = pm2_524;
    }

    public String getIso2() {
        return iso2;
    }

    public void setIso2(String iso2) {
        this.iso2 = iso2;
    }

    public String getIno2() {
        return ino2;
    }

    public void setIno2(String ino2) {
        this.ino2 = ino2;
    }

    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }

    public String getIo3() {
        return io3;
    }

    public void setIo3(String io3) {
        this.io3 = io3;
    }

    public String getIo38() {
        return io38;
    }

    public void setIo38(String io38) {
        this.io38 = io38;
    }

    public String getIpm10() {
        return ipm10;
    }

    public void setIpm10(String ipm10) {
        this.ipm10 = ipm10;
    }

    public String getIpm2_5() {
        return ipm2_5;
    }

    public void setIpm2_5(String ipm2_5) {
        this.ipm2_5 = ipm2_5;
    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public String getPrimarypollutant() {
        return primarypollutant;
    }

    public void setPrimarypollutant(String primarypollutant) {
        this.primarypollutant = primarypollutant;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getTimepoint() {
        return timepoint;
    }

    public void setTimepoint(String timepoint) {
        this.timepoint = timepoint;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getAffect() {
        return affect;
    }

    public void setAffect(String affect) {
        this.affect = affect;
    }

    public Aqi() {

    }

    @Override
    public String toString() {
        return "Aqi{" +
                "so2='" + so2 + '\'' +
                ", so224='" + so224 + '\'' +
                ", no2='" + no2 + '\'' +
                ", no224='" + no224 + '\'' +
                ", co='" + co + '\'' +
                ", co24='" + co24 + '\'' +
                ", o3='" + o3 + '\'' +
                ", o38='" + o38 + '\'' +
                ", o324='" + o324 + '\'' +
                ", pm10='" + pm10 + '\'' +
                ", pm1024='" + pm1024 + '\'' +
                ", pm2_5='" + pm2_5 + '\'' +
                ", pm2_524='" + pm2_524 + '\'' +
                ", iso2='" + iso2 + '\'' +
                ", ino2='" + ino2 + '\'' +
                ", ico='" + ico + '\'' +
                ", io3='" + io3 + '\'' +
                ", io38='" + io38 + '\'' +
                ", ipm10='" + ipm10 + '\'' +
                ", ipm2_5='" + ipm2_5 + '\'' +
                ", aqi='" + aqi + '\'' +
                ", primarypollutant='" + primarypollutant + '\'' +
                ", quality='" + quality + '\'' +
                ", timepoint='" + timepoint + '\'' +
                ", aqiinfo='" + aqiinfo + '\'' +
                ", level='" + level + '\'' +
                ", color='" + color + '\'' +
                ", affect='" + affect + '\'' +
                '}';
    }
}
