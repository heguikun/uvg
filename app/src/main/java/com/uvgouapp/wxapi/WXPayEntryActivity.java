package com.uvgouapp.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.uvgouapp.R;
import com.uvgouapp.api.Api;
import com.uvgouapp.common.constant.Constants;
import com.uvgouapp.model.other.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * - @Author:  ying
 * - @Time:  2019/2/18
 * - @Description:  微信支付
 */
public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private IWXAPI api;

    private int money;//金额
    private String outTradeNo;//订单号

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void voluation(MessageEvent messageEvent) {
        if (messageEvent != null) {
            this.money = messageEvent.money;
            this.outTradeNo = messageEvent.outTradeNo;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_result);
        EventBus.getDefault().register(this);
        api = WXAPIFactory.createWXAPI(this, getString(R.string.weixin_api_key), true);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }


    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            switch (baseResp.errCode) {
                case 0://成功
                    queryOrder();
                    break;
                case -1://失败
                    ToastUtils.showShort("支付失败");
                    finish();
                    break;
                case -2://取消
                    ToastUtils.showShort("支付取消");
                    finish();
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 查询订单
     */
    private void queryOrder() {

        OkGo.<String>get(Api.QUERY_ORDER)
                .params("outTradeNo", outTradeNo)
                .params("fee", money)
                .params("userId", SPUtils.getInstance().getString("userId"))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response != null) {
                            if (response.code() == Constants.SUCCESS_CODE) {
                                ToastUtils.showShort("支付成功");
                                finish();
                            }
                        }
                    }
                });
    }
}
