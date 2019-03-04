package com.uvgouapp.presenter.circle;


import com.blankj.utilcode.util.SPUtils;
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
import com.uvgouapp.contract.circle.CircleInformationContract;
import com.uvgouapp.model.other.InfoBean;
import com.uvgouapp.model.user.LiveBean;
import com.uvgouapp.model.user.ShopBean;
import com.uvgouapp.model.user.ShopInfo;
import com.uvgouapp.model.user.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * - @Author:  ying
 * - @Time:  2019/1/29
 * - @Description:
 */
public class CircleInformationPresenter extends BasePresenter<CircleInformationContract.View> implements CircleInformationContract.Presenter {

    private BaseActivity mBaseActivity;

    public CircleInformationPresenter(BaseActivity baseActivity) {
        this.mBaseActivity = baseActivity;
    }

    /**
     * @param userId       用户id
     * @param solicitudeId 被关注用户ID
     */
    @Override
    public void requestQueryUserFollow(String userId, String solicitudeId) {
        if (!mBaseActivity.isConnectNetWorkAndBindView()) {
            return;
        }

        OkGo.<String>get(Api.USER_QUERY_FOLLOW)
                .params("userId", userId)
                .params("solicitudeId", solicitudeId)
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
                                        String data = infoBean.getData();
                                        if (data != null) {
                                            mView.alreadyFollow();
                                        } else {
                                            mView.AddFollow();
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
    }

    /**
     * @param solicitudeId 被关注用户id
     * @param userId       用户自己id
     */
    @Override
    public void requestFollow(String solicitudeId, String userId) {
        if (!mBaseActivity.isConnectNetWorkAndBindView()) {
            return;
        }

        Map<String, String> map = new HashMap<>();
        map.put("solicitudeId", solicitudeId);
        map.put("userId", userId);
        String json = GsonUtil.GsonToString(map);

        OkGo.<String>post(Api.USER_FOLLOW)
                .upJson(json)
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
                                        mView.followSuccess();
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
                                    if (user.getStatusCode() == Constants.SUCCESS_CODE) {
                                        User.DataBean data = user.getData();
                                        if (data != null) {
                                            String bgImg = data.getSettingImg();//背景
                                            String headImg = data.getHeadImg();//头像
                                            String name = data.getName();//用户名
                                            boolean sex = data.isSex();//性别
                                            String dateOfBirth = data.getDateOfBirth();//出生年月
                                            String constellation = data.getConstellation();//星座
                                            String emotionalState = data.getEmotionalState();//情感状态
                                            String obode = data.getObode();//常住地
                                            String occupation = data.getOccupation();//职业
                                            String stature = data.getStature();//身高
                                            String weight = data.getWeight();//体重
                                            if (!StringUtils.isEmpty(bgImg)) {
                                                mView.showBgImage(bgImg);
                                            } else {
                                                mView.showDefaultImg();
                                            }
                                            if (!StringUtils.isEmpty(headImg)) {
                                                mView.showHeadImage(headImg);
                                            }
                                            if (!StringUtils.isEmpty(name)) {
                                                mView.showUsername(name);
                                            }
                                            mView.showSex(sex);
                                            if (!StringUtils.isEmpty(dateOfBirth)) {
                                                mView.showDateOfBirth(dateOfBirth);
                                            }
                                            if (!StringUtils.isEmpty(constellation)) {
                                                mView.showConstellation(constellation);
                                            }
                                            if (!StringUtils.isEmpty(emotionalState)) {
                                                mView.showEmotionalState(emotionalState);
                                            }
                                            if (!StringUtils.isEmpty(obode)) {
                                                mView.showObode(obode);
                                            }
                                            if (!StringUtils.isEmpty(occupation)) {
                                                mView.showOccupation(occupation);
                                            }
                                            if (!StringUtils.isEmpty(stature)) {
                                                mView.showStature(stature);
                                            }
                                            if (!StringUtils.isEmpty(weight)) {
                                                mView.showWeight(weight);
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
     * 查询店铺信息
     *
     * @param userId 用户id
     */
    @Override
    public void requestQueryShopInfo(String userId) {
        if (!mBaseActivity.isConnectNetWorkAndBindView()) {
            return;
        }

        OkGo.<String>get(Api.QUERY_STORE)
                .params("userId", userId)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response != null) {
                            String body = response.body();
                            if (!StringUtils.isEmpty(body)) {
                                ShopInfo shopInfo = GsonUtil.GsonToBean(body, ShopInfo.class);
                                if (shopInfo != null) {
                                    if (shopInfo.getStatusCode() == Constants.SUCCESS_CODE) {
                                        ShopInfo.DataBean data = shopInfo.getData();
                                        if (data != null) {
                                            String shopName = data.getShopName();
                                            String mainOperation = data.getMainOperation();
                                            if (!StringUtils.isEmpty(shopName)) {
                                                mView.showShopName(shopName);
                                            }
                                            if (!StringUtils.isEmpty(mainOperation)) {
                                                mView.showMainOperation(mainOperation);
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
     * 查询自己是否开店
     *
     * @param userId 用户id
     */
    @Override
    public void requestQueryMyShopInfo(String userId) {
        if (!mBaseActivity.isConnectNetWorkAndBindView()) {
            return;
        }
        OkGo.<String>get(Api.QUERY_STORE)
                .params("userId", SPUtils.getInstance().getString("userId"))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response != null) {
                            String body = response.body();
                            if (!StringUtils.isEmpty(body)) {
                                ShopInfo shopInfo = GsonUtil.GsonToBean(body, ShopInfo.class);
                                if (shopInfo != null) {
                                    if (shopInfo.getStatusCode() == Constants.SUCCESS_CODE) {
                                        ShopInfo.DataBean data = shopInfo.getData();
                                        if (data != null) {
                                            queryShopId(userId);
                                        } else {
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
     * @param userId 用户id  查询别人店铺id
     */
    private void queryShopId(String userId) {
        if (!mBaseActivity.isConnectNetWorkAndBindView()) {
            return;
        }

        OkGo.<String>get(Api.QUERY_STORE)
                .params("userId", userId)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response != null) {
                            String body = response.body();
                            if (!StringUtils.isEmpty(body)) {
                                ShopInfo shopInfo = GsonUtil.GsonToBean(body, ShopInfo.class);
                                if (shopInfo != null) {
                                    if (shopInfo.getStatusCode() == Constants.SUCCESS_CODE) {
                                        ShopInfo.DataBean data = shopInfo.getData();
                                        if (data != null) {
                                            int id = data.getId();
                                            requestYesCopyShop(SPUtils.getInstance().getString("shopId"), String.valueOf(id), "1", "10");
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
    }

    /**
     * 查询用户动态
     *
     * @param userId  用户id
     * @param current 当前页
     * @param size    页面数量
     */
    @Override
    public void requestQueryUserLiveDynamic(String userId, String current, String size) {
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
                                    if (liveBean.getStatusCode() == Constants.SUCCESS_CODE) {
                                        LiveBean.DataBean data = liveBean.getData();
                                        if (data != null) {
                                            List<LiveBean.DataBean.RecordsBean> records = data.getRecords();
                                            if (records != null) {
                                                int size = records.size();
                                                if (size > 0) {
                                                    List<String> list = new ArrayList<>();
                                                    for (int i = 0; i < size; i++) {
                                                        LiveBean.DataBean.RecordsBean recordsBean = records.get(i);
                                                        String imgUrl = recordsBean.getImgUrl();
                                                        if (!StringUtils.isEmpty(imgUrl)) {
                                                            String[] split = imgUrl.split(",");
                                                            List<String> listImage = Arrays.asList(split);
                                                            list.addAll(listImage);
                                                        }
                                                    }

                                                    if (list.size() >= 3) {
                                                        mView.showLiveImage3(list);
                                                    }

                                                    if (list.size() == 2) {
                                                        mView.showLiveImage2(list);
                                                    }

                                                    if (list.size() == 1) {
                                                        mView.showLiveImage1(list);
                                                    }

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

    /**
     * 查询用户商品
     *
     * @param userId   用户id
     * @param pageNo   当前页
     * @param pageSize 页面数量
     */
    @Override
    public void requestQueryUserShop(String userId, String pageNo, String pageSize) {
        if (!mBaseActivity.isConnectNetWorkAndBindView()) {
            return;
        }

        OkGo.<String>get(Api.USER_QUERY_SHOP_INFO)
                .params("userId", userId)
                .params("pageNo", pageNo)
                .params("pageSize", pageSize)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response != null) {
                            String body = response.body();
                            if (!StringUtils.isEmpty(body)) {
                                ShopBean shopBean = GsonUtil.GsonToBean(body, ShopBean.class);
                                if (shopBean != null) {
                                    List<ShopBean.MapListBean> mapList = shopBean.getMapList();
                                    if (mapList != null) {
                                        int size = mapList.size();
                                        if (size > 0) {
                                            mView.setShopListData(mapList);
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
    }

    @Override
    public void requestYesCopyShop(String shopId, String copyId, String pageNo, String pageSize) {
        if (!mBaseActivity.isConnectNetWorkAndBindView()) {
            return;
        }

        OkGo.<String>get(Api.QUERY_YES_COPY_SHOP)
                .params("shopId", shopId)
                .params("copyId", copyId)
                .params("pageNo", pageNo)
                .params("pageSize", pageSize)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response != null) {
                            String body = response.body();
                            if (!StringUtils.isEmpty(body)) {
                                ShopBean shopBean = GsonUtil.GsonToBean(body, ShopBean.class);
                                if (shopBean != null) {
                                    List<ShopBean.MapListBean> mapList = shopBean.getMapList();
                                    if (mapList != null) {
                                        int size = mapList.size();
                                        if (size > 0) {
                                            mView.setCopyShopListData(mapList);
                                            mView.onSuccess();
                                        } else {
                                            ToastUtils.showShort("没有可以复制的商品");
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
    }

    /**
     * @param idList        商品ID集合
     * @param commodityType 商品类型（0：直销 1：分销）
     * @param createBy      创建人  用户自己id
     * @param modifyBy      修改人  用户自己id
     * @param shopId        店铺ID
     */
    @Override
    public void requestOneKeyShop(List<String> idList, int commodityType, String createBy, String modifyBy, String shopId) {
        if (!mBaseActivity.isConnectNetWorkAndBindView()) {
            return;
        }

        //字符串拼接 ","  组成一串字符串
        StringBuffer stringBuffer = new StringBuffer();
        int size = idList.size();
        for (int i = 0; i < size; i++) {
            stringBuffer.append(idList.get(i)).append(",");
        }
        //删掉拼接后最后一个字符串的  ,
        String s = stringBuffer.toString();
        String commodityId = s.substring(0, s.length() - 1);

        Map<String, Object> map = new HashMap<>();
        map.put("commodityId", commodityId);
        map.put("commodityType", 1);
        map.put("createBy", createBy);
        map.put("modifyBy", modifyBy);
        map.put("shopId", shopId);
        String json = GsonUtil.GsonToString(map);

        OkGo.<String>post(Api.ONE_KEY_SHOP)
                .upJson(json)
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
                                        mView.onSuccess("操作成功");
                                    }
                                }
                            }
                        }
                    }
                });
    }

}
