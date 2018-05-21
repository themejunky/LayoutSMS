package sms.layout.themejunky.com.layout_sms_lib.screens.moreThemes;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;


import java.lang.ref.WeakReference;
import java.util.ArrayList;

import sms.layout.themejunky.com.layout_sms_lib.MainApplication;
import sms.layout.themejunky.com.layout_sms_lib.R;
import sms.layout.themejunky.com.layout_sms_lib.retrofit.Service;
import sms.layout.themejunky.com.layout_sms_lib.retrofit.model.InternalAds;
import sms.layout.themejunky.com.layout_sms_lib.retrofit.model.ThemeItem;
import sms.layout.themejunky.com.layout_sms_lib.screens.SlaveActivity;
import sms.layout.themejunky.com.layout_sms_lib.threadPool.callables.Main.CallGoogleRedirect;
import sms.layout.themejunky.com.layout_sms_lib.threadPool.callables.More.CallMore;

public class MoreActivity extends SlaveActivity implements MoreContract.View, View.OnClickListener {
    public MoreAdapter adapter;
    private LinearLayout noInternet_container;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // getSupportActionBar().hide();

        setContentView(R.layout.activity_wallpapers_sms);

        fetchViewAndInit();
    }

    @Override
    public void onResume() {
        super.onResume();
        mGAE = ((MainApplication) getApplication()).mGAEA;
        mGAE.getEvents(R.string.analytics_screens, MoreActivity.class, "");

        requestDashBoardInformation();
    }

    private void fetchViewAndInit() {

        list = (RecyclerView) findViewById(R.id.list);
        noInternet_container = (LinearLayout) findViewById(R.id.noInternetLayout);


        adapter = new MoreAdapter(new WeakReference<Activity>(this), new ArrayList<InternalAds>(), this);

        noInternet_container.setOnClickListener(this);

        setList_GridLayoutManager_Vertical(new WeakReference<Activity>(this), list, adapter, 1);
    }

    @Override
    public void onMoreClick(InternalAds item) {
        if (item != null) {
            String name = item.getStoreUrl().substring(item.getStoreUrl().lastIndexOf("=") + 1);
            mGAE.getEvents(R.string.analytics_event, "MoreApp", "Click on " + name);
            ((MainApplication) getApplication()).addToPool(new CallGoogleRedirect(new WeakReference<Activity>(this), item.getStoreUrl(), true, new WeakReference<>(mGAE)));
        }

    }

    @Override
    public void showMoreResponse(ThemeItem themeConfig) {

        if (themeConfig != null) {
            ((MainApplication) getApplication()).addToPool(new CallMore(new WeakReference<Activity>(this), new WeakReference<>(mGAE), themeConfig));
        }
    }

    @Override
    public void noInternetConnection() {
        noInternet_container.setVisibility(View.VISIBLE);
    }

    private void requestDashBoardInformation() {
        noInternet_container.setVisibility(View.GONE);

        MorePresenter presenter = new MorePresenter(this, Service.getInstance(this));
        presenter.getThemeConfig(this.getPackageName());
    }

    @Override
    public void onClick(View v) {
        if (v.equals(noInternet_container)) {
            requestDashBoardInformation();
        }
    }
}
