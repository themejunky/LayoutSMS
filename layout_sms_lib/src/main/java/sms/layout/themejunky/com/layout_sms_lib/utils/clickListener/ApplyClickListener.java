package sms.layout.themejunky.com.layout_sms_lib.utils.clickListener;

import android.app.Activity;
import android.util.Log;

import java.lang.ref.WeakReference;

import module.themejunky.com.tj_gae.Module_GoogleAnalyticsEvents;


public class ApplyClickListener  {


    private final Activity activity;
    private final String motherType;
    private final WeakReference<Module_GoogleAnalyticsEvents> mGAE;


    public ApplyClickListener(final Activity activity, String motherType, final WeakReference<Module_GoogleAnalyticsEvents> mGAE){
        this.activity = activity;
        this.motherType = motherType;
        this.mGAE = mGAE;

        switch (motherType){

            case "com.smsplus.app":
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("dsasd","icon");
                        new sms.layout.themejunky.com.layout_sms_lib.utils.clickListener.ApplySms(activity,mGAE).applyTheme();
                    }
                });
                break;
        }

    }

}
