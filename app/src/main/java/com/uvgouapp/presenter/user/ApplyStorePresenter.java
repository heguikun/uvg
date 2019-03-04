package com.uvgouapp.presenter.user;


import com.blankj.utilcode.util.RegexUtils;
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
import com.uvgouapp.contract.user.ApplyStoreContract;
import com.uvgouapp.model.other.InfoBean;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * - @Author:  ying
 * - @Time:  2019/1/19
 * - @Description:  申请店铺
 */
public class ApplyStorePresenter extends BasePresenter<ApplyStoreContract.View> implements ApplyStoreContract.Presenter {

    private BaseActivity mBaseActivity;

    public ApplyStorePresenter(BaseActivity baseActivity) {
        this.mBaseActivity = baseActivity;
    }

    /**
     * 申请店铺
     *
     * @param shopName      店铺名称
     * @param mainOperation 经营范围
     * @param userName      用户名称
     * @param idCard        身份证
     * @param mobilePhone   手机号
     * @param smsCode       验证码
     * @param userId        用户id
     * @param select        是否同意
     */
    @Override
    public void requestApplyStore(String shopName, String mainOperation, String userName, String idCard,
                                  String mobilePhone, String smsCode, String userId, boolean select) {

        if (!mBaseActivity.isConnectNetWorkAndBindView()) {
            return;
        }

        if (verification(shopName, mainOperation, userName, idCard, mobilePhone, smsCode, select)) {

            Map<String, String> map = new HashMap<>();
            map.put("shopName", shopName);
            map.put("mainOperation", mainOperation);
            map.put("userName", userName);
            map.put("idCard", idCard);
            map.put("mobilePhone", mobilePhone);
            map.put("smsCode", smsCode);
            map.put("userId", userId);
            String json = GsonUtil.GsonToString(map);

            OkGo.<String>post(Api.APPLY_STORE)
                    .upJson(json)
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
                            String body = stringResult.response().body();
                            if (!StringUtils.isEmpty(body)) {
                                InfoBean infoBean = GsonUtil.GsonToBean(body, InfoBean.class);
                                int statusCode = infoBean.getStatusCode();
                                if (statusCode == Constants.SUCCESS_CODE) {
                                    String data = infoBean.getData();
                                    if (data != null) {
                                        ToastUtils.showShort("申请成功");
                                        mView.onSuccess();
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

    /**
     * 发送手机短信
     *
     * @param phone 手机号
     */
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

    /**
     * 验证
     *
     * @param shopName      店铺名称
     * @param businessScope 经营范围
     * @param userName      用户名称
     * @param idCard        身份证
     * @param mobilePhone   手机号
     * @param smsCode       验证码
     * @param select        是否同意
     * @return true  false
     */
    private boolean verification(String shopName, String businessScope, String userName, String idCard,
                                 String mobilePhone, String smsCode, boolean select) {

        if (StringUtils.isEmpty(shopName)) {
            ToastUtils.showShort("请填写店铺名");
            return false;
        }

        if (StringUtils.isEmpty(businessScope)) {
            ToastUtils.showShort("请填写经营范围");
            return false;
        }

        if (StringUtils.isEmpty(userName)) {
            ToastUtils.showShort("请填写姓名");
            return false;
        }

        if (StringUtils.isEmpty(idCard)) {
            ToastUtils.showShort("请填写身份证");
            return false;
        } else {
            if (!RegexUtils.isIDCard18Exact(idCard)) {
                ToastUtils.showShort("请核对身份证号码是否一致");
                return false;
            }
        }

        if (StringUtils.isEmpty(mobilePhone)) {
            ToastUtils.showShort("请填写手机号");
            return false;
        }

        if (StringUtils.isEmpty(smsCode)) {
            ToastUtils.showShort("请输入验证码");
            return false;
        }

        if (!select) {
            ToastUtils.showShort("请勾选入驻协议");
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
