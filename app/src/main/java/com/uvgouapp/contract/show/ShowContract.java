package com.uvgouapp.contract.show;

import com.uvgouapp.common.base.BaseView;
import com.uvgouapp.model.show.ShowBean;

import java.util.List;

/**
 * - @Author:  ying
 * - @Time:  2019/1/8
 * - @Description: 秀场
 */
public interface ShowContract {

    interface Presenter {
        //经度,纬度,位置,范围,商品ID,类型(0：个人 1：订单),当前用户ID,当前页,页面数量
        void requestNewQueryAll(String lon, String lat, String positionName, String raidus, String commodityId,
                                String thumbsType, String userId, String pageNo, String pageSize);

        //被点赞信息ID  点赞类型 1生活圈  2商友圈 3秀场   用户ID
        void requestGive(int position, String isThumbsId, int thumbsType, String userId);

        // solicitudeId 被关注用户的ID   userId用户自己的ID
        void requestFollow(int position, String solicitudeId, String userId);
    }

    interface View extends BaseView {

        void setNewQueryAllListData(List<ShowBean.MapListBean> listData);

        void update2Follow(int position);

        void update2Favort(int position);
    }

}
