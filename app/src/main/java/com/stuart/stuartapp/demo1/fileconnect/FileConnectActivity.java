package com.stuart.stuartapp.demo1.fileconnect;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.stuart.stuartapp.BaseActivity;
import com.stuart.stuartapp.R;

import butterknife.ButterKnife;

public class FileConnectActivity extends BaseActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_file_connect);

    ButterKnife.bind(this);
    //com.flowertv.files.download/video
  }
}
