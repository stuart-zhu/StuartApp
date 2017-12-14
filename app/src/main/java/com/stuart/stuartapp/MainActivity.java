package com.stuart.stuartapp;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.stuart.stuartapp.activity.TwoColorBallActivity;
import com.stuart.stuartapp.callback.GetSSQListener;
import com.stuart.stuartapp.dao.SsqDao;
import com.stuart.stuartapp.entity.SSQ;
import com.stuart.stuartapp.service.FileLogService;
import com.stuart.stuartapp.utils.DataUtils;
import com.stuart.stuartapp.utils.LogUtil;
import com.stuart.stuartapp.utils.ToastUtil;
import com.stuart.stuartapp.widget.PagerPointView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stuart on 2016/11/7.
 */
public class MainActivity extends ActivityGroup {

    private static final String TAG = "MainActivity";

    private DrawerLayout mDrawerLayout;

    private List<View> mViews;

    private ViewPager viewPager;

    private PagerPointView mPagerPointView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.vp);

        mPagerPointView = (PagerPointView) findViewById(R.id.pagerPoint);


        loadSsq();

        mViews = new ArrayList<>();

        mViews.add(

                getLocalActivityManager().startActivity("demo",new Intent("demo")).getDecorView()

        );
        mViews.add(getLocalActivityManager().startActivity("demo1", new Intent("demo1")).getDecorView());

        mPagerPointView.initialize(2, 0);
        viewPager.setAdapter(new MyPagerAdapter(mViews));

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mPagerPointView.setSelectPosition(position);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        final NavigationView mNaviagionView = (NavigationView) findViewById(R.id.navigation_view);
        mNaviagionView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.two_color_ball:
                        startActivity(new Intent(MainActivity.this, TwoColorBallActivity.class));
                        break;
                    case R.id.demo:
                        startActivity(new Intent("demo"));
                        break;
                    case R.id.kotlin:
                        startActivity(new Intent("kotlin"));
                        break;
                }
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });


        /*Intent localePicker = new Intent();
        localePicker.setClassName("com.android.provision","com.android.provision.LocaleSettingsActivity");
        localePicker.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        localePicker.setAction("android.intent.action.VIEW");
        startActivity(localePicker);*/
    }


    private void loadSsq() {
        DataUtils.getSsq(50, new GetSSQListener() {
            @Override
            public void onGetSuccess(List<SSQ> infos) {
                for (SSQ s : infos) {
                    LogUtil.i(TAG, "onCreate", "onGetSuccess " + s);
                    SsqDao.getInstance().insert(MainActivity.this, s);
                }
            }

            @Override
            public void onGetFaile(final String result) {

                runOnUiThread(new Runnable() {
                                  @Override
                                  public void run() {
                                      ToastUtil.getInstance(MainActivity.this).show(result);
                                  }
                              }
                );
            }
        });
    }



    class MyPagerAdapter extends PagerAdapter {
        List<View> mList;

        public MyPagerAdapter(List<View> list) {
            mList = list;
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == (arg1);
        }

        @Override
        public void destroyItem(View container, int position, Object object) {
            ((ViewPager) container).removeView(mList.get(position));
        }

        @Override
        public Object instantiateItem(View container, int position) {
            ((ViewPager) container).addView(mList.get(position), 0);
            return mList.get(position);
        }

        @Override
        public void finishUpdate(ViewGroup container) {
            super.finishUpdate(container);
        }
    }
}
