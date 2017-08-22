package com.stuart.stuartapp.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.stuart.stuartapp.ILog;

/**
 * Created by stuart on 2017/8/14.
 */

public class FileLogService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private MyConnection c;
    @Override
    public void onCreate() {
        super.onCreate();
        Intent intent = new Intent("com.stuart.service.log");
         c = new MyConnection();
        bindService(intent, c, Service.BIND_AUTO_CREATE);

    }

   private class MyConnection implements ServiceConnection{
       private ILog service;

       @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            this.service = ILog.Stub.asInterface(service);
            try {
               this. service.start();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            service = null;
        }

        private ILog getService() {
            return service;
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (c != null && c.getService() != null) {
            try {
                c.getService().stop();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
