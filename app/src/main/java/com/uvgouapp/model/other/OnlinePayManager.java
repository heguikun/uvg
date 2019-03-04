package com.uvgouapp.model.other;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.alipay.sdk.app.PayTask;
import com.blankj.utilcode.util.StringUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.uvgouapp.R;
import com.uvgouapp.api.Api;
import com.uvgouapp.common.constant.Constants;
import com.uvgouapp.common.util.GsonUtil;
import com.uvgouapp.model.user.WxPayBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by jeffrey on 2016/12/28.
 */

public class OnlinePayManager {

    public static final int SDK_PAY_FLAG = 1;
    public static final int SDK_AUTH_FLAG = 2;
    public static final int WXP_SUCCESS = 0x261;
    public static final int WXP_FAIL = 0x262;
    public static final int WXP_CANCEL = 0x263;
    public static final int WXP_ERROR = 0x264;
    public static final int WXP_REFRESH = 0x265;

    private static class OnlinePayManagerHolder {

        private static OnlinePayManager instance = new OnlinePayManager();
    }


    private OnlinePayManager() {
    }

    public static OnlinePayManager getInstance() {
        return OnlinePayManager.OnlinePayManagerHolder.instance;
    }

    public void clearData() {
        new OnlinePayManager.OnlinePayManagerHolder();
    }

    public void startWXPay(final Context context, final JSONObject jsonObject) {

        final IWXAPI wxapi = WXAPIFactory.createWXAPI(context, null);
        wxapi.registerApp("wxb4ba3c02aa476ea1");

        PayReq request = new PayReq(); //调起微信APP的对象
        //下面是设置必要的参数，也就是前面说的参数,这几个参数从何而来请看上面说明
        request.appId = context.getString(R.string.weixin_api_key);
        try {
            //request.appId = jsonObject.getString("appid");
            request.appId = context.getString(R.string.weixin_api_key);
            request.partnerId = jsonObject.getString("partnerid");
            request.prepayId = jsonObject.getString("prepayid");//(String)hashData.get("prepayid");
            request.packageValue = jsonObject.getString("package");
            request.nonceStr = jsonObject.getString("noncestr");//(String)hashData.get("noncestr");
            request.timeStamp = String.valueOf(jsonObject.getLong("timestamp"));
            request.sign = jsonObject.getString("sign");
            wxapi.sendReq(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void toWXPay(Context context, JSONObject jsonObject) {
        IWXAPI wxapi = WXAPIFactory.createWXAPI(context, null);
        wxapi.registerApp("wxb4ba3c02aa476ea1");

        try {
            String paywxnum = jsonObject.getString("paywxnum");
            OkGo.<String>get(Api.USER_WECHAT_PAYMENT)
                    .params("totalFee", Integer.parseInt(paywxnum))
                    .params("type", 2)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            if (response != null) {
                                String body = response.body();
                                Log.i("UWE_JS", body);
                                if (!StringUtils.isEmpty(body)) {
                                    WxPayBean wxPayBean = GsonUtil.GsonToBean(body, WxPayBean.class);
                                    if (wxPayBean != null) {
                                        if (wxPayBean.getStatusCode() == Constants.SUCCESS_CODE) {
                                            WxPayBean.DataBean data = wxPayBean.getData();
                                            if (data != null) {

                                                Runnable payRunnable = new Runnable() {
                                                    @Override
                                                    public void run() {
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
                                        }
                                    }
                                }
                            }
                        }
                    });
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    //    public void startZFBPay(final Context context, final String aliPayStr, final Handler messageHandler) {
    //        Runnable payRunnable = new Runnable() {
    //            @Override
    //            public void run() {
    //                Handler msgHandler = messageHandler;
    //                PayTask alipay = new PayTask((Activity) context);
    //                String orderInfo = aliPayStr;
    //                Map<String, String> result = alipay.payV2(orderInfo, true);
    //
    //
    //                Message msg = new Message();
    //                msg.what = SDK_PAY_FLAG;
    //                msg.obj = result;
    //                msgHandler.sendMessage(msg);
    //            }
    //        };
    //
    //        Thread payThread = new Thread(payRunnable);
    //        payThread.start();
    //    }

    public String startZFBPay(Context context, String aliPayStr) {
        PayTask alipay = new PayTask((Activity) context);
        Map<String, String> map = alipay.payV2(aliPayStr, true);
        return new Gson().toJson(map);
    }

}
