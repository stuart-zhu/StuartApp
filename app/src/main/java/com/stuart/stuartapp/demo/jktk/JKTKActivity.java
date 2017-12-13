package com.stuart.stuartapp.demo.jktk;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.stuart.stuartapp.BaseActivity;
import com.stuart.stuartapp.R;
import com.stuart.stuartapp.demo.jktk.entity.JKTK;
import com.stuart.stuartapp.demo.jktk.widget.JKTKView;

import java.util.List;

/**
 * Created by stuart on 2017/12/1.
 */

public class JKTKActivity extends BaseActivity {

    private JKTKView mJktkView;


    private Button answer, nextQues;

    private ProgressDialog mDialog;

    private TextView tvAnswer;

    private void prepareDialog() {
        mDialog = new ProgressDialog(this);
        mDialog.setCancelable(false);
        mDialog.setMessage("正在加载题库");
        mDialog.show();
    }
    private JKTK mCurrentJktk;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jktk);
        mJktkView = (JKTKView) findViewById(R.id.jktk_view);
        tvAnswer = (TextView) findViewById(R.id.answer_layout);
        answer = (Button) findViewById(R.id.answer);
        nextQues = (Button) findViewById(R.id.next_question);


        nextQues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mJktkView.nextQustion();
            }
        });

        answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvAnswer.setText(mCurrentJktk.getAnswer() +"\n" +mCurrentJktk.getExplain());
            }
        });
        mJktkView.setOnJKTKViewListener(onJKTKViewListener);
        nextQues.setEnabled(false);
        JKTKUtils.loadJktk("1","20", /*"normal"*/ "rand", "1", "C1", new JKTKUtils.OnLoadJKTKListener() {
            @Override
            public void onJKTKLoad(final List<JKTK> list) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (mDialog != null && mDialog.isShowing()) mDialog.cancel();

                        mJktkView.setListJktk(list);
                    }
                });
            }

            @Override
            public void onLoadJKTLFaile(String msg) {

            }

            @Override
            public void onJKTKLoadStart() {
                prepareDialog();
            }
        });
    }


    private JKTKView.onJKTKViewListener onJKTKViewListener = new JKTKView.onJKTKViewListener() {
        @Override
        public void onJktkStatusChange(int stutus) {
            switch (stutus) {
                case STATUS_NEW_QUESTION:
                    nextQues.setEnabled(false);
                    tvAnswer.setText("");
                    break;

                case STATUS_SELECT:
                    nextQues.setEnabled(true);
                    break;

                case STATUS_LAST:
                    nextQues.setText("完成");
                    break;
            }
        }

        @Override
        public void onSelectJktk(JKTK jktk) {
            mCurrentJktk = jktk;
        }


    };
}
