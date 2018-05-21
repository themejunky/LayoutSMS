package sms.layout.themejunky.com.layout_sms_lib.screens.main;

import sms.layout.themejunky.com.layout_sms_lib.retrofit.model.AdsConfigResponse;
import sms.layout.themejunky.com.layout_sms_lib.retrofit.model.ThemeItem;

public class MainContract {

    public interface Retroift {
        void retrofitResponse_AdsConfig(AdsConfigResponse body);
        void retrofitResponse_ThemeConfig(ThemeItem body);

        void noInternetConnection();
    }

    public interface View {
        void stopLoader();
    }
    interface Presenter {
    }

}
