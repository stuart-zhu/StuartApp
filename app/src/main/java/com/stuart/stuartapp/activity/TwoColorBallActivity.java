package com.stuart.stuartapp.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.nfc.cardemulation.HostNfcFService;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.stuart.stuartapp.BaseActivity;
import com.stuart.stuartapp.R;
import com.stuart.stuartapp.entity.SSQ;
import com.stuart.stuartapp.utils.JxUtils;
import com.stuart.stuartapp.utils.ToastUtil;
import com.stuart.stuartapp.widget.TwoColorBall;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by stuart on 2016/11/7.
 */
public class TwoColorBallActivity extends BaseActivity {

    private GridView redBallLv, blueBallLv;

    private EditText etCount;

    private BallAdapter mRedAdapter, mBlueAdapter;

    private ProgressDialog mProgress;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        setContentView(R.layout.activity_two_color);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        redBallLv = (GridView) findViewById(R.id.red_ball_lv);

        blueBallLv = (GridView) findViewById(R.id.blue_ball_lv);


        etCount = (EditText) findViewById(R.id.same_count_et);

        mRedAdapter = new BallAdapter(this, BallAdapter.TYPE_BALL_RED, 33);
        redBallLv.setAdapter(mRedAdapter);

        mBlueAdapter = new BallAdapter(this, BallAdapter.TYPE_BALL_BLUE, 16);
        blueBallLv.setAdapter(mBlueAdapter);

       /* blueBallLv.setOnItemClickListener(onItemClickListener);
        redBallLv.setOnItemClickListener(onItemClickListener);*/

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (sensorManager != null) {
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }
        if (sensor != null) {
            sensorManager.registerListener(sensorEventListener,
                    sensor,
                    SensorManager.SENSOR_DELAY_GAME);//这里选择感应频率
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (sensorManager != null)
            sensorManager.unregisterListener(sensorEventListener);
    }

    public void goGo(View v) {
        switch (v.getId()) {
            case R.id.btn_yao_yao:
                startGet();
                break;
            case R.id.btn_hisotry:
                startActivity(new Intent(this, SSQHistoryActivity.class));
                break;
        }
    }

    private void startGet() {
        String text = etCount.getText().toString();
        if (TextUtils.isEmpty(text)) {
            ToastUtil.getInstance(this ).show("说个数呀！");
            return;
        }
        etCount.setText("");
        final int count = Integer.parseInt(text);

        mHandler.sendEmptyMessage(MESSAGE_SHOW_PROGRESS);
        Observable.just(33, 16).subscribeOn(Schedulers.newThread())
                /*.observeOn(AndroidSchedulers.mainThread())*/.map(new Func1<Integer, List<String>>() {
            @Override
            public List<String> call(Integer integer) {
                int x;
                if (integer > 16) x = 6;
                else x = 1;
                return JxUtils.getList(integer, x, count);
            }
        }).subscribe(new Action1<List<String>>() {
            @Override
            public void call(final List<String> strings) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (strings.size() > 1) {
                            mRedAdapter.setSelectList(strings);
                        } else {
                            mBlueAdapter.setSelectList(strings);
                            mHandler.sendEmptyMessage(MESSAGE_CANCLE_PROGRESS);
                        }
                    }
                });
            }
        });


    }

    private void cancleProgress() {
        if (mProgress != null && mProgress.isShowing()) {
            mProgress.cancel();
            mProgress = null;
        }
    }

    private static final int MESSAGE_START_LOAD = 1;
    private static final int MESSAGE_SHOW_PROGRESS = 2;
    private static final int MESSAGE_CANCLE_PROGRESS = 3;

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_START_LOAD:
                    startGet();
                    break;
                case MESSAGE_SHOW_PROGRESS:
                    mProgress = new ProgressDialog(TwoColorBallActivity. this, ProgressDialog.STYLE_SPINNER);
                    mProgress.setMessage("稍等");
                    mProgress.show();
                    break;
                case MESSAGE_CANCLE_PROGRESS:
                    cancleProgress();
                    break;
            }
        }
    };
    private class BallAdapter extends BaseAdapter {

        public static final int TYPE_BALL_RED = 1;
        public static final int TYPE_BALL_BLUE = 2;

        private int type;
        private LayoutInflater mInflator;

        private List<String> mSelectList;

        private int count;

        public BallAdapter(Context context, int type, int count) {
            mInflator = LayoutInflater.from(context);
            this.type = type;
            this.count = count;
        }

        public void setSelectList(List<String> list) {
            mSelectList = list;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return count;
        }

        @Override
        public Integer getItem(int position) {
            return position + 1;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
             if (convertView == null) {
            convertView = mInflator.inflate(R.layout.two_color_ball_item, null);
            }

            final TwoColorBall tcb = (TwoColorBall) convertView;

            String color = null;
            switch (type) {
                case TYPE_BALL_RED:
                    color = TwoColorBall.COLOR_RED;
                    break;
                case TYPE_BALL_BLUE:
                    color = TwoColorBall.COLOR_BLUE;
                    break;
            }
            tcb.setColor(color);
            final String text = getItem(position) + "";

            tcb.setText(text);
            if (mSelectList != null && mSelectList.contains(text)) {

                tcb.setChecked(true);

//                tcb.setPressed(true);
            } else {

                tcb.setChecked(false);
            }

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        if (tcb.isChecked()) {
                            tcb.setChecked(false);
                        } else {
                            tcb.setChecked(true);
                        }

                }
            });
            return convertView;
        }
    }

    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (view instanceof TwoColorBall) {
                TwoColorBall v = (TwoColorBall) view;
                if (v.isChecked()) {
                    v.setChecked(false);
                } else {
                    v.setChecked(true);
                }
            }

        }
    };


    private SensorManager sensorManager;
    private Sensor sensor;
    private Vibrator vibrator;
    private static final int UPTATE_INTERVAL_TIME = 50;
    private static final int SPEED_SHRESHOLD = 30;//这个值调节灵敏度
    private long lastUpdateTime;
    private float lastX;
    private float lastY;
    private float lastZ;

    private SensorEventListener sensorEventListener = new SensorEventListener() {


        @Override
        public void onSensorChanged(SensorEvent event) {
            long currentUpdateTime = System.currentTimeMillis();
            long timeInterval = currentUpdateTime - lastUpdateTime;
            if (timeInterval < UPTATE_INTERVAL_TIME) {
                return;
            }
            lastUpdateTime = currentUpdateTime;
            // 传感器信息改变时执行该方法
            float[] values = event.values;
            float x = values[0]; // x轴方向的重力加速度，向右为正
            float y = values[1]; // y轴方向的重力加速度，向前为正
            float z = values[2]; // z轴方向的重力加速度，向上为正
            float deltaX = x - lastX;
            float deltaY = y - lastY;
            float deltaZ = z - lastZ;


            lastX = x;
            lastY = y;
            lastZ = z;
            double speed = (Math.sqrt(deltaX * deltaX + deltaY * deltaY
                    + deltaZ * deltaZ) / timeInterval) * 100;
            if (speed >= SPEED_SHRESHOLD) {
                vibrator.vibrate(300);
                mHandler.removeMessages(MESSAGE_START_LOAD);
                mHandler.sendEmptyMessageDelayed(MESSAGE_START_LOAD, 300);
            }
        }


        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };


}
