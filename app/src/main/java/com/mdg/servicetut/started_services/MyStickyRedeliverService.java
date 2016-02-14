package com.mdg.servicetut.started_services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by manu on 14/02/16.
 */
public class MyStickyRedeliverService extends Service {

    private boolean isServiceStopped;

    private int counterToStop = 20;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("myDebug", "MyStickyRedeliverService : onCreate: ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, final int startId) {

        Log.d("myDebug", "MyStickyRedeliverService : onStartCommand: intent = " + intent);
        Log.d("myDebug", "MyStickyRedeliverService : onStartCommand: flags = " + flags);
        Log.d("myDebug", "MyStickyRedeliverService : onStartCommand: startId = " + startId);

        new Thread(new Runnable() {
            int i = 0;

            @Override
            public void run() {
                for (; ; ) {
                    if (!isServiceStopped) {
                        Log.d("myDebug", "MyStickyRedeliverService : run: " + i++);
                        if (i == counterToStop) {
                            stopSelf(startId);
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Thread.interrupted();
                    }
                }
            }
        }).start();

        return START_REDELIVER_INTENT;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("myDebug", "MyStickyRedeliverService : onBind: ");
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("myDebug", "MyStickyRedeliverService : onDestroy: ");
        isServiceStopped = true;
    }
}
