package com.uvgouapp.adapter.user;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.uvgouapp.R;
import com.uvgouapp.common.util.ImageLoaderUtil;
import com.uvgouapp.model.user.ShopBean;

import java.util.Locale;

/**
 * - @Author:  ying
 * - @Time:  2019/1/29
 * - @Description:  商品
 */
public class ShopAdapter extends BaseQuickAdapter<ShopBean.MapListBean, BaseViewHolder> {

    private boolean flag;//标记

    public ShopAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopBean.MapListBean item) {

        ImageView imageView = helper.getView(R.id.iv_picture);//图片
        TextView tvTradePrice = helper.getView(R.id.tv_trade_price);//批发价
        TextView tvDescribe = helper.getView(R.id.tv_describe);//描述
        TextView tvSelect = helper.getView(R.id.tv_select_shop);//选中
        TextView tvPrice = helper.getView(R.id.tv_price);//价格
        TextView tvVolume = helper.getView(R.id.tv_volume);

        String imgUrl = item.getImgUrl();
        String shopId = SPUtils.getInstance().getString("shopId");//商铺id
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
        //商铺判断显示批发价
        if (StringUtils.isEmpty(shopId)) {
            tvTradePrice.setVisibility(View.GONE);
        } else {
            tvTradePrice.setVisibility(View.VISIBLE);
        }
        if (!StringUtils.isEmpty(commodityName)) {
            tvDescribe.setText(commodityName);
        }
        if (retailPrice > 0) {
            tvPrice.setText(String.format(Locale.ENGLISH, "%.2f", retailPrice));
        }

        if (flag) {
            tvSelect.setVisibility(View.VISIBLE);
        } else {
            tvSelect.setVisibility(View.GONE);
        }

    }

    public void setFlag(boolean flag) {
        this.flag = flag;
        notifyDataSetChanged();
    }

}
