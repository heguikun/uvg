package com.uvgouapp.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.uvgouapp.api.Api;
import com.uvgouapp.common.constant.Constants;
import com.uvgouapp.common.util.GsonUtil;
import com.uvgouapp.common.util.WxShareAndLoginUtil;
import com.uvgouapp.model.user.User;
import com.uvgouapp.model.other.MessageEvent;


import org.greenrobot.eventbus.EventBus;

import androidx.annotation.Nullable;

/**
 * - @Author:  ying
 * - @Time:  2019/1/15
 * - @Description:  微信
 */
public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    private int WX_LOGIN = 1;

    private IWXAPI api = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WxShareAndLoginUtil.getWXAPI(this);
        //注意：
        //第三方开发者如果使用透明界面来实现WXEntryActivity
        // 需要判断handleIntent的返回值，如果返回值为false，则说明入参不合法未被SDK处理
        // 应finish当前透明界面，避免外部通过传递非法参数的Intent导致停留在透明界面，引起用户的疑惑
        try {
            api.handleIntent(getIntent(), this);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        api.handleIntent(data, this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
        finish();
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        int type = baseResp.getType();
        if (type == WX_LOGIN) {
            switch (baseResp.errCode) {
                case BaseResp.ErrCode.ERR_OK:
                    String code = ((SendAuth.Resp) baseResp).code;
                    wechatLogin(code);
                    break;
                case BaseResp.ErrCode.ERR_AUTH_DENIED://用户拒绝授权
                    finish();
                    break;
                case BaseResp.ErrCode.ERR_USER_CANCEL://用户取消
                    finish();
                    break;
                default:
                    break;
            }
        } else {
            switch (baseResp.errCode) {
                case BaseResp.ErrCode.ERR_OK:
                    //分享成功
                    finish();
                    break;
                case BaseResp.ErrCode.ERR_USER_CANCEL:
                    //分享取消
                    ToastUtils.showShort("分享取消");
                    finish();
                    break;
                case BaseResp.ErrCode.ERR_AUTH_DENIED:
                    //分享拒绝
                    ToastUtils.showShort("分享拒绝");
                    finish();
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 微信登录成功回调
     *
     * @param code 返回码
     */
    private void wechatLogin(String code) {
        OkGo.<String>get(Api.USER_WX_LOGIN)
                .params("code", code)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        if (!StringUtils.isEmpty(body)) {
                            User user = GsonUtil.GsonToBean(body, User.class);
                            if (user.getStatusCode() == Constants.SUCCESS_CODE) {
                                User.DataBean data = user.getData();
                                //-------------特意存用户id  用户名 用户头像 方便后面使用--------------
                                long id = data.getId();
                                String name = data.getName();
                                String headImg = data.getHeadImg();
                                SPUtils.getInstance().put("userId", String.valueOf(id));
                                SPUtils.getInstance().put("name", name);
                                SPUtils.getInstance().put("headImg", headImg);
                                //--------------发送消息给登录页面----------
                                MessageEvent messageEvent = new MessageEvent();
                                messageEvent.flag = true;
                                EventBus.getDefault().post(messageEvent);
                                finish();
                            }

                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        ToastUtils.showShort("登录失败");
                        finish();
                    }
                });
    }

}
