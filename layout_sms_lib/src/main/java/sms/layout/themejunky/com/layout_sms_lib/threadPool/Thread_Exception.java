package sms.layout.themejunky.com.layout_sms_lib.threadPool;

import android.util.Log;

public class Thread_Exception implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Log.w("Thread_Exception", "TN : " + t.getName() + " ER : " + e.getMessage());
    }
}
