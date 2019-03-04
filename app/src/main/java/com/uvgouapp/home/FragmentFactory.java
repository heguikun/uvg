package com.uvgouapp.home;

import com.uvgouapp.common.base.BaseFragment;
import com.uvgouapp.ui.circle.CircleFragment;
import com.uvgouapp.ui.news.NewsFragment;
import com.uvgouapp.ui.shop.ShopFragment;
import com.uvgouapp.ui.show.ShowFragment;
import com.uvgouapp.ui.user.UserFragment;

/**
 * - @Author:  ying
 * - @Time:  2018/12/23
 * - @Description:  fragment工厂
 */
public class FragmentFactory {

    private FragmentFactory() {
    }

    private static class Instance {
        private static final FragmentFactory instance = new FragmentFactory();
    }

    /**
     * @return 静态内部类  单例模式
     */
    public static FragmentFactory getInstance() {
        return Instance.instance;
    }

    /**
     * @return 商城
     */
    public BaseFragment createShopFragment() {
        return new ShopFragment();
    }

    /**
     * @return 秀场
     */
    public BaseFragment createShowFragment() {
        return new ShowFragment();
    }

    /**
     * @return 消息
     */
    public BaseFragment createNewsFragment() {
        return new NewsFragment();
    }

    /**
     * @return 淘友圈
     */
    public BaseFragment createCircleTwoFragment() {
        return new CircleFragment();
    }

    /**
     * @return 我的
     */
    public BaseFragment createUserFragment() {
        return new UserFragment();
    }

}
