package com.uvgouapp.presenter.circle;


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
import com.uvgouapp.contract.circle.CircleLookOtherInfoContract;
import com.uvgouapp.model.circle.CircleLookAllBean;
import com.uvgouapp.model.circle.CircleLookAllDynamic;
import com.uvgouapp.model.circle.CircleLookShopDynamic;
import com.uvgouapp.model.user.LiveBean;
import com.uvgouapp.model.user.User;

import java.util.ArrayList;
import java.util.List;

/**
 * - @Author:  ying
 * - @Time:  2019/2/12
 * - @Description:  查看他人淘友圈信息
 */
public class CircleLookOtherInfoPresenter extends BasePresenter<CircleLookOtherInfoContract.View> implements CircleLookOtherInfoContract.Presenter {

    private BaseActivity mBaseActivity;

    public CircleLookOtherInfoPresenter(BaseActivity baseActivity) {
        this.mBaseActivity = baseActivity;
    }

    @Override
    public void requestCircleLookAll(String userId, String current, String size) {
        if (!mBaseActivity.isConnectNetWorkAndBindView()) {
            return;
        }

        OkGo.<String>get(Api.CIRCLE_QUERY_USER_ALL_DYNAMIC)
                .params("userId", userId)
                .params("current", current)
                .params("size", size)
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
                                CircleLookAllDynamic circleLookAllDynamic = GsonUtil.GsonToBean(body, CircleLookAllDynamic.class);
                                if (circleLookAllDynamic != null) {
                                    int statusCode = circleLookAllDynamic.getStatusCode();
                                    if (statusCode == Constants.SUCCESS_CODE) {
                                        CircleLookAllDynamic.DataBean data = circleLookAllDynamic.getData();
                                        if (data != null) {
                                            List<CircleLookAllDynamic.DataBean.RecordsBean> records = data.getRecords();
                                            if (records != null) {
                                                int size = records.size();
                                                if (size > 0) {
                                                    List<CircleLookAllBean> list = new ArrayList<>();
                                                    for (int i = 0; i < size; i++) {
                                                        CircleLookAllBean circleLookAllBean = new CircleLookAllBean();
                                                        CircleLookAllDynamic.DataBean.RecordsBean recordsBean = records.get(i);
                                                        int tableType = recordsBean.getTableType();
                                                        switch (tableType) {
                                                            //---------------商品---------------
                                                            case 0:
                                                                circleLookAllBean.itemType = 0;
                                                                circleLookAllBean.commodityName = recordsBean.getCommodityName();
                                                                circleLookAllBean.createTime = recordsBean.getCreateTime();
                                                                circleLookAllBean.imgUrl = recordsBean.getImgUrl();
                                                                circleLookAllBean.commodityDescribe = recordsBean.getCommodityDescribe();
                                                                circleLookAllBean.retailPrice = recordsBean.getRetailPrice();
                                                                break;
                                                            //---------------生活----------------
                                                            case 1:
                                                                circleLookAllBean.itemType = 1;
                                                                circleLookAllBean.createTime = recordsBean.getCreateTime();
                                                                circleLookAllBean.content = recordsBean.getContent();
                                                                String imgUrl = recordsBean.getImgUrl();
                                                                if (StringUtils.isEmpty(imgUrl)) {
                                                                    circleLookAllBean.imgUrl = "";
                                                                } else {
                                                                    circleLookAllBean.imgUrl = imgUrl;
                                                                }
                                                                break;
                                                            default:
                                                                break;
                                                        }
                                                        list.add(circleLookAllBean);
                                                    }
                                                    mView.setCircleLookAllDataList(list);
                                                } else {
                                                    ToastUtils.showShort("没有更多的数据");
                                                }
                                            }
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

    @Override
    public void requestCircleLookShop(String userId, String current, String size) {
        if (!mBaseActivity.isConnectNetWorkAndBindView()) {
            return;
        }

        OkGo.<String>get(Api.CIRCLE_QUERY_USER_SHOP_DYNAMIC)
                .params("userId", userId)
                .params("current", current)
                .params("size", size)
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
                                CircleLookShopDynamic circleLookShopDynamic = GsonUtil.GsonToBean(body, CircleLookShopDynamic.class);
                                if (circleLookShopDynamic != null) {
                                    int statusCode = circleLookShopDynamic.getStatusCode();
                                    if (statusCode == Constants.SUCCESS_CODE) {
                                        CircleLookShopDynamic.DataBean data = circleLookShopDynamic.getData();
                                        if (data != null) {
                                            List<CircleLookShopDynamic.DataBean.RecordsBean> records = data.getRecords();
                                            if (records != null) {
                                                int size = records.size();
                                                if (size > 0) {
                                                    List<CircleLookAllBean> list = new ArrayList<>();
                                                    for (int i = 0; i < size; i++) {
                                                        CircleLookAllBean circleLookAllBean = new CircleLookAllBean();
                                                        CircleLookShopDynamic.DataBean.RecordsBean recordsBean = records.get(i);
                                                        circleLookAllBean.itemType = 0;
                                                        circleLookAllBean.commodityName = recordsBean.getCommodityName();
                                                        circleLookAllBean.createTime = recordsBean.getCreateTime();
                                                        circleLookAllBean.imgUrl = recordsBean.getImgUrl();
                                                        circleLookAllBean.commodityDescribe = recordsBean.getCommodityDescribe();
                                                        circleLookAllBean.retailPrice = recordsBean.getRetailPrice();
                                                        list.add(circleLookAllBean);
                                                    }
                                                    mView.setCircleLookAllDataList(list);
                                                } else {
                                                    ToastUtils.showShort("没有更多的数据");
                                                }
                                            }
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

    @Override
    public void requestCircleLookLive(String userId, String current, String size) {
        if (!mBaseActivity.isConnectNetWorkAndBindView()) {
            return;
        }

        OkGo.<String>get(Api.CIRCLE_QUERY_USER_LIVE_DYNAMIC)
                .params("userId", userId)
                .params("current", current)
                .params("size", size)
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
                                LiveBean liveBean = GsonUtil.GsonToBean(body, LiveBean.class);
                                if (liveBean != null) {
                                    int statusCode = liveBean.getStatusCode();
                                    if (statusCode == Constants.SUCCESS_CODE) {
                                        LiveBean.DataBean data = liveBean.getData();
                                        if (data != null) {
                                            List<LiveBean.DataBean.RecordsBean> records = data.getRecords();
                                            if (records != null) {
                                                int size = records.size();
                                                if (size > 0) {
                                                    List<CircleLookAllBean> list = new ArrayList<>();
                                                    for (int i = 0; i < size; i++) {
                                                        CircleLookAllBean circleLookAllBean = new CircleLookAllBean();
                                                        LiveBean.DataBean.RecordsBean recordsBean = records.get(i);
                                                        circleLookAllBean.itemType = 1;
                                                        circleLookAllBean.createTime = recordsBean.getCreateTime();
                                                        circleLookAllBean.content = recordsBean.getContent();
                                                        String imgUrl = recordsBean.getImgUrl();
                                                        if (StringUtils.isEmpty(imgUrl)) {
                                                            circleLookAllBean.imgUrl = "";
                                                        } else {
                                                            circleLookAllBean.imgUrl = imgUrl;
                                                        }
                                                        list.add(circleLookAllBean);
                                                    }
                                                    mView.setCircleLookAllDataList(list);
                                                } else {
                                                    ToastUtils.showShort("没有更多的数据");
                                                }
                                            }
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

    @Override
    public void requestQueryUserInfo(String userId) {
        if (!mBaseActivity.isConnectNetWorkAndBindView()) {
            return;
        }

        OkGo.<String>get(Api.USER_QUERY)
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
                                            String byname = data.getName();
                                            String settingImg = data.getSettingImg();
                                            if (!StringUtils.isEmpty(headImg)) {
                                                mView.showHeadImg(headImg);
                                            }
                                            if (!StringUtils.isEmpty(byname)) {
                                                mView.showUsername(byname);
                                            }
                                            if (!StringUtils.isEmpty(settingImg)) {
                                                mView.showBgImg(settingImg);
                                            } else {
                                                mView.showDefaultImg();
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
