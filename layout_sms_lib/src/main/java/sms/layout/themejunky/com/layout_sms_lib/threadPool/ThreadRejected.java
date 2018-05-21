package sms.layout.themejunky.com.layout_sms_lib.threadPool;

import android.util.Log;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadRejected implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable nR, ThreadPoolExecutor nE) {
        Log.d("Thread_Rejected", " Message : " + nR.toString() + " Queue size : " + nE.getQueue().size());
    }
}