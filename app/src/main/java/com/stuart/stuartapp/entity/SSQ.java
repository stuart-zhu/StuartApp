package com.stuart.stuartapp.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by stuart on 2016/11/14.
 */
public class SSQ {

    private String expect; // 期号

    private List<String> reds; // 红球

    private String blue; // 蓝球

    private String openTime; // 开奖时间

    public SSQ() {
    }

    public SSQ(String expect, List<String> reds, String blue, String openTime) {
        this.expect = expect;
        this.reds = reds;
        this.blue = blue;
        this.openTime = openTime;
        loadOpenTimeDate();
    }

    public String getExpect() {
        return expect;
    }

    public void setExpect(String expect) {
        this.expect = expect;
    }

    public List<String> getReds() {
        return reds;
    }

    public void setReds(List<String> reds) {
        this.reds = reds;
    }

    public void addRed(String red) {
        if (reds == null)
            reds = new ArrayList<>();
        if (!reds.contains(red)) {
            reds.add(red);
        }
    }

    public void addRed(String[] red) {
        if (reds == null)
            reds = new ArrayList<>();
        for (String s : red) {
            if (!reds.contains(s)) {
                reds.add(s);
            }
        }
    }

    public String getBlue() {
        return blue;
    }

    public void setBlue(String blue) {
        this.blue = blue;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
        loadOpenTimeDate();

    }

    private void loadOpenTimeDate() {
        long i = 0;
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date d = fmt.parse(openTime);
            i = d.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        openTimeDate = i;
    }


    private long openTimeDate;

    public long getOpenTimeDate() {
        return openTimeDate;
    }

    @Override
    public String toString() {
        return "SSQ{" +
                "expect='" + expect + '\'' +
                ", reds=" + reds +
                ", blue='" + blue + '\'' +
                ", openTime='" + openTime + '\'' +
                '}';
    }
}
