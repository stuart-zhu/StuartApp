package com.stuart.stuartapp.holder;

import android.view.View;

import com.stuart.stuartapp.entity.ItemDemo;

public abstract class DemoItemHolder extends BaseHolder<ItemDemo>{
  public DemoItemHolder(View itemView) {
    super(itemView);
  }

  @Override
  public void onBind(ItemDemo o) {
    bindItem(o);
  }

  abstract void bindItem(ItemDemo i);

}
