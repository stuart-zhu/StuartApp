package com.stuart.stuartapp.holder;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.stuart.stuartapp.R;
import com.stuart.stuartapp.entity.ItemDemo;

public class TextViewDemoItemHolder extends DemoItemHolder{
  public TextViewDemoItemHolder(View itemView) {
    super(itemView);
  }

  @Override
  void bindItem(final ItemDemo i) {
    TextView tv = ((TextView) baseContentView.findViewById(R.id.my_vh_tv_id));
    tv.setBackgroundResource(R.drawable.button_click_style);
    tv.setText(i.resId);
    tv.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        view.getContext().startActivity(new Intent(i.action));      }
    });
  }
}
