package sms.layout.themejunky.com.layout_sms_lib.screens;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.lang.ref.WeakReference;

import module.themejunky.com.tj_gae.Module_GoogleAnalyticsEvents;

public class SlaveActivity extends AppCompatActivity {

    public Module_GoogleAnalyticsEvents mGAE;
    public RecyclerView list;

    protected GridLayoutManager setList_GridLayoutManager_Vertical(WeakReference<Activity> activity, final RecyclerView list, final RecyclerView.Adapter adapter, int cols) {
        GridLayoutManager GL = new GridLayoutManager(activity.get(), cols, LinearLayoutManager.VERTICAL, false);
        list.setLayoutManager(GL);
        list.setAdapter(adapter);
        return GL;
    }
}
