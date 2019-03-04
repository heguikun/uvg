package com.uvgouapp.contract.circle;


import com.uvgouapp.common.base.BaseView;
import com.uvgouapp.model.circle.CircleLookAllBean;

import java.util.List;

/**
 * - @Author:  ying
 * - @Time:  2019/2/12
 * - @Description:  查看他人淘友圈信息
 */
public interface CircleLookOtherInfoContract {

    interface Presenter {

        //查询个人全部淘友圈   userId 用户id   current 当前页   size 页面记录数
        void requestCircleLookAll(String userId, String current, String size);

        //查询个人商品淘友圈   userId 用户id   current 当前页   size 页面记录数
        void requestCircleLookShop(String userId, String current, String size);

        //查询个人生活淘友圈   userId 用户id   current 当前页   size 页面记录数
        void requestCircleLookLive(String userId, String current, String size);

        //用户id 查询用户
        void requestQueryUserInfo(String userId);

    }

    interface View extends BaseView {

        void showBgImg(String bgImg);

        void showDefaultImg();

        void showHeadImg(String headImg);

        void showUsername(String username);

        void setCircleLookAllDataList(List<CircleLookAllBean> dataList);
    }
}
