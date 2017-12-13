package com.stuart.stuartapp.demo.weather.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.stuart.stuartapp.demo.weather.entity.HourWeather;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stuart on 2017/5/10.
 */

public class TrendGraph extends View {

    private static final int LIMIT_COUNT = 8;

    private Paint mLinePaint;
    private TextPaint mTextPaint;

    private int circleRadius;

    public TrendGraph(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mLinePaint = new Paint();
        mLinePaint.setColor(Color.BLUE);
        mTextPaint = new TextPaint();
        mTextPaint.setTextSize(30);
        circleRadius = 3;
    }

    private List<HourWeather> mHourWeathers;

    public void setHourWeathers(List<HourWeather> list) {
        if (list.size() > LIMIT_COUNT * 2) {
            mHourWeathers = new ArrayList<>();
            for (int i = 0; i < LIMIT_COUNT; i++) {
                mHourWeathers.add(list.get(i * 2));
            }
        } else {
            mHourWeathers = list;
        }
        invalidate();
    }

    public double getMaxUp() {
        double max = 0;
        for (int i = 0; i < mHourWeathers.size(); i++) {
            HourWeather hourWeather = mHourWeathers.get(i);

            if (i == 0) {
                max = Double.parseDouble(hourWeather.getTemp());
            } else {
                Double d = Double.parseDouble(hourWeather.getTemp());
                if (d > max)
                    max = d;
            }
        }
        return max;
    }

    private double getMinDown() {
        double min = 0;
        for (int i = 0; i < mHourWeathers.size(); i++) {
            HourWeather hourWeather = mHourWeathers.get(i);

            if (i == 0) {
                min = Double.parseDouble(hourWeather.getTemp());
            } else {
                Double d = Double.parseDouble(hourWeather.getTemp());
                if (d < min)
                    min = d;
            }
        }
        return min;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mHourWeathers == null || mHourWeathers.size() == 0) {
            return;
        }
        double max_up = getMaxUp();
        double min_down = getMinDown();
        //  canvas.setDrawFilter(mDrawFilter);
        mLinePaint.setStrokeWidth(2);
        float width = getWidth();
        float grap = width / mHourWeathers.size();
        float textSize = mTextPaint.getTextSize();
        int textMargin = circleRadius * /*2*/ 8;
        float margin_top = textSize + 2 * textMargin;

        float height = getHeight() - 2 * margin_top;

        float timeY = getHeight() ;

        for (int i = 0; i < mHourWeathers.size() - 1; i++) {
            float startX = i * grap + grap / 2;
            float stopX = (i + 1) * grap + grap / 2;
            float startY = (float) (max_up - Double.parseDouble(mHourWeathers.get(i).getTemp())) / (float) (max_up -
                    min_down) * height + margin_top;
            float stopY = (float) (max_up - Double.parseDouble(mHourWeathers.get(i + 1).getTemp())) / (float) (max_up -
                    min_down) * height + margin_top;

            canvas.drawText((Integer.parseInt(mHourWeathers.get(i).getTemp())) + "℃", startX - textSize, startY -
                    textMargin, mTextPaint);
            canvas.drawCircle(startX, startY, circleRadius, mLinePaint);
            canvas.drawLine(startX, startY, stopX, stopY, mLinePaint);

            canvas.drawText(mHourWeathers.get(i).getWeather(), startX - textSize, startY
                             + textMargin*2, mTextPaint);
            canvas.drawText(mHourWeathers.get(i).getTime(), startX - textSize, /*startY
                             + textMargin*2*/timeY, mTextPaint);
            if (i == mHourWeathers.size() - 2) {
                canvas.drawText(Integer.parseInt(mHourWeathers.get(i + 1).getTemp()) + "℃", stopX - textSize, stopY
                        - textMargin, mTextPaint);
                canvas.drawCircle(stopX, stopY, circleRadius, mLinePaint);

                canvas.drawText(mHourWeathers.get(i+1).getWeather(), stopX - textSize, stopY
                        + textMargin*2, mTextPaint);
                canvas.drawText(mHourWeathers.get(i + 1).getTime(), stopX - textSize, /*stopY
                                     + textMargin*2 */ timeY, mTextPaint);
            }
            


        }
    }
}
