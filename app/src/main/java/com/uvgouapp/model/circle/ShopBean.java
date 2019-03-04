package com.uvgouapp.model.circle;


import java.util.ArrayList;
import java.util.List;

/**
 * - @Author:  ying
 * - @Time:  2018/12/29
 * - @Description:  商品
 */
public class ShopBean {

    public int tableId;//实体id

    //---------------用户--------------
    public List<CircleUser> mUserList = new ArrayList<>();//用户

    public String commodityTitle;//商品标题
    public String commodityName;//商品名称
    public String commodityCode;//商品编码
    public String commodityType;//商品类型
    public String commodityUnit;//商品规格
    public String commodityDescribe;//商品描述
    public double originalPrice;//成本价
    public double retailPrice;//零售价
    public String discountPrice;//促销价
    public Object collect;//收藏
    public Object thumbs;//点赞
    public String imgUrl;//图片
    public long createTime;//日期

    public List<FavortItem> favorters = new ArrayList<>(); //点赞
    public List<CommentItem> comments = new ArrayList<>(); //评论

    public boolean hasFavort() {
        if (favorters != null && favorters.size() > 0) {
            return true;
        }
        return false;
    }

    public boolean hasComment() {
        if (comments != null && comments.size() > 0) {
            return true;
        }
        return false;
    }

}
