package com.uvgouapp.ui.other;

import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.uvgouapp.R;
import com.uvgouapp.common.base.BaseActivity;
import com.uvgouapp.contract.other.RechargeRecordContract;
import com.uvgouapp.presenter.other.RechargeRecordPresenter;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

public class RechargeRecordActivity extends BaseActivity<RechargeRecordPresenter> implements RechargeRecordContract.View {

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recharge_record;
    }

    @Override
    protected void initView() {
        mPresenter = new RechargeRecordPresenter();

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
            case R.id.iv_back://返回
                finish();
                break;
            default:
                break;
        }
    }
}
