package com.stuart.stuartapp.demo.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.stuart.stuartapp.BaseActivity;
import com.stuart.stuartapp.R;

import java.util.Set;

public class TestTask extends BaseActivity {

  private TextView tvTask;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_test_task);

    tvTask = (TextView) findViewById(R.id.tv_msg);
    findViewById(R.id.btn_jump).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
       // ComponentName cn = new ComponentName("com.bixin.bixin_android","com.bixin.bixin_android.modules.transfer.refactor.TransferV2Activity");
        Intent intent = new Intent();
        //intent.setComponent(cn);
        intent.setData(getUri());
        intent.addCategory("com.bixin.bixin_android" + ".INTERNAL_CATEGORY");
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivityForResult(intent, 1);
      }
    });


  }


  private Uri getUri() {
    return new Uri.Builder()
        .scheme("bixin")
        .authority("transfer")
        .appendPath("base")
        .appendQueryParameter("target_payment_uri", "")
        .appendQueryParameter("is_eos", "true")
        .build();
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    Log.i("stuart", "" +requestCode +"," +resultCode +"," + data);
    if (resultCode == RESULT_OK) {
      if (data != null) {
        Bundle extras = data.getExtras();
        if (extras != null) {
          Set<String> strings = extras.keySet();
          if (strings.size() > 0) {
            StringBuffer sb = new StringBuffer();
            for (String s : strings) {

              sb.append(s).append(" : ").append(extras.getString(s)).append("\n");
            }
            tvTask.setText(sb.toString());
          }


        }


      }
    }
  }
}
