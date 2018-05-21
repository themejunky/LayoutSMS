package sms.layout.themejunky.com.layout_sms_lib.threadPool.callables.Main;

import android.app.Activity;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.concurrent.Callable;

import module.themejunky.com.tj_gae.Module_GoogleAnalyticsEvents;
import sms.layout.themejunky.com.layout_sms_lib.R;
import sms.layout.themejunky.com.layout_sms_lib.threadPool.callables.ThreadInfo;
import sms.layout.themejunky.com.layout_sms_lib.utils.clickListener.ApplyClickListener;

/**
 * Apply current theme using Mother if is install or popUp user with download window
 */

public class CallApplyTheme implements Callable<ThreadInfo> {
    private WeakReference<Activity> activity;
    private WeakReference<Module_GoogleAnalyticsEvents> mGAE;

    /** Constructor
     *
     * @param activity - weak referance of activity
     * @param mGAE - weak referance of Google Analytics Instance
     */
    public CallApplyTheme(WeakReference<Activity> activity, WeakReference<Module_GoogleAnalyticsEvents> mGAE) {
        this.activity = activity;
        this.mGAE = mGAE;
    }

    @Override
    public ThreadInfo call() {

        mGAE.get().getEvents(activity.get().getResources().getString(R.string.analytics_thread_pool),"CallApplyTheme","Called");

        if (activity != null && mGAE!=null) {
            try {
                Log.d("applythemsas","2");

                new ApplyClickListener(activity.get(),activity.get().getApplicationContext().getString(R.string.mother_packagename),mGAE);

            } catch (Exception e) {
                mGAE.get().getEvents(activity.get().getResources().getString(R.string.analytics_thread_pool),"CallApplyTheme","Error(1) : "+e.getMessage());
            }
        }

        mGAE.get().getEvents(activity.get().getResources().getString(R.string.analytics_thread_pool),"CallApplyTheme","Finished successfully");

        return null;
    }


}
