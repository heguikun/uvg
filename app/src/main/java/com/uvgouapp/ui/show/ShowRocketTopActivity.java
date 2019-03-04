package com.uvgouapp.ui.show;

import android.content.Intent;
import android.view.View;

import com.uvgouapp.R;
import com.uvgouapp.common.base.BaseActivity;
import com.uvgouapp.ui.other.RechargeActivity;

import butterknife.OnClick;

public class ShowRocketTopActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_show_rocket_top;
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

    @OnClick({R.id.iv_back, R.id.btn_rocket})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back://返回
                finish();
                break;
            case R.id.btn_rocket://购买火箭
                startActivity(new Intent(this, RechargeActivity.class));
                finish();
                break;
            default:
                break;
        }
    }
}
