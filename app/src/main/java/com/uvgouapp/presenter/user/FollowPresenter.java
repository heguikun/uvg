package com.uvgouapp.presenter.user;


import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.model.Result;
import com.lzy.okrx2.adapter.ObservableResult;
import com.trello.rxlifecycle3.android.ActivityEvent;
import com.uvgouapp.api.Api;
import com.uvgouapp.common.base.BaseActivity;
import com.uvgouapp.common.base.BasePresenter;
import com.uvgouapp.common.constant.Constants;
import com.uvgouapp.common.util.GsonUtil;
import com.uvgouapp.contract.user.FollowContract;
import com.uvgouapp.model.other.InfoBean;
import com.uvgouapp.model.user.FollowFriendsBean;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * - @Author:  ying
 * - @Time:  2019/1/26
 * - @Description:  关注
 */
public class FollowPresenter extends BasePresenter<FollowContract.View> implements FollowContract.Presenter {

    private BaseActivity mBaseActivity;

    public FollowPresenter(BaseActivity baseActivity) {
        this.mBaseActivity = baseActivity;
    }

    @Override
    public void requestUserQueryFollowFriends(String userId, String pageNo, String pageSize) {

        if (!mBaseActivity.isConnectNetWorkAndBindView()) {
            return;
        }

        OkGo.<String>get(Api.USER_QUERY_FOLLOW_FRIENDS)
                .params("userId", userId)
                .params("pageNo", pageNo)
                .params("pageSize", pageSize)
                .converter(new StringConvert())
                .adapt(new ObservableResult<>())
                .subscribeOn(Schedulers.io())
                .compose(mBaseActivity.<Result<String>>bindUntilEvent(ActivityEvent.DESTROY))
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
                        mBaseActivity.addDisposable(d);
                    }

                    @Override
                    public void onNext(Result<String> stringResult) {
                        if (stringResult != null) {
                            String body = stringResult.response().body();
                            if (!StringUtils.isEmpty(body)) {
                                FollowFriendsBean followFriendsBean = GsonUtil.GsonToBean(body, FollowFriendsBean.class);
                                if (followFriendsBean != null) {
                                    List<FollowFriendsBean.MapListBean> mapList = followFriendsBean.getMapList();
                                    if (mapList != null) {
                                        int size = mapList.size();
                                        if (size > 0) {
                                            mView.setFollowListData(mapList);
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
     * 取消关注
     *
     * @param userId       用户id
     * @param solicitudeId 被关注用户id
     */
    @Override
    public void requestCancleFollow(String userId, String solicitudeId) {

        if (!mBaseActivity.isConnectNetWorkAndBindView()) {
            return;
        }

        OkGo.<String>get(Api.USER_CANCEL_FOLLOW)
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
                                        String msg = infoBean.getMsg();
                                        if (!StringUtils.isEmpty(msg)) {
                                            ToastUtils.showShort(msg);
                                        } else {
                                            ToastUtils.showShort("取消成功");
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
    }
}
