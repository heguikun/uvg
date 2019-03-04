package com.uvgouapp.ui.user;


import android.content.Intent;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.uvgouapp.R;
import com.uvgouapp.common.base.BaseActivity;
import com.uvgouapp.contract.user.ApplyStoreContract;
import com.uvgouapp.presenter.user.ApplyStorePresenter;

import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

public class ApplyStoreActivity extends BaseActivity<ApplyStorePresenter> implements ApplyStoreContract.View {

    @BindView(R.id.et_store_name)
    EditText mEtStoreName;//店铺名
    @BindView(R.id.et_business_scope)
    EditText mEtBusinessScope;//经营范围
    @BindView(R.id.et_name)
    EditText mEtName;//姓名
    @BindView(R.id.et_card_id)
    EditText mEtCardId;//身份证
    @BindView(R.id.et_phone_number)
    EditText mEtPhoneNumber;//手机号
    @BindView(R.id.tv_send_sms_code)
    TextView mTvSendSmsCode;//发送验证码
    @BindView(R.id.et_verification_code)
    EditText mEtVerificationCode;//验证码
    @BindView(R.id.tv_read_konw)
    TextView mTvReadKnow;//同意

    private CountDownTimer mTimer = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_apply_store;
    }

    @Override
    protected void initView() {
        mPresenter = new ApplyStorePresenter(this);
        mPresenter.attachView(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        //监听输入手机号 改变发送短信背景颜色以及文字颜色
        mEtPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String phone = mEtPhoneNumber.getText().toString();
                if (phone.length() > 0) {
                    mTvSendSmsCode.setEnabled(true);
                    mTvSendSmsCode.setTextColor(getResources().getColor(R.color.app_color));
                    mTvSendSmsCode.setBackground(getResources().getDrawable(R.drawable.shape_send_verification_code));
                } else {
                    mTvSendSmsCode.setText(String.format(Locale.ENGLISH, "%s", "发送验证码"));
                    mTvSendSmsCode.setEnabled(false);
                    mTvSendSmsCode.setTextColor(getResources().getColor(R.color.color_999999));
                    mTvSendSmsCode.setBackground(getResources().getDrawable(R.drawable.shape_un_send_verification_code));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @OnClick({R.id.tv_read_konw, R.id.tv_cancel, R.id.tv_send_sms_code, R.id.btn_apply_now})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_send_sms_code://发送验证码
                sendSmsCode();
                mPresenter.sendPhoneSms(mEtPhoneNumber.getText().toString().trim());
                break;
            case R.id.tv_read_konw://同意
                mTvReadKnow.setSelected(!mTvReadKnow.isSelected());
                break;
            case R.id.tv_cancel://取消
                finish();
                break;
            case R.id.btn_apply_now://立即申请
                String userId = SPUtils.getInstance().getString("userId");//用户id
                String shopName = mEtStoreName.getText().toString().trim();//店铺名
                String businessScope = mEtBusinessScope.getText().toString().trim();//经营范围
                String userName = mEtName.getText().toString().trim();//姓名
                String idCard = mEtCardId.getText().toString().trim();//身份证
                String phone = mEtPhoneNumber.getText().toString().trim();//手机号
                String smsCode = mEtVerificationCode.getText().toString().trim();//验证码
                mPresenter.requestApplyStore(shopName, businessScope, userName, idCard, phone,
                        smsCode, userId, mTvReadKnow.isSelected());
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
    protected void onDestroy() {
        super.onDestroy();
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }

    @Override
    public void onSuccess() {
        startActivity(new Intent(this, EnterAgreementActivity.class));
        finish();
    }
}
