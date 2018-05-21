package sms.layout.themejunky.com.layout_sms_lib;

import android.app.Application;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import com.crashlytics.android.Crashlytics;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import io.fabric.sdk.android.Fabric;
import module.themejunky.com.tj_gae.Module_GoogleAnalyticsEvents;
import sms.layout.themejunky.com.layout_sms_lib.threadPool.ThreadPoolMonitor;
import sms.layout.themejunky.com.layout_sms_lib.threadPool.callables.ThreadInfo;
import themejunky.module_adsmanager.ModuleAdsManager;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class MainApplication extends Application {
    private ThreadPoolExecutor threadPoolExecutor;
    private ThreadPoolMonitor threadPoolMonitor;
    public Module_GoogleAnalyticsEvents mGAEA;
    public ModuleAdsManager adsManager;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        adsManager = new ModuleAdsManager();
        adsManager.initManagers(this,true);
        adsManager.setLogName("testLog");

        adsManager.getManagerInterstitial().initInterstitialDisplay("6354","3067");
        adsManager.getManagerInterstitial().initInterstitialAdmob("ca-app-pub-8562466601970101/5081303159");
        adsManager.getManagerInterstitial().initInterstitialAppnext("cdd052e2-9394-407c-99d4-323439dd7398");

        //adsManager.getManagerNative().initNativeAdmob("ca-app-pub-8562466601970101/5081303159",false);
        //adsManager.getManagerNative().iniNativeAppnext("cdd052e2-9394-407c-99d4-323439dd7398",false);

        //adsManager = ModuleAdsManager.getInstance(this);
        mGAEA = Module_GoogleAnalyticsEvents.getInstance(this,getResources().getString(R.string.analytics_traker_id));

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder().setDefaultFontPath("fonts/Montserrat-Regular.ttf").setFontAttrId(R.attr.fontPath).build());

        startThreadPool();
    }

    private void startThreadPool() {
        try {
            if (threadPoolExecutor == null || threadPoolExecutor.isShutdown() || threadPoolExecutor.isTerminated()) {
                int mP = Runtime.getRuntime().availableProcessors();
                threadPoolExecutor = new ThreadPoolExecutor(mP, mP, 1, TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(20), new sms.layout.themejunky.com.layout_sms_lib.threadPool.ThreadFactoryBase(), new sms.layout.themejunky.com.layout_sms_lib.threadPool.ThreadRejected());
            }

            if (threadPoolMonitor == null) {
                threadPoolMonitor = new ThreadPoolMonitor(threadPoolExecutor);
                Thread monitorThread = new Thread(threadPoolMonitor);
                monitorThread.start();
            }

            threadPoolExecutor.prestartCoreThread();
        } catch (Exception e) {
            Log.d("Eroare_Thread"," startThreadPool : 1 "+e.getMessage());
        }
    }

    public void addToPool(final Callable<ThreadInfo> nCT) {
        try
        {
            threadPoolExecutor.submit(nCT);
        } catch (Exception e) {
            Log.d("Eroare_Thread"," addToPool : 1 "+e.getMessage());
        }
    }

    public void startShaking(ImageView imageView) {
        Animation mAnimation = new TranslateAnimation(
                TranslateAnimation.ABSOLUTE, 0,
                TranslateAnimation.ABSOLUTE, 0,
                TranslateAnimation.RELATIVE_TO_PARENT, 0f,
                TranslateAnimation.RELATIVE_TO_PARENT, 1.5f);
        mAnimation.setDuration(3000);
        mAnimation.setRepeatCount(-1);
        mAnimation.setRepeatMode(Animation.REVERSE);
        mAnimation.setInterpolator(new LinearInterpolator());
        imageView.startAnimation(mAnimation);


    }
}
