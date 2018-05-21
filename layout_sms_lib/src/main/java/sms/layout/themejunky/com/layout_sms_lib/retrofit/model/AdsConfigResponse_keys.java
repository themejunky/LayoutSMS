package sms.layout.themejunky.com.layout_sms_lib.retrofit.model;

import com.google.gson.annotations.SerializedName;

public class AdsConfigResponse_keys {

    @SerializedName("facebook_native")
    private String facebookNativeKey;
    @SerializedName("facebook_interstitial")
    private String facebookInterstitialKey;
    @SerializedName("admob_native")
    private String admobNativeKey;
    @SerializedName("admob_interstitial")
    private String admobInterstitialKey;
    @SerializedName("appnext")
    private String appnext;
    @SerializedName("supersonic")
    private String supersonic;



    public String getFacebookNativeKey() {
        return facebookNativeKey;
    }

    public void setFacebookNativeKey(String facebookNativeKey) {
        this.facebookNativeKey = facebookNativeKey;
    }

    public String getFacebookInterstitialKey() {
        return facebookInterstitialKey;
    }

    public void setFacebookInterstitialKey(String facebookInterstitialKey) {
        this.facebookInterstitialKey = facebookInterstitialKey;
    }

    public String getAdmobNativeKey() {
        return admobNativeKey;
    }

    public void setAdmobNativeKey(String admobNativeKey) {
        this.admobNativeKey = admobNativeKey;
    }

    public String getAdmobInterstitialKey() {
        return admobInterstitialKey;
    }

    public void setAdmobInterstitialKey(String admobInterstitialKey) {
        this.admobInterstitialKey = admobInterstitialKey;
    }

    public String getAppnext() {
        return appnext;
    }

    public void setAppnext(String appnext) {
        this.appnext = appnext;
    }

    public String getSupersonic() {
        return supersonic;
    }

    public void setSupersonic(String supersonic) {
        this.supersonic = supersonic;
    }
}
