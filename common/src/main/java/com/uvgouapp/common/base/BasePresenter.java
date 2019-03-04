package com.uvgouapp.common.base;

/**
 * - @Author:  ying
 * - @Time:  2018/12/22
 * - @Description:  presenter基类
 */
public class BasePresenter<V extends BaseView> {

    protected V mView;

    /**
     * 绑定view,一般在初始化中调用该方法
     *
     * @param view view
     */
    public void attachView(V view) {
        this.mView = view;
    }

    /**
     * 解除绑定view,一般在onDestroy中调用
     */

    public void detachView() {
        this.mView = null;
    }

    /**
     * View是否绑定
     *
     * @return true
     */
    public boolean isViewAttached() {
        return mView != null;
    }
}
