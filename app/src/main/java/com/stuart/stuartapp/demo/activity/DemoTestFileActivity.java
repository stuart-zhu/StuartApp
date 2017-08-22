package com.stuart.stuartapp.demo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.stuart.stuartapp.BaseActivity;
import com.stuart.stuartapp.ndk.NdkManager;
import com.stuart.stuartapp.utils.DES;
import com.stuart.stuartapp.utils.LogUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by stuart on 2017/7/28.
 */

public class DemoTestFileActivity extends BaseActivity {

    private static final String TAG = "DemoTestFileActivity";

    public static final String TEST_FILE_SEND = "sdcard/send123.txt";
    private static final String TEST_FILE_PATH = "sdcard/send123333333333333.txt";
    private static final String TEST_FILE_DEC_PATH = "sdcard/recorgdec.txt";
    private FileInputStream fis;

    private FileOutputStream fos;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        File f = new File(TEST_FILE_PATH);
        /*if (!f.exists()) {

            LogUtil.i(TAG, "onCreate", "File " + f.getAbsolutePath() +" is not exist");
            finish();
            return;
        }*/

        try {
            if (!f.exists()) f.createNewFile();
            fis = new FileInputStream(f);
            File fi = new File(TEST_FILE_DEC_PATH);
            if (fi.exists()) fi.delete();
            if (!fi.exists()) fi.createNewFile();
            fos = new FileOutputStream(fi, true);
            new MyThread().start();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private class TestFileThread extends  Thread {
        @Override
        public void run() {


            byte[] btt = new byte[1080];
            if (fis != null) {
                boolean isReady = true;
                while(isReady) {

                    try {
                        int length = fis.read(btt);
                        if (length > 0 ) {
                            byte[] bytes = Arrays.copyOf(btt, length);
                            LogUtil.i(TAG, " TestFileThread run", length + "," + Arrays.toString(bytes));


                            List<Byte> blist = new ArrayList<>();
                            for (int i = 0; i < length; i++) {
                                blist.add(bytes[i]);
                            }
                            while (blist.size() >= 21) {


                                if (blist.get(0) == (byte) 0xAA) {
                                    String int2SixString2 = int2SixString2(
                                            blist.get(1), 8);
                                    int type = (byte) Integer.parseInt(
                                            int2SixString2.substring(0, 3), 2);
                                    length = Integer.parseInt(
                                            int2SixString2.substring(3, 8), 2);

                                    final byte[] bs = new byte[3 + length + 2];
                                    byte x = (byte) 0xAA;
                                    for (int j = 0; j < bs.length; j++) {
                                        bs[j] = blist.get(j);
                                        if (j > 0 && j < bs.length - 1) {
                                            x = (byte) (x ^ bs[j]);
                                        }
                                    }
                                    // 验证通过
                                    if (x == bs[bs.length - 1]) {

                                        final byte[] data = new byte[length];

                                        System.arraycopy(bs, 2, data, 0,
                                                data.length);


                                        for (int i = 0; i < 21; i++)
                                            blist.remove(0);

                                        byte[] dd = DES.decrypt(data);

                                        byte[] bt = new byte[11];
                                        System.arraycopy(dd, 0, bt, 0, dd.length);
                                        System.arraycopy(bs, bs.length - 3, bt,
                                                bt.length - 3, 2);
                                        byte[] temp_read_buffer = NdkManager
                                                .getNdkManager().decodeData(bt);

                                        if (fos != null) fos.write(temp_read_buffer);
                                        // if (fos2 != null) fos2.write(temp_read_buffer);
                                        int read_ret = temp_read_buffer.length;


                                    } else {
                                        blist.remove(0);
                                    }
                                } else {
                                    blist.remove(0);
                                }

                            }
                        } else {
                            isReady = false;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static String int2SixString2(int a, int length) {
        String b = Integer.toBinaryString(a);
        if (b.length() < length) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < length - b.length(); i++) {
                sb.append("0");
            }
            sb.append(b);
            b = sb.toString();
        } else if (b.length() > length) {
            b = b.substring(b.length() - length);
        }
        return b;
    }



    public class MyThread extends Thread  {
        @Override
        public void run() {
            try {
                FileInputStream is = new FileInputStream(new File(TEST_FILE_SEND));
                FileOutputStream fos = new FileOutputStream(new File(TEST_FILE_PATH), true);

                byte[] buffer1 = new byte[1080 * 10];
                boolean isReady = true;
                while(isReady) {


                    int xx = is.read(buffer1);
                    if (xx > 0) {
                        List<byte[]> subByet = NdkManager.subByet(Arrays.copyOf(buffer1, xx),
                                1080);
                        for (byte[] b : subByet) {


                            List<byte[]> ulaw_bufs = NdkManager
                                    .getNdkManager().encodeData(b); // melps压缩

                            try {
                                for (byte[] ulaw_buf : ulaw_bufs) {

                                    byte[] copyOf = Arrays.copyOf(ulaw_buf,
                                            8); // 取出前八位

                                    long tt = System.currentTimeMillis();
                                    byte[] data = DES.encrypt(copyOf);


                                    byte[] d = new byte[3 + data.length + 2];
                                    d[0] = (byte) 0xAA;
                                    int type = 1;

                                    String str = int2SixString2(type, 3)
                                            + int2SixString2(data.length, 5);
                                    int strI = Integer.valueOf(str, 2);
                                    d[1] = (byte) strI;

                                    System.arraycopy(data, 0, d, 2,
                                            data.length);
                                    System.arraycopy(ulaw_buf,
                                            ulaw_buf.length - 2, d,
                                            d.length - 3, 2);
                                    byte x = d[0];
                                    for (int i = 1; i < d.length - 1; i++) {
                                        x = (byte) (x ^ d[i]);
                                    }
                                    d[d.length - 1] = x;

                                    if (fos != null) fos.write(d);
                                }
                            } catch (Exception e) {

                            }
                        }
                    } else {
                        new TestFileThread().start();
                        isReady = false;
                    }
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }}}
