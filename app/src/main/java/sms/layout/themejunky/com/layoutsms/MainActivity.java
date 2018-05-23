package sms.layout.themejunky.com.layoutsms;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import sms.layout.themejunky.com.layout_sms_lib.ManagerOnboarding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        finish(); //in order to not be returned to this screen after user exits the library activities
        ManagerOnboarding.setStatus(this, true); // if boolean isRating is set to true, the user is notified about the rating
    }
}
