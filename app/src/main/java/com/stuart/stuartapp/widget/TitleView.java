package com.stuart.stuartapp.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.stuart.stuartapp.R;

public class TitleView extends RelativeLayout {

  private ImageView back;

  private TextView title;

  private TextView right;


  public TitleView(Context context) {
    this(context, null);
  }

  public TitleView(Context context, AttributeSet attrs) {
    this(context, attrs, -1);
  }

  public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
    TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleView);
    String t = typedArray.getString(R.styleable.TitleView_title);
    boolean showBack = typedArray.getBoolean(R.styleable.TitleView_show_back, false);
    String r = typedArray.getString(R.styleable.TitleView_right);
    typedArray.recycle();
    if (!TextUtils.isEmpty(t))
    title.setText(t);
    back.setVisibility(showBack ? View.GONE: View.VISIBLE);
    if (!TextUtils.isEmpty(r)) right.setText(r);
//    back.setOnClickListener(v -> {if (getContext() instanceof Activity) ((Activity) getContext()).finish();});

  }

  private void init() {
    LayoutInflater.from(getContext()).inflate(R.layout.title_view, this);
    back = findViewById(R.id.back);
    title = findViewById(R.id.title);
    right = findViewById(R.id.right);

  }

}
