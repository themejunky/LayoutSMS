package sms.layout.themejunky.com.layout_sms_lib.threadPool.callables.More;

import android.app.Activity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import module.themejunky.com.tj_gae.Module_GoogleAnalyticsEvents;
import sms.layout.themejunky.com.layout_sms_lib.R;
import sms.layout.themejunky.com.layout_sms_lib.retrofit.model.InternalAds;
import sms.layout.themejunky.com.layout_sms_lib.retrofit.model.ThemeItem;
import sms.layout.themejunky.com.layout_sms_lib.screens.moreThemes.MoreActivity;
import sms.layout.themejunky.com.layout_sms_lib.threadPool.callables.ThreadInfo;

public class CallMore implements Callable<ThreadInfo> {

    private WeakReference<Activity> activity;
    private WeakReference<Module_GoogleAnalyticsEvents> mGAE;
    private ThemeItem themeConfig;
    /** Constructor
     *
     * @param activity - weak referance of activity
     * @param mGAE - weak referance of Google Analytics Instance
     */
    public CallMore(WeakReference<Activity> activity, WeakReference<Module_GoogleAnalyticsEvents> mGAE, ThemeItem themeConfig) {
        this.activity = activity;
        this.mGAE = mGAE;
        this.themeConfig = themeConfig;
    }

    @Override
    public ThreadInfo call() {

        mGAE.get().getEvents(activity.get().getResources().getString(R.string.analytics_thread_pool),"CallMore","Called");

        if (activity!=null && mGAE!=null && themeConfig!=null) {
            final List<InternalAds> model = new ArrayList<>();
            int size = themeConfig.getInternalAds().size();
            for (int i=0;i<size;i++) {
                InternalAds item = themeConfig.getInternalAds().get(i);
                if (item.getText().contains("type_promo")) {
                    model.add(item);
                }
            }

            activity.get().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                 //   Toast.makeText(activity.get(),"Sir?",Toast.LENGTH_SHORT).show();
                    ((MoreActivity) activity.get()).adapter.setNewData(model);
                }
            });


        } else {
            mGAE.get().getEvents(activity.get().getResources().getString(R.string.analytics_thread_pool),"CallMore","At least 1 weak reference null");
        }

        mGAE.get().getEvents(activity.get().getResources().getString(R.string.analytics_thread_pool),"CallMore","Finished successfully");

        return null;
    }
}
