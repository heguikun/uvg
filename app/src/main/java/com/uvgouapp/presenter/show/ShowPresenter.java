package com.uvgouapp.presenter.show;


import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.model.Result;
import com.lzy.okrx2.adapter.ObservableResult;
import com.trello.rxlifecycle3.android.FragmentEvent;
import com.uvgouapp.common.base.BaseFragment;
import com.uvgouapp.api.Api;
import com.uvgouapp.common.base.BasePresenter;
import com.uvgouapp.common.constant.Constants;
import com.uvgouapp.common.util.GsonUtil;
import com.uvgouapp.contract.show.ShowContract;
import com.uvgouapp.model.other.InfoBean;
import com.uvgouapp.model.show.ShowBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * - @Author:  ying
 * - @Time:  2019/1/8
 * - @Description:秀场
 */
public class ShowPresenter extends BasePresenter<ShowContract.View> implements ShowContract.Presenter {

    private BaseFragment mBaseFragment;

    public ShowPresenter(BaseFragment baseFragment) {
        this.mBaseFragment = baseFragment;
    }

    @Override
    public void requestNewQueryAll(String lon, String lat, String positionName, String raidus, String commodityId,
                                   String thumbsType, String userId, String pageNo, String pageSize) {

        if (!mBaseFragment.isConnectNetWorkAndBindView()) {
            return;
        }

        OkGo.<String>get(Api.SHOW_QUERY_ALL)
                .params("lon", lon)
                .params("lat", lat)
                //.params("positionName", positionName)
                .params("raidus", raidus)
                //.params("commodityId", commodityId)
                //.params("thumbsType", thumbsType)
                .params("userId", userId)
                .params("pageNo", pageNo)
                .params("pageSize", pageSize)
                .converter(new StringConvert())
                .adapt(new ObservableResult<String>())
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
                            Response<String> response = stringResult.response();
                            int code = response.code();
                            if (code == Constants.SUCCESS_CODE) {
                                String body = response.body();
                                ShowBean showBean = GsonUtil.GsonToBean(body, ShowBean.class);
                                if (showBean != null) {
                                    List<ShowBean.MapListBean> mapList = showBean.getMapList();
                                    if (mapList != null) {
                                        int size = mapList.size();
                                        if (size > 0) {
                                            mView.setNewQueryAllListData(mapList);
                                        } else {
                                            ToastUtils.showShort("没有更多数据");
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

    /**
     * 点赞
     *
     * @param position   当前位置
     * @param isThumbsId 被点赞id
     * @param thumbsType 点赞类型 1.生活圈 2.淘友圈 3.秀场
     * @param userId     用户id
     */
    @Override
    public void requestGive(int position, String isThumbsId, int thumbsType, String userId) {

        if (!mBaseFragment.isConnectNetWorkAndBindView()) {
            return;
        }

        Map<String, Object> map = new HashMap<>();
        map.put("isThumbsId", isThumbsId);
        map.put("thumbsType", thumbsType);
        map.put("userId", userId);
        String json = GsonUtil.GsonToString(map);

        OkGo.<String>post(Api.SHOW_GIVE_THUMBS_UP)
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
                                        mView.update2Favort(position);
                                    }
                                }
                            }
                        }
                    }
                });
    }

    /**
     * 关注
     *
     * @param position     当前位置
     * @param solicitudeId 被关注用户id
     * @param userId       用户自己id
     */
    @Override
    public void requestFollow(int position, String solicitudeId, String userId) {

        if (!mBaseFragment.isConnectNetWorkAndBindView()) {
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
                                        mView.update2Follow(position);
                                    }
                                }
                            }
                        }
                    }
                });
    }
}
