package com.uvgouapp.adapter.circle;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.EncodeUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.uvgouapp.R;
import com.uvgouapp.common.util.ImageLoaderUtil;
import com.uvgouapp.model.circle.CircleLookAllBean;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;

/**
 * - @Author:  ying
 * - @Time:  2019/2/13
 * - @Description:  查看全部
 */
public class CircleLookAllAdapter extends BaseMultiItemQuickAdapter<CircleLookAllBean, BaseViewHolder> {

    public CircleLookAllAdapter(List<CircleLookAllBean> data) {
        super(data);
        addItemType(CircleLookAllBean.TYPE_SHOP, R.layout.item_circle_look_shop);
        addItemType(CircleLookAllBean.TYPE_LIVE, R.layout.item_circle_look_live);
    }

    @Override
    public void onViewRecycled(@NonNull BaseViewHolder holder) {
        super.onViewRecycled(holder);
        ImageView ivShop = holder.getView(R.id.iv_shop_photo);
        if (ivShop != null) {
            ImageLoaderUtil.clearImageView(mContext, ivShop);
            ImageLoaderUtil.GuideClearMemory(mContext);
        }

        ImageView ivLive = holder.getView(R.id.iv_live_photo);
        if (ivLive != null) {
            ImageLoaderUtil.clearImageView(mContext, ivLive);
            ImageLoaderUtil.GuideClearMemory(mContext);
        }
    }

    @Override
    protected void convert(BaseViewHolder helper, CircleLookAllBean item) {

        switch (helper.getItemViewType()) {
            //-----------------------商品---------------------
            case CircleLookAllBean.TYPE_SHOP:
                TextView tvShopDay = helper.getView(R.id.tv_shop_date_day);//天
                TextView tvShopMonth = helper.getView(R.id.tv_shop_date_month);//月
                ImageView ivShop = helper.getView(R.id.iv_shop_photo);//图片
                TextView tvShopDescribe = helper.getView(R.id.tv_shop_describe);//描述
                TextView tvShopPrice = helper.getView(R.id.tv_shop_price);//价格
                TextView tvShopSize = helper.getView(R.id.tv_shop_size);//多少张图片

                long createTimeShop = item.createTime;//日期
                String imgUrlShop = item.imgUrl;//图片
                String commodityDescribeShop = item.commodityDescribe;//描述
                double retailPriceShop = item.retailPrice;//价格
                if (createTimeShop > 0) {
                    Date date = TimeUtils.millis2Date(createTimeShop);
                    int day = TimeUtils.getValueByCalendarField(date, Calendar.DAY_OF_MONTH);
                    int month = TimeUtils.getValueByCalendarField(date, Calendar.MONTH + 1);
                    tvShopDay.setText(String.format(Locale.ENGLISH, "%d", day));
                    tvShopMonth.setText(String.format(Locale.ENGLISH, "%d月", month));
                }
                if (!StringUtils.isEmpty(imgUrlShop)) {
                    String[] split = imgUrlShop.split(",");
                    List<String> listShop = Arrays.asList(split);
                    ImageLoaderUtil.into(mContext, listShop.get(0), ivShop);
                    tvShopSize.setText(String.format(Locale.ENGLISH, "%d张", listShop.size()));
                }
                if (!StringUtils.isEmpty(commodityDescribeShop)) {
                    tvShopDescribe.setText(EncodeUtils.urlDecode(commodityDescribeShop, "UTF-8"));
                }
                if (retailPriceShop > 0) {
                    tvShopPrice.setText(String.format(Locale.ENGLISH, "价格：%.2f", retailPriceShop));
                }
                break;
            //-----------------------生活---------------------
            case CircleLookAllBean.TYPE_LIVE:
                TextView tvLiveDay = helper.getView(R.id.tv_live_date_day);//天
                TextView tvLiveMonth = helper.getView(R.id.tv_live_date_month);//月
                ImageView ivLive = helper.getView(R.id.iv_live_photo);//图片
                TextView tvLiveContent = helper.getView(R.id.tv_live_content);//内容
                TextView tvLiveSize = helper.getView(R.id.tv_live_size);//多少张图片

                if (item.createTime > 0) {
                    Date date = TimeUtils.millis2Date(item.createTime);
                    int day = TimeUtils.getValueByCalendarField(date, Calendar.DAY_OF_MONTH);
                    int month = TimeUtils.getValueByCalendarField(date, Calendar.MONTH + 1);
                    tvLiveDay.setText(String.format(Locale.ENGLISH, "%d", day));
                    tvLiveMonth.setText(String.format(Locale.ENGLISH, "%d月", month));
                }
                if (StringUtils.isEmpty(item.imgUrl)) {
                    ivLive.setVisibility(View.GONE);
                    tvLiveSize.setVisibility(View.GONE);
                } else {
                    String[] split = item.imgUrl.split(",");
                    List<String> listShop = Arrays.asList(split);
                    ImageLoaderUtil.into(mContext, listShop.get(0), ivLive);
                    tvLiveSize.setText(String.format(Locale.ENGLISH, "%d张", listShop.size()));
                    ivLive.setVisibility(View.VISIBLE);
                    tvLiveSize.setVisibility(View.VISIBLE);
                }
                if (!StringUtils.isEmpty(item.content)) {
                    tvLiveContent.setText(EncodeUtils.urlDecode(item.content, "UTF-8"));
                }
                break;
            default:
                break;
        }

    }
}
