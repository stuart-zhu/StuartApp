package com.stuart.stuartapp.demo1.yueche.http;

import com.stuart.stuartapp.demo.weather.request.HttpRequest;
import com.stuart.stuartapp.demo.weather.request.converter.StringConverterFactory;
import com.stuart.stuartapp.demo.weather.request.trust.TrustAllCerts;
import com.stuart.stuartapp.demo.weather.request.trust.TrustAllHostnameVerifier;
import com.stuart.stuartapp.demo1.yueche.http.form.FormTokenInterceptor;
import com.stuart.stuartapp.utils.LogUtil;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by stuart on 2018/5/29.
 */
public class YCHttpUtils {


    private static final long DEFAULT_TIMEOUT = 3000;

    private Retrofit retrofit;
    private YCHttpRequest movieService;

    private YCHttpUtils() {

    }

    private static YCHttpUtils mInstance;

    public static YCHttpUtils getInstance() {
        if (mInstance == null) mInstance = new YCHttpUtils();
        return mInstance;

    }

    private List<Cookie> mListCo = new ArrayList<>();
    public void login(final Observer subscriber, RequestBody map) {

        mListCo.clear();

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder().addInterceptor(new FormTokenInterceptor())
                .cookieJar(new CookieJar() {
                    @Override
                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {

                        LogUtil.i("", "saveFromResponse url ", url.toString());
                        for (Cookie cookie : cookies) {
                            mListCo.add(cookie);

                            LogUtil.i("", "saveFromResponse cook ", cookie.toString());
                        }
                    }


                    @Override
                    public List<Cookie> loadForRequest(HttpUrl url) {
                        ArrayList<Cookie> cookies = new ArrayList<>();
                        Cookie cookie = new Cookie.Builder()
                                .hostOnlyDomain(url.host())
                                .name("SESSION").value("afb")
                                .build();
                        cookies.add(cookie);
                        return cookies;


                    }
                });
        httpClientBuilder.sslSocketFactory(createSSLSocketFactory());
        httpClientBuilder.hostnameVerifier(new TrustAllHostnameVerifier());
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        String BASE_URL = YueContants.BASE_LOGIN_URL;

        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(StringConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        movieService = retrofit.create(YCHttpRequest.class);

        movieService.login(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void setLoginStatus(final Observer subscriber, RequestBody map) {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder().addInterceptor(new FormTokenInterceptor())
                .cookieJar(new CookieJar() {
                    @Override
                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {

                        LogUtil.i("", "saveFromResponse url ", url.toString());
                        for (Cookie cookie : cookies) {
                            LogUtil.i("", "saveFromResponse cook ", cookie.toString());
                            mListCo.add(cookie);
                        }
                    }


                    @Override
                    public List<Cookie> loadForRequest(HttpUrl url) {
                        ArrayList<Cookie> cookies = new ArrayList<>();
                       /* Cookie cookie = new Cookie.Builder()
                                .hostOnlyDomain(url.host())
                                  .name("SESSION").value("afb")
                                .build();
                        cookies.add(cookie);*/
                        cookies.addAll(mListCo);
                        return cookies;


                    }
                });

//        httpClientBuilder.sslSocketFactory(createSSLSocketFactory());
//        httpClientBuilder.hostnameVerifier(new TrustAllHostnameVerifier());
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        String BASE_URL = YueContants.BASE_LONG_QUAN;

        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(StringConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        movieService = retrofit.create(YCHttpRequest.class);

        movieService.setLoginStatus(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getCardInfo(final Observer subscriber, RequestBody map) {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder().addInterceptor(new FormTokenInterceptor()) .cookieJar(new CookieJar() {
            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {

                LogUtil.i("", "saveFromResponse url ", url.toString());
                for (Cookie cookie : cookies) {
                    LogUtil.i("", "saveFromResponse cook ", cookie.toString());
                }
            }


            @Override
            public List<Cookie> loadForRequest(HttpUrl url) {
                ArrayList<Cookie> cookies = new ArrayList<>();
                /*Cookie cookie = new Cookie.Builder()
                        .hostOnlyDomain(url.host())
                        .name("SESSION").value("afb")
                        .build();
                cookies.add(cookie);*/
                cookies.addAll(mListCo);
                return cookies;


            }
        });
//        httpClientBuilder.sslSocketFactory(createSSLSocketFactory());
//        httpClientBuilder.hostnameVerifier(new TrustAllHostnameVerifier());
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        String BASE_URL = YueContants.BASE_LONG_QUAN;

        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(StringConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        movieService = retrofit.create(YCHttpRequest.class);

        movieService.queryMsg(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


    public void yueche(final Observer subscriber, RequestBody map) {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder().addInterceptor(new FormTokenInterceptor()) .cookieJar(new CookieJar() {
            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {

                LogUtil.i("", "saveFromResponse url ", url.toString());
                for (Cookie cookie : cookies) {
                    LogUtil.i("", "saveFromResponse cook ", cookie.toString());
                }
            }


            @Override
            public List<Cookie> loadForRequest(HttpUrl url) {
                ArrayList<Cookie> cookies = new ArrayList<>();
               /* Cookie cookie = new Cookie.Builder()
                        .hostOnlyDomain(url.host())
                        .name("SESSION").value("afb")
                        .build();
                cookies.add(cookie);*/
                cookies.addAll(mListCo);
                return cookies;


            }
        });
//        httpClientBuilder.sslSocketFactory(createSSLSocketFactory());
//        httpClientBuilder.hostnameVerifier(new TrustAllHostnameVerifier());
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        String BASE_URL = YueContants.BASE_LONG_QUAN;

        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(StringConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        movieService = retrofit.create(YCHttpRequest.class);

        movieService.yueChe(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    public RequestBody buildLoginStatusEntry(String xybh, String psw, String jgid) {

        long time = System.currentTimeMillis();
        return new FormBody.Builder().
                addEncoded("xybh", xybh)
                .addEncoded("password", psw)
                .addEncoded("jpid", jgid)
                .addEncoded("jgid", jgid)
//                .addEncoded("callback", "jQuery1910801947789549011_" + time)
                .addEncoded("ISJSONP", "true")
                .addEncoded("os", "pc")
//                .addEncoded("_", String.valueOf(time))
        .build();


    }

    public RequestBody buildYueche(String xxzh, String cardP, String car) {
       return new FormBody.Builder()
                .addEncoded("xxzh", xxzh)
//        .addEncoded("cnbh", /*"19021"*/cardP)
//        nvps.add(new BasicNameValuePair("callback", "jQuery1910801947789549011_" + System.currentTimeMillis()));
        .addEncoded("ISJSONP", "true")
                .addEncoded("os", "pc")
                .addEncoded("isJcsdYyMode", "1")
//        nvps.add(new BasicNameValuePair("_", String.valueOf(System.currentTimeMillis())));
                .addEncoded("params", car).build();

    }

    private static SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllCerts()}, new SecureRandom());

            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }

        return ssfFactory;
    }

    public RequestBody buildLogin(String username, String psw) {
        psw = psw.toLowerCase();
        LogUtil.i("buildLogin", username, psw);

        long currentTimeMillis = System.currentTimeMillis();
        return new FormBody.Builder()
                .addEncoded("username", username)
                .addEncoded("passwordmd5", psw)
//                .addEncoded("callback", "jQuery191016093109438557973_" + currentTimeMillis)
                .addEncoded("ISJSONP", "true")
                .addEncoded("os", "pc")
//                .addEncoded("_", String.valueOf(currentTimeMillis))
                .build();

    }

    public RequestBody buildGetCarInfo(String xxzh) {
        LogUtil.i("xx", "buildgetCardInfo (" , xxzh);
        long currentTimeMillis = System.currentTimeMillis();


        return new FormBody.Builder()
                .addEncoded("xxzh", xxzh)
                .addEncoded("jlcbh", "")
                .addEncoded("callback", "jQuery1910801947789549011_" + currentTimeMillis)
                .addEncoded("ISJSONP", "true")
                .addEncoded("os", "pc")
                .addEncoded("_", String.valueOf(currentTimeMillis))
                .build();
    }
}
