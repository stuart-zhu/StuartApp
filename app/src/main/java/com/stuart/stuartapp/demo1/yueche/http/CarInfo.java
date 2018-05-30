package com.stuart.stuartapp.demo1.yueche.http;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by stuart on 2018/5/30.
 */
public class CarInfo implements Parcelable{


    private String yyrq;
    private int yyrqXH;
    private String xnsd; // 3001 表示上午 08:00-12:00 3002 表示 13:00-17:00 3003 17:00-20:00
    private String xnsdName;
    private String qsName;
    private String qsid;
    private int sl;
    private int ks;
    private String isBpked;
    private int IsBpked_SK;
    private String isCreate;
    private String yyClInfo;

    public CarInfo() {

    }
    protected CarInfo(Parcel in) {
        yyrq = in.readString();
        yyrqXH = in.readInt();
        xnsd = in.readString();
        xnsdName = in.readString();
        qsName = in.readString();
        qsid = in.readString();
        sl = in.readInt();
        ks = in.readInt();
        isBpked = in.readString();
        IsBpked_SK = in.readInt();
        isCreate = in.readString();
        yyClInfo = in.readString();
    }

    public static final Creator<CarInfo> CREATOR = new Creator<CarInfo>() {
        @Override
        public CarInfo createFromParcel(Parcel in) {
            return new CarInfo(in);
        }

        @Override
        public CarInfo[] newArray(int size) {
            return new CarInfo[size];
        }
    };

    public String getYyrq() {
        return yyrq;
    }

    public void setYyrq(String yyrq) {
        this.yyrq = yyrq;
    }

    public int getYyrqXH() {
        return yyrqXH;
    }

    public void setYyrqXH(int yyrqXH) {
        this.yyrqXH = yyrqXH;
    }

    public String getXnsd() {
        return xnsd;
    }

    public void setXnsd(String xnsd) {
        this.xnsd = xnsd;
    }

    public String getXnsdName() {
        return xnsdName;
    }

    public void setXnsdName(String xnsdName) {
        this.xnsdName = xnsdName;
    }

    public String getQsName() {
        return qsName;
    }

    public void setQsName(String qsName) {
        this.qsName = qsName;
    }

    public String getQsid() {
        return qsid;
    }

    public void setQsid(String qsid) {
        this.qsid = qsid;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }

    public int getKs() {
        return ks;
    }

    public void setKs(int ks) {
        this.ks = ks;
    }

    public String isBpked() {
        return isBpked;
    }

    public void setBpked(String bpked) {
        isBpked = bpked;
    }

    public int getIsBpked_SK() {
        return IsBpked_SK;
    }

    public void setIsBpked_SK(int isBpked_SK) {
        IsBpked_SK = isBpked_SK;
    }

    public String isCreate() {
        return isCreate;
    }

    public void setCreate(String create) {
        isCreate = create;
    }

    public String getYyClInfo() {
        return yyClInfo;
    }

    public void setYyClInfo(String yyClInfo) {
        this.yyClInfo = yyClInfo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString( yyrq);
        dest.writeInt(  yyrqXH);
        dest.writeString( xnsd); // 3001 表示上午 08:00-12:00 3002 表示 13:00-17:00 3003 17:00-20:00
        dest.writeString( xnsdName);
        dest.writeString( qsName);
        dest.writeString( qsid);
        dest.writeInt( sl);
        dest.writeInt(  ks);
        dest.writeString(isBpked);
        dest.writeInt( IsBpked_SK);
        dest.writeString( isCreate);
        dest.writeString( yyClInfo);
    }

    @Override
    public String toString() {
        return "CarInfo{" +
                "yyrq='" + yyrq + '\'' +
                ", yyrqXH=" + yyrqXH +
                ", xnsd='" + xnsd + '\'' +
                ", xnsdName='" + xnsdName + '\'' +
                ", qsName='" + qsName + '\'' +
                ", qsid='" + qsid + '\'' +
                ", sl=" + sl +
                ", ks=" + ks +
                ", isBpked='" + isBpked + '\'' +
                ", IsBpked_SK=" + IsBpked_SK +
                ", isCreate='" + isCreate + '\'' +
                ", yyClInfo='" + yyClInfo + '\'' +
                '}';
    }
}
