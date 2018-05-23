package sms.layout.themejunky.com.layout_sms_lib.threadPool.callables.Main;

import android.app.Activity;

import com.example.tj_notifyrating.Module_NotifyRating;

import java.lang.ref.WeakReference;
import java.util.concurrent.Callable;

import module.themejunky.com.tj_gae.Module_GoogleAnalyticsEvents;
import sms.layout.themejunky.com.layout_sms_lib.R;
import sms.layout.themejunky.com.layout_sms_lib.screens.main.MainActivity;
import sms.layout.themejunky.com.layout_sms_lib.threadPool.callables.ThreadInfo;

/**
 * Sets and start Custom Module Rating
 */
public class CallStartModuleRating implements Callable<ThreadInfo> {

    private WeakReference<Activity> activity;
    private WeakReference<Module_GoogleAnalyticsEvents> mGAE;

    public CallStartModuleRating(WeakReference<Activity> activity, WeakReference<Module_GoogleAnalyticsEvents> mGAE) {
        this.activity = activity;
        this.mGAE = mGAE;
    }

    @Override
    public ThreadInfo call() {

        mGAE.get().getEvents(activity.get().getResources().getString(R.string.analytics_thread_pool), "CallStartModuleRating", "Called");

        if (activity != null && mGAE != null) {
            Module_NotifyRating modulRatingNotify = new Module_NotifyRating(activity.get(), MainActivity.class, "com.themejunky.icontheme")
                    .set_GaePropertyId(activity.get().getResources().getString(R.string.analytics_traker_id), activity.get().getResources().getString(R.string.analytics_lib), activity.get().getResources()
                            .getString(R.string.analytics_lib_crash));
            modulRatingNotify.set_HoursAndRepeateTimes(24 * 60 * 60 * 1000, 2, 15 * 60 * 1000);
            modulRatingNotify.set_TextAndIcon(activity.get().getResources().getString(R.string.notification_title), activity.get().getResources().getString(R.string.notification_subtitle), R.drawable.ic_launcher);
            modulRatingNotify.start();
        } else {
            mGAE.get().getEvents(activity.get().getResources().getString(R.string.analytics_thread_pool), "CallStartModuleRating", "At least 1 weak reference null");
        }

        mGAE.get().getEvents(activity.get().getResources().getString(R.string.analytics_thread_pool), "CallStartModuleRating", "Finished successfully");

        return null;
    }
}
