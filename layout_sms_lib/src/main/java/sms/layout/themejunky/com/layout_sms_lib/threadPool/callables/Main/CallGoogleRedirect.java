package sms.layout.themejunky.com.layout_sms_lib.threadPool.callables.Main;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import java.lang.ref.WeakReference;
import java.util.concurrent.Callable;

import module.themejunky.com.tj_gae.Module_GoogleAnalyticsEvents;
import sms.layout.themejunky.com.layout_sms_lib.R;
import sms.layout.themejunky.com.layout_sms_lib.threadPool.callables.ThreadInfo;

/**
 * Redirect the user to Google Play
 */
public class CallGoogleRedirect implements Callable<ThreadInfo> {

    private WeakReference<Activity> activity;
    private String packageName;
    private Boolean parseStringForPackageName;
    private WeakReference<Module_GoogleAnalyticsEvents> mGAE;

    /** Constructor
     *
     * @param activity - activity
     * @param packageName - string that can contain packageName or google url from whitch the packageName will be extract
     * @param parseStringForPackageName - if "true" -> packageName should be extracted from prev parameter ; if "false" packageName is in prev parameter
     * @param mGAE - weak referance of Google Analytics Instance
     */
    public CallGoogleRedirect(WeakReference<Activity> activity, String packageName, Boolean parseStringForPackageName, WeakReference<Module_GoogleAnalyticsEvents> mGAE) {
        this.activity = activity;
        this.packageName = packageName;
        this.parseStringForPackageName = parseStringForPackageName;
        this.mGAE = mGAE;
    }

    @Override
    public ThreadInfo call() {

        mGAE.get().getEvents(activity.get().getResources().getString(R.string.analytics_thread_pool),"CallGoogleRedirect","Called");

        if (activity!=null && mGAE!=null) {
            if (!parseStringForPackageName) {
                redirectToMarket(activity.get(), packageName);
            } else {
                redirectToMarket(activity.get(), parseStringForPackageName(packageName));
            }
        }

        mGAE.get().getEvents(activity.get().getResources().getString(R.string.analytics_thread_pool),"CallGoogleRedirect","Finished successfully");

        return null;
    }

    /** Redirect user to google play
     *
     * @param activity - activity
     * @param packageName - exact packageName
     */

    private void redirectToMarket(Activity activity, String packageName) {

            try {
                mGAE.get().getEvents(activity.getResources().getString(R.string.analytics_thread_pool),"CallGoogleRedirect","Market Redirect for Mother Install(1)");
                activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName)));
            } catch (android.content.ActivityNotFoundException anfe) {
                mGAE.get().getEvents(activity.getResources().getString(R.string.analytics_thread_pool),"CallGoogleRedirect","Market Redirect for Mother Install(2)");
                activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + packageName)));
            }


    }

    /** Parse and extract packageName form url provided by dashboard
     *
     * @param url - url provided by dashboard
     * @return exact pakageName
     */
    private String parseStringForPackageName (String url) {
        String packageName;

        url = url.replace("&hl=en", "");
        packageName = (url.split("=").length == 2 ? url.split("=")[1] : "");
        packageName = (packageName.split(" ").length == 2 ? packageName.split(" ")[0] : packageName);

        return packageName;
    }
}
