package sms.layout.themejunky.com.layout_sms_lib;

import android.content.Context;
import android.content.Intent;

public class ManagerOnboarding {
    private final Context mContext;
    public static Class classRedirected;

    public ManagerOnboarding(Context mContext) {
        this.mContext = mContext;
    }

    public static void initLayout(Context mContext, Class redirectTo){;
        classRedirected = redirectTo;
        Intent Intent = new Intent(mContext, sms.layout.themejunky.com.layout_sms_lib.screens.main.MainActivity.class);
        mContext.startActivity(Intent);
    }

}
