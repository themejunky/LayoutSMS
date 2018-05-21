package sms.layout.themejunky.com.layout_sms_lib.retrofit.model;

import com.google.gson.annotations.SerializedName;

public class WallpapersItem {
    @SerializedName("image_url")
    private String image;
    @SerializedName("thumbnail")
    private String thumbnail;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
