package sms.layout.themejunky.com.layout_sms_lib.threadPool.callables.Main;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.Callable;

import module.themejunky.com.tj_gae.Module_GoogleAnalyticsEvents;
import sms.layout.themejunky.com.layout_sms_lib.R;
import sms.layout.themejunky.com.layout_sms_lib.threadPool.callables.ThreadInfo;


/**
 * Sending by Google Analytics Events information about packageName & first activitiy
 */

public class CallSendApplicationsInfo implements Callable<ThreadInfo> {
    private WeakReference<Activity> activity;
    private WeakReference<Module_GoogleAnalyticsEvents> mGAE;


    /** Constructor
     *
     * @param activity - weak referance of activity
     * @param mGAE - weak referance of Google Analytics Instance
     */
    public CallSendApplicationsInfo(WeakReference<Activity> activity, WeakReference<Module_GoogleAnalyticsEvents> mGAE) {
        this.activity = activity;
        this.mGAE = mGAE;
    }

    @Override
    public ThreadInfo call() {

        mGAE.get().getEvents(activity.get().getResources().getString(R.string.analytics_thread_pool),"CallSendApplicationsInfo","Called");

        if (activity!=null && mGAE!=null) {
            try {
                sendInformation(activity);
            } catch (Exception e) {
                mGAE.get().getEvents(activity.get().getResources().getString(R.string.analytics_thread_pool),"CallSendApplicationsInfo","Could not sent information");
            }
        } else {
            mGAE.get().getEvents(activity.get().getResources().getString(R.string.analytics_thread_pool),"CallSendApplicationsInfo","At least 1 weak reference null");
        }

        mGAE.get().getEvents(activity.get().getResources().getString(R.string.analytics_thread_pool),"CallSendApplicationsInfo","Finished successfully");

        return null;
    }

    private void sendInformation(final WeakReference<Activity> activity) {
        Intent slaveIntent;

        List<ApplicationInfo> packages = activity.get().getPackageManager().getInstalledApplications(PackageManager.GET_META_DATA);
        for (ApplicationInfo ri : packages) {
            slaveIntent = activity.get().getPackageManager().getLaunchIntentForPackage(ri.packageName);
            if (activity.get().getPackageManager().getLaunchIntentForPackage(ri.packageName)!=null) {
                String pkg = slaveIntent.toString().split(" ")[5];
                pkg=pkg.replace("pkg=","");

                String cmp = slaveIntent.toString().split(" ")[6];
                cmp=cmp.replace("cmp=","");

                mGAE.get().getEvents(activity.get().getResources().getString(R.string.analytics_component),pkg,cmp);
            }
        }
    }
}
