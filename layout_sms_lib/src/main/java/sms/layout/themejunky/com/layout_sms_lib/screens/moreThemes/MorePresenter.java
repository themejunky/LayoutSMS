package sms.layout.themejunky.com.layout_sms_lib.screens.moreThemes;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sms.layout.themejunky.com.layout_sms_lib.retrofit.Service;
import sms.layout.themejunky.com.layout_sms_lib.retrofit.model.ThemeItem;

public class MorePresenter implements MoreContract.Presenter {
    private MoreContract.View view;
    private Service service;

    MorePresenter(MoreContract.View view, Service service) {
        this.view = view;
        this.service = service;
    }

    void getThemeConfig(String package_name) {   //final Boolean isFromCategory
        Call<List<ThemeItem>> mCall = service.maingetInterface().getThemeConfig(package_name);
        mCall.enqueue(new Callback<List<ThemeItem>>() {
            @Override
            public void onResponse(Call<List<ThemeItem>> mCall, Response<List<ThemeItem>> mResponse) {
                if (mResponse.raw().cacheResponse() != null) {
                    sendResonse_ThemeConfig(mResponse);
                } else {
                    sendResonse_ThemeConfig(mResponse);
                }
            }

            @Override
            public void onFailure(Call<List<ThemeItem>> mCall, Throwable mThrowable) {
                if (view != null) {
                    view.noInternetConnection();
                }
            }
        });
    }

    /***
     All the methods below sends the response recived from cache or from dashboard
     */
    private void sendResonse_ThemeConfig(Response<List<ThemeItem>> mResponse) {
        if (mResponse.code() == 200) {
            if (view != null && mResponse.body().size()>0) {
                view.showMoreResponse(mResponse.body().get(0));
            }
        }
    }
}
