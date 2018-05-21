package sms.layout.themejunky.com.layout_sms_lib.retrofit.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AdsConfigResponse_flow {
    @SerializedName("apply_theme")
    private List<String> applyTheme;

    @SerializedName("on_enter_interstitial")
    private List<String> onEnterInterstitial;

    @SerializedName("app_of_the_day")
    private List<String> appOfTheDay;

    @SerializedName("on_exit_interstitial")
    private List<String> onExitInterstitial;

    @SerializedName("more_themes")
    private List<String> moreThemes;

    @SerializedName("recommended_themes")
    private List<String> recommended;

    @SerializedName("on_exit_wallpaper")
    private List<String> onExitWallpapers;

    @SerializedName("on_enter_wallpaper")
    private List<String> onEnterWallpaper;

    @SerializedName("native")
    private List<String> nativeFlow;

    public List<String> getApplyTheme() {
        return applyTheme;
    }

    public List<String> getOnEnterInterstitial() {
        return onEnterInterstitial;
    }

    public List<String> getAppOfTheDay() {
        return appOfTheDay;
    }

    public List<String> getOnExitInterstitial() {
        return onExitInterstitial;
    }

    public List<String> getMoreThemes() {
        return moreThemes;
    }

    public List<String> getRecommended() {
        return recommended;
    }

    public List<String> getOnExitWallpapers() {
        return onExitWallpapers;
    }

    public List<String> getOnEnterWallpaper() {
        return onEnterWallpaper;
    }

    public List<String> getNativeFlow() {
        return nativeFlow;
    }
}
