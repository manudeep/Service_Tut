package com.mdg.servicetut.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by manu on 14/02/16.
 */
public class DataBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("myDebug", "DataBroadcastReceiver : onReceive: ");
    }
}
