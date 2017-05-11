package com.stuart.stuartapp.weather.entity;

/**
 * Created by stuart on 2017/5/9.
 */

public class HourWeather {

    private String time;
    private String weather;
    private String temp;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    @Override
    public String toString() {
        return "HourWeather{" +
                "time='" + time + '\'' +
                ", weather='" + weather + '\'' +
                ", temp='" + temp + '\'' +
                '}';
    }
}
