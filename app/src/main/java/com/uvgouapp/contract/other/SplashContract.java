package com.uvgouapp.contract.other;


import com.uvgouapp.common.base.BaseView;

/**
 * - @Author:  ying
 * - @Time:  2019/2/21
 * - @Description:  欢迎页
 */
public interface SplashContract {

    interface Presenter {

        //创建
        void onCreate();

        //请求版本更新
        void requestVersionUpdate();

    }

    interface View extends BaseView {

        void showRootWarning();

        void launchAfterPermission();

        void onReceiveNewVersion(String msg, String url);

        void onSuccessCheckVersion();

        void onFailError(String errorMsg);

    }

}
