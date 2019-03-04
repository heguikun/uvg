package com.uvgouapp.common.util;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.blankj.utilcode.util.ScreenUtils;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.uvgouapp.common.R;

import java.io.File;

/**
 * - @Author:  ying
 * - @Time:  2018/12/22
 * - @Description:  图片加载工具类
 */
public class ImageLoaderUtil {

    private ImageLoaderUtil() {
    }

    private static RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            //.format(DecodeFormat.PREFER_RGB_565)
            .skipMemoryCache(true)//跳过内存缓存
            .placeholder(R.color.bg_no_photo)
            .dontAnimate()//去掉glide 自带的效果，防止加载自定义控件时只显示替换图
            .override(ScreenUtils.getScreenWidth(), ScreenUtils.getScreenHeight()) //指定图片的宽度和高度
            .fitCenter()
            //.centerCrop()
            //.error(R.mipmap.ic_launcher)
            .priority(Priority.HIGH);//下载的优先级

    public static void clearDiskCache(final Context context) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                GlideApp.get(context).clearDiskCache();
                return null;
            }
        }.execute();
    }

    /**
     * glide 从字符串中加载图片（网络地址或者本地地址）
     */
    public static void into(Context context, String url, ImageView imageView) {
        GlideApp.with(context)
                .load(url)
                .apply(OPTIONS)
                .into(imageView);
    }

    /**
     * glide 加载资源图片
     */
    public static void into(Context context, int id, ImageView imageView) {
        GlideApp.with(context)
                .load(id)
                .apply(OPTIONS)
                .into(imageView);
    }

    /**
     * glide 从文件中加载图片
     */
    public static void into(Context context, File file, ImageView imageView) {
        GlideApp.with(context)
                .load(file)
                .apply(OPTIONS)
                .into(imageView);
    }

    /**
     * glide 从URI中加载图片
     */
    public static void into(Context context, Uri uri, ImageView imageView) {
        GlideApp.with(context)
                .load(uri)
                .apply(OPTIONS)
                .into(imageView);
    }

    /**
     * 恢复请求  一般在停止滚动的时候
     */
    public static void resumeRequests(Context context) {
        GlideApp.with(context)
                .resumeRequests();
    }

    /**
     * 暂停请求 正在滚动的时候
     */
    public static void pauseRequests(Context context) {
        GlideApp.with(context)
                .pauseRequests();
    }

    /**
     * 清理磁盘缓存
     */
    public static void GuideClearDiskCache(Context context) {
        //理磁盘缓存 需要在子线程中执行
        GlideApp.get(context)
                .clearDiskCache();
    }

    /**
     * 清理内存缓存
     */
    public static void GuideClearMemory(Context context) {
        //清理内存缓存  可以在UI主线程中进行
        GlideApp.get(context).clearMemory();
    }

    /**
     * @param context   上下文
     * @param imageView 清理图片
     */
    public static void clearImageView(Context context, ImageView imageView) {
        GlideApp.with(context).clear(imageView);
    }

}
