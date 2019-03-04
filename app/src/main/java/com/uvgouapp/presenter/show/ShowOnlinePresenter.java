package com.uvgouapp.presenter.show;


import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.uvgouapp.api.Api;
import com.uvgouapp.common.base.BaseActivity;
import com.uvgouapp.common.base.BasePresenter;
import com.uvgouapp.common.constant.Constants;
import com.uvgouapp.common.util.GsonUtil;
import com.uvgouapp.contract.show.ShowOnlineContract;
import com.uvgouapp.model.other.InfoBean;
import com.uvgouapp.model.show.RocketNumberBean;
import com.uvgouapp.model.show.ShowOnlineBean;
import com.uvgouapp.model.user.User;

import java.util.List;

/**
 * - @Author:  ying
 * - @Time:  2019/1/11
 * - @Description:  在线
 */
public class ShowOnlinePresenter extends BasePresenter<ShowOnlineContract.View> implements ShowOnlineContract.Presenter {

    private BaseActivity mBaseActivity;


    public ShowOnlinePresenter(BaseActivity baseActivity) {
        this.mBaseActivity = baseActivity;
    }

    @Override
    public void requestUserOnline(String lon, String lat, String raidus, String pageNo, String pageSize) {

        if (!mBaseActivity.isConnectNetWorkAndBindView()) {
            return;
        }

        OkGo.<String>get(Api.USER_ONLINE)
                .params("lon", lon)
                .params("lat", lat)
                .params("raidus", raidus)
                .params("pageNo", pageNo)
                .params("pageSize", pageSize)
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
                                ShowOnlineBean showOnlineBean = GsonUtil.GsonToBean(body, ShowOnlineBean.class);
                                if (showOnlineBean != null) {
                                    List<ShowOnlineBean.MapListBean> mapList = showOnlineBean.getMapList();
                                    if (mapList != null) {
                                        int size = mapList.size();
                                        if (size > 0) {
                                            mView.setOnlineData(mapList);
                                        } else {
                                            ToastUtils.showShort("没有更多数据");
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
     * 火箭数量
     *
     * @param userId 用户id
     */
    @Override
    public void requestRocketNumber(String userId) {

        if (!mBaseActivity.isConnectNetWorkAndBindView()) {
            return;
        }

        OkGo.<String>get(Api.ROCKET_QUERY_NUMBER)
                .params("userId", userId)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response != null) {
                            String body = response.body();
                            if (!StringUtils.isEmpty(body)) {
                                RocketNumberBean rocketNumberBean = GsonUtil.GsonToBean(body, RocketNumberBean.class);
                                if (rocketNumberBean != null) {
                                    int statusCode = rocketNumberBean.getStatusCode();
                                    if (statusCode == Constants.SUCCESS_CODE) {
                                        RocketNumberBean.DataBean data = rocketNumberBean.getData();
                                        if (data != null) {
                                            int sumNumber = data.getSumNumber();
                                            if (sumNumber >= 0) {
                                                mView.showRocketNumber(sumNumber);
                                            } else {
                                                mView.showRocketNumber(0);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
    }

    /**
     * 火箭置顶
     *
     * @param userId 用户id
     */
    @Override
    public void requestRocketTop(String userId) {
        if (!mBaseActivity.isConnectNetWorkAndBindView()) {
            return;
        }
        OkGo.<String>get(Api.ROCKET_PUT_TOP)
                .params("userId", userId)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response != null) {
                            String body = response.body();
                            if (!StringUtils.isEmpty(body)) {
                                InfoBean infoBean = GsonUtil.GsonToBean(body, InfoBean.class);
                                if (infoBean != null) {
                                    int statusCode = infoBean.getStatusCode();
                                    if (statusCode == Constants.SUCCESS_CODE) {
                                        mView.onSuccess();
                                    } else {
                                        mView.onFail(infoBean.getMsg());
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
        if (!mBaseActivity.isConnectNetWorkAndBindView()) {
            return;
        }
        OkGo.<String>get(Api.QUERY_MY)
                .params("id", userId)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response != null) {
                            String body = response.body();
                            if (!StringUtils.isEmpty(body)) {
                                User user = GsonUtil.GsonToBean(body, User.class);
                                if (user != null) {
                                    int statusCode = user.getStatusCode();
                                    if (statusCode == Constants.SUCCESS_CODE) {
                                        User.DataBean data = user.getData();
                                        if (data != null) {
                                            String headImg = data.getHeadImg();
                                            if (!StringUtils.isEmpty(headImg)) {
                                                mView.showUserHeadImg(headImg);
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
