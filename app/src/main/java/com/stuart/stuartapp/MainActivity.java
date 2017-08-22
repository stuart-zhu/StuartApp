package com.stuart.stuartapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import com.stuart.stuartapp.activity.TwoColorBallActivity;
import com.stuart.stuartapp.callback.GetSSQListener;
import com.stuart.stuartapp.dao.SsqDao;
import com.stuart.stuartapp.entity.SSQ;
import com.stuart.stuartapp.service.FileLogService;
import com.stuart.stuartapp.utils.DataUtils;
import com.stuart.stuartapp.utils.LogUtil;
import com.stuart.stuartapp.utils.ToastUtil;

import java.util.List;

/**
 * Created by stuart on 2016/11/7.
 */
public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // startService(new Intent(this, FileLogService.class));
        /*TelephonyManager mTelephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        int simState = mTelephonyManager.getSimState();
        String hintMessage = "";
        switch (simState) {
            case TelephonyManager.SIM_STATE_UNKNOWN:
                hintMessage = "Unknown";
                break;
            case TelephonyManager.SIM_STATE_ABSENT:
                hintMessage = "no SIM card is available in the device";
                break;
            case TelephonyManager.SIM_STATE_PIN_REQUIRED:
                hintMessage = "Locked: requires the user's SIM PIN to unlock";
                break;
            case TelephonyManager.SIM_STATE_PUK_REQUIRED:
                hintMessage = "Locked: requires the user's SIM PUK to unlock ";
                break;
            case TelephonyManager.SIM_STATE_NETWORK_LOCKED:
                hintMessage = "Locked: requries a network PIN to unlock";
                break;
            case TelephonyManager.SIM_STATE_READY:
                hintMessage = "Ready";
                break;
            default:
                break;
        }

        ToastUtil.getInstance(this).show(hintMessage);
        */

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


}
