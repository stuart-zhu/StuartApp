package com.stuart.stuartapp.demo1.fileconnect;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;

import com.stuart.stuartapp.BaseActivity;
import com.stuart.stuartapp.R;
import com.stuart.stuartapp.utils.ToastUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FileConnectActivity extends BaseActivity {

  @BindView(R.id.path) EditText path;
  @BindView(R.id.file) EditText file;

  @OnClick(R.id.btn)
  public void start() {
    String paths = path.getText().toString();
    String files = file.getText().toString();
    if (TextUtils.isEmpty(paths)) {
      ToastUtil.showMessage("路径不能空");
      return;
    }
    File f = new File(paths);

    if (!f.exists()) {
      ToastUtil.showMessage("文件不存在");
      return;
    }
    doAction(f);
  }

  private void doAction(File f) {
    File[] list = f.listFiles();
    final Map<String,List<String>> map= new HashMap<>();

    for (File s : list)
    {
      ArrayList<String> l = new ArrayList<>();
      if (s.isDirectory()) {
        String[] list1 = s.list();
        for (String xx : list1)
          l.add(xx);
        Collections.sort(l, new Comparator<String>() {

          @Override
          public int compare(String s, String t1) {
            return s.compareTo(t1);
          }
        });
        map.put(s.getName(), l);
      }
    }




   /* for (String s : l) {
      if (!s.endsWith("ts"))continue;
      String xx = s.substring(0, 4);
      if (map.containsKey(xx)) {
        map.get(xx).add(s);
      } else {
        ArrayList<String> ls = new ArrayList<>();
        ls.add(s);
        map.put(xx, ls);
      }
    }*/

    Log.i("stuart",map.size()+"");
    final Set<String> strings = map.keySet();
    new Thread() {
      @Override
      public void run() {
        super.run();
        for (String s :strings) {
          ToastUtil.showMessage("开始"+ s);
          connect(s,map.get(s));
          try {
            sleep(10000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }

      }
    }.start();


  }

  private static final String OUT_PATH = "sdcard/stuart/video";

  private static final String EXTERNAL_STORAGE_PERMISSION = "android.permission.WRITE_EXTERNAL_STORAGE";
  private static final String EXTERNAL_STORAGE_PERMISSION_ = "android.permission.READ_EXTERNAL_STORAGE";
  @RequiresApi(api = Build.VERSION_CODES.M)
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_file_connect);

    ButterKnife.bind(this);
    //com.flowertv.files.download/video
//    path.setText("/sdcard/Android/data/com.flowertv/files/download/video/");
    path.setText("/sdcard/.MovieFans/Cache/");

    if(checkCallingOrSelfPermission(EXTERNAL_STORAGE_PERMISSION) != PackageManager.PERMISSION_GRANTED) {
      requestPermissions(new String[]{EXTERNAL_STORAGE_PERMISSION},12);
    }

    if(checkCallingOrSelfPermission(EXTERNAL_STORAGE_PERMISSION_) != PackageManager.PERMISSION_GRANTED) {
      requestPermissions(new String[]{EXTERNAL_STORAGE_PERMISSION_},13);
    }
    File out = new File(OUT_PATH);
    out.mkdirs();
  }

  private void connect(final String name, final List<String> list) {
    new Thread(){
      @Override
      public void run() {
        File file = new File(OUT_PATH, name+".ts");
        Log.i("stuart", " file " + file.getAbsolutePath());
        if (file.exists()) file.delete();
        FileOutputStream outputStream = null;
        try {
          boolean newFile = file.createNewFile();
          Log.i("stuart", "new "+ newFile );
          outputStream = new FileOutputStream(file, true);
          for (String s: list) {
            File f = new File(path.getText().toString() +"/"+name,s);
            FileInputStream ins = new FileInputStream(f);
            byte[] bt = new byte[8*1024];
            int legth = 0;
            do {
              legth = ins.read(bt);
              if (legth > 0) {
                outputStream.write(bt, 0 , legth);
              }

            } while (legth >0);
            ins.close();
          }
        } catch (IOException e) {
          e.printStackTrace();
          if (outputStream != null) {
            try {
              outputStream.close();
            } catch (IOException e1) {
              e1.printStackTrace();
            }
          }
        } finally {
          if (outputStream != null) {
            try {
              outputStream.close();
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
          Log.i("stuart", " finish " + name);

          ToastUtil.showMessage("完成"+ name);
        }

      }
    }.start();


  }

  @OnClick(R.id.check)
  public void check() {
    new Thread(new Runnable() {
      @Override
      public void run() {
        Log.i("stuart", "start");
        doCheckFile(new File("/sdcard"));
        Log.i("stuart", "end");
      }
    }).start();



  }

  private void doCheckFile(File f) {
    if (f.isDirectory()) {

      File[] files = f.listFiles();
      for (File file : files)
        doCheckFile(file);
    } else
    {
      docheck(f);
    }
  }
  private void docheck(File f) {
    if (f.length() > 1024* 500 && f.getAbsolutePath().startsWith("/sdcard/.")) {
      Log.i("stuart", f.getAbsolutePath() +"  ->  " + f.length());
    }
  }
}
