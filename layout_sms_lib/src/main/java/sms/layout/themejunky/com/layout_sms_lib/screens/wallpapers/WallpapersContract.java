package sms.layout.themejunky.com.layout_sms_lib.screens.wallpapers;

import sms.layout.themejunky.com.layout_sms_lib.retrofit.model.ThemeItem;
import sms.layout.themejunky.com.layout_sms_lib.retrofit.model.WallpapersItem;

public class WallpapersContract {
    public interface View {
        void onWallpaperClick(WallpapersItem wallpaper);
        void showThemeResponse(ThemeItem themeConfig);
        void noInternetConnection();
    }

    public interface Presenter {
    }
}
