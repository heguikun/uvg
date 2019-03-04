package com.uvgouapp.view;

import android.content.Context;
import android.widget.ImageView;

import com.uvgouapp.common.util.ImageLoaderUtil;
import com.youth.banner.loader.ImageLoader;

/**
 * - @Author:  ying
 * - @Time:  2018/12/24
 * - @Description:  图片加载器
 */
public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {

        ImageLoaderUtil.into(context, (String) path, imageView);

    }
}
