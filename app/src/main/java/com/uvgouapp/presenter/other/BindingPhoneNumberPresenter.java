package com.uvgouapp.presenter.other;


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
import com.uvgouapp.contract.other.BindingPhoneNumberContract;
import com.uvgouapp.model.other.InfoBean;

/**
 * - @Author:  ying
 * - @Time:  2019/2/21
 * - @Description:  绑定手机号码
 */
public class BindingPhoneNumberPresenter extends BasePresenter<BindingPhoneNumberContract.View> implements BindingPhoneNumberContract.Presenter {

    private BaseActivity mBaseActivity;

    public BindingPhoneNumberPresenter(BaseActivity baseActivity) {
        this.mBaseActivity = baseActivity;
    }

    @Override
    public void sendPhoneSms(String phone) {
        if (!mBaseActivity.isConnectNetWorkAndBindView()) {
            return;
        }

        if (isPhoneEmpty(phone)) {
            OkGo.<String>get(Api.SEND_PHONE_SMS)
                    .params("phone", phone)
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
                                            ToastUtils.showShort("发送成功");
                                        } else {
                                            ToastUtils.showShort(infoBean.getMsg());
                                        }
                                    }
                                }
                            }
                        }
                    });
        }
    }

    @Override
    public void requestUserBindingPhone(String userId, String phone, String smsCode) {
        if (!mBaseActivity.isConnectNetWorkAndBindView()) {
            return;
        }

        if (verification(phone, smsCode)) {
            OkGo.<String>get(Api.USER_BINDING_PHONE)
                    .params("userId", userId)
                    .params("phone", phone)
                    .params("smsCode", smsCode)
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
                                if (response.code() == Constants.SUCCESS_CODE) {
                                    mView.onSuccess(phone);
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

    }

    private boolean verification(String phone, String smsCode) {
        if (StringUtils.isEmpty(phone)) {
            ToastUtils.showShort("请输入手机号");
            return false;
        }
        if (StringUtils.isEmpty(smsCode)) {
            ToastUtils.showShort("请输入验证码");
            return false;
        }
        return true;
    }

    /**
     * @param phone 手机号
     * @return 是否为空
     */
    private boolean isPhoneEmpty(String phone) {
        if (StringUtils.isEmpty(phone)) {
            ToastUtils.showShort("请输入手机号");
            return false;
        }
        return true;
    }
}
