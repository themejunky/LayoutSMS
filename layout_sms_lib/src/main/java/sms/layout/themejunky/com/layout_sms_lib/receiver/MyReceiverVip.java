package sms.layout.themejunky.com.layout_sms_lib.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Junky2 on 11/24/2017.
 */

public class MyReceiverVip extends BroadcastReceiver {
    public static boolean isVip;
    @Override
    public void onReceive(Context context, Intent intent) {


            isVip = intent.getBooleanExtra("isVipOrNot",false);
        Log.d("isUserVip", "answer received: " + isVip);

        }
}
