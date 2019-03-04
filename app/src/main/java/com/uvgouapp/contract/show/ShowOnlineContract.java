package com.uvgouapp.contract.show;

import com.uvgouapp.common.base.BaseView;
import com.uvgouapp.model.show.ShowOnlineBean;

import java.util.List;

/**
 * - @Author:  ying
 * - @Time:  2019/1/11
 * - @Description:  在线
 */
public interface ShowOnlineContract {

    interface Presenter {

        //-----秀场用户在线  lon 经度 lat 纬度  raidus范围 (米为单位)  pageNo当前页  pageSize页面数量
        void requestUserOnline(String lon, String lat, String raidus, String pageNo, String pageSize);

        //查询火箭数量
        void requestRocketNumber(String userId);

        //火箭置顶
        void requestRocketTop(String userId);

        //用户id 查询用户
        void requestQueryUserInfo(String userId);

    }

    interface View extends BaseView {

        void setOnlineData(List<ShowOnlineBean.MapListBean> data);

        void showRocketNumber(int sumNumber);

        void showUserHeadImg(String headImg);

        void onSuccess();

        void onFail(String msg);
    }

}
