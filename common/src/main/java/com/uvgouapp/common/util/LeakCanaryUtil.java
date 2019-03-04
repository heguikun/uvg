package com.uvgouapp.common.util;


import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

public class LeakCanaryUtil {

    public static void install(Application application) {
        if (LeakCanary.isInAnalyzerProcess(application)) {
            // 判断是否和 LeakCanary 初始化同一进程
            return;
        }
        LeakCanary.install(application);
    }

}
