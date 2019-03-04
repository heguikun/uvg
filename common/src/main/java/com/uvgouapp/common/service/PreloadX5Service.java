package com.uvgouapp.common.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import com.tencent.smtt.sdk.QbSdk;
import com.uvgouapp.common.constant.Constants;

import androidx.annotation.Nullable;

/**
 * - @Author:  ying
 * - @Time:  2018/12/22
 * - @Description:
 */
public class PreloadX5Service extends IntentService {

    private static final String CHANNEL_ID = "CHANNEL_ID";
    private static final String CHANNEL_NAME = "CHANNEL_NAME";
    private Notification mNotification = null;

    public PreloadX5Service() {
        super("PreloadX5Service");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //Android 8.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            if (manager != null) {
                manager.createNotificationChannel(channel);
            }
            mNotification = new Notification.Builder(getApplicationContext(), CHANNEL_ID)
                    .setAutoCancel(true)
                    .setCategory(Notification.CATEGORY_SERVICE)
                    .setOngoing(true)
                    .build();
            startForeground(Constants.SERVICE_ID, mNotification);
        }
        initX5WebView();
        preinitX5WebCore();
    }

    @Override
    public void onStart(@Nullable Intent intent, int startId) {
        super.onStart(intent, startId);
        //Android 8.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (mNotification != null) {
                startForeground(Constants.SERVICE_ID, mNotification);
            }
        }
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
    }

    private void initX5WebView() {
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败,会自动切换到系统内核。
                Log.i("UWE", " onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(), cb);
    }

    private void preinitX5WebCore() {
        if (!QbSdk.isTbsCoreInited()) {
            QbSdk.preInit(this, new QbSdk.PreInitCallback() {
                @Override
                public void onCoreInitFinished() {
                }

                @Override
                public void onViewInitFinished(boolean b) {
                }
            });// 设置X5初始化完成的回调接口
        }
    }

}
