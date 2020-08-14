package com.example.alarm.helpers;

import android.content.Context;
import android.os.Build;
import android.os.PowerManager;

public class SleepWakeScreen {

    public static void screenWake(Context mContext) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            PowerManager nPowerManager = (PowerManager) mContext.getSystemService(Context.POWER_SERVICE);
            if (!nPowerManager.isInteractive()) {
                PowerManager.WakeLock nWakeLock = nPowerManager.newWakeLock
                        (PowerManager.FULL_WAKE_LOCK
                                        | PowerManager.ACQUIRE_CAUSES_WAKEUP
                                        | PowerManager.ON_AFTER_RELEASE,
                                "com.example.alarm:screen_lock");
                nWakeLock.acquire(10_000);
                PowerManager.WakeLock nW = nPowerManager.newWakeLock
                        (PowerManager.PARTIAL_WAKE_LOCK,
                                "com.example.alarm:screen_lock");
            }

        }

    }
}
