package com.stuart.stuartapp.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.stuart.stuartapp.R;
import com.stuart.stuartapp.entity.ItemDemo;
import com.stuart.stuartapp.holder.ButtonDemoItemHolder;
import com.stuart.stuartapp.holder.DemoItemHolder;
import com.stuart.stuartapp.holder.TextViewDemoItemHolder;
import com.stuart.stuartapp.utils.LogUtil;
import com.stuart.stuartapp.utils.ViewProvider;

import java.util.List;

public class BaseDemoFragment extends Fragment{

  private static final String TAG = "BaseDemoFragment";
  protected List<ItemDemo> list;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    list = prepareData();
    return super.onCreateView(inflater, container, savedInstanceState);
  }

  protected List<ItemDemo> prepareData() {
    return null;
  }

  protected class Divider extends RecyclerView.ItemDecoration {
    private Paint mPaint;

    public Divider(Context context) {

      mPaint = new Paint();
      mPaint.setColor(Color.parseColor("#33256780"));
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
      super.onDrawOver(c, parent, state);
      int count = parent.getChildCount();
      for (int i = 0; i < parent.getChildCount(); i++) {
        View child = parent.getChildAt(i);
        int dividerTop = child.getBottom();
        int left = child.getLeft();
        int  right =  child.getRight();
        c.drawRect(new Rect(left + 100, dividerTop, right, dividerTop + 1),mPaint);
      }
    }
  }

  protected class MyAdapter extends RecyclerView.Adapter {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      LogUtil.i(TAG, "onCreateViewHolder", "list  size   =  " + viewType);

      return createHolder(viewType);

    }

    @Override

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

      Log.i("stuart", "list --> " + list.get(position));
      ((DemoItemHolder) holder).onBind(
          list.get(position)
      );
    }

    @Override
    public int getItemViewType(int position) {
      return list.get(position).mtype;
    }

    @Override
    public int getItemCount() {
      LogUtil.i(TAG, "getItemCount", "list  size   =  " + list.size());
      return list.size();
    }
  }

  private DemoItemHolder createHolder(int type) {
    switch (type) {
      case 1:
        TextView textView = new TextView(getActivity());
        textView.setId(R.id.my_vh_tv_id);
        textView.setGravity(Gravity.CENTER);
        LinearLayout linearLayout = ViewProvider.getLinearLayout(getActivity());
        linearLayout.addView(textView);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        lp.gravity = Gravity.CENTER;
        textView.setLayoutParams(lp);

        return new TextViewDemoItemHolder(linearLayout);
      case 2:

        Button bt = new Button(getActivity());
        bt.setId(R.id.my_vh_bt_id);
        RelativeLayout rl = ViewProvider.getRealtiveLayout(getActivity());
        rl.addView(bt);
        RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        );
        rlp.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        bt.setLayoutParams(rlp);

        return new ButtonDemoItemHolder(rl);
    }
    return null;
  }
}
