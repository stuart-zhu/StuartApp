package com.stuart.stuartapp.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.stuart.stuartapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stuart on 2017/12/14.
 */

public class PagerPointView extends BaseDoTView {
    /**
     * 小圆点集合
     */
    protected List<Dot> mDots;

    public PagerPointView(Context context) {
        super(context);
    }

    public PagerPointView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initViews() {
        LayoutInflater.from(getContext()).inflate(R.layout.default_indicator, this);
    }

    @Override
    public void initialize(int slideCount, int first_page_num) {
        removeAllViews();
        mDots = new ArrayList<>();
        mSlideCount = slideCount;

        for (int i = 0; i < slideCount; i++) {
            Dot dot = new Dot(getContext());
            dot.setColor(getUnselectedIndicatorColor());
            LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            dot.setCircleRadius(getCircleRadius());
            addView(dot, params);
            mDots.add(dot);
        }
        setSelectPosition(first_page_num);
    }

    @Override
    public void setSelectPosition(int index) {

        setCurrentposition(index);
        for (int i = 0; i < mSlideCount; i++) {
            if (i == index)
                mDots.get(i).setColor(getSelectedIndicatorColor());
            else
                mDots.get(i).setColor(getUnselectedIndicatorColor());
            mDots.get(i).postInvalidate();
        }
        postInvalidate();//刷新界面
    }
}