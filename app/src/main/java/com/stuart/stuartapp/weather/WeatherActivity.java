package com.stuart.stuartapp.weather;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.stuart.stuartapp.R;
import com.stuart.stuartapp.utils.Contants;
import com.stuart.stuartapp.utils.ToastUtil;
import com.stuart.stuartapp.weather.entity.WeatherInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by stuart on 2017/4/13.
 */

public class WeatherActivity extends FragmentActivity {
    private LocationManager locationManager;

    private static final int REQUEST_ADD_CITY = 12;

    private class WeatherFragmentPage extends FragmentStatePagerAdapter {
        public WeatherFragmentPage(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public int getItemPosition(Object object) {
            return PagerAdapter.POSITION_NONE;
        }
    }

    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            mShowingFragment = mFragments.get(position);
            if (indicator != null && indicator.getChildCount() > position) {
                for (int i = 0; i < indicator.getChildCount(); i++) {
                    View v = indicator.getChildAt(i);
                    if (position == i) {
                        v.setSelected(true);
                    } else {
                        v.setSelected(false);
                    }
                }
            }
        }

        ;

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    private List<Fragment> mFragments = new ArrayList<>();

    private Fragment mShowingFragment;

    private ViewPager vp;
    private WeatherFragmentPage mFragmentPage;

    private ImageView ivAdd;

    private ImageView ivDel;

    private ImageView ivRefresh;
    private LinearLayout indicator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        vp = (ViewPager) findViewById(R.id.vp);
        mFragmentPage = new WeatherFragmentPage(getSupportFragmentManager());
        vp.setAdapter(mFragmentPage);
        vp.setOnPageChangeListener(onPageChangeListener);
        ivAdd = (ImageView) findViewById(R.id.add);
        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Contants.isCityAlready(WeatherActivity.this)) {
                    startActivityForResult(new Intent("com.stuart.add_weather_city"), REQUEST_ADD_CITY);
                } else {
                    ToastUtil.getInstance(WeatherActivity.this).show(R.string.
                            city_is_not_ok);
                }
            }
        });

        ivDel = (ImageView) findViewById(R.id.delete);

        ivRefresh = (ImageView) findViewById(R.id.refresh);

        ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFragments.size() == 1) {
                    ToastUtil.getInstance(WeatherActivity.this).show(R.string.
                            no_city_del);
                    return;
                } else {
                    new AlertDialog.Builder(WeatherActivity.this).setTitle(android.R.string.dialog_alert_title)
                            .setTitle(getString(R.string.sure_to_del, (String) mShowingFragment.getArguments().get("city"))).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            removeIndicator();
                            Contants.removeCityList(WeatherActivity.this, (String)mShowingFragment.getArguments().get("city"));


                            // getSupportFragmentManager().beginTransaction().remove(mShowingFragment).commit();
                            mFragments.remove(mShowingFragment);

                            mFragmentPage.notifyDataSetChanged();
                            //vp.setCurrentItem(i == mFragments.size() ? i -1 : i+1);

                        }
                    }).setNeutralButton(android.R.string.cancel, null).
                            show();

                }
            }
        });
        ivRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mShowingFragment != null) ((WeatherFragment) mShowingFragment).loadData();
            }
        });

        indicator = (LinearLayout) findViewById(R.id.indicator);
        if (!Contants.isCityAlready(this)) {

            WeatherDateUtils.getAllCityInfo(WeatherActivity.this, new WeatherDateUtils.OnCityGetListener() {
                @Override
                public void onCityGetStart() {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.getInstance(WeatherActivity.this).show(R.string.get_city_start);
                        }
                    });
                }

                @Override
                public void onCityGetFinish() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.getInstance(WeatherActivity.this).show(R.string.get_city_finish);
                        }
                    });
                }
            });


        }

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);//获得位置服务
        String provider = judgeProvider(locationManager);

        if (provider != null) {//有位置提供器的情况
            //为了压制getLastKnownLocation方法的警告
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                            PackageManager.PERMISSION_GRANTED) {
                return;
            }
            Location location = locationManager.getLastKnownLocation(provider);
            /*if (location != null) {
                getLocation(location);//得到当前经纬度并开启线程去反向地理编码
            } else {
                Toast.makeText(this, "暂时无法获得当前位置", Toast.LENGTH_SHORT).show();
            }*/
        } else {//不存在位置提供器的情况

        }

        Set<String> cityList = Contants.getCityList(this);
        if (cityList.size() == 0) {
          /*  cityList.add("朝阳区");
            Contants.addCityList(this, "朝阳区");*/
            cityList.add("昌平区");
            Contants.addCityList(this, "昌平区");
        }

        for (String s : cityList) {
            chooseCity(s, false);
        }
    }

    private void addIndicator() {
        ImageView iv = new ImageView(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(20, 20);
        layoutParams.setMargins(10, 0, 0, 0);
        layoutParams.weight = 1;
        iv.setLayoutParams(layoutParams);
        iv.setImageResource(R.drawable.weather_indiactor);
        indicator.addView(iv);
        if (indicator.getChildCount() == 1) iv.setSelected(true);
    }

    private void removeIndicator() {
        if (indicator != null && indicator.getChildCount() > 0)
            indicator.removeViewAt(indicator.getChildCount() - 1);
    }

    private String judgeProvider(LocationManager locationManager) {
        List<String> prodiverlist = locationManager.getProviders(true);
        if (prodiverlist.contains(LocationManager.GPS_PROVIDER)) {
            return LocationManager.GPS_PROVIDER;
        } else if (prodiverlist.contains(LocationManager.NETWORK_PROVIDER)) {
            return LocationManager.NETWORK_PROVIDER;
        } else {
            Toast.makeText(WeatherActivity.this, "没有可用的位置提供器", Toast.LENGTH_SHORT).show();
        }
        return null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ADD_CITY && resultCode == RESULT_OK) {
            String city = data.getStringExtra("city");
            Contants.addCityList(this, city);
            chooseCity(city, true);
        }
    }

    public void getLocation(Location location) {
        String latitude = location.getLatitude() + "";
        String longitude = location.getLongitude() + "";
        //   String uri =  "http://maps.google.com/maps/api/geocode/json?latlng="+latitude+","+longitude+"&language=zh-CN&sensor=true";
        final String url = "http://api.map.baidu.com/geocoder/v2/?ak=pPGNKs75nVZPloDFuppTLFO3WXebPgXg&callback=renderReverse&location=" + latitude + "," + longitude + "&output=json&pois=0";

        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {
                new HttpUtils().send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        // renderReverse&&renderReverse({"status":0,"result":{"location":{"lng":116.28729851999992,"lat":40.0406300663805},"formatted_address":"北京市海淀区马连洼北路18号","business":"马连洼,西北旺,上地","addressComponent":{"country":"中国","country_code":0,"province":"北京市","city":"北京市","district":"海淀区","adcode":"110108","street":"马连洼北路","street_number":"18号","direction":"西北","distance":"73"},"pois":[],"poiRegions":[{"direction_desc":"内","name":"百草园社区","tag":"房地产"}],"sematic_description":"百草园社区内","cityCode":131}})
                        try {
                            String s = responseInfo.result.substring(responseInfo.result.indexOf("{"), responseInfo.result.length());
                            JSONObject jo = new JSONObject(s);
                            JSONObject result = jo.getJSONObject("result");
                            JSONObject j = result.getJSONObject("addressComponent");

                            String district = j.getString("district");
                            chooseCity(district, true);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        Log.i("stuart", "");
                    }
                });
                return null;
            }
        }.execute(url);
    }

    private void chooseCity(String city, boolean select) {
        mHandler.obtainMessage(MSG_ADD_WEATHER_PAGE, select ? 1 : 0, 1,city).sendToTarget();
    }

    private static final int MSG_ADD_WEATHER_PAGE = 1;
    private static final int MSG_ADD_WEATHER_CITY = 2;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_ADD_WEATHER_PAGE:
                    String city = (String) msg.obj;
                    Fragment f = new WeatherFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("city", city);
                    f.setArguments(bundle);
                    mFragments.add(f);
                    if (mFragments.size() == 1) {
                        mShowingFragment = mFragments.get(0);
                    }
                    addIndicator();
                    mFragmentPage.notifyDataSetChanged();
                    if (msg.arg1 == 1) {
                        vp.setCurrentItem(mFragments.size() -1);
                    }

                    break;
                case MSG_ADD_WEATHER_CITY:
                    WeatherInfo info = (WeatherInfo) msg.obj;
                    for (Fragment fr : mFragments) {
                        if (fr.getArguments().get("city").equals(info.getCity())) {
                            if (fr instanceof WeatherFragment) {
                                ((WeatherFragment) fr).setWeather(info);
                            }
                            break;
                        }
                    }
                    break;

            }
        }
    };
}
