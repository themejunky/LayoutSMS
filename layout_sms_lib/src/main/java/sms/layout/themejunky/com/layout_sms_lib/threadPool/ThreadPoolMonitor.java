package sms.layout.themejunky.com.layout_sms_lib.threadPool;

import android.util.Log;

import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPoolMonitor implements Runnable {
    private ThreadPoolExecutor mTPE;
    private Boolean mR = true;

    public ThreadPoolMonitor(ThreadPoolExecutor nTPE) {
        mTPE = nTPE;
    }

    public void TPM_ShutDown() {
        mR = false;
    }

    @Override
    public void run() {
        while (mR) {
            Log.d("TPE_monitor", String.format("[monitor] [%d/%d] Active: %d, Completed: %d, Task: %d, isShutdown: %s, isTerminated: %s | in Queue : %s",
                    mTPE.getPoolSize(), mTPE.getCorePoolSize(), mTPE.getActiveCount(), mTPE.getCompletedTaskCount(),
                    mTPE.getTaskCount(), mTPE.isShutdown(), mTPE.isTerminated(), mTPE.getQueue()));

            try {
                Thread.sleep(1000);
            } catch (InterruptedException mE) {
                Log.w("Thread_Pool_Monitor", "Message : " + mE.getMessage());
            }
        }
    }
}