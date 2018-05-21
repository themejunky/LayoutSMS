package sms.layout.themejunky.com.layout_sms_lib.retrofit.model;

import com.google.gson.annotations.SerializedName;

public class InternalAds {
    @SerializedName("id")
    private int id;
    @SerializedName("destination_url")
    private String storeUrl;
    @SerializedName("text")
    private String text;
    @SerializedName("image")
    private String image;

    private String type = "internalAd";

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
