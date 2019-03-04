package com.uvgouapp.contract.circle;


import com.luck.picture.lib.entity.LocalMedia;
import com.uvgouapp.common.base.BaseView;
import com.uvgouapp.model.circle.CircleAllBean;
import com.uvgouapp.model.circle.CommentConfig;
import com.uvgouapp.model.circle.CommentItem;
import com.uvgouapp.model.circle.FavortConfig;
import com.uvgouapp.model.circle.FavortItem;

import java.util.List;

/**
 * - @Author:  ying
 * - @Time:  2019/1/20
 * - @Description:  淘友圈
 */
public interface CircleContract {

    interface Presenter {

        //淘友圈全部动态  用户id  当前页   页面记录数
        void requestCircleAllData(String userId, String current, String size);

        //淘友圈商品动态  用户id  当前页   页面记录数
        void requestShopDynamic(String userId, String current, String size);

        //淘友圈生活动态  用户id  当前页   页面记录数
        void requestLiveDynamic(String userId, String current, String size);

        //被点赞信息ID  点赞类型 1生活圈  2商品圈 3秀场   用户ID
        void requestGive(FavortConfig favortConfig, String userId);

        //回复评论  commentType 评论的类型(0:普通评论 1：对人回复)  content 评论内容
        // contentUser  当前评论的用户id   当前评论的用户id  被回复的用户id   tableId    tableType
        void replyComment(String content, CommentConfig commentConfig);

        //添加收藏  商品id  用户id  店铺id
        void addCollect(String commodityId, String userId, String shopId, int position);

        //用户id 查询用户
        void requestQueryUserInfo(String userId);

        //上传图片
        void uploadPictures(LocalMedia localMedia, int type);

    }

    interface View extends BaseView {

        void showBgImg(String bgImg);

        void showDefaultImg();

        void showHeadImg(String headImg);

        void showUsername(String username);

        void updateloadCircleData(List<CircleAllBean> datas);

        void updateAddFavorite(int itemType, int position, FavortItem favortItem);

        void updateReplyComment(int tableType, int position, CommentItem commentItem);

        void updateCollect(int position, boolean collect);

    }

}
