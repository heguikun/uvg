package com.uvgouapp.presenter.circle;


import com.blankj.utilcode.util.EncodeUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.PictureFileUtils;
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
import com.uvgouapp.contract.circle.ShareLiveContract;
import com.uvgouapp.model.circle.CirclePhotoBean;
import com.uvgouapp.model.other.InfoBean;

import java.io.File;
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
 * - @Time:  2019/1/14
 * - @Description:  分享生活
 */
public class ShareLivePresenter extends BasePresenter<ShareLiveContract.View> implements ShareLiveContract.Presenter {

    private BaseActivity mBaseActivity;

    public ShareLivePresenter(BaseActivity baseActivity) {
        this.mBaseActivity = baseActivity;
    }

    /**
     * 发布生活
     *
     * @param content      内容
     * @param imgUrlList   图片集合
     * @param positionName 地址
     * @param userId       用户id
     */
    @Override
    public void sendLiveDynamicState(String content, List<String> imgUrlList, String positionName,
                                     String userId) {

        if (!mBaseActivity.isConnectNetWorkAndBindView()) {
            return;
        }

        if (verificationString(content, imgUrlList)) {
            //字符串拼接 ","  组成一串字符串
            StringBuffer stringBuffer = new StringBuffer();
            int size = imgUrlList.size();
            for (int i = 0; i < size; i++) {
                stringBuffer.append(imgUrlList.get(i)).append(",");
            }
            //删掉拼接后最后一个字符串的  ,
            String s = stringBuffer.toString();
            String imgUrl = s.substring(0, s.length() - 1);

            Map<String, String> map = new HashMap<>();
            map.put("content", EncodeUtils.urlEncode(content, "UTF-8"));
            map.put("imgUrl", imgUrl);
            map.put("positionName", positionName);
            map.put("userId", userId);
            String jsonObject = GsonUtil.GsonToString(map);

            OkGo.<String>post(Api.CIRCLE_SHARE_LIFE)
                    .upJson(jsonObject)
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
                            Response<String> response = stringResult.response();
                            String body = response.body();
                            if (!StringUtils.isEmpty(body)) {
                                InfoBean infoBean = GsonUtil.GsonToBean(body, InfoBean.class);
                                if (infoBean != null) {
                                    int statusCode = infoBean.getStatusCode();
                                    if (statusCode == Constants.SUCCESS_CODE) {
                                        mView.onStatusCode("分享成功");
                                    } else {
                                        mView.onFail("分享失败");
                                    }
                                }
                            } else {
                                mView.onFail("分享失败");
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

    /**
     * 发布内容
     *
     * @param content      内容
     * @param positionName 地址
     * @param userId       用户id
     */
    @Override
    public void sendLiveContentState(String content, String positionName, String userId) {
        if (!mBaseActivity.isConnectNetWorkAndBindView()) {
            return;
        }
        if (!StringUtils.isEmpty(content)) {
            Map<String, String> map = new HashMap<>();
            map.put("content", EncodeUtils.urlEncode(content, "UTF-8"));
            map.put("positionName", positionName);
            map.put("userId", userId);
            String jsonObject = GsonUtil.GsonToString(map);

            OkGo.<String>post(Api.CIRCLE_SHARE_LIFE)
                    .upJson(jsonObject)
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
                            Response<String> response = stringResult.response();
                            String body = response.body();
                            if (!StringUtils.isEmpty(body)) {
                                InfoBean infoBean = GsonUtil.GsonToBean(body, InfoBean.class);
                                if (infoBean != null) {
                                    int statusCode = infoBean.getStatusCode();
                                    if (statusCode == Constants.SUCCESS_CODE) {
                                        mView.onStatusCode("分享成功");
                                    } else {
                                        mView.onFail("分享失败");
                                    }
                                }
                            } else {
                                mView.onFail("分享失败");
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

        } else {
            ToastUtils.showShort("请输入内容");
        }
    }

    /**
     * @param localMedia 上传图片
     */
    @Override
    public void uploadPictures(LocalMedia localMedia) {

        if (!mBaseActivity.isConnectNetWorkAndBindView()) {
            return;
        }

        String path;
        if (localMedia.isCut() && !localMedia.isCompressed()) {
            //裁剪过
            path = localMedia.getCutPath();
        } else if (localMedia.isCompressed() || (localMedia.isCut() && localMedia.isCompressed())) {
            // 压缩过,或者裁剪同时压缩过,以最终压缩过图片为准
            path = localMedia.getCompressPath();
        } else {
            // 原图
            path = localMedia.getPath();
        }

        OkGo.<String>post(Api.CIRCLE_UPLOAD_PICTURES)
                .params("file", new File(path))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        if (!StringUtils.isEmpty(body)) {
                            CirclePhotoBean circlePhotoBean = GsonUtil.GsonToBean(body, CirclePhotoBean.class);
                            if (circlePhotoBean != null) {
                                if (circlePhotoBean.getStatusCode() == Constants.SUCCESS_CODE) {
                                    String imageUrl = circlePhotoBean.getData();
                                    mView.addImageUrl(imageUrl);
                                    //包括裁剪和压缩后的缓存，要在上传成功后调用，注意：需要系统sd卡权限
                                    PictureFileUtils.deleteCacheDirFile(mBaseActivity);
                                }
                            }
                        }
                    }

                });
    }

    /**
     * @param content    输入的内容
     * @param imgUrlList 图片集合
     * @return
     */
    private boolean verificationString(String content, List<String> imgUrlList) {
        if (StringUtils.isEmpty(content)) {
            ToastUtils.showShort("请输入内容");
            return false;
        }
        if (imgUrlList == null || imgUrlList.size() <= 0) {
            ToastUtils.showShort("请重新选择图片");
            return false;
        }
        return true;
    }

}
