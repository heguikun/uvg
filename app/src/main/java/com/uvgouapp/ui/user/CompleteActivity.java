package com.uvgouapp.ui.user;

import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.uvgouapp.R;
import com.uvgouapp.common.base.BaseActivity;

import butterknife.OnClick;

public class CompleteActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_complete;
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

    @OnClick({R.id.tv_complete, R.id.tv_contact_service})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_complete://完成
                finish();
                break;
            case R.id.tv_contact_service://联系客服
                ToastUtils.showShort("联系客服");
                break;
            default:
                break;
        }
    }
}
