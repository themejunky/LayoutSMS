package sms.layout.themejunky.com.layout_sms_lib.lwp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.content.SharedPreferencesCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import module.themejunky.com.tj_gae.Module_GoogleAnalyticsEvents;
import sms.layout.themejunky.com.layout_sms_lib.MainApplication;

import sms.layout.themejunky.com.layout_sms_lib.R;
import sms.layout.themejunky.com.layout_sms_lib.receiver.MyReceiverVip;
import sms.layout.themejunky.com.layout_sms_lib.utils.ConstantsAction;
import themejunky.module_adsmanager.ModuleAdsManager;

public class SettingsLwp extends AppCompatActivity implements View.OnClickListener,RadioGroup.OnCheckedChangeListener {

    private RadioGroup radioGroupFalling,radioGroupDirection,radioGroupSpeed,radioGroupSize;
    private int objectPreference,directionPreference,sizePreference,speedPreference;
    private RadioButton rbOneObject,rbTwoObjects,rbTop,rbButtom,rbSmall,rbMedium,rbLarge,rbOn,rbOff;
    private ModuleAdsManager adsManager;
    private Module_GoogleAnalyticsEvents mGae;
    private List<String> flowTest = Arrays.asList("display","admob","appnext");
    private ImageView wallpaperImage;
    private TextView text_wallpaper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_lwp);

        mGae =((MainApplication)getApplication()).mGAEA;
        adsManager = ((MainApplication)getApplication()).adsManager;
        init();

        ((MainApplication)getApplication()).startShaking(wallpaperImage);

        switch (objectPreference){
            case 1: rbOneObject.setChecked(true);
            break;
            case 2: rbTwoObjects.setChecked(true);
            break;
        }
        switch (directionPreference){
            case 1: rbTop.setChecked(true);
            break;
            case 2: rbButtom.setChecked(true);
            break;
        }
        switch (sizePreference){
            case 1: rbSmall.setChecked(true);
            break;
            case 2: rbMedium.setChecked(true);
            break;
            case 3: rbLarge.setChecked(true);
            break;
        }
        switch (speedPreference){
            case 1: rbOn.setChecked(true);
            break;
            case 2: rbOff.setChecked(true);
            break;
        }

    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.btn_wallpapers) {
            if (adsManager.getManagerInterstitial().isSomeAdLoaded() && flowTest.size() > 0 && !MyReceiverVip.isVip) {
                adsManager.getManagerInterstitial().showInterstitial(flowTest, ConstantsAction.WALLPAPERS);
            } else {
                mGae.getEvents(R.string.analytics_event, "Buttons", "Click on Wallpapers Button");
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + "com.themejunky.wallpapers")));
            }

        } else if (i == R.id.saveButton) {
            onBackPressed();

        }
    }



    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
        if (i == R.id.nOneObject) {
            editor.putInt(ConstantsLwp.OBJECTS, 1);

        } else if (i == R.id.nTwoObject) {
            editor.putInt(ConstantsLwp.OBJECTS, 2);

        } else if (i == R.id.nTop) {
            editor.putInt(ConstantsLwp.DIRECTION, 1);

        } else if (i == R.id.nButtom) {
            editor.putInt(ConstantsLwp.DIRECTION, 2);

        } else if (i == R.id.nSmall) {
            editor.putInt(ConstantsLwp.SIZE, 1);

        } else if (i == R.id.nMedium) {
            editor.putInt(ConstantsLwp.SIZE, 2);

        } else if (i == R.id.nLarge) {
            editor.putInt(ConstantsLwp.SIZE, 3);

        } else if (i == R.id.nOn) {
            editor.putInt(ConstantsLwp.SPEED, 1);

        } else if (i == R.id.nOff) {
            editor.putInt(ConstantsLwp.SPEED, 2);

        }

        SharedPreferencesCompat.EditorCompat.getInstance().apply(editor);
    }

    public void init(){
        radioGroupFalling = findViewById(R.id.radioGroup1);
        radioGroupDirection = findViewById(R.id.radioGroup2);

        radioGroupSpeed = findViewById(R.id.radioGroup3);
        radioGroupSize = findViewById(R.id.radioGroup4);

        findViewById(R.id.nFalling).setOnClickListener(this);
        findViewById(R.id.nDirection).setOnClickListener(this);
        findViewById(R.id.nSize).setOnClickListener(this);
        findViewById(R.id.nSpeed).setOnClickListener(this);
        findViewById(R.id.saveButton).setOnClickListener(this);

        rbOneObject = findViewById(R.id.nOneObject);
        rbTwoObjects = findViewById(R.id.nTwoObject);
        rbTop = findViewById(R.id.nTop);
        rbButtom = findViewById(R.id.nButtom);
        rbSmall = findViewById(R.id.nSmall);
        rbMedium = findViewById(R.id.nMedium);
        rbLarge = findViewById(R.id.nLarge);
        rbOn = findViewById(R.id.nOn);
        rbOff = findViewById(R.id.nOff);

        radioGroupFalling.setOnCheckedChangeListener(this);
        radioGroupDirection.setOnCheckedChangeListener(this);
        radioGroupSpeed.setOnCheckedChangeListener(this);
        radioGroupSize.setOnCheckedChangeListener(this);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        objectPreference = sp.getInt(ConstantsLwp.OBJECTS,1);
        directionPreference = sp.getInt(ConstantsLwp.DIRECTION,1);
        sizePreference = sp.getInt(ConstantsLwp.SIZE,1);
        speedPreference = sp.getInt(ConstantsLwp.SPEED,1);

        findViewById(R.id.btn_wallpapers).setOnClickListener(this);
        wallpaperImage = findViewById(R.id.btn_wallpaper_image);
        text_wallpaper = findViewById(R.id.text_wallpaper);
        text_wallpaper.setShadowLayer(30, 0, 0, Color.BLACK);


    }
}
