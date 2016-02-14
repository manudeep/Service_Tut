package com.mdg.servicetut;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.mdg.servicetut.started_services.MyIntentService;
import com.mdg.servicetut.started_services.MyNonStickyService;
import com.mdg.servicetut.started_services.MyStickyRedeliverService;
import com.mdg.servicetut.started_services.MyStickyService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:

//                //this is the intent that will be broadcasted by service.
//                Intent broadcastReceiverIntent = new Intent(this, DataBroadcastReceiver.class);
//
//                //create pending intent for broadcasting the DataBroadcastReceiver
//                PendingIntent pi = PendingIntent.getBroadcast(this, 0, broadcastReceiverIntent, 0);
//                Bundle bundle = new Bundle();
//                bundle.putParcelable("receiver", pi);
//
//                //we want to start our service (for handling our time-consuming operation)
//                Intent serviceIntent = new Intent(this, MyStickyService.class);
//                serviceIntent.putExtras(bundle);
//                startService(serviceIntent);

                startService(new Intent(this, MyStickyService.class));
                break;
            case R.id.btn2:
                startService(new Intent(this, MyNonStickyService.class));
                break;
            case R.id.btn3:
                startService(new Intent(this, MyStickyRedeliverService.class));
                break;
            case R.id.btn4:
                startService(new Intent(this, MyIntentService.class));
                break;

            case R.id.btn5:
                Log.d("myDebug", "MainActivity : onClick: " + stopService(new Intent(this, MyStickyService.class)));
                break;
            case R.id.btn6:
                Log.d("myDebug", "MainActivity : onClick: " + stopService(new Intent(this, MyNonStickyService.class)));
                break;
            case R.id.btn7:
                Log.d("myDebug", "MainActivity : onClick: " + stopService(new Intent(this, MyStickyRedeliverService.class)));
                break;
            case R.id.btn8:
                Log.d("myDebug", "MainActivity : onClick: " + stopService(new Intent(this, MyIntentService.class)));
                break;
            case R.id.btn9:
                startActivity(new Intent(this, SecondActivity.class));
                break;
        }
    }
}