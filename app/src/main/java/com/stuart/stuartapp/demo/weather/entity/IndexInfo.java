package com.stuart.stuartapp.demo.weather.entity;

/**
 * Created by stuart on 2017/5/8.
 */
// 空气指数
public class IndexInfo {
    private String iName;
    private String ivalue;
    private String detail;

    public String getiName() {
        return iName;
    }

    public void setiName(String iName) {
        this.iName = iName;
    }

    public String getIvalue() {
        return ivalue;
    }

    public void setIvalue(String ivalue) {
        this.ivalue = ivalue;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public IndexInfo(String iName, String ivalue, String detail) {
        this.iName = iName;
        this.ivalue = ivalue;
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "IndexInfo{" +
                "iName='" + iName + '\'' +
                ", ivalue='" + ivalue + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}
