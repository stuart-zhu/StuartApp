package com.stuart.stuartapp;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.stuart.stuartapp.activity.Demo1Activity;
import com.stuart.stuartapp.activity.DemoActivity;
import com.stuart.stuartapp.activity.TwoColorBallActivity;
import com.stuart.stuartapp.callback.GetSSQListener;
import com.stuart.stuartapp.dao.SsqDao;
import com.stuart.stuartapp.entity.SSQ;
import com.stuart.stuartapp.service.FileLogService;
import com.stuart.stuartapp.utils.DataUtils;
import com.stuart.stuartapp.utils.LogUtil;
import com.stuart.stuartapp.utils.ToastUtil;
import com.stuart.stuartapp.utils.ViewFindUtils;
import com.stuart.stuartapp.widget.PagerPointView;
import com.stuart.stuartapp.widget.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stuart on 2016/11/7.
 */
public class MainActivity extends FragmentActivity {

    private static final String TAG = "MainActivity";

    private DrawerLayout mDrawerLayout;

    private List<View> mViews;

    private ViewPager viewPager;

    private PagerPointView mPagerPointView;

    private SlidingTabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = ViewFindUtils.find(getWindow().getDecorView(), R.id.stl);


        viewPager = (ViewPager) findViewById(R.id.vp);

        mPagerPointView = (PagerPointView) findViewById(R.id.pagerPoint);



        loadSsq();

        mViews = new ArrayList<>();


        mPagerPointView.initialize(3, 0);
        loadFragments();
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));


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
        tabLayout.setViewPager(viewPager);
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




    private List<Fragment> mFragments;
    private String[] mTitles = new String[] {
            "A",
            "B",
            "C",
            "D",
            "E",
            "F",
            "G",
            "H",
            "I",
            "J",
            "K"
    };

    private void loadFragments() {
        mFragments = new ArrayList<>();
        for(int i = 0; i < mTitles.length; i++) {
            if (i%2 == 0) {
                mFragments.add(new DemoActivity());
            } else {
                mFragments.add(new Demo1Activity());
            }
        }
    }
    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}
