package com.mdg.servicetut.started_services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by manu on 14/02/16.
 */
public class MyNonStickyService extends Service {

    private boolean isServiceStopped;

    private int counterToStop = 20;


//    private ServiceHandler mServiceHandler = new ServiceHandler();

//    private final class ServiceHandler extends Handler {
//        public ServiceHandler(Looper looper) {
//            super(looper);
//        }
//
//        @Override
//        public void handleMessage(Message msg) {
//            Log.d("myDebug", "MyNonStickyService : handleMessage: ");
//            //... performing some time-consuming operation
//            Bundle bundle = msg.getData();
//            PendingIntent receiver = bundle.getParcelable("receiver");
//            // Perform the operation associated with PendingIntent
//            try {
//                //you can attach data from the operation in the intent.
//                Intent intent = new Intent();
//                Bundle b = new Bundle();
//                //b.putString("key", value);
//                intent.putExtras(b);
//                receiver.send(getApplicationContext(), status, intent);
//            } catch (PendingIntent.CanceledException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("myDebug", "MyNonStickyService : onCreate: ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, final int startId) {
        Log.d("myDebug", "MyNonStickyService : onStartCommand: intent = " + intent);
        Log.d("myDebug", "MyNonStickyService : onStartCommand: flags = " + flags);
        Log.d("myDebug", "MyNonStickyService : onStartCommand: startId = " + startId);

//        msg.setData(bundle);
//        mServiceHandler.sendMessage(msg);

        new Thread(new Runnable() {
            int i = 0;

            @Override
            public void run() {
                for (; ; ) {
                    if (!isServiceStopped) {
                        Log.d("myDebug", "MyNonStickyService : run: " + i++);
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

        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("myDebug", "MyNonStickyService : onBind: ");
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("myDebug", "MyNonStickyService : onDestroy: ");
        isServiceStopped = true;
    }
}