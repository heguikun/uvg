package com.uvgouapp.presenter.other;


import android.util.Log;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.StringUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.uvgouapp.api.Api;
import com.uvgouapp.common.base.BasePresenter;
import com.uvgouapp.common.constant.Constants;
import com.uvgouapp.common.util.GsonUtil;
import com.uvgouapp.contract.other.SplashContract;
import com.uvgouapp.model.other.VersionBean;

/**
 * - @Author:  ying
 * - @Time:  2019/2/21
 * - @Description:  欢迎页
 */
public class SplashPresenter extends BasePresenter<SplashContract.View> implements SplashContract.Presenter {


    @Override
    public void onCreate() {

        //判断手机是否root
        if (AppUtils.isAppRoot()) {
            mView.showRootWarning();
        } else {
            mView.launchAfterPermission();
        }
    }

    @Override
    public void requestVersionUpdate() {

        OkGo.<String>get(Api.APP_VERSION)
                .params("type", 2)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response != null) {
                            String body = response.body();
                            if (!StringUtils.isEmpty(body)) {
                                Log.i("UWE", body);
                                VersionBean versionBean = GsonUtil.GsonToBean(body, VersionBean.class);
                                if (versionBean != null) {
                                    if (versionBean.getStatusCode() == Constants.SUCCESS_CODE) {
                                        VersionBean.DataBean data = versionBean.getData();
                                        if (data != null) {
                                            int versionCode = data.getVersionCode();
                                            int appVersionCode = AppUtils.getAppVersionCode();
                                            if (versionCode > appVersionCode) {
                                                mView.onReceiveNewVersion("有新版本更新,请及时下载！", data.getUrl());
                                            } else {
                                                mView.onSuccessCheckVersion();
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
                        mView.onFailError("网络异常");
                    }
                });
    }

}
