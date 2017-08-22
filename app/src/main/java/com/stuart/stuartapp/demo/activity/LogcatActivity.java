package com.stuart.stuartapp.demo.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.stuart.stuartapp.BaseActivity;
import com.stuart.stuartapp.R;
import com.stuart.stuartapp.utils.LogUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by stuart on 2017/8/7.
 */

public class LogcatActivity extends BaseActivity {

    private TextView tv;

    private Button start, stop;

    private EditText et;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logcat);

        et = (EditText) findViewById(R.id.et);

        tv = (TextView) findViewById(R.id.tv);

        start = (Button) findViewById(R.id.start);

        stop = (Button) findViewById(R.id.stop);
        stop.setEnabled(false);
    }

    private Process process;

    public void doit(View v) {
        start.setEnabled(false);
        stop.setEnabled(true);
        switch (v.getId()) {
            case R.id.start:
                new Thread() {
                    @Override
                    public void run() {
                        String path = et.getText().toString();
                        FileOutputStream fos = null;
                        if (!TextUtils.isEmpty(path)) {
                            File f = new File("sdcard/" + path +".txt");
                            if (f.exists()) f.delete();
                            if (!f.exists()) try {
                                f.createNewFile();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            try {
                                fos = new FileOutputStream(f);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        }


                        try {
                            process = Runtime.getRuntime().exec("logcat -v threadtime");

                            BufferedReader bufferedReader = new BufferedReader(
                                    new InputStreamReader(process.getInputStream()));

                            StringBuilder log=new StringBuilder();
                            String line;
                            while ((line = bufferedReader.readLine()) != null) {
                                log.append(line);
                                mHandler.obtainMessage(0, line).sendToTarget();
                                if (fos != null) {
                                    fos.write(line.getBytes());
                                    fos.write("\n".getBytes());
                                }

                            }


                        } catch (IOException e) {
                        }


                    }
                }.start();


                break;
            case R.id.stop:
                if (process != null) process.destroy();
                start.setEnabled(true);
                stop.setEnabled(false);
                break;
        }

    }

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            tv.setText(String.valueOf(msg.obj));
        }
    };

}
