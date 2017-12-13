package com.stuart.stuartapp.demo.weather.entity;

/**
 * Created by stuart on 2017/4/13.
 */

public class CityInfo {
    private int id;

    private int cityId;

    private int parentId; // 上级城市Id

    private String cityCode;

    private String city;

    private int parentPId;

    private String cityP;

    private String cityPP;

    public int getParentPId() {
        return parentPId;
    }

    public void setParentPId(int parentPId) {
        this.parentPId = parentPId;
    }

    public String getCityP() {
        return cityP;
    }

    public void setCityP(String cityP) {
        this.cityP = cityP;
    }

    public String getCityPP() {
        return cityPP;
    }

    public void setCityPP(String cityPP) {
        this.cityPP = cityPP;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


}
