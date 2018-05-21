package sms.layout.themejunky.com.layout_sms_lib.threadPool.callables.Wallpapers;


import android.app.Activity;
import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.concurrent.Callable;

import sms.layout.themejunky.com.layout_sms_lib.R;
import sms.layout.themejunky.com.layout_sms_lib.threadPool.callables.ThreadInfo;

public class setWallpaper implements Callable<ThreadInfo> {

    private WeakReference<Activity> activity;
    private String urlWallpaper;

    public setWallpaper(WeakReference<Activity> activity, String urlWallpaper) {
        this.activity = activity;
        this.urlWallpaper = urlWallpaper;
    }

    @Override
    public ThreadInfo call() throws Exception {

        try {
            Bitmap result= Picasso.with(activity.get().getApplicationContext()).load(urlWallpaper).get();
            WallpaperManager wallpaperManager = WallpaperManager.getInstance(activity.get().getApplicationContext());
            wallpaperManager.setBitmap(result);

            activity.get().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(activity.get().getApplicationContext(),activity.get().getResources().getString(R.string.wallpaper_set), Toast.LENGTH_SHORT).show();
                }
            });


        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
