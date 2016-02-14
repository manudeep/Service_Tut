package com.mdg.servicetut.started_services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by manu on 14/02/16.
 */
public class MyStickyService extends Service {

    private boolean isServiceStopped;

    private int counterToStop = 20;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("myDebug", "MyStickyService : onCreate: ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, final int startId) {
        Log.d("myDebug", "MyStickyService : onStartCommand: intent = " + intent);
        Log.d("myDebug", "MyStickyService : onStartCommand: flags = " + flags);
        Log.d("myDebug", "MyStickyService : onStartCommand: startId = " + startId);

        // special case, when service is restarted by system
        if (intent == null) {

        }

        new Thread(new Runnable() {
            int i = 0;

            @Override
            public void run() {
                for (; ; ) {
                    if (!isServiceStopped) {
                        Log.d("myDebug", "MyStickyService : run: " + i++);
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

        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("myDebug", "MyStickyService : onBind: ");
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("myDebug", "MyStickyService : onDestroy: ");
        isServiceStopped = true;
    }
}
