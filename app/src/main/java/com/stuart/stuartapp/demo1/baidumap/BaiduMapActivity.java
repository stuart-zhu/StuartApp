package com.stuart.stuartapp.demo1.baidumap;

import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.stuart.stuartapp.BaseActivity;
import com.stuart.stuartapp.R;

/**
 * Created by stuart on 2018/1/4.
 */

@ContentView(R.layout.activity_baidu_map)
public class BaiduMapActivity extends BaseActivity {


    @ViewInject(R.id.mapView)
    private MapView mapView;

    private BaiduMap mBaiduMap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ;
        ViewUtils.inject(this);

        mBaiduMap = mapView.getMap();

        // 显示实时路况
        mBaiduMap.setTrafficEnabled(true);
        //开启交通图
        // mBaiduMap.setBaiduHeatMapEnabled(true);


        Location netWorkLocation = LocationUtil.getBestLocation(this, null);

        LocationClient locationClient = new LocationClient(this);

        //设置定位条件

        LocationClientOption option = new LocationClientOption();

        option.setOpenGps(true);        //是否打开GPS

        option.setCoorType("bd09ll");       //设置返回值的坐标类型。

        option.setPriority(LocationClientOption.NetWorkFirst);  //设置定位优先级

        option.setProdName("LocationDemo"); //设置产品线名称。强烈建议您使用自定义的产品线名称，方便我们以后为您提供更高效准确的定位服务。

        option.setScanSpan(1000);    //设置定时定位的时间间隔。单位毫秒

        locationClient.setLocOption(option);


        //注册位置监听器

        locationClient.registerLocationListener(new BDLocationListener() {

            @Override

            public void onReceiveLocation(BDLocation location) {

                // TODO Auto-generated method stub

                if (location == null) {

                    return;

                }

                StringBuffer sb = new StringBuffer(256);

                sb.append("Time : ");

                sb.append(location.getTime());

                sb.append("\nError code : ");

                sb.append(location.getLocType());

                sb.append("\nLatitude : ");

                sb.append(location.getLatitude());

                sb.append("\nLontitude : ");

                sb.append(location.getLongitude());

                sb.append("\nRadius : ");

                sb.append(location.getRadius());

                if (location.getLocType() == BDLocation.TypeGpsLocation) {

                    sb.append("\nSpeed : ");

                    sb.append(location.getSpeed());

                    sb.append("\nSatellite : ");

                    sb.append(location.getSatelliteNumber());

                } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {

                    sb.append("\nAddress : ");

                    sb.append(location.getAddrStr());

                }

                mHanlder.obtainMessage(0, location).sendToTarget();

            }


        });

        locationClient.start();
        locationClient.requestLocation();
    }

    public Handler mHanlder = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            BDLocation location = (BDLocation) msg.obj;
            // 开启定位图层
            mBaiduMap.setMyLocationEnabled(true);


            // 构造定位数据
            MyLocationData locData = new MyLocationData.Builder()
//                    .accuracy(location.get)
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);

            MyLocationConfiguration.LocationMode mCurrentMode = MyLocationConfiguration.LocationMode.FOLLOWING;//定位跟随态
            mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;   //默认为 LocationMode.NORMAL 普通态
            mCurrentMode = MyLocationConfiguration.LocationMode.COMPASS;  //定位罗盘态
// 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）
            BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory
                    .fromResource(R.drawable.icon_geo);
            MyLocationConfiguration config = new MyLocationConfiguration(mCurrentMode, true, mCurrentMarker);


            mBaiduMap.setMyLocationConfiguration(new MyLocationConfiguration(
                    mCurrentMode, true, mCurrentMarker, 0xAAFFFF88,
                    0xAA00FF00
            ));


// 当不需要定位图层时关闭定位图层
//        mBaiduMap.setMyLocationEnabled(false);
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
}
