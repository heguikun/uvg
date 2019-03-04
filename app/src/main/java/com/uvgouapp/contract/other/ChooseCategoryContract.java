package com.uvgouapp.contract.other;


import com.uvgouapp.common.base.BaseView;
import com.uvgouapp.model.other.ChooseCategoryBean;

import java.util.List;

/**
 * - @Author:  ying
 * - @Time:  2019/2/17
 * - @Description:  选择类目
 */
public interface ChooseCategoryContract {

    interface Presenter {

        void requestShopCategory();
    }

    interface View extends BaseView {

        void setShopCategoryList(List<ChooseCategoryBean.DataBean> listData);
    }
}
