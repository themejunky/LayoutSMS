package sms.layout.themejunky.com.layout_sms_lib.screens.rate;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;

import sms.layout.themejunky.com.layout_sms_lib.MainApplication;
import sms.layout.themejunky.com.layout_sms_lib.R;
import sms.layout.themejunky.com.layout_sms_lib.screens.SlaveActivity;

public class RateActivity extends SlaveActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        mGAE = ((MainApplication) getApplication()).mGAEA;
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id="+getPackageName())));
        mGAE.getEvents(R.string.analytics_event,"Popup Rate","Click on image");
        finish();

    }




}
