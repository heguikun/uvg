package com.uvgouapp.common.service;

import android.content.Context;
import android.content.Intent;
import android.os.Build;



/**
 * - @Author:  ying
 * - @Time:  2018/12/22
 * - @Description:  初始化X5WebView
 */
public class X5WebViewUtil {

    public static void initX5WebView(Context context) {
        // Android 8.0 还对特定函数做出了以下变更：
        // （1）如果针对 Android 8.0 的应用尝试在不允许其创建后台服务的情况下使用 startService() 函数，则该函数将引发一个 IllegalStateException。
        // 新的 Context.startForegroundService() 函数将启动一个前台服务。
        // （2）即使应用在后台运行，系统也允许其调用 Context.startForegroundService()。应用必须在创建服务后的五秒内调用该服务的 startForeground() 函数。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(new Intent(context, PreloadX5Service.class));
        } else {
            context.startService(new Intent(context, PreloadX5Service.class));
        }
    }
}
