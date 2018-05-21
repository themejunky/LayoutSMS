package sms.layout.themejunky.com.layout_sms_lib.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Junky2 on 10/20/2017.
 */

public class BackgroundService extends Service {

    private  boolean isRunning;
    private Context context;
    private Thread backgroundThread;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        this.context = this;
        this.isRunning =false;
        this.backgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                stopSelf();
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
               long fistApply =  sharedPreferences.getLong("firstApply",0L);
                long curenTime = System.currentTimeMillis();
                Log.d("curenttime", "firstApply: "+ String.valueOf(fistApply)) ;
                Log.d("curenttime", "curentTime: "+ String.valueOf(System.currentTimeMillis())) ;
                Log.d("curenttime", "diferenta: "+ String.valueOf((curenTime-fistApply)/(1000*60))) ;

                if((curenTime-fistApply)/(1000*60)>=0 || (curenTime-fistApply)/(1000*60)==1){




                }


            }
        });
    }

    @Override
    public void onDestroy() {
        this.isRunning=false;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(!this.isRunning){
            this.isRunning = true;
            this.backgroundThread.start();
        }
        return START_STICKY;
    }
}
