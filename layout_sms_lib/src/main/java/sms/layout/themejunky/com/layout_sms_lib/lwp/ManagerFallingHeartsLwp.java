package sms.layout.themejunky.com.layout_sms_lib.lwp;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.widget.Toast;

import sms.layout.themejunky.com.layout_sms_lib.R;

public class ManagerFallingHeartsLwp {

    private final Activity activity;

    public ManagerFallingHeartsLwp(Activity activity){
        this.activity = activity;

    }

    public void set() {
        if (Build.VERSION.SDK_INT >= 16) {
            Intent intent = new Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);
            intent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT,
                    new ComponentName(activity, sms.layout.themejunky.com.layout_sms_lib.lwp.WaterDropMain.class));
            activity.startActivity(intent);
        } else {
            Toast.makeText(
                    activity,
                    "Choose \'"
                            + activity
                            .getString(R.string.app_name)
                            + "\' in the list to start the Live Wallpaper.",
                    Toast.LENGTH_SHORT).show();
            Intent var6 = new Intent();
            var6.setAction("android.service.wallpaper.LIVE_WALLPAPER_CHOOSER");
            activity.startActivity(var6);
        }
    }

}
