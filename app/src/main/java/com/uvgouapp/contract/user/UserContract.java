package com.uvgouapp.contract.user;


import com.uvgouapp.common.base.BaseView;

/**
 * - @Author:  ying
 * - @Time:  2019/1/17
 * - @Description:  我的
 */
public interface UserContract {

    interface Presenter {

        //用户id   自己定义  true  点击查询   false 没有点击查询
        void requestQueryShop(String userId, boolean flag);

        //用户id 查询用户
        void requestQueryUserInfo(String userId);

    }

    interface View extends BaseView {

        void applyStore();

        void showHeadImg(String headImg);

        void showByname(String byname);

        void showShopName(String shopName);

        void loadWebView();

    }

}
