package com.stuart.stuartapp.demo.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.stuart.stuartapp.BaseActivity;
import com.stuart.stuartapp.R;
import com.stuart.stuartapp.utils.LogUtil;
import com.stuart.stuartapp.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stuart on 2016/12/15.
 */
@ContentView(R.layout.demo_act_test_recycler)
public class TestRecycler extends BaseActivity{

    private static final String TAG = "TestRecycler";

    @ViewInject(R.id.recycler_view)
    private RecyclerView mRecyclerView;

    private List<String> mData;

    private RecyAdapter mAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.inject(this);
        prepareData();
//        new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
//        new GridLayoutManager(this, 5)
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 7));
        mRecyclerView.setAdapter(mAdapter = new RecyAdapter());
       // mRecyclerView.addItemDecoration(new RecyItemDecoration(this));
      //  mRecyclerView.addItemDecoration(new LeftRightItemDecoration(this));
      //  mRecyclerView.addItemDecoration(new MyItemDecoration(this));
        mRecyclerView.addItemDecoration(new MyItemDecoration1(this));
        mRecyclerView.addItemDecoration(new MyItemDecoration2(this));

    }

    private void prepareData() {
        mData = new ArrayList<>();
        for (int i = 0 ; i < 100; i++) {
            mData.add(i+"");
        }
    }
    private class RecyAdapter extends RecyclerView.Adapter{

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new RecyViewHolder(LayoutInflater.from(TestRecycler.this).inflate(R.layout.demo_act_test_recycler_item,parent,false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

            RecyViewHolder vh = (RecyViewHolder) holder;
            vh.tv.setText(mData.get(position));
            vh.tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.getInstance(TestRecycler.this).show("你点击了" + mData.get(position));
                }
            });
        }

        @Override
        public int getItemCount() {
            return mData == null ? 0 : mData.size();
        }

        private class RecyViewHolder extends RecyclerView.ViewHolder {
            TextView tv;
            public RecyViewHolder(View itemView) {
                super(itemView);
                tv = (TextView) itemView.findViewById(R.id.tv);
            }
        }
    }

    private class RecyItemDecoration extends RecyclerView.ItemDecoration {

        private int dividerHeight;
        private Paint dividerPaint;

        public RecyItemDecoration(Context context) {
            dividerPaint = new Paint();
            dividerPaint.setColor(context.getResources().getColor(android.R.color.black));
            dividerHeight = context.getResources().getDimensionPixelSize(R.dimen.divider_height);
        }


        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.bottom = dividerHeight;
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            int childCount = parent.getChildCount();
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();

            for (int i = 0; i < childCount - 1; i++) {
                View view = parent.getChildAt(i);
                float top = view.getBottom();
                float bottom = view.getBottom() + dividerHeight;
                c.drawRect(left, top, right, bottom, dividerPaint);
            }
        }
    }

    private class LeftRightItemDecoration extends RecyclerView.ItemDecoration {

        private int tagWidth;
        private Paint leftPaint;
        private Paint rightPaint;

        public LeftRightItemDecoration(Context context) {
            leftPaint = new Paint();
            leftPaint.setColor(context.getResources().getColor(R.color.colorAccent));
            rightPaint = new Paint();
            rightPaint.setColor(context.getResources().getColor(R.color.colorPrimary));
            tagWidth = context.getResources().getDimensionPixelSize(R.dimen.tag_width);
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDrawOver(c, parent, state);
            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = parent.getChildAt(i);
                int pos = parent.getChildAdapterPosition(child);
                boolean isLeft = pos % 2 == 0;
                if (isLeft) {
                    float left = child.getLeft();
                    float right = left + tagWidth;
                    float top = child.getTop();
                    float bottom = child.getBottom();
                    c.drawRect(left, top, right, bottom, leftPaint);
                } else {
                    float right = child.getRight();
                    float left = right - tagWidth;
                    float top = child.getTop();
                    float bottom = child.getBottom();
                    c.drawRect(left, top, right, bottom, rightPaint);

                }
            }
        }
    }

    private class MyItemDecoration extends RecyclerView.ItemDecoration {

        private int width;
        Paint mPaint;
        public MyItemDecoration(Context context) {
            mPaint = new Paint();
            mPaint.setColor(context.getResources().getColor(android.R.color.black));
            width = context.getResources().getDimensionPixelSize(R.dimen.divider_height);
        }

        /*@Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.right = width;
        }*/
        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDraw(c, parent, state);

            for (int x = 0 ; x < parent.getChildCount(); x++) {
                View v = parent.getChildAt(x);

                int top =  v.getTop();
                int bottom = v.getBottom();
                int right = v.getRight();
                c.drawRect(right - width, top, right, bottom, mPaint);

            }
        }
    }

    private class MyItemDecoration1 extends RecyclerView.ItemDecoration {

        int corner;
        int width;
        Paint mPaint;
        public MyItemDecoration1(Context c) {

            width = c.getResources().getDimensionPixelSize(R.dimen.divider_height);
            corner = c.getResources().getDimensionPixelSize(R.dimen.card_corner) / 3 *2;
            mPaint = new Paint();
            mPaint.setColor(Color.parseColor("#ede5e5"));

        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDrawOver(c, parent, state);

            int count = parent.getChildCount();
            for (int i = 0 ; i < count ; i++) {
                View child = parent.getChildAt(i);

                int top = child.getTop() + corner;
                int right = child.getRight();
                int bottom = child.getBottom() - corner;

                c.drawRect(right - width, top, right, bottom, mPaint);

            }
        }
    }

    private class MyItemDecoration2 extends RecyclerView.ItemDecoration {

        int width, corner;
        Paint mPaint;
        private MyItemDecoration2(Context c) {

            width = c.getResources().getDimensionPixelSize(R.dimen.divider_height);
            corner = c.getResources().getDimensionPixelSize(R.dimen.card_corner) / 3 * 2;

            mPaint = new Paint();
            mPaint.setColor(Color.parseColor("#ede5e5"));
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDrawOver(c, parent, state);
            int count = parent.getChildCount();

            for (int i = 0 ; i < count ; i++) {
                View child = parent.getChildAt(i);
                int bottom = child.getBottom();
                int top = bottom - width;
                int left = child.getLeft() + corner;
                int right = child.getRight() - corner;

                c.drawRect(left, top, right, bottom, mPaint);
            }
        }
    }
}
