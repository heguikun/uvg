package com.uvgouapp.model.circle;


import java.util.ArrayList;
import java.util.List;

/**
 * - @Author:  ying
 * - @Time:  2018/12/29
 * - @Description:  生活
 */
public class LiveBean {

    public int tableId;

    //------------用户--------------
    public List<CircleUser> mUserList = new ArrayList<>();//用户

    public String imgUrl;//图片
    public String contentLive;//内容
    public Object thumbs;//点赞
    public Object collect;//收藏
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
