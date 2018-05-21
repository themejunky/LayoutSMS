package sms.layout.themejunky.com.layout_sms_lib.threadPool.callables.Main;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v4.content.SharedPreferencesCompat;

import java.lang.ref.WeakReference;
import java.util.concurrent.Callable;

import module.themejunky.com.tj_gae.Module_GoogleAnalyticsEvents;
import sms.layout.themejunky.com.layout_sms_lib.R;
import sms.layout.themejunky.com.layout_sms_lib.threadPool.callables.ThreadInfo;

import static android.content.Context.MODE_PRIVATE;

/**
 * Verify if is the first Install or not; If yes Google Event is send for recording
 * Also start the thread that collects Component Info
 */

public class CallTrackFirstInstall implements Callable<ThreadInfo> {

    private WeakReference<Activity> activity;
    private WeakReference<Module_GoogleAnalyticsEvents> mGAE;

    /** Constructor
     *
     * @param activity - weak referance of activity
     * @param mGAE - weak referance of Google Analytics Instance
     */
    public CallTrackFirstInstall(WeakReference<Activity> activity, WeakReference<Module_GoogleAnalyticsEvents> mGAE) {
        this.activity = activity;
        this.mGAE = mGAE;
    }

    @Override
    public ThreadInfo call() {

        mGAE.get().getEvents(activity.get().getResources().getString(R.string.analytics_thread_pool),"CallTrackFirstInstall","Called");

        if (activity!=null && mGAE!=null) {

            SharedPreferences sharedPref = activity.get().getPreferences(MODE_PRIVATE);
            Boolean wasInstalled = sharedPref.getBoolean("wasInstalled", false);

            if (!wasInstalled)
            {
                final SharedPreferences.Editor edit = sharedPref.edit();
                edit.putBoolean("wasInstalled", true);
                SharedPreferencesCompat.EditorCompat.getInstance().apply(edit);

                Intent intent = activity.get().getPackageManager().getLaunchIntentForPackage(activity.get().getApplicationContext().getString(R.string.mother_packagename));
                if (intent != null) {
                    mGAE.get().getEvents(activity.get().getResources().getString(R.string.analytics_infos),"First Install - with mother", activity.get().getPackageName());
                    mGAE.get().getEvents(activity.get().getResources().getString(R.string.analytics_infos),"First Install - with mother", "First Install - with mother");
                }else {
                    mGAE.get().getEvents(activity.get().getResources().getString(R.string.analytics_infos),"First Install - with mother", "First Install - without mother");
                    mGAE.get().getEvents(activity.get().getResources().getString(R.string.analytics_infos),"First Install - without mother", activity.get().getPackageName());
                }
               // ((MainApplication) activity.get().getApplication()).addToPool(new CallSendApplicationsInfo(activity,mGAE));
            }
        } else {
            mGAE.get().getEvents(activity.get().getResources().getString(R.string.analytics_thread_pool),"CallTrackFirstInstall","At least 1 weak reference null");
        }

        mGAE.get().getEvents(activity.get().getResources().getString(R.string.analytics_thread_pool),"CallTrackFirstInstall","Finished successfully");

        return null;
    }
}
