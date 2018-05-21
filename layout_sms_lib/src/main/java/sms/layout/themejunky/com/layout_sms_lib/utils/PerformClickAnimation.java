package sms.layout.themejunky.com.layout_sms_lib.utils;

import android.graphics.PorterDuff;
import android.os.Handler;
import android.widget.ImageView;

import sms.layout.themejunky.com.layout_sms_lib.retrofit.model.InternalAds;
import sms.layout.themejunky.com.layout_sms_lib.retrofit.model.WallpapersItem;
import sms.layout.themejunky.com.layout_sms_lib.screens.moreThemes.MoreContract;


/**
 * @CustomMade : B.C.
 * Animates onClick; Instead of using a external library
 */

public class PerformClickAnimation {
    private int delay = 25;

    /**
     * @image : the image on witch it will be simulete the click press
     * @listener : used to perform the action on the desired method after the 2 animation are finished
     * @item : all the information of the iconPack on witch perform click is made ( used to perform the click action
     */
    public PerformClickAnimation(final ImageView image, final sms.layout.themejunky.com.layout_sms_lib.screens.wallpapers.WallpapersContract.View listener, final WallpapersItem item) {
        //animation_1(image, delay);
       // animaion_2(image, delay * 2);
        clickActions(new Runnable() {
            public void run() {
                listener.onWallpaperClick(item);
            }
        }, (delay * 2) + 1);
    }

    public PerformClickAnimation(final ImageView image, final MoreContract.View listener, final InternalAds item) {
       // animation_1(image, delay);
        //animaion_2(image, delay * 2);
        clickActions(new Runnable() {
            public void run() {
                listener.onMoreClick(item);
            }
        }, (delay * 2) + 1);
    }


    /**
     * First animation that puts a transparent overlay to simulat press on
     *
     * @image : image on witch the overlay will be shown
     * @delayTime : delay for the handler
     */
    private void animation_1(final ImageView image, int delayTime) {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                image.getDrawable().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                image.invalidate();
            }
        }, delayTime);
    }

    /**
     * Second animation that remove the overlay
     *
     * @image : image on witch the overlay will be shown
     * @delayTime : delay for the handler ( higher that the first animation )
     */
    private void animaion_2(final ImageView image, int delayTime) {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                image.getDrawable().clearColorFilter();
                image.invalidate();
            }
        }, delayTime);
    }

    /**
     * The action of the onClick after all animation finish
     */
    private void clickActions(Runnable runnable, int delay) {
        new Handler().postDelayed(runnable, delay);
    }
}
