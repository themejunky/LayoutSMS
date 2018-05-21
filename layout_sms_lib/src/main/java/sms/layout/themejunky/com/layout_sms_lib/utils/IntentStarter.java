package sms.layout.themejunky.com.layout_sms_lib.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import java.lang.ref.WeakReference;

import sms.layout.themejunky.com.layout_sms_lib.R;
import sms.layout.themejunky.com.layout_sms_lib.screens.dont.DontActivity;
import sms.layout.themejunky.com.layout_sms_lib.screens.moreThemes.MoreActivity;
import sms.layout.themejunky.com.layout_sms_lib.screens.rate.RateActivity;

public class IntentStarter
{
    private WeakReference<Activity> activity;

    public IntentStarter(WeakReference<Activity> activity) {
        this.activity = activity;
    }

    public void redirect(int witchActivityNo) {
        if (activity!=null) {
            switch (witchActivityNo) {
                case 1 : redirectUserTo_MoreThemes(); break;
                case 2 : redirectUserTo_Wallpapers(); break;
                case 3 : redirectUserTo_Rate(); break;
                case 4 : redirectUserTo_Dont(); break;
                default: somethingWentWrong(); break;
            }
        } else {
            somethingWentWrong();
        }
    }

    private void redirectUserTo_MoreThemes() {
        redirect(new Intent(activity.get(), MoreActivity.class));
    }

    private void redirectUserTo_Wallpapers() {
        activity.get().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + "com.themejunky.wallpapers")));
        //redirect(new Intent(activity.get(), WallpapersActivity.class));
    }

    private void redirectUserTo_Rate() {
        redirect(new Intent(activity.get(), RateActivity.class));
    }

    private void redirectUserTo_Dont() {
        redirect(new Intent(activity.get(), DontActivity.class));
    }


    private void redirect(Intent intent) {
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        activity.get().startActivity(intent);
    }


    private void somethingWentWrong() {
        Toast.makeText(activity.get(),activity.get().getResources().getString(R.string.something_wrong,"1"), Toast.LENGTH_SHORT).show();
    }
}
