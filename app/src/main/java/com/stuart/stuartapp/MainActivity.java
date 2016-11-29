package com.stuart.stuartapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.stuart.stuartapp.activity.TwoColorBallActivity;
import com.stuart.stuartapp.callback.GetSSQListener;
import com.stuart.stuartapp.dao.SsqDao;
import com.stuart.stuartapp.entity.SSQ;
import com.stuart.stuartapp.utils.DataUtils;
import com.stuart.stuartapp.utils.LogUtil;
import com.stuart.stuartapp.utils.ToastUtil;

import java.util.List;

/**
 * Created by stuart on 2016/11/7.
 */
public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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


        /*Intent localePicker = new Intent();
        localePicker.setClassName("com.android.provision","com.android.provision.LocaleSettingsActivity");
        localePicker.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        localePicker.setAction("android.intent.action.VIEW");
        startActivity(localePicker);*/
    }

    public void toClick(View v) {
        switch (v.getId()) {
            case R.id.two_color_ball:
                startActivity(new Intent(this, TwoColorBallActivity.class));
                break;
        }
    }
}
