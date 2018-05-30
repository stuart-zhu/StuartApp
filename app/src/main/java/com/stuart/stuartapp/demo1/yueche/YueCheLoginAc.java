package com.stuart.stuartapp.demo1.yueche;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.JsonObject;
import com.stuart.stuartapp.BaseActivity;
import com.stuart.stuartapp.R;
import com.stuart.stuartapp.demo1.yueche.http.CarInfo;
import com.stuart.stuartapp.demo1.yueche.http.YCHttpRequest;
import com.stuart.stuartapp.demo1.yueche.http.YCHttpUtils;
import com.stuart.stuartapp.demo1.yueche.http.YueContants;
import com.stuart.stuartapp.utils.JSONUtil;
import com.stuart.stuartapp.utils.LogUtil;
import com.stuart.stuartapp.utils.ToastUtil;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.IOException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by stuart on 2018/5/29.
 */
public class YueCheLoginAc extends BaseActivity implements View.OnClickListener{


    private static final String TAG = "YueCheLoginAc";
    private EditText etUser;
    private EditText etpsw;
    private EditText etCarcode;
    private Button btnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yue_che_login);

        etUser = (EditText) findViewById(R.id.user);

        etpsw = (EditText) findViewById(R.id.psw);

        etCarcode  = (EditText) findViewById(R.id.che_code);
        etUser.setText("13366044690");
        etpsw.setText("afb111111");
        etCarcode.setText("16059");

        btnLogin = (Button) findViewById(R.id.login);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.login:
                String username = etUser.getText().toString();
                String psw = etpsw.getText().toString();
                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(psw)) {
                    ToastUtil.showMessage("请输入正确的用户名、密码");
                    return ;
                }

                login(username, psw);


                break;
        }
    }

    private void login(String username, String psw) {
        Observer observer = new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object value) {
                LogUtil.i(TAG, "login onNext", value.toString());
                if (value == null) return;
                if (value instanceof ResponseBody) {
                    ResponseBody b =  (ResponseBody) value;

                    try {
                        String v =  new String(b.bytes());
                        LogUtil.i(TAG, "login onNext", v);
                        JSONObject jsonObject = new JSONObject(v);
                        JSONObject data  = JSONUtil.getJSONObject(jsonObject, "data");
                        YueChePerson p = new YueChePerson();

                        p.setUserName(JSONUtil.getString(data, "USERNAME"));
                        p.setPhoneNum(JSONUtil.getString(data, "PHONENUM"));
                        p.setNickName(JSONUtil.getString(data, "NICKNAME"));
                        p.setId(JSONUtil.getString(data, "ID"));
                        p.setXxzh(JSONUtil.getString(data, "XXZH"));
                        p.setJgid(JSONUtil.getString(data, "JGID"));
                        p.setXybh(JSONUtil.getString(data, "XYBH"));
                        p.setSfzh(JSONUtil.getString(data, "SFZH"));
                        p.setJxcode(JSONUtil.getString(data, "JXCODE"));
                        p.setApiurl(JSONUtil.getString(data, "APIURL"));
                        p.setApiUriios(JSONUtil.getString(data, "APIURLIOS"));
                        p.setJxmc(JSONUtil.getString(data, "JXMC"));
                        p.setXm(JSONUtil.getString(data, "XM"));
                        p.setUsertype(JSONUtil.getString(data, "USERTYPE"));
                        p.setDz(JSONUtil.getString(data, "DZ"));
                        p.setSchoolpsw(JSONUtil.getString(data, "SCHOOLPWD"));
                        p.setSqcx(JSONUtil.getString(data, "SQCX"));
                        p.setSsbx(JSONUtil.getString(data, "SSBX"));
                        p.setBackgroundiamge(JSONUtil.getString(data, "BACKGROUNDIMAGE"));
                        p.setHandImage(JSONUtil.getString(data, "HANDIMAGE"));

                        setLoginStatus(p);


                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onError(Throwable e) {
                LogUtil.e(TAG, "login onError", e.getLocalizedMessage());
            }

            @Override
            public void onComplete() {

            }
        };
        RequestBody body = YCHttpUtils.getInstance().buildLogin(username, MD5(psw));

        YCHttpUtils.getInstance().login(observer,body );
    }



    private void setLoginStatus(final YueChePerson p) {
        Observer observer = new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object value) {
                LogUtil.i(TAG, "setLoginStatus value",value.toString());
                if (value == null) return;
                if (value instanceof ResponseBody) {
                    ResponseBody b =  (ResponseBody) value;
                    try {
                        String v =  new String(b.bytes());
                        LogUtil.i(TAG, "setLoginStatus onNext", v);
                        JSONObject jsonObject = new JSONObject(v);

                        getCardInfo(p);
                    }
                    catch (IOException e) {
                    } catch (JSONException je) {

                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                LogUtil.e(TAG, "setLoginStatus onError", e.getLocalizedMessage());
            }

            @Override
            public void onComplete() {

            }
        };
        RequestBody body = YCHttpUtils.getInstance().buildLoginStatusEntry(p.getXybh(), p.getSchoolpsw(), p.getJgid());
        YCHttpUtils.getInstance().setLoginStatus(observer, body);
    }

    private void getCardInfo(final YueChePerson p) {
        Observer observer = new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object value) {
                LogUtil.i(TAG, "getCardInfo value",value.toString());
                if (value == null) return;
                if (value instanceof ResponseBody) {
                    ResponseBody b =  (ResponseBody) value;
                    try {
                        String v =  new String(b.bytes());
                        LogUtil.i(TAG, "getCardInfo onNext", v);
                        JSONObject jsonObject = new JSONObject(v);
                        JSONObject data = JSONUtil.getJSONObject(jsonObject, "data");

                        JSONArray ja = JSONUtil.getJSONArray(data, "UIDatas");

                        List<CarInfo> listCardInfo = new ArrayList<>();
                        for (int i = 0 ; i < ja.length(); i ++) {
                            JSONObject jo = ja.getJSONObject(i);
                            CarInfo info = new CarInfo();
                            info.setYyrq(JSONUtil.getString(jo, "Yyrq"));
                            info.setYyrqXH(JSONUtil.getInt(jo, "YyrqXH", 0));
                            info.setXnsd(JSONUtil.getString(jo, "Xnsd"));
                            info.setXnsdName(JSONUtil.getString(jo, "XnsdName"));
                            info.setQsName(JSONUtil.getString(jo, "QsName"));
                            info.setQsid(JSONUtil.getString(jo, "Qsid"));
                            info.setSl(JSONUtil.getInt(jo,"SL", 0));
                            info.setKs(JSONUtil.getInt(jo, "KS", 0));
                            info.setBpked(JSONUtil.getBoolean(jo, "IsBpked", false)+"");
                            info.setIsBpked_SK(JSONUtil.getInt(jo, "IsBpked_SK",0));
                            info.setCreate(JSONUtil.getBoolean(jo, "IsCreate", false)+"");
                            info.setYyClInfo(JSONUtil.getString(jo, "YyClInfo"));

                            listCardInfo.add(info);



                        }
                        LogUtil.i(TAG, "cardInfo " ,"------>");
                        for (CarInfo info : listCardInfo) {
                            LogUtil.i(TAG, "cardInfo " , info.toString());
                        }
                        LogUtil.i(TAG, "cardInfo " ,"<------");

                        yueChe(p, listCardInfo.get(listCardInfo.size() -1));
                    }
                        catch (IOException e) {
                    } catch (JSONException je) {

                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                LogUtil.e(TAG, "getCardInfo onError", e.getLocalizedMessage());
            }

            @Override
            public void onComplete() {

            }
        };
        RequestBody body = YCHttpUtils.getInstance().buildGetCarInfo(p.getXxzh());
        YCHttpUtils.getInstance().getCardInfo(observer, body);
    }



    private void yueChe( final YueChePerson p, final CarInfo info) {
        Observer<Object> observer = new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object value) {

                LogUtil.i(TAG, "yueche", value.toString());
                if (value != null) {
                    ResponseBody b = (ResponseBody) value;
                    try {
                        String v = b.string();
                         v =  v.substring("callback(".length(), v.length() - 1);
                        LogUtil.i(TAG, "yueche",v);
                        JSONObject jo = new JSONObject(v);
                        ToastUtil.showMessage(JSONUtil.getString(jo, "message"));

                        login(etUser.getText().toString(),  etpsw.getText().toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                LogUtil.e(TAG, "yueche onError", e.getLocalizedMessage());
            }

            @Override
            public void onComplete() {

            }
        };
        String carP = etCarcode.getText().toString();
        RequestBody body = YCHttpUtils.getInstance().buildYueche(p.getXxzh(), carP,carP +"."+ dateFormat(info.getYyrq()) +".3001.");
        YCHttpUtils.getInstance().yueche(observer, body);
    }

    public static String dateFormat(String dateTime) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String result = "";
        try {
            Date date = format.parse(dateTime);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            result = dateFormat.format(date);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    private String MD5(String s) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(s.getBytes("utf-8"));
            return toHex(bytes);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String toHex(byte[] bytes) {

        final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
        StringBuilder ret = new StringBuilder(bytes.length * 2);
        for (int i=0; i<bytes.length; i++) {
            ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
            ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
        }
        return ret.toString();
    }
}
