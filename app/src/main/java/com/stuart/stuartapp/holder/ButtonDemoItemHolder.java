package com.stuart.stuartapp.holder;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.stuart.stuartapp.R;
import com.stuart.stuartapp.entity.ItemDemo;

public class ButtonDemoItemHolder extends DemoItemHolder {
  public ButtonDemoItemHolder(View itemView) {
    super(itemView);
  }

  @Override
  void bindItem(final ItemDemo i) {
    Button bt = ((Button) baseContentView.findViewById(R.id.my_vh_bt_id));
    bt.setText(i.resId);

    bt.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        view.getContext().startActivity(new Intent(i.action));
      }
    });
  }
}
