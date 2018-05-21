package sms.layout.themejunky.com.layout_sms_lib.retrofit.model;

import com.google.gson.annotations.SerializedName;

public class AdsConfigResponse
{
    @SerializedName("keys")
    private sms.layout.themejunky.com.layout_sms_lib.retrofit.model.AdsConfigResponse_keys keys;
    @SerializedName("flow")
    private sms.layout.themejunky.com.layout_sms_lib.retrofit.model.AdsConfigResponse_flow adsFlow;

    public sms.layout.themejunky.com.layout_sms_lib.retrofit.model.AdsConfigResponse_keys getKeys() {
        return keys;
    }

    public sms.layout.themejunky.com.layout_sms_lib.retrofit.model.AdsConfigResponse_flow getAdsFlow() {
        return adsFlow;
    }
}