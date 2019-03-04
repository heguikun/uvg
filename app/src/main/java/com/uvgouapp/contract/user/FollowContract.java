package com.uvgouapp.contract.user;


import com.uvgouapp.common.base.BaseView;
import com.uvgouapp.model.user.FollowFriendsBean;

import java.util.List;

/**
 * - @Author:  ying
 * - @Time:  2019/1/26
 * - @Description:  关注
 */
public interface FollowContract {

    interface Presenter {

        //用户id  当前页数  页面数量
        void requestUserQueryFollowFriends(String userId, String pageNo, String pageSize);

        //用户id   被关注用户id
        void requestCancleFollow(String userId, String solicitudeId);

    }

    interface View extends BaseView {

        void setFollowListData(List<FollowFriendsBean.MapListBean> listData);

    }

}
