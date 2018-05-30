package com.stuart.stuartapp.demo1.yueche;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by stuart on 2018/5/29.
 */
public class YueChePerson implements Parcelable{

    private String userName;
    private String phoneNum;
    private String nickName;
    private String id;
    private String xxzh;
    private String jgid;
    private String xybh;
    private String sfzh;
    private String jxcode;
    private String apiurl;
    private String apiUriios;
    private String jxmc;
    private String xm;
    private String usertype;
    private String dz;
    private String schoolpsw;
    private String sqcx;
    private String ssbx;
    private String backgroundiamge;
    private String handImage;

    public YueChePerson() {}
    protected YueChePerson(Parcel in) {
        userName = in.readString();
        phoneNum = in.readString();
        nickName = in.readString();
        id = in.readString();
        xxzh = in.readString();
        jgid = in.readString();
        xybh = in.readString();
        sfzh = in.readString();
        jxcode = in.readString();
        apiurl = in.readString();
        apiUriios = in.readString();
        jxmc = in.readString();
        xm = in.readString();
        usertype = in.readString();
        dz = in.readString();
        schoolpsw = in.readString();
        sqcx = in.readString();
        ssbx = in.readString();
        backgroundiamge = in.readString();
        handImage = in.readString();
    }

    public static final Creator<YueChePerson> CREATOR = new Creator<YueChePerson>() {
        @Override
        public YueChePerson createFromParcel(Parcel in) {
            return new YueChePerson(in);
        }

        @Override
        public YueChePerson[] newArray(int size) {
            return new YueChePerson[size];
        }
    };

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getXxzh() {
        return xxzh;
    }

    public void setXxzh(String xxzh) {
        this.xxzh = xxzh;
    }

    public String getJgid() {
        return jgid;
    }

    public void setJgid(String jgid) {
        this.jgid = jgid;
    }

    public String getXybh() {
        return xybh;
    }

    public void setXybh(String xybh) {
        this.xybh = xybh;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public String getJxcode() {
        return jxcode;
    }

    public void setJxcode(String jxcode) {
        this.jxcode = jxcode;
    }

    public String getApiurl() {
        return apiurl;
    }

    public void setApiurl(String apiurl) {
        this.apiurl = apiurl;
    }

    public String getApiUriios() {
        return apiUriios;
    }

    public void setApiUriios(String apiUriios) {
        this.apiUriios = apiUriios;
    }

    public String getJxmc() {
        return jxmc;
    }

    public void setJxmc(String jxmc) {
        this.jxmc = jxmc;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getDz() {
        return dz;
    }

    public void setDz(String dz) {
        this.dz = dz;
    }

    public String getSchoolpsw() {
        return schoolpsw;
    }

    public void setSchoolpsw(String schoolpsw) {
        this.schoolpsw = schoolpsw;
    }

    public String getSqcx() {
        return sqcx;
    }

    public void setSqcx(String sqcx) {
        this.sqcx = sqcx;
    }

    public String getSsbx() {
        return ssbx;
    }

    public void setSsbx(String ssbx) {
        this.ssbx = ssbx;
    }

    public String getBackgroundiamge() {
        return backgroundiamge;
    }

    public void setBackgroundiamge(String backgroundiamge) {
        this.backgroundiamge = backgroundiamge;
    }

    public String getHandImage() {
        return handImage;
    }

    public void setHandImage(String handImage) {
        this.handImage = handImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userName);
        dest.writeString(phoneNum);
        dest.writeString(nickName);
        dest.writeString(id);
        dest.writeString(xxzh);
        dest.writeString(jgid);
        dest.writeString(xybh);
        dest.writeString(sfzh);
        dest.writeString(jxcode);
        dest.writeString(apiurl);
        dest.writeString(apiUriios);
        dest.writeString(jxmc);
        dest.writeString(xm);
        dest.writeString(usertype);
        dest.writeString(dz);
        dest.writeString(schoolpsw);
        dest.writeString(sqcx);
        dest.writeString(ssbx);
        dest.writeString(backgroundiamge);
        dest.writeString(handImage);

    }


}
