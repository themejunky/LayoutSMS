package sms.layout.themejunky.com.layout_sms_lib.screens.main;

import android.app.Activity;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.List;

import sms.layout.themejunky.com.layout_sms_lib.R;
import sms.layout.themejunky.com.layout_sms_lib.retrofit.model.InternalAds;

public class MainCustomCarousel implements MainCustomCarouselListener {
    private ViewPager viewPager;
    private Activity activity;
    private List<InternalAds> banners;
    private int pozitie = 0;
    private int max;
    private Boolean isCarouselOn = true;
    private MainCustomCarouselAdapter adapter;

    public MainCustomCarousel(List<InternalAds> banners, Activity activity) {
        this.activity = activity;
        this.banners = banners;
        this.max = banners.size();
    }

    public View startEngine() {
        LayoutInflater factory = LayoutInflater.from(activity);
        View DialogInflateView = factory.inflate(R.layout.custom_made_carouse_container, null);
        RelativeLayout container = (RelativeLayout) DialogInflateView.findViewById(R.id.containerCarousel);

        this.viewPager = (ViewPager) container.findViewById(R.id.ViewPagerr);
        TabLayout tabLayout = (TabLayout) container.findViewById(R.id.tabDotss);

        tabLayout.setupWithViewPager(viewPager, true);


        adapter = new MainCustomCarouselAdapter(activity, this);
        adapter.deleteModel();
        adapter.setBanners(banners);

        viewPager.setAdapter(adapter);
        maryGoesAround();

        return container;
    }

    private synchronized void maryGoesAround() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (pozitie++ >= adapter.getCount() - 1) {
                    pozitie = 0;
                }
                if (isCarouselOn) {
                    viewPager.setCurrentItem(pozitie);
                    maryGoesAround();
                }
            }
        }, 3000);
    }

    @Override
    public void onCarouselImageTouch() {
        isCarouselOn = false;
    }
}