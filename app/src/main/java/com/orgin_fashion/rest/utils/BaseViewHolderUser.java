package com.orgin_fashion.rest.utils;

import android.support.v7.widget.RecyclerView;
import android.view.View;


public abstract class BaseViewHolderUser extends RecyclerView.ViewHolder {

  private int mCurrentPosition;

  public BaseViewHolderUser(View itemView) {
    super(itemView);
  }

  protected abstract void clear();

  public void onBind(int position) {
    mCurrentPosition = position;
    clear();
  }

  public int getCurrentPosition() {
    return mCurrentPosition;
  }
}

