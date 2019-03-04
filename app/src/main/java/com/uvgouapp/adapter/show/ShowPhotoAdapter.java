package com.uvgouapp.adapter.show;

import android.widget.ImageView;

import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.uvgouapp.R;
import com.uvgouapp.common.util.ImageLoaderUtil;

import androidx.annotation.NonNull;


/**
 * - @Author:  ying
 * - @Time:  2019/1/9
 * - @Description:  秀场图片
 */
public class ShowPhotoAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public ShowPhotoAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    public void onViewRecycled(@NonNull BaseViewHolder holder) {
        super.onViewRecycled(holder);
        ImageView imageView = holder.getView(R.id.iv_photo);
        if (imageView != null) {
            ImageLoaderUtil.clearImageView(mContext, imageView);
            ImageLoaderUtil.GuideClearMemory(mContext);
        }
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

        ImageView imageView = helper.getView(R.id.iv_photo);
        if (!StringUtils.isEmpty(item)) {
            ImageLoaderUtil.into(mContext, item, imageView);
        }

    }
}
