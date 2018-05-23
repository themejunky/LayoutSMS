package sms.layout.themejunky.com.layout_sms_lib.screens.main;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.koushikdutta.ion.Ion;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import module.themejunky.com.tj_gae.Module_GoogleAnalyticsEvents;
import sms.layout.themejunky.com.layout_sms_lib.MainApplication;
import sms.layout.themejunky.com.layout_sms_lib.ManagerOnboarding;
import sms.layout.themejunky.com.layout_sms_lib.R;
import sms.layout.themejunky.com.layout_sms_lib.lwp.ManagerFallingHeartsLwp;
import sms.layout.themejunky.com.layout_sms_lib.receiver.MyReceiverVip;
import sms.layout.themejunky.com.layout_sms_lib.retrofit.Service;
import sms.layout.themejunky.com.layout_sms_lib.retrofit.model.AdsConfigResponse;
import sms.layout.themejunky.com.layout_sms_lib.retrofit.model.InternalAds;
import sms.layout.themejunky.com.layout_sms_lib.retrofit.model.ThemeItem;
import sms.layout.themejunky.com.layout_sms_lib.screens.SlaveActivity;
import sms.layout.themejunky.com.layout_sms_lib.threadPool.callables.Main.CallApplyTheme;
import sms.layout.themejunky.com.layout_sms_lib.threadPool.callables.Main.CallGoogleRedirect;
import sms.layout.themejunky.com.layout_sms_lib.threadPool.callables.Main.CallParsBanners;
import sms.layout.themejunky.com.layout_sms_lib.threadPool.callables.Main.CallTrackFirstInstall;
import sms.layout.themejunky.com.layout_sms_lib.utils.ConstantsAction;
import sms.layout.themejunky.com.layout_sms_lib.utils.IntentStarter;
import themejunky.module_adsmanager.ModuleAdsManager;
import themejunky.module_adsmanager.ads.AdsListenerManager;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class MainActivity extends SlaveActivity implements MainContract.View, MainContract.Retroift, View.OnClickListener, AdsListenerManager.ListenerAds {

    /* general */
    private WeakReference<Activity> localWeakReference;
    private Activity localStrongReference;
    private IntentStarter intentStarter;
    public Module_GoogleAnalyticsEvents mGAE;
    public WeakReference<Module_GoogleAnalyticsEvents> mGAEWeak;


    /* views */
    private LinearLayout noInternet_container;
    private ImageView loading_animation;
    private RelativeLayout loading_animation_container;
    private RelativeLayout containerCarousel;
    private MainCustomCarousel customCarousel;

    /* from retrofit */
    public List<InternalAds> bannerList = new ArrayList<>();
    public List<InternalAds> bestList = new ArrayList<>();
    public List<InternalAds> getMoreList = new ArrayList<>();


    private Uri uri;
    private Handler loadingHandler;
    private Runnable loadingRunnable;
    private ModuleAdsManager adsManager;
    private RelativeLayout containerFacebook, containerAdmob, containerAppnext;
    private boolean isOnPause;
    private List<String> flowAdsFirst, flowAdsApply,  flowAdsExit, flowAdsWall;
    private List<String> flowTest = Arrays.asList("display","admob","appnext");
    private Timer timer;
    private TimerTask timerTask;
    private ImageView mImageShake,ourBest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setBarAndContentView();
        fetchViewAndInit();
        startModulRating();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder().setDefaultFontPath("fonts/Montserrat-Regular.ttf").setFontAttrId(R.attr.fontPath).build());

        sendRequestBroadcast(this);
        initAdsManager();

        loadingHandler = new Handler();
        loadingRunnable = new Runnable() {
            @Override
            public void run() {
                loading_animation_container.setVisibility(View.GONE);
                if (!MyReceiverVip.isVip && flowTest != null) {
                    if (flowTest.size() > 0) {
                        //adsManager.setNativeFlowAndShowAds(flowAdsFirst, containerFacebook, containerAdmob, containerAppnext);
                        //adsManager.setInterFlowAndShowAds(flowAdsFirst, ConstantsAction.INTRO);
                        adsManager.getManagerInterstitial().showInterstitial(flowTest, ConstantsAction.INTRO);
                        StartShaking();
                    }
                }
            }
        };
        loadingHandler.postDelayed(loadingRunnable, 6000);

    }

    private void setBarAndContentView() {
        try {
            getSupportActionBar().hide();
        } catch (NullPointerException ignored) {
        }
        setContentView(R.layout.activity_main_sms);

    }

    private void fetchViewAndInit() {
        localStrongReference = this;
        localWeakReference = new WeakReference<Activity>(this);
        intentStarter = new IntentStarter(localWeakReference);
        containerFacebook = (RelativeLayout) findViewById(R.id.containerFacebook);
        containerAdmob = (RelativeLayout) findViewById(R.id.containerAdmob);
        containerAppnext = (RelativeLayout) findViewById(R.id.containerAppnext);
        loading_animation = (ImageView) findViewById(R.id.loading_animation);
        loading_animation_container = (RelativeLayout) findViewById(R.id.rlSplash);
        mImageShake  = findViewById(R.id.nImageShake);
        ourBest = findViewById(R.id.btn_best);

        noInternet_container = (LinearLayout) findViewById(R.id.noInternetLayout);


        uri = Uri.parse("android.resource://" + this.getPackageName() + "/" + R.drawable.loading_theme_sms);
        Ion.with(localStrongReference).load(uri.toString()).intoImageView(loading_animation);


        containerCarousel = (RelativeLayout) findViewById(R.id.containerCarousel);
        containerCarousel.removeAllViews();

        customCarousel = new MainCustomCarousel(new ArrayList<InternalAds>(0), this);
        containerCarousel.addView(customCarousel.startEngine());

        findViewById(R.id.btn_apply).setOnClickListener(this);
        findViewById(R.id.btn_best).setOnClickListener(this);
        findViewById(R.id.btn_wallpapers).setOnClickListener(this);
        findViewById(R.id.btn_liveWallpaper).setOnClickListener(this);

        noInternet_container.setOnClickListener(this);
        mImageShake.setOnClickListener(this);

        Log.d("testttt","firstRun");
        mGAE = ((MainApplication) getApplication()).mGAEA;
        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), MODE_PRIVATE);
        if (sharedPreferences.getBoolean("firstRun", true)) {
            //mGAE.getEvents(R.string.analytics_event_themes, "Buttons", "First Run in v1.0.51 (without numbers)");
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("firstRun", false);
            editor.commit();
        } else {
            //not first run
        }
    }

    private void StartShaking(){
        Log.d("testttt","start shaking");

        Animation mAnimation = new TranslateAnimation(
                TranslateAnimation.ABSOLUTE, 0,
                TranslateAnimation.ABSOLUTE, 0,
                TranslateAnimation.RELATIVE_TO_PARENT, 0f,
                TranslateAnimation.RELATIVE_TO_PARENT, 1.5f);
        mAnimation.setDuration(3000);
        mAnimation.setRepeatCount(-1);
        mAnimation.setRepeatMode(Animation.REVERSE);
        mAnimation.setInterpolator(new LinearInterpolator());
        ourBest.startAnimation(mAnimation);

        startShakeImage(this,mImageShake,6000,2000);
    }

    @Override
    public void onResume() {
        super.onResume();
        requestDashBoardInformation();
        mGAE = ((MainApplication) getApplication()).mGAEA;
        mGAEWeak = new WeakReference<>(mGAE);

        ((MainApplication) getApplication()).addToPool(new CallTrackFirstInstall(localWeakReference, mGAEWeak));
        mGAE.getEvents(R.string.analytics_screens, MainActivity.class, "");

    }

    private void requestDashBoardInformation() {
        noInternet_container.setVisibility(View.GONE);
        MainPresenter presenter = new MainPresenter(this, Service.getInstance(this));
        presenter.getAdsConfig(localStrongReference.getPackageName());
        presenter.getThemeConfig(localStrongReference.getPackageName());
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_apply) {
            mGAE.getEvents(R.string.analytics_event_themes, "Buttons", "Click on Apply Button");
            if (adsManager.getManagerInterstitial().isSomeAdLoaded() && flowTest.size() > 0 && !MyReceiverVip.isVip) {
                adsManager.getManagerInterstitial().showInterstitial(flowTest, ConstantsAction.APPLY);
            } else {
                ((MainApplication) getApplication()).addToPool(new CallApplyTheme(localWeakReference, mGAEWeak));
            }
        } else if (i == R.id.btn_best) {
            mGAE.getEvents(R.string.analytics_event_themes, "Buttons", "Click on Our Best Button");
            if (bestList.size() > 0) {
                ((MainApplication) getApplication()).addToPool(new CallGoogleRedirect(localWeakReference, bestList.get(0).getStoreUrl(), true, mGAEWeak));
            } else {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.themejunky.launchers")));
            }
        } else if (i == R.id.btn_wallpapers) {
            mGAE.getEvents(R.string.analytics_event_themes, "Buttons", "Click on Wallpapers Button");
            if (adsManager.getManagerInterstitial().isSomeAdLoaded() && flowTest.size() > 0 && !MyReceiverVip.isVip) {
                adsManager.getManagerInterstitial().showInterstitial(flowTest, ConstantsAction.WALLPAPERS);
            } else {
                intentStarter.redirect(2);
            }
        } else if (i == R.id.nImageShake) {
            mGAE.getEvents(R.string.analytics_event_themes, "Buttons", "Click on Share Button");
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.share_text));
            sendIntent.setType("text/plain");
            startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.app_name)));
        } else if (i == R.id.btn_liveWallpaper) {
            mGAE.getEvents(R.string.analytics_event_themes, "Buttons", "Click on Live Wallpapers Button");
            if (adsManager.getManagerInterstitial().isSomeAdLoaded() && flowTest.size() > 0 && !MyReceiverVip.isVip) {
                adsManager.getManagerInterstitial().showInterstitial(flowTest, ConstantsAction.LIVE_WALLPAPERS);
            } else {
                Log.d("testttt","btn_liveWallpaper 2");
                new ManagerFallingHeartsLwp(this).set();
            }

        }
        if (v.equals(noInternet_container)) {
            requestDashBoardInformation();
        }

    }

    @Override
    public void noInternetConnection() {
        //noInternet_container.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopLoader() {
        // stop_Loader();
    }

    private void stop_Loader() {
        Ion.with(localStrongReference).load("").intoImageView(loading_animation);
        loading_animation_container.setVisibility(View.GONE);

        if (getIntent().hasExtra("tapOnRatingNotification")) {
            if (getIntent().getStringExtra("tapOnRatingNotification").equals("yes")) {
                getIntent().putExtra("tapOnRatingNotification", "no");
                intentStarter.redirect(3);
            }
        }
    }

    /**
     * Callable : Main -> CallParsBanners;
     */
    public void startCarousel() {
        customCarousel = new MainCustomCarousel(bannerList, this);
        containerCarousel.addView(customCarousel.startEngine());
    }

    /**
     * From : presenter.getThemeConfig()
     *
     * @param themeInfo - all informtion about theme
     */
    @Override
    public void retrofitResponse_ThemeConfig(final ThemeItem themeInfo) {

        ((MainApplication) getApplication()).addToPool(new CallParsBanners(localWeakReference, themeInfo.getInternalAds(), mGAEWeak));

        adsManager.getManagerInterstitial().initInterstitialFacebook(themeInfo.getFacebookInterstitialAds());
        //adsManager.initInterstitialFacebookAds(themeInfo.getFacebookInterstitialAds());
        //adsManager.initFacebookNativeAds(containerFacebook,themeInfo.getFacebookInterstitialAds());

    }

    /**
     * From : presenter.getAdsConfig()
     *
     * @param body - information aboud ads ( keys )
     */
    @Override
    public void retrofitResponse_AdsConfig(AdsConfigResponse body) {
        if (adsManager != null && body != null) {
            flowAdsFirst = body.getAdsFlow().getAppOfTheDay();
            flowAdsApply = body.getAdsFlow().getApplyTheme();
            flowAdsWall = body.getAdsFlow().getOnEnterWallpaper();
            flowAdsExit = body.getAdsFlow().getOnExitInterstitial();

            //adsManager.getManagerInterstitial().initInterstitialAdmob(body.getKeys().getAdmobInterstitialKey());
            //adsManager.getManagerInterstitial().initInterstitialAppnext(body.getKeys().getAppnext());
        }

    }

    private void startModulRating() {
        // ((MainApplication) getApplication()).addToPool(new CallStartModuleRating(localWeakReference,mGAEWeak));
    }

    /**
     * Used for Caligraphy library
     */
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onBackPressed() {
        if (adsManager.getManagerInterstitial().isSomeAdLoaded() && flowTest.size() > 0 && !MyReceiverVip.isVip) {
            adsManager.getManagerInterstitial().showInterstitial(flowTest, ConstantsAction.GET_MORE);
        } else {
            Intent intent = getPackageManager().getLaunchIntentForPackage(getApplicationContext().getString(R.string.mother_packagename));
            if (intent != null) {
                finish();
                intentStarter.redirect(1);
            } else {
                finish();
                intentStarter.redirect(4);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        isOnPause = true;
        //adsManager.setAdmobeMute(this);
    }

    @Override
    public void loadedNativeAds(String s) {

    }

    @Override
    public void loadedInterstitialAds() {

    }

    @Override
    public void afterInterstitialIsClosed(String s) {
        switch (s) {
            case ConstantsAction.INTRO:
                loading_animation_container.setVisibility(View.GONE);
                StartShaking();
                break;
            case ConstantsAction.APPLY:
                ((MainApplication) getApplication()).addToPool(new CallApplyTheme(localWeakReference, mGAEWeak));
                break;
            case ConstantsAction.GET_MORE:
                Intent intent = getPackageManager().getLaunchIntentForPackage(getApplicationContext().getString(R.string.mother_packagename));
                if (intent != null) {
                    finish();
                    intentStarter.redirect(1);
                } else {
                    finish();
                    intentStarter.redirect(4);
                }
                break;
            case ConstantsAction.BEST_APP:
                ((MainApplication) getApplication()).addToPool(new CallGoogleRedirect(localWeakReference, bestList.get(0).getStoreUrl(), true, mGAEWeak));
                break;
            case ConstantsAction.RATE:
                intentStarter.redirect(3);
                break;
            case ConstantsAction.WALLPAPERS:
                intentStarter.redirect(2);
                break;
            case ConstantsAction.LIVE_WALLPAPERS:
                new ManagerFallingHeartsLwp(this).set();
                break;

        }
    }




    public void sendRequestBroadcast(Activity activity) {
        Intent sendIntent = new Intent();
        sendIntent.setAction("com.request.sms.vip.theme");
        sendIntent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        activity.sendBroadcast(sendIntent);
        Log.d("isUserVip", "send request");
    }

    public void initAdsManager() {
        adsManager = ((MainApplication)getApplication()).adsManager;
        adsManager.setListenerAds(this);

        //adsManager.initAppnextNativeAds(containerAppnext, "cdd052e2-9394-407c-99d4-323439dd7398");//sms
        //adsManager.initAdmobNativeAds(containerAdmob, "ca-app-pub-8562466601970101/5081303159");//sms
        //adsManager.initAdmobNativeAds(containerAdmob, "ca-app-pub-8562466601970101/9984599253");//kplus


    }

    private void startShakeImage(final Activity activity, final ImageView imageView,long delayStartMilisec,long startAfterMilisec){
        Log.d("testttt","animation started");
        timer = new Timer();
        timerTask = new TimerTask() {
            Handler handler = new Handler();
            public void run() {
                handler.post(new Runnable() {
                    public void run(){
                        final Animation animShake = AnimationUtils.loadAnimation(activity, R.anim.shake);
                        imageView.startAnimation(animShake);
                    }
                });
            }
        };
        timer.schedule(timerTask, delayStartMilisec, startAfterMilisec);
    }
}
