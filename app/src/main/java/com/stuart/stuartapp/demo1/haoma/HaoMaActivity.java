package com.stuart.stuartapp.demo1.haoma;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.stuart.stuartapp.BaseActivity;
import com.stuart.stuartapp.R;


/**
 * Created by stuart on 2017/12/12.
 */

@ContentView(R.layout.activity_hao_ma)
public class HaoMaActivity extends BaseActivity {

    @ViewInject(R.id.phone_number)
    private EditText etNumber;

    @ViewInject(R.id.btn_gsd)
    private Button btnCheck;

    @ViewInject(R.id.gsd)
    private TextView tvGsd;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.inject(this);

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HaoMaUtils.loadGSD(etNumber.getText().toString(), new HaoMaUtils.CallBack() {
                    @Override
                    public void onLoadFailed(final String msg) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvGsd.setText(msg);
                            }
                        });
                    }

                    @Override
                    public void onLoadSuccess(final String msg) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvGsd.setText(msg);
                            }
                        });
                    }
                });
            }
        });
    }
}
