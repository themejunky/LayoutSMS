package sms.layout.themejunky.com.layout_sms_lib.retrofit.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mpopa on 09/04/2017.
 */

public class ThemeItem implements Serializable {

    @SerializedName("id")
    private int id;
    @SerializedName("store_link")
    private String storeUrl;
    @SerializedName("name")
    private String name;
    @SerializedName("account")
    private String account;
    @SerializedName("primary_color")
    private String primaryColor;
    @SerializedName("accent_color")
    private String accentColor;
    @SerializedName("price")
    private int price;
    @SerializedName("price_category")
    private String priceCategory;
    @SerializedName("icon")
    private String icon;
    @SerializedName("thumbnail")
    private String thumbnail;
    @SerializedName("no_ads")
    private boolean noAds;
    @SerializedName("facebook_interstitial_id")
    private String facebookInterstitialAds;
    @SerializedName("likes")
    private long likes;
    @SerializedName("screenshots")
    private List<String> screenshots;
    @SerializedName("account_internal_ads")
    private List<InternalAds> internalAds;
    @SerializedName("account_wallpapers")
    private List<WallpapersItem> wallpaperItems;
    private THEME_TYPE themeType = THEME_TYPE.THEME;

    public THEME_TYPE getThemeType() {
        return themeType;
    }

    public void setThemeType(THEME_TYPE themeType) {
        this.themeType = themeType;
    }

    public List<InternalAds> getInternalAds() {
        return internalAds;
    }

    public void setInternalAds(List<InternalAds> internalAds) {
        this.internalAds = internalAds;
    }

    public List<WallpapersItem> getWallpaperItems() {
        return wallpaperItems;
    }

    public void setWallpaperItems(List<WallpapersItem> wallpaperItems) {
        this.wallpaperItems = wallpaperItems;
    }

    public String getFacebookInterstitialAds() {
        return facebookInterstitialAds;
    }

    public enum THEME_TYPE {
        THEME, AD
    }

    public ThemeItem() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStoreUrl() {
        return storeUrl;
    }

    public void setStoreUrl(String storeUrl) {
        this.storeUrl = storeUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(String primaryColor) {
        this.primaryColor = primaryColor;
    }

    public String getAccentColor() {
        return accentColor;
    }

    public void setAccentColor(String accentColor) {
        this.accentColor = accentColor;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPriceCategory() {
        return priceCategory;
    }

    public void setPriceCategory(String priceCategory) {
        this.priceCategory = priceCategory;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public long getLikes() {
        return likes;
    }

    public void setLikes(long likes) {
        this.likes = likes;
    }

    public List<String> getScreenshots() {
        return screenshots;
    }

    public void setScreenshots(List<String> screenshots) {
        this.screenshots = screenshots;
    }

    public boolean isNoAds() {
        return noAds;
    }

    public void setNoAds(boolean noAds) {
        this.noAds = noAds;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
