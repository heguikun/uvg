package com.uvgouapp.contract.shop;

import com.uvgouapp.common.base.BaseView;
import com.uvgouapp.model.shop.ShopCategoryBean;

import java.util.List;

/**
 * - @Author:  ying
 * - @Time:  2018/12/24
 * - @Description:  商城
 */
public interface ShopContract {

    interface Presenter {

        void requestShopLogin();

        void requestShopCategory(String categoryId, String current, String size);//查询商品类目

    }

    interface View extends BaseView {

        void setShopCategoryListData(List<ShopCategoryBean.DataBean.RecordsBean> listData);

    }
}
