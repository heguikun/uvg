package com.uvgouapp.model.circle;


import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * - @Author:  ying
 * - @Time:  2019/2/20
 * - @Description:  全部
 */
public class CircleAllBean implements MultiItemEntity {

    public static final int TYPE_SHOP = 0;
    public static final int TYPE_LIVE = 1;

    public int itemType; //0.商品  1.生活

    public int id;
    public int tableId;//实体id

    public String userId;//用户id
    public String userName;//用户名
    public String headImg;//用户头像
    public int tableType;//区分商品,生活

    public long createTime;//时间
    public String imgUrl;//图片
    public Object longitude;//经度
    public Object latitude;//纬度
    public String positionName;//位置
    public Object deleted;
    public Object thumbs;//点赞
    public Object collect;//收藏

    public String content;//生活内容

    public double originalPrice;//成本价
    public double retailPrice;//零售价
    public double discountPrice;//促销价
    public String commodityShopId;//商品id
    public String commodityName;//商品名称
    public String commodityCode;//商品编码
    public Object commodityType;//商品类型
    public Object commodityTitle;//商品标题
    public String commodityDescribe;//商品描述
    public Object commodityUnit;//商品规格

    public List<FavortItem> favorters = new ArrayList<>(); //点赞
    public List<CommentItem> comments = new ArrayList<>(); //评论

    public boolean hasFavort() {
        if (favorters.size() > 0) {
            return true;
        }
        return false;
    }

    public boolean hasComment() {
        if (comments.size() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

}
