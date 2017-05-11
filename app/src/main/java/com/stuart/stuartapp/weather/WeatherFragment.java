package com.stuart.stuartapp.weather;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.stuart.stuartapp.R;
import com.stuart.stuartapp.weather.entity.WeatherInfo;
import com.stuart.stuartapp.weather.widget.TrendGraph;

/**
 * Created by stuart on 2017/5/9.
 */

public class WeatherFragment extends Fragment {


    private static final String TEMP_SIGN = "°"/*"℃"*/;

    private ScrollView scrollView;

    private View viewSign;
    private TextView tvCity;

    private TextView tvWeather;

    private TextView tvTemp;

    private ImageView ivWeather;

    private TextView tvTempRound;

    private TextView tvAqi;

    private TextView tvQuality;

    private ImageView ivLevel;

    private TrendGraph mTrend;

    private ListView mDailyLv;

    private ListView mIndexLv;

    private void initView(View v) {
        scrollView = (ScrollView) v.findViewById(R.id.sc);
        viewSign = v.findViewById(R.id.viewSign);

        viewSign.setFocusable(true);
        viewSign.setFocusableInTouchMode(true);
        viewSign.requestFocus();
        tvCity = (TextView) v.findViewById(R.id.city);
        tvWeather = (TextView) v.findViewById(R.id.weather);
        tvTemp = (TextView) v.findViewById(R.id.temp);
        ivWeather = (ImageView) v.findViewById(R.id.ic_weather);
        tvTempRound = (TextView) v.findViewById(R.id.temp_round);
        tvAqi = (TextView) v.findViewById(R.id.aqi);
        tvQuality = (TextView) v.findViewById(R.id.quality);
        ivLevel = (ImageView) v.findViewById(R.id.level);

        mTrend = (TrendGraph) v.findViewById(R.id.trend);
        mDailyLv = (ListView) v.findViewById(R.id.daily_lv);
        mIndexLv = (ListView) v.findViewById(R.id.index_lv);


    }



    private WeatherInfo weatherInfo;
    public void setWeather(WeatherInfo info) {
        weatherInfo = info;

        tvCity.setText(weatherInfo.getCity());
        tvWeather.setText(weatherInfo.getWeather());
        tvTemp.setText(weatherInfo.getTemp()+ TEMP_SIGN);
        ivWeather.setImageResource(WeatherIconHelper.getWeatherIconResId(getActivity(), info.getWeather()));
        tvTempRound.setText(weatherInfo.getTemplow() + TEMP_SIGN + "/" + weatherInfo.getTemphigh() + "       " + weatherInfo.getWinddirect() + weatherInfo.getWindpower());
        tvAqi.setText(weatherInfo.getAqi().getAqi());
        tvQuality.setText(weatherInfo.getAqi().getQuality());
        ivLevel.setImageResource(WeatherIconHelper.getWeatherLevelIcon(weatherInfo.getAqi().getLevel()));
        mTrend.setHourWeathers(weatherInfo.getHourWeathers());
        mDailyLv.setAdapter(new DailyWeatherAdapter(getActivity(), weatherInfo.getDailyWeathers()));
        mIndexLv.setAdapter(new IndexAdapter(getActivity(), weatherInfo.getIndexs()));
        setListViewHeightBasedOnChildren(mDailyLv);
        setListViewHeightBasedOnChildren(mIndexLv);

        mHandler.sendEmptyMessage(MSG_HIDE_DIALOG);
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, null);
        initView(view);
        prepareDialog();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadData();
    }

    public void loadData() {
        mHandler.sendEmptyMessage(MSG_SHOW_DIALOG);
        String city = (String)getArguments().get("city");
        WeatherDateUtils.getWeather(getActivity(), city, new WeatherDateUtils.onWeatherGetListener() {
            @Override
            public void onWeatherGet(WeatherInfo info) {
                mHandler.obtainMessage(MSG_UPDATE_WEATHER_CITY, info).sendToTarget();

            }

            @Override
            public void onWeatherGetFailure(String msg) {

                mHandler.obtainMessage(MSG_HIDE_DIALOG, msg).sendToTarget();
            }
        });
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        if(listView == null) return;
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }


    private AlertDialog mLoadDialog;

    private void prepareDialog() {
        mLoadDialog = new AlertDialog.Builder(getActivity()).setMessage(android.R.string.dialog_alert_title)
                .setMessage(R.string.load_weather).create();
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(!isVisibleToUser) {
            mHandler.sendEmptyMessage(MSG_SCROLL_TOP);
        }
    }

    private static final int MSG_SCROLL_TOP = 1;
    private static final int MSG_UPDATE_WEATHER_CITY = 2;
    private static final int MSG_SHOW_DIALOG = 3;
    private static final int MSG_HIDE_DIALOG = 4;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_SCROLL_TOP:
                    if (scrollView != null)
                    scrollView.scrollTo(0,0);
                    break;
                case MSG_UPDATE_WEATHER_CITY:
                    setWeather((WeatherInfo)msg.obj);
                    break;
                case MSG_SHOW_DIALOG:
                    if (mLoadDialog != null)
                    mLoadDialog.show();
                    break;
                case MSG_HIDE_DIALOG:
                    if (mLoadDialog != null && mLoadDialog.isShowing()) mLoadDialog.cancel();
                    if (msg.obj != null) Toast.makeText(getActivity(), String.valueOf(msg.obj), Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
}
