package sms.layout.themejunky.com.layout_sms_lib.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Junky2 on 10/20/2017.
 */

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent background = new Intent(context, sms.layout.themejunky.com.layout_sms_lib.services.BackgroundService.class);
        context.startService(background);
    }
}
