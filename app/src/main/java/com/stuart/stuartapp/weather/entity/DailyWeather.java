package com.stuart.stuartapp.weather.entity;

/**
 * Created by stuart on 2017/5/9.
 */

public class DailyWeather {

    private String date;
    private String week;
    private String sunrise;
    private String sunset;

    private String night_weather;
    private String templow;
    private String night_winddirect;
    private String night_windpower;

    private String day_weather;
    private String tempHigh;
    private String day_winddirect;
    private String day_windpower;

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

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public String getNight_weather() {
        return night_weather;
    }

    public void setNight_weather(String night_weather) {
        this.night_weather = night_weather;
    }

    public String getTemplow() {
        return templow;
    }

    public void setTemplow(String templow) {
        this.templow = templow;
    }

    public String getNight_winddirect() {
        return night_winddirect;
    }

    public void setNight_winddirect(String night_winddirect) {
        this.night_winddirect = night_winddirect;
    }

    public String getNight_windpower() {
        return night_windpower;
    }

    public void setNight_windpower(String night_windpower) {
        this.night_windpower = night_windpower;
    }

    public String getDay_weather() {
        return day_weather;
    }

    public void setDay_weather(String day_weather) {
        this.day_weather = day_weather;
    }

    public String getTempHigh() {
        return tempHigh;
    }

    public void setTempHigh(String tempHigh) {
        this.tempHigh = tempHigh;
    }

    public String getDay_winddirect() {
        return day_winddirect;
    }

    public void setDay_winddirect(String day_winddirect) {
        this.day_winddirect = day_winddirect;
    }

    public String getDay_windpower() {
        return day_windpower;
    }

    public void setDay_windpower(String day_windpower) {
        this.day_windpower = day_windpower;
    }

    @Override
    public String toString() {
        return "DailyWeather{" +
                "date='" + date + '\'' +
                ", week='" + week + '\'' +
                ", sunrise='" + sunrise + '\'' +
                ", sunset='" + sunset + '\'' +
                ", night_weather='" + night_weather + '\'' +
                ", templow='" + templow + '\'' +
                ", night_winddirect='" + night_winddirect + '\'' +
                ", night_windpower='" + night_windpower + '\'' +
                ", day_weather='" + day_weather + '\'' +
                ", tempHigh='" + tempHigh + '\'' +
                ", day_winddirect='" + day_winddirect + '\'' +
                ", day_windpower='" + day_windpower + '\'' +
                '}';
    }
}
