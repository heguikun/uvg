package com.uvgouapp.contract.circle;


import com.uvgouapp.common.base.BaseView;
import com.uvgouapp.model.user.ShopBean;

import java.util.List;

/**
 * - @Author:  ying
 * - @Time:  2019/1/29
 * - @Description:  查看圈个人信息
 */
public interface CircleInformationContract {

    interface Presenter {
        //查询用户是否关注  userId 用户id    solicitudeId 被关注用户ID
        void requestQueryUserFollow(String userId, String solicitudeId);

        //关注用户   solicitudeId 被关注用户id  userId   用户自己id
        void requestFollow(String solicitudeId, String userId);

        //用户id 查询用户
        void requestQueryUserInfo(String userId);

        //用户id  查询店铺
        void requestQueryShopInfo(String userId);

        //用户id  查询自己店铺
        void requestQueryMyShopInfo(String userId);

        //用户id  查询用户全部动态   当前页  页面数量
        void requestQueryUserLiveDynamic(String userId, String current, String size);

        //用户id   查询用户全部商品
        void requestQueryUserShop(String userId, String pageNo, String pageSize);

        //一键开店   commodityId 商品id  commodityType  商品类型(0：直销 1：分销)  createBy 用户自己id  modifyBy  用户自己id  shopId   店铺ID
        void requestOneKeyShop(List<String> idList, int commodityType, String createBy, String modifyBy, String shopId);

        //查询可以复制的商品   自己店铺id   被拷贝店铺ID  当前页  页面数量
        void requestYesCopyShop(String shopId, String copyId, String pageNo, String pageSize);

    }

    interface View extends BaseView {

        void showBgImage(String bgImage);

        void showDefaultImg();

        void showHeadImage(String headImage);

        void showUsername(String username);

        void showSex(boolean sex);

        void showDateOfBirth(String dateOfBirth);

        void showConstellation(String constellation);

        void showEmotionalState(String emotionalState);

        void showObode(String obode);

        void showOccupation(String occupation);

        void showStature(String stature);

        void showWeight(String weight);

        void showShopName(String shopName);

        void showMainOperation(String mainOperation);

        void setShopListData(List<ShopBean.MapListBean> listData);

        void setCopyShopListData(List<ShopBean.MapListBean> listData);

        void showLiveImage1(List<String> listImage);

        void showLiveImage2(List<String> listImage);

        void showLiveImage3(List<String> listImage);

        void applyStore();

        void onSuccess();

        void onSuccess(String msg);

        void AddFollow();

        void alreadyFollow();

        void followSuccess();

    }

}
