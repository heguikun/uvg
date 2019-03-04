package com.uvgouapp.ui.other;


import android.view.View;
import android.widget.EditText;

import com.blankj.utilcode.util.ToastUtils;
import com.uvgouapp.R;
import com.uvgouapp.common.base.BaseActivity;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * - @Author:  ying
 * - @Time:  2019/2/14
 * - @Description:  品牌
 */
public class BrandActivity extends BaseActivity {

    @BindView(R.id.et_search)
    EditText mEtSearch;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_brand;
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

    @OnClick({R.id.iv_back, R.id.tv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back://返回
                finish();
                break;
            case R.id.tv_search://搜索
                ToastUtils.showShort("搜索");
                break;
            default:
                break;
        }
    }
}
