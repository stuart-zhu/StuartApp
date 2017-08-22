package com.stuart.stuartapp.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import android.util.Log;

import com.stuart.stuartapp.ILog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by stuart on 2017/8/14.
 */

public class StuartLogService extends Service {

    private Process process;
    private ILog.Stub mLog = new ILog.Stub() {
        @Override
        public void start() throws RemoteException {
            new Thread() {
                @Override
                public void run() {

                    FileOutputStream fos = null;

                        File f = new File("sdcard/" + 12345 +".txt");
                        if (f.exists()) f.delete();
                        if (!f.exists()) try {
                            f.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            fos = new FileOutputStream(f);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }



                    try {
                        process = Runtime.getRuntime().exec("logcat -v threadtime");

                        BufferedReader bufferedReader = new BufferedReader(
                                new InputStreamReader(process.getInputStream()));

                        StringBuilder log=new StringBuilder();
                        String line;
                        while ((line = bufferedReader.readLine()) != null) {
                            log.append(line);
//                            mHandler.obtainMessage(0, line).sendToTarget();
                            if (fos != null) {
                                fos.write(line.getBytes());
                                fos.write("\n".getBytes());
                            }

                        }


                    } catch (IOException e) {
                    }


                }
            }.start();


        }

        @Override
        public void stop() throws RemoteException {

            if (process != null) {
                process.destroy();
                process = null;
            }
        }
    };
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mLog;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("stuart", "LogLogLogLogLogLog -- start");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("stuart", "LogLogLogLogLogLog -- stop");
    }
}
