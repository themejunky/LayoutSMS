package sms.layout.themejunky.com.layout_sms_lib;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ManagerOnboarding {
    private static boolean status = false;
    private final Context mContext;

    public ManagerOnboarding(Context mContext) {
        this.mContext = mContext;
    }

    public static void setStatus(Context mContext, boolean isRating){
        status = isRating;
        Log.d("testing","setStatus "+status);
        Intent Intent = new Intent(mContext, sms.layout.themejunky.com.layout_sms_lib.screens.main.MainActivity.class);
        mContext.startActivity(Intent);
    }

    public static boolean getStatus(){
        return status;
    }

}
