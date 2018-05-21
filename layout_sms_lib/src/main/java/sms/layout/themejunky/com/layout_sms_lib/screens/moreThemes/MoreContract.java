package sms.layout.themejunky.com.layout_sms_lib.screens.moreThemes;


import sms.layout.themejunky.com.layout_sms_lib.retrofit.model.InternalAds;
import sms.layout.themejunky.com.layout_sms_lib.retrofit.model.ThemeItem;

public class MoreContract {
    public interface View {
        void onMoreClick(InternalAds item);
        void showMoreResponse(ThemeItem themeConfig);
        void noInternetConnection();


    }

    public interface Presenter {
    }
}

