package sms.layout.themejunky.com.layout_sms_lib.screens.dont;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import java.lang.ref.WeakReference;

import sms.layout.themejunky.com.layout_sms_lib.MainApplication;
import sms.layout.themejunky.com.layout_sms_lib.R;
import sms.layout.themejunky.com.layout_sms_lib.screens.SlaveActivity;
import sms.layout.themejunky.com.layout_sms_lib.threadPool.callables.Main.CallGoogleRedirect;

public class DontActivity extends SlaveActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // getSupportActionBar().hide();

        setContentView(R.layout.activity_dont_sms);

        fetchViewAndInit();
    }

    @Override
    public void onResume() {
        super.onResume();
        mGAE = ((MainApplication) getApplication()).mGAEA;
        mGAE.getEvents(R.string.analytics_screens, DontActivity.class, "");
    }

    private void fetchViewAndInit() {
        findViewById(R.id.buttonDownload).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonDownload) {
            ((MainApplication) getApplication()).addToPool(new CallGoogleRedirect(new WeakReference<Activity>(this), getResources().getString(R.string.mother_packagename), false, new WeakReference<>(mGAE)));
            mGAE.getEvents(R.string.analytics_event, "Don't leave", "Clicks on Download Mother");
            mGAE.getEvents(R.string.analytics_clicks, DontActivity.class, "Button : \"Download Icon Pack\"");
        }
    }
}