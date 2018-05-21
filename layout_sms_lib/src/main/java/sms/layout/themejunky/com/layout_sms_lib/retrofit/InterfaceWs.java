package sms.layout.themejunky.com.layout_sms_lib.retrofit;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import sms.layout.themejunky.com.layout_sms_lib.retrofit.model.AdsConfigResponse;
import sms.layout.themejunky.com.layout_sms_lib.retrofit.model.ThemeItem;

public interface InterfaceWs {
    @GET("ads")
    Call<AdsConfigResponse> getAdsConfig(@Query("package_name") String packageName, @Query("type") String type);

    @GET("themes")
    Call<List<ThemeItem>> getThemeConfig(@Query("package_name") String packageName);
}
