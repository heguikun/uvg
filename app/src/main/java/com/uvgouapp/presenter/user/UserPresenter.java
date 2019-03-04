package com.uvgouapp.presenter.user;


import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.model.Result;
import com.lzy.okrx2.adapter.ObservableResult;
import com.trello.rxlifecycle3.android.FragmentEvent;
import com.uvgouapp.api.Api;
import com.uvgouapp.common.base.BaseFragment;
import com.uvgouapp.common.base.BasePresenter;
import com.uvgouapp.common.constant.Constants;
import com.uvgouapp.common.util.GsonUtil;
import com.uvgouapp.contract.user.UserContract;
import com.uvgouapp.model.user.StoreInfoBean;
import com.uvgouapp.model.user.User;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * - @Author:  ying
 * - @Time:  2019/1/17
 * - @Description:  我的
 */
public class UserPresenter extends BasePresenter<UserContract.View> implements UserContract.Presenter {

    private BaseFragment mBaseFragment;

    public UserPresenter(BaseFragment baseFragment) {
        this.mBaseFragment = baseFragment;
    }

    /**
     * 查询用户是否开店
     *
     * @param userId 用户id
     */
    @Override
    public void requestQueryShop(String userId, boolean flag) {

        if (!mBaseFragment.isConnectNetWorkAndBindView()) {
            return;
        }

        OkGo.<String>get(Api.QUERY_STORE)
                .params("userId", userId)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        if (!StringUtils.isEmpty(body)) {
                            StoreInfoBean storeInfoBean = GsonUtil.GsonToBean(body, StoreInfoBean.class);
                            if (storeInfoBean != null) {
                                int statusCode = storeInfoBean.getStatusCode();
                                if (statusCode == Constants.SUCCESS_CODE) {
                                    StoreInfoBean.DataBean data = storeInfoBean.getData();
                                    if (data != null) {
                                        int shopId = data.getId();
                                        //保存店铺id
                                        SPUtils.getInstance().put("shopId", String.valueOf(shopId));
                                        String shopName = data.getShopName();
                                        if (!StringUtils.isEmpty(shopName)) {
                                            mView.showShopName(shopName);
                                        }
                                        if (flag) {
                                            mView.loadWebView();
                                        }
                                    } else {
                                        if (flag) {
                                            mView.applyStore();
                                        }
                                    }
                                }
                            }
                        }
                    }
                });

    }

    /**
     * 查询用户信息
     *
     * @param userId 用户id
     */
    @Override
    public void requestQueryUserInfo(String userId) {

        if (!mBaseFragment.isConnectNetWorkAndBindView()) {
            return;
        }

        OkGo.<String>get(Api.QUERY_MY)
                .params("id", userId)
                .converter(new StringConvert())
                .adapt(new ObservableResult<>())
                .subscribeOn(Schedulers.io())
                .compose(mBaseFragment.<Result<String>>bindUntilEvent(FragmentEvent.DESTROY))
                .doOnSubscribe(new Consumer<Disposable>() {

                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mView.showLoading();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<String>>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        mBaseFragment.addDisposable(d);
                    }

                    @Override
                    public void onNext(Result<String> stringResult) {
                        if (stringResult != null) {
                            String body = stringResult.response().body();
                            if (!StringUtils.isEmpty(body)) {
                                User user = GsonUtil.GsonToBean(body, User.class);
                                if (user != null) {
                                    int statusCode = user.getStatusCode();
                                    if (statusCode == Constants.SUCCESS_CODE) {
                                        User.DataBean data = user.getData();
                                        if (data != null) {
                                            String headImg = data.getHeadImg();
                                            String byname = data.getName();
                                            if (!StringUtils.isEmpty(headImg)) {
                                                mView.showHeadImg(headImg);
                                            }
                                            if (!StringUtils.isEmpty(byname)) {
                                                mView.showByname(byname);
                                            }
                                        }
                                    }
                                }
                            }
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.onFailError(e);
                    }

                    @Override
                    public void onComplete() {
                        mView.hideLoading();
                    }
                });

    }
}
