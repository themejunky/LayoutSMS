package sms.layout.themejunky.com.layout_sms_lib.threadPool.callables.Main;

import android.app.Activity;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.Callable;

import module.themejunky.com.tj_gae.Module_GoogleAnalyticsEvents;
import sms.layout.themejunky.com.layout_sms_lib.R;
import sms.layout.themejunky.com.layout_sms_lib.retrofit.model.InternalAds;
import sms.layout.themejunky.com.layout_sms_lib.screens.main.MainActivity;
import sms.layout.themejunky.com.layout_sms_lib.threadPool.callables.ThreadInfo;

/**
 * Parse the list of banners recived from Dashboard and send each banner to specific list
 */

public class CallParsBanners implements Callable<ThreadInfo>
{
    private WeakReference<Activity> activity;
    private List<InternalAds> bannerList;
    private MainActivity instActivity;
    private WeakReference<Module_GoogleAnalyticsEvents> mGAE;

    /** Constructor
     *
     * @param activity - weak referance of activity
     * @param bannerList - list of all banner to be sorted :D
     * @param mGAE - weak referance of Google Analytics Instance
     */
    public CallParsBanners(WeakReference<Activity> activity, List<InternalAds> bannerList, WeakReference<Module_GoogleAnalyticsEvents> mGAE) {
        this.activity = activity;
        this.bannerList = bannerList;
        this.mGAE = mGAE;
    }

    @Override
    public ThreadInfo call() {

        mGAE.get().getEvents(activity.get().getResources().getString(R.string.analytics_thread_pool),"CallParsBanners","Called");

        if (activity.get() !=null && activity.get() instanceof MainActivity && mGAE != null) {

            instActivity = ((MainActivity) activity.get());

            instActivity.bannerList.clear();
            instActivity.bestList.clear();
            instActivity.getMoreList.clear();
            int listSize = bannerList.size();
            for (int i = 0; i < listSize; i++) {
                InternalAds item = bannerList.get(i);

                if (item.getText().contains("type_banner")) {
                    instActivity.bannerList.add(item);
                } else if (item.getText().contains("type_best")) {
                    instActivity.bestList.add(item);
                } else if (item.getText().contains("type_promo")) {
                    instActivity.getMoreList.add(item);
                }
            }
            instActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    instActivity.startCarousel();
                }
            });
        } else {
            mGAE.get().getEvents(activity.get().getResources().getString(R.string.analytics_thread_pool),"CallParsBanners","At least 1 weak reference null");
        }

        mGAE.get().getEvents(activity.get().getResources().getString(R.string.analytics_thread_pool),"CallParsBanners","Finished successfully");


        return null;
    }
}
