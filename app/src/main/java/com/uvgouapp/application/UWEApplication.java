package com.uvgouapp.application;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.tencent.bugly.crashreport.CrashReport;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;
import com.uvgouapp.common.service.X5WebViewUtil;
import com.uvgouapp.common.util.GlideApp;
import com.uvgouapp.common.util.ImageLoaderUtil;
import com.uvgouapp.common.util.LeakCanaryUtil;
import com.uvgouapp.common.util.OkGoUtil;

/**
 * - @Author:  ying
 * - @Time:  2018/12/22
 * - @Description:
 */
public class UWEApplication extends Application {

    private static UWEApplication mApplication = null;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplication = this;

        //初始化Bugly
        CrashReport.initCrashReport(this, "0e6d3c3eaa", true);
        //初始化LeakCanary
        LeakCanaryUtil.install(this);
        //初始化工具类
        Utils.init(this);
        //初始化OkGo
        OkGoUtil.initOkGo(this);
        //初始化X5WebView
        X5WebViewUtil.initX5WebView(this);
        //初始化扫一扫
        ZXingLibrary.initDisplayOpinion(this);
        //清理缓存
        ImageLoaderUtil.clearDiskCache(this);

    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        switch (level) {
            case TRIM_MEMORY_UI_HIDDEN:
                GlideApp.get(this).clearMemory();
                break;
            case TRIM_MEMORY_RUNNING_LOW:
                ImageLoaderUtil.GuideClearMemory(this);
                break;
            default:
                break;
        }
        GlideApp.get(this).trimMemory(level);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        //内存低的时候   清理Glide的缓存
        GlideApp.get(this).clearMemory();
    }

    /**
     * @return 为整个应用的其他模块提供上下文
     */
    public static UWEApplication getInstance() {
        return mApplication;
    }
}
