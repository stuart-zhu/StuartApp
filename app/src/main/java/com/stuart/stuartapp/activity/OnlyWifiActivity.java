package com.stuart.stuartapp.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.stuart.stuartapp.BaseActivity;
import com.stuart.stuartapp.R;
import com.stuart.stuartapp.utils.ToastUtil;

/**
 * Created by stuart on 2017/6/12.
 */

public class OnlyWifiActivity extends BaseActivity {

    private EditText etWifi, etSsid;

    private Button btnSave;


    private SharedPreferences sp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = getSharedPreferences("wifi",MODE_PRIVATE);
        setContentView(R.layout.activity_only_wifi);
        setTitle(R.string.only_wifi);
        initView();
    }

    private void initView() {
        etWifi = (EditText) findViewById(R.id.wifi);
        etSsid = (EditText) findViewById(R.id.ssid);
        btnSave = (Button) findViewById(R.id.save_wifi);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etWifi.getText().toString())) {
                    ToastUtil.showMessage(R.string.none_wifi_name);
                    return;
                }
                sp.edit().putString("wifi", etWifi.getText().toString().trim()).commit();
                sp.edit().putString("ssid", etSsid.getText().toString().trim()).commit();
            }
        });
    }


    private class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

        }
    }
}
