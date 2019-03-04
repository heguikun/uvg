package com.uvgouapp.ui.other;


import android.content.Intent;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.uvgouapp.R;
import com.uvgouapp.common.base.BaseActivity;
import com.uvgouapp.contract.other.BindingPhoneNumberContract;
import com.uvgouapp.presenter.other.BindingPhoneNumberPresenter;

import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * - @Author:  ying
 * - @Time:  2019/2/21
 * - @Description:  绑定手机号码
 */
public class BindingPhoneNumberActivity extends BaseActivity<BindingPhoneNumberPresenter> implements BindingPhoneNumberContract.View {

    @BindView(R.id.et_phone_number)
    EditText mEtPhoneNumber;//手机号码
    @BindView(R.id.et_sms_code)
    EditText mEtSmsCode;//验证码
    @BindView(R.id.tv_send_sms_code)
    TextView mTvSendSmsCode;//发送验证码

    private CountDownTimer mTimer = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_binding_phone_number;
    }

    @Override
    protected void initView() {
        mPresenter = new BindingPhoneNumberPresenter(this);
        mPresenter.attachView(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @OnClick({R.id.iv_back, R.id.tv_send_sms_code, R.id.btn_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back://返回
                finish();
                break;
            case R.id.tv_send_sms_code://发送验证码
                String phoneNumber = mEtPhoneNumber.getText().toString().trim();//手机号码
                if (StringUtils.isEmpty(phoneNumber)) {
                    ToastUtils.showShort("请输入手机号码");
                } else {
                    sendSmsCode();
                    mPresenter.sendPhoneSms(phoneNumber);
                }
                break;
            case R.id.btn_ok://绑定
                String phone = mEtPhoneNumber.getText().toString().trim();//手机号码
                String smsCode = mEtSmsCode.getText().toString().trim();//验证码
                mPresenter.requestUserBindingPhone(SPUtils.getInstance().getString("userId"), phone, smsCode);
                break;
            default:
                break;
        }
    }

    /**
     * 发送验证码倒计时
     */
    private void sendSmsCode() {
        mTimer = new CountDownTimer(60 * 1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                mTvSendSmsCode.setEnabled(false);
                mEtPhoneNumber.setFocusable(false);
                mEtPhoneNumber.setFocusableInTouchMode(false);
                mTvSendSmsCode.setText(String.format(Locale.ENGLISH, "已发送(%d)", millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                mTvSendSmsCode.setEnabled(true);
                mEtPhoneNumber.setFocusable(true);
                mEtPhoneNumber.setFocusableInTouchMode(true);
                mTvSendSmsCode.setText("重新获取验证码");
            }
        }.start();

    }

    @Override
    public void onSuccess(String phone) {
        Intent intent = new Intent();
        intent.putExtra("phone", phone);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }


}
