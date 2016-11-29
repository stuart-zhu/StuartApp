package com.stuart.stuartapp.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.stuart.stuartapp.R;

public class TwoColorBall extends RelativeLayout {

    private static final String TAG = "TwoColorBall";
    public static final String COLOR_RED = "red";
    public static final String COLOR_BLUE = "blue";

    private ImageView iv;

    private TextView tv;

    private View container;
    private String color;

    private boolean enable;

    public TwoColorBall(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.TwoColorBall);
        color = a.getString(R.styleable.TwoColorBall_ball_color);
        enable = a.getBoolean(R.styleable.TwoColorBall_ball_enable, true);
        a.recycle();
        LayoutInflater.from(getContext())
                .inflate(R.layout.two_color_ball, this);
        iv = (ImageView) findViewById(R.id.image_bg);

        tv = (TextView) findViewById(R.id.image_tv);

        iv.setBackground(null);

        setColorSetting();

        container = findViewById(R.id.two_color_ball);
        container.setClickable(enable);
        container.setEnabled(enable);
    }


    public void setColor(String color) {
        this.color = color;
        setColorSetting();
    }

    private void setColorSetting() {
        if (COLOR_BLUE.equals(color)) {
            iv.setImageResource(R.drawable.ball_blue_bg);
            tv.setTextColor(getContext().getResources().getColorStateList(
                    R.color.ball_text_color_blue));
        } else if (COLOR_RED.equals(color)) {
            iv.setImageResource(R.drawable.ball_red_bg);
            tv.setTextColor(getContext().getResources().getColorStateList(
                    R.color.ball_text_color_red));
        }
    }

    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
        iv.setSelected(isChecked);
        tv.setSelected(isChecked);

    }

    private boolean isChecked;

    public boolean isChecked() {

        return isChecked;
    }


    public void setText(String text) {
        tv.setText(text);
    }

}
