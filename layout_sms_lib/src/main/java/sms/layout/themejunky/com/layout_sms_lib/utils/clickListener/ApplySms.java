package sms.layout.themejunky.com.layout_sms_lib.utils.clickListener;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;


import com.example.tj_notifyrating.Module_NotifyRating;

import java.lang.ref.WeakReference;

import module.themejunky.com.tj_gae.Module_GoogleAnalyticsEvents;
import sms.layout.themejunky.com.layout_sms_lib.ManagerOnboarding;
import sms.layout.themejunky.com.layout_sms_lib.R;
import sms.layout.themejunky.com.layout_sms_lib.screens.rate.RateActivity;


public class ApplySms {

    private final Activity activity;
    private final WeakReference<Module_GoogleAnalyticsEvents>  mGae;
    private Intent intent;
    public ProgressDialog mProgressDialog;

    public ApplySms(Activity activity, WeakReference<Module_GoogleAnalyticsEvents> mGae){
        this.mGae = mGae;
        this.activity = activity;
    }

    public void applyTheme() {
        String mainApp = isPackageInstalled("com.jb.gosms.theme.bestthemes.paris") ? "com.jb.gosms.theme.bestthemes.paris" : "com.smsplus.app";
        intent = activity.getPackageManager().getLaunchIntentForPackage(activity.getPackageName().contains("samsung") ? "com.smsplus.samsung.app" : mainApp);

        if (intent != null) {
            /*
            Log.d("testttt","test "+ManagerOnboarding.getStatus());

            if (ManagerOnboarding.getStatus()) {
                Module_NotifyRating notifyRating = new Module_NotifyRating(activity, RateActivity.class, activity.getPackageName());
                notifyRating.set_DebugMode("notifiTest");
                notifyRating.set_HoursAndRepeateTimes((1000 * 60 * 15), 1, (1000 * 60 * 5));
                notifyRating.set_TextAndIcon("Your opinion matters!", "How would you rate our app?", R.drawable.ic_launcher);
                notifyRating.start();
            }
            */

            mProgressDialog = new ProgressDialog(activity);
            mProgressDialog.setMessage("Applying theme...");
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.show();
            new android.os.Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mProgressDialog.dismiss();
                }
            },3000);
            intent.setClassName(activity.getPackageName().contains("samsung") ? "com.smsplus.samsung.app" : mainApp, "com.smsplus.app.activities.MainActivity");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.putExtra("themeId", activity.getResources().getString(R.string.theme_id));
            intent.putExtra("installed", true);
            intent.putExtra("theme_name", activity.getResources().getString(R.string.theme_name));
            activity.startActivity(intent);
        } else {
            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

            dialog.setContentView(R.layout.dialog_go_sms);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            Button btn1 = dialog.findViewById(R.id.btnCancel);
            Button btn2 = dialog.findViewById(R.id.btnDownload);
            TextView tvDesc = (TextView) dialog.findViewById(R.id.tvDesc);
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mGae.get().getEvents(R.string.analytics_event,"Popup no mother","Click on Cancel Button");
                    dialog.cancel();
                }
            });
            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mGae.get().getEvents(R.string.analytics_event,"Popup no mother","Click on Download Button");
                    intent = new Intent(Intent.ACTION_VIEW);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setData(Uri.parse("market://details?id=" + "com.smsplus.app"));
                    activity.startActivity(intent);
                    dialog.cancel();
                }
            });
            dialog.show();
        }

    }

    private boolean isPackageInstalled(String packagename) {
        try {
            activity.getPackageManager().getPackageInfo(packagename, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

}
