package com.uvgouapp.presenter.other;


import com.blankj.utilcode.util.StringUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.uvgouapp.api.Api;
import com.uvgouapp.common.base.BaseActivity;
import com.uvgouapp.common.base.BasePresenter;
import com.uvgouapp.common.constant.Constants;
import com.uvgouapp.common.util.GsonUtil;
import com.uvgouapp.contract.other.ChooseCategoryContract;
import com.uvgouapp.model.other.ChooseCategoryBean;

import java.util.List;

/**
 * - @Author:  ying
 * - @Time:  2019/2/17
 * - @Description:  选择类目
 */
public class ChooseCategoryPresenter extends BasePresenter<ChooseCategoryContract.View> implements ChooseCategoryContract.Presenter {

    private BaseActivity mBaseActivity;

    public ChooseCategoryPresenter(BaseActivity baseActivity) {
        this.mBaseActivity = baseActivity;
    }

    @Override
    public void requestShopCategory() {
        if (!mBaseActivity.isConnectNetWorkAndBindView()) {
            return;
        }

        OkGo.<String>get(Api.QUERY_SHOP_CATEGORY)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response != null) {
                            String body = response.body();
                            if (!StringUtils.isEmpty(body)) {
                                ChooseCategoryBean chooseCategoryBean = GsonUtil.GsonToBean(body, ChooseCategoryBean.class);
                                if (chooseCategoryBean != null) {
                                    int statusCode = chooseCategoryBean.getStatusCode();
                                    if (statusCode == Constants.SUCCESS_CODE) {
                                        List<ChooseCategoryBean.DataBean> data = chooseCategoryBean.getData();
                                        if (data != null) {
                                            int size = data.size();
                                            if (size > 0) {
                                                mView.setShopCategoryList(data);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
    }
}
