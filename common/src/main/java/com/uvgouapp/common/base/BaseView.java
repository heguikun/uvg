package com.uvgouapp.common.base;

/**
 * - @Author:  ying
 * - @Time:  2018/12/22
 * - @Description:  视图基类
 */
public interface BaseView {

    /**
     * 显示加载中
     */
    void showLoading();

    /**
     * 隐藏加载
     */
    void hideLoading();

    /**
     * 数据获取失败
     *
     * @param throwable 异常
     */
    void onFailError(Throwable throwable);

}
