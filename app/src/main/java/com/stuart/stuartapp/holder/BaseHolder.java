package com.stuart.stuartapp.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class BaseHolder<T> extends RecyclerView.ViewHolder {
  protected View baseContentView;

  public BaseHolder(View itemView) {
    super(itemView);
    baseContentView = itemView;
  }

  public abstract void onBind(T o);

}
