package com.uvgouapp.ui.other;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.uvgouapp.R;
import com.uvgouapp.common.base.BaseActivity;
import com.uvgouapp.common.util.WxShareAndLoginUtil;
import com.uvgouapp.home.HomeActivity;
import com.uvgouapp.model.other.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.annotation.Nullable;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void messageEventBus(MessageEvent messageEvent) {
        //-----------进入主页面------------------
        if (messageEvent.flag) {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @OnClick(R.id.rl_login_wx)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_login_wx:
                WxShareAndLoginUtil.startWxLogin(this);
                break;
            default:
                break;
        }

    }
}
