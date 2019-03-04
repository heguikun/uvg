package com.uvgouapp.dialog.user;


import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.uvgouapp.R;
import com.uvgouapp.api.Api;
import com.uvgouapp.common.constant.Constants;
import com.uvgouapp.common.util.GsonUtil;
import com.uvgouapp.model.other.InfoBean;
import com.uvgouapp.model.user.User;
import com.uvgouapp.ui.other.LoginActivity;

import razerdp.basepopup.BasePopupWindow;

/**
 * - @Author:  ying
 * - @Time:  2019/1/24
 * - @Description:  退出登录提示
 */
public class UserLoginOutDialog extends BasePopupWindow implements View.OnClickListener {

    public UserLoginOutDialog(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        findViewById(R.id.tv_yes).setOnClickListener(this);
        findViewById(R.id.tv_no).setOnClickListener(this);
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.dialog_mine_login_out);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_yes://退出登录
                dismiss();
                logOut();
                break;
            case R.id.tv_no://取消
                dismiss();
                break;
            default:
                break;
        }
    }

    /**
     * 退出登录
     */
    private void logOut() {
        OkGo.<String>get(Api.USER_QUERY)
                .params("id", SPUtils.getInstance().getString("userId"))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response != null) {
                            String body = response.body();
                            if (!StringUtils.isEmpty(body)) {
                                User user = GsonUtil.GsonToBean(body, User.class);
                                if (user != null) {
                                    if (user.getStatusCode() == Constants.SUCCESS_CODE) {
                                        User.DataBean data = user.getData();
                                        if (data != null) {
                                            String opendId = data.getOpendId();
                                            requestLogOut(opendId);
                                        }
                                    }
                                }
                            }
                        }
                    }
                });

    }

    private void requestLogOut(String opendId) {
        OkGo.<String>get(Api.USER_LOGOUT)
                .params("openid", opendId)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response != null) {
                            String body = response.body();
                            if (!StringUtils.isEmpty(body)) {
                                InfoBean infoBean = GsonUtil.GsonToBean(body, InfoBean.class);
                                if (infoBean != null) {
                                    if (infoBean.getStatusCode() == Constants.SUCCESS_CODE) {
                                        //-------移除用户id  用户名
                                        SPUtils.getInstance().remove("userId");
                                        SPUtils.getInstance().remove("name");
                                        ActivityUtils.finishAllActivities();//结束所有 Activity
                                        //跳到登录页
                                        getContext().startActivity(new Intent(getContext(), LoginActivity.class));
                                    } else {
                                        ToastUtils.showShort("退出失败,请重新退出");
                                    }
                                } else {
                                    ToastUtils.showShort("退出失败,请重新退出");
                                }
                            }
                        }
                    }
                });
    }
}
