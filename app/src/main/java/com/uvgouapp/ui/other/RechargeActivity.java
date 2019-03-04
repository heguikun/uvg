package com.uvgouapp.ui.other;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.uvgouapp.R;
import com.uvgouapp.api.Api;
import com.uvgouapp.common.base.BaseActivity;
import com.uvgouapp.common.constant.Constants;
import com.uvgouapp.common.util.GsonUtil;
import com.uvgouapp.model.other.MessageEvent;
import com.uvgouapp.model.user.WxPayBean;

import org.greenrobot.eventbus.EventBus;

import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

public class RechargeActivity extends BaseActivity {

    @BindView(R.id.iv_add)
    ImageView mIvAdd;//加号
    @BindView(R.id.et_number)
    EditText mEtNumber;//数量
    @BindView(R.id.iv_reduce)
    ImageView mIvReduce;//减号
    @BindView(R.id.tv_select_zfb)
    TextView mTvSelectZfb;//支付宝
    @BindView(R.id.tv_select_wx)
    TextView mTvSelectWx;//微信

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recharge;
    }

    @Override
    protected void initView() {
        mTvSelectWx.setSelected(true);//默认选中微信
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mEtNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String number = mEtNumber.getText().toString().trim();
                if (!StringUtils.isEmpty(number)) {
                    int sum = Integer.parseInt(number);
                    if (sum > 0) {
                        mIvReduce.setBackground(getResources().getDrawable(R.drawable.icon_recharge_jian));
                    } else {
                        mIvReduce.setBackground(getResources().getDrawable(R.drawable.icon_recharge_un_jian));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick({R.id.iv_back, R.id.tv_recharge_record, R.id.iv_add, R.id.iv_reduce, R.id.rl_zfb, R.id.rl_wx,
            R.id.btn_payment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back://返回
                finish();
                break;
            case R.id.tv_recharge_record://充值记录
                startActivity(new Intent(this, RechargeRecordActivity.class));
                break;
            case R.id.iv_add://加号
                String numberAdd = mEtNumber.getText().toString().trim();
                if (!StringUtils.isEmpty(numberAdd)) {
                    int sum = Integer.parseInt(numberAdd);
                    sum++;
                    mIvReduce.setBackground(getResources().getDrawable(R.drawable.icon_recharge_jian));
                    mEtNumber.setText(String.format(Locale.ENGLISH, "%d", sum));
                } else {
                    ToastUtils.showShort("请输入购买次数");
                }
                break;
            case R.id.iv_reduce://减号
                String numberReduce = mEtNumber.getText().toString().trim();
                if (!StringUtils.isEmpty(numberReduce)) {
                    int sum = Integer.parseInt(numberReduce);
                    sum--;
                    if (sum <= 0) {
                        mIvReduce.setBackground(getResources().getDrawable(R.drawable.icon_recharge_un_jian));
                        mEtNumber.setText(String.format(Locale.ENGLISH, "%d", 0));
                    } else {
                        mEtNumber.setText(String.format(Locale.ENGLISH, "%d", sum));
                    }
                } else {
                    ToastUtils.showShort("请输入购买次数");
                }

                break;
            case R.id.rl_zfb://支付宝
                mTvSelectZfb.setSelected(true);
                mTvSelectWx.setSelected(false);
                break;
            case R.id.rl_wx://微信
                mTvSelectZfb.setSelected(false);
                mTvSelectWx.setSelected(true);
                break;
            case R.id.btn_payment://支付
                String number = mEtNumber.getText().toString().trim();//购买次数
                if (!StringUtils.isEmpty(number)) {
                    if (mTvSelectZfb.isSelected()) {
                        ToastUtils.showShort("正在开发...");
                    }
                    if (mTvSelectWx.isSelected()) {
                        int sum = Integer.parseInt(number);
                        if (sum > 0) {
                            payment(sum * 600);
                        } else {
                            ToastUtils.showShort("最少购买一个");
                        }
                    }
                } else {
                    ToastUtils.showShort("请输入购买次数");
                }
                break;
        }
    }

    /**
     * 支付
     *
     * @param money 金额
     */
    private void payment(int money) {
        IWXAPI wxapi = WXAPIFactory.createWXAPI(this, null);
        wxapi.registerApp("wxb4ba3c02aa476ea1");

        OkGo.<String>get(Api.USER_WECHAT_PAYMENT)
                .params("userId", SPUtils.getInstance().getString("userId"))
                .params("totalFee", money)
                .params("type", 1)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response != null) {
                            String body = response.body();
                            if (!StringUtils.isEmpty(body)) {
                                WxPayBean wxPayBean = GsonUtil.GsonToBean(body, WxPayBean.class);
                                if (wxPayBean != null) {
                                    if (wxPayBean.getStatusCode() == Constants.SUCCESS_CODE) {
                                        WxPayBean.DataBean data = wxPayBean.getData();
                                        if (data != null) {

                                            Runnable payRunnable = new Runnable() {
                                                @Override
                                                public void run() {
                                                    MessageEvent messageEvent = new MessageEvent();
                                                    messageEvent.money = money;
                                                    messageEvent.outTradeNo = data.getOutTradeNo();
                                                    EventBus.getDefault().post(messageEvent);

                                                    //调起微信APP的对象
                                                    PayReq payReq = new PayReq();
                                                    payReq.appId = data.getAppid();
                                                    payReq.partnerId = data.getPartnerid();
                                                    payReq.prepayId = data.getPrepayid();
                                                    payReq.packageValue = data.getpackageValue();
                                                    payReq.nonceStr = data.getNoncestr();
                                                    payReq.timeStamp = data.getTimestamp();
                                                    payReq.sign = data.getSign();
                                                    wxapi.sendReq(payReq);

                                                }
                                            };

                                            Thread payThread = new Thread(payRunnable);
                                            payThread.start();

                                        }
                                    } else {
                                        ToastUtils.showShort("支付失败,重新支付");
                                    }
                                }
                            }
                        }
                    }
                });
    }
}
