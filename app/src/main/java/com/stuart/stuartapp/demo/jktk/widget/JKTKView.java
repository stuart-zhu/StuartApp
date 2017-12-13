package com.stuart.stuartapp.demo.jktk.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.stuart.stuartapp.R;
import com.stuart.stuartapp.demo.jktk.entity.JKTK;

import java.util.List;

/**
 * Created by stuart on 2017/12/4.
 */

public class JKTKView extends LinearLayout {

    private TextView tvQuestion;
    private RadioGroup rg1, rg2;
    private RadioButton rb1, rb2, rb3, rb4, rb5, rb6;
    private ImageView iv;

    private TextView tvNumber;

    public JKTKView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.jktk_sign_view, this);

        tvNumber = (TextView) findViewById(R.id.number);
        tvQuestion = (TextView) findViewById(R.id.question);
        rg1 = (RadioGroup) findViewById(R.id.rg1);
        rg2 = (RadioGroup) findViewById(R.id.rg2);
        rb1 = (RadioButton) findViewById(R.id.rb1);
        rb2 = (RadioButton) findViewById(R.id.rb2);
        rb3 = (RadioButton) findViewById(R.id.rb3);
        rb4 = (RadioButton) findViewById(R.id.rb4);
        rb5 = (RadioButton) findViewById(R.id.rb5);
        rb6 = (RadioButton) findViewById(R.id.rb6);

        rb1.setOnCheckedChangeListener(onCheckedChangeListener);
        rb2.setOnCheckedChangeListener(onCheckedChangeListener);
        rb3.setOnCheckedChangeListener(onCheckedChangeListener);
        rb4.setOnCheckedChangeListener(onCheckedChangeListener);
        rb5.setOnCheckedChangeListener(onCheckedChangeListener);
        rb6.setOnCheckedChangeListener(onCheckedChangeListener);

        iv = (ImageView) findViewById(R.id.iv);

    }

    private void resetLoad() {
        rg1.clearCheck();
        rg2.clearCheck();

    }
    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            if (listener != null) listener.onJktkStatusChange(onJKTKViewListener.STATUS_SELECT);
        }
    };

    private onJKTKViewListener listener;

    public void setOnJKTKViewListener(onJKTKViewListener l) {
        listener = l;
    }
    public interface onJKTKViewListener{
        public void onJktkStatusChange(int stutus);

        public void onSelectJktk(JKTK jktk);

        public static final int STATUS_NEW_QUESTION = 1;

        public static final int STATUS_SELECT = 2;

        public static final int STATUS_LAST = 3;




    }
    private JKTK mJktk;

    private List<JKTK> mJktks;

    private int postion = 0;
    public void setListJktk(List<JKTK> jktks) {
        mJktks = jktks;
       next();
    }

    public void nextQustion() {

        if(postion == mJktks.size()) {
            return;
        }
        next();
        if (listener != null) listener.onJktkStatusChange(onJKTKViewListener.STATUS_NEW_QUESTION);
        if (postion == mJktks.size()) {
            if (listener != null) listener.onJktkStatusChange(onJKTKViewListener.STATUS_LAST);
        }
    }

    private void next() {
        resetLoad();
        setJKTK(mJktks.get(postion ++));
    }
    private void setJKTK(JKTK jktk) {
        mJktk = jktk;
        if (listener != null)
            listener.onSelectJktk(mJktk);
        tvNumber.setText(postion +".");
        tvQuestion.setText(mJktk.getQuestion());

        rb1.setText(mJktk.getOption1());
        rb2.setText(mJktk.getOption2());
        rb3.setText(mJktk.getOption3());
        rb4.setText(mJktk.getOption4());
        showRg2();

        if (!TextUtils.isEmpty(mJktk.getPic())) {
            iv.setVisibility(View.VISIBLE);
            BitmapUtils bm = new BitmapUtils(getContext());
            bm.display(iv, mJktk.getPic());
        } else {
            iv.setVisibility(View.GONE);
        }
    }
    private void showRg2() {

        if (mJktk == null) {
            return;
        }

        boolean singleC = mJktk.isSingleChoose();
        rg2.setVisibility(singleC ? View.VISIBLE : View.GONE);
        rg1.setVisibility(singleC ? View.GONE : View.VISIBLE);
    }

    public boolean confirmQuestion() {
        String answer = mJktk.getAnswer();

        return answer.equals(getChooseQustion());
    }

    private String getChooseQustion() {
        if (mJktk == null) {
            return "";
        }

        boolean singleC = mJktk.isSingleChoose();
        String t = null;
        if (singleC) {

            t = (String) findViewById(rg2.getCheckedRadioButtonId()).getTag();
        } else {
            t = (String) findViewById(rg1.getCheckedRadioButtonId()).getTag();
        }
        return t;
    }




}
