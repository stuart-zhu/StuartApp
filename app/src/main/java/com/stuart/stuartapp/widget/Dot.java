package com.stuart.stuartapp.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.stuart.stuartapp.R;

/**
 * Created by stuart on 2017/12/14.
 */

public class Dot extends ImageView {
    /**
     * 放大放大倍数
     */
    public static final float SCALE = 1.0f;
    /**
     * 透明度
     */
    public static final int ALPHA = 255;

    private float[] scaleFloats = new float[]{SCALE, SCALE, SCALE};

    private int[] alphas = new int[]{ALPHA, ALPHA, ALPHA,};

    public Dot(Context context) {
        this(context, null);
    }

    public Dot(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    /**
     * 小圆初始化设置
     *
     * @param context
     * @param attrs
     */
    private void init(Context context, AttributeSet attrs) {
        paint = new Paint();
        paint.setAntiAlias(true);// 设置画笔的锯齿效果。 true是去除，大家一看效果就明白了
        setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        if (null != attrs) {
            TypedArray arr = context.obtainStyledAttributes(attrs, R.styleable.BaseDoT);
            try {
                int selectedIndicatorColor = arr.getColor(R.styleable.BaseDoT_circleColor, Color.rgb(0x00,0x00,0x00));
                this.setColor(selectedIndicatorColor);
            } finally {
                arr.recycle();
            }
        }
    }

    /**
     * Dot默认宽度或高度
     */
    private float defaultSize = 15;

    /**
     * 修改DoT默认尺寸
     *
     * @param circleRadius
     */
    public void setCircleRadius(float circleRadius) {
        if (circleRadius > 0)
            defaultSize = circleRadius * 3;
    }

    /**
     * 小圆画笔
     */
    private Paint paint = null;

    public void setColor(int color) {
        if (paint != null)
            paint.setColor(color);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float radius = getWidth() / 3;
        float x = getWidth() / 2;
        float y = getHeight() / 2;
        canvas.save();//保存设置
        float translateX = x;
        canvas.translate(translateX, y);//平移画笔起始点
        canvas.scale(scaleFloats[0], scaleFloats[0]);//放大
        paint.setAlpha(alphas[0]);//设置透明度
        canvas.drawCircle(0, 0, radius, paint);//画圆
        canvas.restore();
    }


    /**
     * 当控件的父元素正要放置该控件时调用
     * 根据父容器传递跟子容器的大小要求来确定子容器的大小
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = measureDimension(dip2px(getContext(), defaultSize), widthMeasureSpec);
        int height = measureDimension(dip2px(getContext(), defaultSize), heightMeasureSpec);
        setMeasuredDimension(width, height);
    }


    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 根据父容器传递跟子容器的大小要求来确定子容器的大小
     *
     * @param defaultSize
     * @param measureSpec
     * @return
     */
    private int measureDimension(int defaultSize, int measureSpec) {
        int result = defaultSize;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else if (specMode == MeasureSpec.AT_MOST) {
            result = Math.min(defaultSize, specSize);
        } else {
            result = defaultSize;
        }
        return result;
    }
}
