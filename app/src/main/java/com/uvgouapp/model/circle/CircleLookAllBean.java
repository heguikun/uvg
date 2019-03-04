package com.uvgouapp.model.circle;


import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;

/**
 * - @Author:  ying
 * - @Time:  2019/2/13
 * - @Description: 查看全部
 */
public class CircleLookAllBean implements MultiItemEntity, Serializable {

    public static final int TYPE_SHOP = 0;
    public static final int TYPE_LIVE = 1;

    public int itemType; //0.商品  1.生活

    public long createTime;//日期
    public String imgUrl;//图片
    public String content;//内容
    public String commodityName;//商品名称
    public String commodityDescribe;//描述
    public double retailPrice;//价格

    @Override
    public int getItemType() {
        return itemType;
    }
}
