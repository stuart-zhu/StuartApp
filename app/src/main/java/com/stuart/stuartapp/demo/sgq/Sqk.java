package com.stuart.stuartapp.demo.sgq;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;

import com.stuart.stuartapp.BaseActivity;
import com.stuart.stuartapp.utils.ToastUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by stuart on 2017/11/20.
 */

public class Sqk extends BaseActivity{

    private static final String BACK_UP_PATH = "sdcard/backupRecord.txt";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        File file = new File(BACK_UP_PATH);
        if (!file.exists()) {
            finish();
            ToastUtil.showMessage("文件没了");
        } else {
            try {
                FileInputStream fis = new FileInputStream(file);
                byte [] bt = new byte[1024 * 200];
                int length = fis.read(bt);
                byte [] v = Arrays.copyOf(bt, length);

              /*  byte [] b = new byte[v.length -46];
                System.arraycopy(v, 46, b ,0 ,b.length);
              */  Log.i("stuart", " "   + new String(v,"GBK")
                );
              /*  File f = new File("sdcard/6.xml");
                if (f.exists()) f.delete();
                if (!f.exists()) f.createNewFile();
                FileOutputStream fos = new FileOutputStream(f);
                fos.write(b);*/

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
