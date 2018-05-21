package sms.layout.themejunky.com.layout_sms_lib.screens.wallpapers;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

import sms.layout.themejunky.com.layout_sms_lib.MainApplication;
import sms.layout.themejunky.com.layout_sms_lib.R;
import sms.layout.themejunky.com.layout_sms_lib.retrofit.Service;
import sms.layout.themejunky.com.layout_sms_lib.retrofit.model.ThemeItem;
import sms.layout.themejunky.com.layout_sms_lib.retrofit.model.WallpapersItem;
import sms.layout.themejunky.com.layout_sms_lib.screens.SlaveActivity;
import sms.layout.themejunky.com.layout_sms_lib.screens.rate.RateActivity;
import sms.layout.themejunky.com.layout_sms_lib.threadPool.callables.Wallpapers.setWallpaper;

public class WallpapersActivity extends SlaveActivity implements WallpapersContract.View, View.OnClickListener {

    private WallpapersAdaper adapter;
    private LinearLayout noInternet_container;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();


        fetchViewAndInit();
    }

    @Override
    public void onResume() {
        super.onResume();
        mGAE = ((MainApplication) getApplication()).mGAEA;
        mGAE.getEvents(R.string.analytics_screens,RateActivity.class,"");

        requestDashBoardInformation();
    }

    private void fetchViewAndInit() {
        list = (RecyclerView) findViewById(R.id.list);
        noInternet_container = (LinearLayout) findViewById(R.id.noInternetLayout);

        adapter = new WallpapersAdaper(new WeakReference<Activity>(this), new ArrayList<WallpapersItem>(), this);
        noInternet_container.setOnClickListener(this);
        setList_GridLayoutManager_Vertical(new WeakReference<Activity>(this),list,adapter,2);
    }

    @Override
    public void onWallpaperClick(WallpapersItem wallpaper) {
        //showDialog(wallpaper);
    }

    @Override
    public void showThemeResponse(ThemeItem themeConfig) {
        adapter.setNewData(themeConfig.getWallpaperItems());
    }

    @Override
    public void noInternetConnection() {
        noInternet_container.setVisibility(View.VISIBLE);
    }

    private void showDialog(final WallpapersItem wallpaper) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.wallpaper_question)
                .setPositiveButton(R.string.wallpaper_yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ((MainApplication) getApplication()).addToPool(new setWallpaper(new WeakReference<Activity>(WallpapersActivity.this),wallpaper.getImage()));
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(R.string.wallpaper_no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        builder.create().show();
    }

    private void requestDashBoardInformation(){
        noInternet_container.setVisibility(View.GONE);

        WallpapersPresenter presenter = new WallpapersPresenter(this, Service.getInstance(this));
        presenter.getThemeConfig(this.getPackageName());
    }

    @Override
    public void onClick(View v) {
        if (v.equals(noInternet_container)) {
            requestDashBoardInformation();
        }
    }
}
