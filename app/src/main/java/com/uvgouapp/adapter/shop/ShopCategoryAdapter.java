package com.uvgouapp.adapter.shop;


import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.uvgouapp.R;
import com.uvgouapp.common.util.ImageLoaderUtil;
import com.uvgouapp.model.shop.ShopCategoryBean;

import java.util.Locale;

import androidx.annotation.NonNull;

/**
 * - @Author:  ying
 * - @Time:  2019/2/18
 * - @Description:  商品类别
 */
public class ShopCategoryAdapter extends BaseQuickAdapter<ShopCategoryBean.DataBean.RecordsBean, BaseViewHolder> {

    public ShopCategoryAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    public void onViewRecycled(@NonNull BaseViewHolder holder) {
        super.onViewRecycled(holder);
        ImageView imageView = holder.getView(R.id.iv_picture);
        if (imageView != null) {
            ImageLoaderUtil.clearImageView(mContext, imageView);
            ImageLoaderUtil.GuideClearMemory(mContext);
        }
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopCategoryBean.DataBean.RecordsBean item) {
        ImageView imageView = helper.getView(R.id.iv_picture);
        TextView tvDescribe = helper.getView(R.id.tv_describe);
        TextView tvPrice = helper.getView(R.id.tv_price);
        TextView tvVolume = helper.getView(R.id.tv_volume);

        String imgUrl = item.getImgUrl();
        String commodityName = item.getCommodityName();
        double retailPrice = item.getRetailPrice();

        if (!StringUtils.isEmpty(imgUrl)) {
            if (imgUrl.contains(",")) {
                String[] split = imgUrl.split(",");
                ImageLoaderUtil.into(mContext, split[0], imageView);
            } else {
                ImageLoaderUtil.into(mContext, imgUrl, imageView);
            }
        }

        if (!StringUtils.isEmpty(commodityName)) {
            tvDescribe.setText(commodityName);
        }
        if (retailPrice > 0) {
            tvPrice.setText(String.format(Locale.ENGLISH, "%.2f", retailPrice));
        }

    }
}
