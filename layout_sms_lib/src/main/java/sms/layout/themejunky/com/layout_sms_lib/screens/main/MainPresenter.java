package sms.layout.themejunky.com.layout_sms_lib.screens.main;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sms.layout.themejunky.com.layout_sms_lib.retrofit.Service;
import sms.layout.themejunky.com.layout_sms_lib.retrofit.model.AdsConfigResponse;
import sms.layout.themejunky.com.layout_sms_lib.retrofit.model.ThemeItem;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.Retroift view;
    private Service service;

    MainPresenter(MainContract.Retroift view, Service service) {
        this.view = view;
        this.service = service;
    }

    void getAdsConfig(String package_name) {
        Call<AdsConfigResponse> mCall =service.maingetInterface().getAdsConfig(package_name,"regular");
        mCall.enqueue(new Callback<AdsConfigResponse>() {
            @Override
            public void onResponse(Call<AdsConfigResponse> mCall, Response<AdsConfigResponse> mResponse) {
                sendResonse_AdsConfig(mResponse);
            }

            @Override
            public void onFailure(Call<AdsConfigResponse> mCall, Throwable mThrowable) {
                if (view != null) {
                    view.noInternetConnection();
                }
            }
        });
    }

    void getThemeConfig(String package_name) {
        Call<List<ThemeItem>> mCall = service.maingetInterface().getThemeConfig(package_name);
        mCall.enqueue(new Callback<List<ThemeItem>>() {
            @Override
            public void onResponse(Call<List<ThemeItem>> mCall, Response<List<ThemeItem>> mResponse) {
                sendResonse_ThemeConfig(mResponse);
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
            if (view != null && mResponse.body()!=null && mResponse.body().size()>0) {
                view.retrofitResponse_ThemeConfig(mResponse.body().get(0));
            }else {
            }
        }
    }

    private void sendResonse_AdsConfig(Response<AdsConfigResponse> mResponse) {
        if (mResponse.code() == 200) {
            if (view != null && mResponse.body()!= null) {
                view.retrofitResponse_AdsConfig(mResponse.body());
            }
        }
    }
}
