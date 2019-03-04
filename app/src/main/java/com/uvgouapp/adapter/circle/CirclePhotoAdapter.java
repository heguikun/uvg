package com.uvgouapp.adapter.circle;

import android.widget.ImageView;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.uvgouapp.R;
import com.uvgouapp.common.util.ImageLoaderUtil;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * - @Author:  ying
 * - @Time:  2018/12/29
 * - @Description:
 */
public class CirclePhotoAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public CirclePhotoAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
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

        int size = getData().size();

        if (size == 1) {//一张图片的话   图片调大
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ScreenUtils.getScreenWidth() / 2, ScreenUtils.getScreenWidth() / 2);
            imageView.setLayoutParams(layoutParams);
        } else {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int) mContext.getResources().getDimension(R.dimen.dp_80));
            layoutParams.setMargins((int) mContext.getResources().getDimension(R.dimen.dp_3), (int) mContext.getResources().getDimension(R.dimen.dp_3),
                    (int) mContext.getResources().getDimension(R.dimen.dp_3), (int) mContext.getResources().getDimension(R.dimen.dp_3));
            imageView.setLayoutParams(layoutParams);
        }

        if (!StringUtils.isEmpty(item)) {
            ImageLoaderUtil.into(mContext, item, imageView);
        }

    }
}
