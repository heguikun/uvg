package com.uvgouapp.presenter.shop;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.uvgouapp.api.Api;
import com.uvgouapp.common.base.BaseFragment;
import com.uvgouapp.common.base.BasePresenter;
import com.uvgouapp.common.constant.Constants;
import com.uvgouapp.common.util.GsonUtil;
import com.uvgouapp.contract.shop.ShopContract;
import com.uvgouapp.model.shop.ShopCategoryBean;
import com.uvgouapp.model.user.User;

import java.util.List;


/**
 * - @Author:  ying
 * - @Time:  2018/12/24
 * - @Description:商城
 */
public class ShopPresenter extends BasePresenter<ShopContract.View> implements ShopContract.Presenter {

    private BaseFragment mBaseFragment;

    public ShopPresenter(BaseFragment baseFragment) {
        this.mBaseFragment = baseFragment;
    }

    @Override
    public void requestShopLogin() {
        if (!mBaseFragment.isConnectNetWorkAndBindView()) {
            return;
        }

        OkGo.<String>get(Api.USER_QUERY)
                .params("id", SPUtils.getInstance().getString("userId"))
                .execute(new StringCallback() {
                    @Override
                    public void onStart(Request<String, ? extends Request> request) {
                        super.onStart(request);
                        mView.showLoading();
                    }

                    @Override
                    public void onSuccess(Response<String> response) {
                        mView.hideLoading();
                        if (response != null) {
                            String body = response.body();
                            if (!StringUtils.isEmpty(body)) {
                                User user = GsonUtil.GsonToBean(body, User.class);
                                if (user != null) {
                                    if (user.getStatusCode() == Constants.SUCCESS_CODE) {
                                        User.DataBean data = user.getData();
                                        if (data != null) {
                                            String opendId = data.getOpendId();
                                            shopLogin(opendId);
                                        }
                                    }
                                }
                            }
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        mView.hideLoading();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        mView.hideLoading();
                    }

                });
    }

    /**
     * 商城登录
     */
    private void shopLogin(String opendId) {
        OkGo.<String>get(Api.USER_SHOP_LOGIN)
                .params("phone", opendId)
                .params("verifyCode", "797979")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                    }
                });
    }

    /**
     * @param categoryId 类目id
     * @param current    当前页
     * @param size       页面数量
     */
    @Override
    public void requestShopCategory(String categoryId, String current, String size) {
        if (!mBaseFragment.isConnectNetWorkAndBindView()) {
            return;
        }

        OkGo.<String>get(Api.SHOP_QUERY_CATEGORY)
                .params("categoryId", categoryId)
                .params("current", current)
                .params("size", size)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response != null) {
                            String body = response.body();
                            if (!StringUtils.isEmpty(body)) {
                                ShopCategoryBean shopCategoryBean = GsonUtil.GsonToBean(body, ShopCategoryBean.class);
                                if (shopCategoryBean != null) {
                                    if (shopCategoryBean.getStatusCode() == Constants.SUCCESS_CODE) {
                                        ShopCategoryBean.DataBean data = shopCategoryBean.getData();
                                        if (data != null) {
                                            List<ShopCategoryBean.DataBean.RecordsBean> records = data.getRecords();
                                            if (records != null) {
                                                int size = records.size();
                                                if (size > 0) {
                                                    mView.setShopCategoryListData(records);
                                                }
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
