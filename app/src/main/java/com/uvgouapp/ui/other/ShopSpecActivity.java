package com.uvgouapp.ui.other;


import android.view.View;

import com.uvgouapp.R;
import com.uvgouapp.common.base.BaseActivity;

import butterknife.OnClick;

/**
 * - @Author:  ying
 * - @Time:  2019/2/14
 * - @Description:  商品规格
 */
public class ShopSpecActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shop_spec;
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

    @OnClick(R.id.iv_back)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            default:
                break;
        }
    }
}
