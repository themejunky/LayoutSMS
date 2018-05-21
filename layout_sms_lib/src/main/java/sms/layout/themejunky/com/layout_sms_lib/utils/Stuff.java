package sms.layout.themejunky.com.layout_sms_lib.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;


public class Stuff {
    public static String getPackageNameFromAllUrl(String url)
    {
        String packageName;

        url = url.replace("&hl=en", "");
        packageName = (url.split("=").length == 2 ? url.split("=")[1] : "");
        packageName = (packageName.split(" ").length == 2 ? packageName.split(" ")[0] : packageName);

        return packageName;
    }

    public static void marketGooglePlayRedirect(Context context, String appPackageName) {
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }
}
