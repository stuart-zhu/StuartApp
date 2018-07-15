package com.stuart.stuartapp.demo1.ss;

import android.Manifest;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.zxing.client.android.CaptureActivity;
import com.stuart.stuartapp.BaseActivity;
import com.stuart.stuartapp.R;
import com.stuart.stuartapp.utils.Base64Utils;
import com.stuart.stuartapp.utils.LogUtil;
import com.stuart.stuartapp.utils.ToastUtil;

public class SSMainA extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "ssMainA";
    private TextView tvResult;

    private Button btnCopy;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ssmain);

        findViewById(R.id.scan).setOnClickListener(this);

        tvResult = (TextView) findViewById(R.id.tv_result);
        btnCopy = (Button) findViewById(R.id.copy);
        btnCopy.setOnClickListener(this);

//        loadP();

    }

    private static final String [] PERMISSION_C = new String[] {
            Manifest.permission.CAMERA
    };

    private void loadP() {
        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.CAMERA ) != PackageManager.PERMISSION_GRANTED )
        ActivityCompat.requestPermissions(this, new String[] {
                Manifest.permission.CAMERA
        }, 1);
        else
            scan();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 1) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                finish();
            } else {
                scan();
            }
        }
    }

    private void scan() {
        Intent intent = new  Intent(this, CaptureActivity.class);

        startActivityForResult(intent, 123);
//        aes-256-cfb:f55.fun-55140759@jp01.1ss.bid:11172




//   B     BASE64Decoder
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123 && resultCode == RESULT_OK) {
            String text = data.getStringExtra("result_text");

            text = text.replace("ss://", "").trim();
            LogUtil.i(TAG, "onActivityResult", "text = " + text);
            text = Base64Utils.decode(text);
            String[] split = text.split(":");
            StringBuilder sb = new StringBuilder();
            for (String s : split) {
                sb.append(s).append("\n");
            }
            tvResult.setText(sb.toString());
            if (!TextUtils.isEmpty(sb.toString())) btnCopy.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.copy:
                copy();
                break;

            case R.id.scan:
                loadP();
                break;
        }

    }

    private void copy() {
        ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        cm.setText(tvResult.getText().toString());
        ToastUtil.showMessage("复制好了");
    }
}
