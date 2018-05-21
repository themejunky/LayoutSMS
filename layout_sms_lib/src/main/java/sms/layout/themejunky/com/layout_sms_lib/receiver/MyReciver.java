package sms.layout.themejunky.com.layout_sms_lib.receiver;

import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


public class MyReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intents) {

        String witchLauncher = intents.getStringExtra("witchLauncher");

        Toast.makeText(context,""+context.getPackageName()+"/"+witchLauncher, Toast.LENGTH_LONG).show();

        Intent nextApply = context.getPackageManager().getLaunchIntentForPackage("com.teslacoilsw.launcher");
        Intent intent;
        if (nextApply != null) {
            try {

                intent = new Intent("com.teslacoilsw.launcher.APPLY_ICON_THEME");
                intent.setPackage("com.teslacoilsw.launcher");
                intent.putExtra("com.teslacoilsw.launcher.extra.ICON_THEME_TYPE", "GO");

                if (context.getApplicationContext().getPackageName() != null) {
                    intent.putExtra("com.teslacoilsw.launcher.extra.ICON_THEME_PACKAGE", ""+context.getApplicationContext().getPackageName()); }
                context.sendBroadcast(intent);
                context.startActivity(intent);

            } catch (ActivityNotFoundException e) {
                Toast.makeText(context,"NASPA 1", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context,"NASPA 2", Toast.LENGTH_SHORT).show();
        }
    }


}
