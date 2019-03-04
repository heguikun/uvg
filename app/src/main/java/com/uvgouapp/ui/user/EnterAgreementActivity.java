package com.uvgouapp.ui.user;


import android.content.Intent;
import android.view.View;

import com.tencent.smtt.sdk.WebView;
import com.uvgouapp.R;
import com.uvgouapp.api.Api;
import com.uvgouapp.common.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class EnterAgreementActivity extends BaseActivity {

    @BindView(R.id.webview)
    WebView mWebView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_enter_agreement;
    }

    @Override
    protected void initView() {
        //加载入驻协议
        mWebView.loadUrl(Api.ENTER_AGREEMENT);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @OnClick(R.id.btn_know)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_know:
                startActivity(new Intent(this, CompleteActivity.class));
                finish();
                break;
            default:
                break;
        }
    }
}


