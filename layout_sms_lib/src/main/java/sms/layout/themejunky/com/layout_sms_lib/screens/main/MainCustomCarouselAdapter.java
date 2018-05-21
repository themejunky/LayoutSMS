package sms.layout.themejunky.com.layout_sms_lib.screens.main;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.List;

import module.themejunky.com.tj_gae.Module_GoogleAnalyticsEvents;
import sms.layout.themejunky.com.layout_sms_lib.MainApplication;
import sms.layout.themejunky.com.layout_sms_lib.R;
import sms.layout.themejunky.com.layout_sms_lib.retrofit.model.InternalAds;
import sms.layout.themejunky.com.layout_sms_lib.threadPool.callables.Main.CallGoogleRedirect;

public class MainCustomCarouselAdapter extends PagerAdapter {
    private Activity activity;
    private List<InternalAds> banners;
    private MainCustomCarouselListener listener;
    private RelativeLayout containerImage;
    private Module_GoogleAnalyticsEvents mGAEs;

    public MainCustomCarouselAdapter(Activity activity, MainCustomCarouselListener listener) {
        this.activity = activity;
        this.listener = listener;
        this.mGAEs = ((MainActivity) activity).mGAE;
    }

    public View getItem(final int position) {

        LayoutInflater factory = LayoutInflater.from(activity);
        View DialogInflateView = factory.inflate(R.layout.custom_made_carousel_image, null);
        containerImage = (RelativeLayout) DialogInflateView.findViewById(R.id.containerImage);

        Log.d("testsad",banners.get(position).getStoreUrl());

        containerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = banners.get(position).getStoreUrl().substring(banners.get(position).getStoreUrl().lastIndexOf("=")+1);
                mGAEs.getEvents(R.string.analytics_event,"Carousel",name+ " from " +position + " position" );
                ((MainApplication) activity.getApplication()).addToPool(new CallGoogleRedirect(new WeakReference<>(activity),banners.get(position).getStoreUrl(),true, new WeakReference<Module_GoogleAnalyticsEvents>(mGAEs)));
            }
        });

        ImageView imageView = (ImageView) containerImage.findViewById(R.id.imgItem) ;

        containerImage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                listener.onCarouselImageTouch();
                return false;
            }
        });

        Picasso.with(activity).load(banners.get(position).getImage()).into(imageView);
        return containerImage;
    }

    public void setBanners(List<InternalAds> banners) {
        this.banners = banners;
        notifyDataSetChanged();
    }

    public void deleteModel() {
        if (this.banners != null)
            this.banners.clear();
    }

    public void addItemOneByOne(InternalAds item) {
        this.banners.add(item);
    }

    public int getModelSize() {
        return this.banners.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return banners.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View mItemView = getItem(position);
        container.addView(mItemView);
        return mItemView;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
