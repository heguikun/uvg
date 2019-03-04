package com.uvgouapp.ui.user;

import android.view.View;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.luck.picture.lib.decoration.RecycleViewDivider;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.uvgouapp.R;
import com.uvgouapp.adapter.user.FollowAdapter;
import com.uvgouapp.common.base.BaseActivity;
import com.uvgouapp.contract.user.FollowContract;
import com.uvgouapp.model.user.FollowFriendsBean;
import com.uvgouapp.presenter.user.FollowPresenter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

public class FollowActivity extends BaseActivity<FollowPresenter> implements FollowContract.View {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;

    private FollowAdapter mFollowAdapter = null;

    private int page = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_follow;
    }

    @Override
    protected void initView() {
        mPresenter = new FollowPresenter(this);
        mPresenter.attachView(this);
        mFollowAdapter = new FollowAdapter(R.layout.item_follow);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mFollowAdapter);
        mFollowAdapter.setPresenter(mPresenter);
        //添加默认分割线：高度为2px，颜色为灰色
        mRecyclerView.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL));
        mRefreshLayout.autoRefresh();
    }

    @Override
    protected void initData() {
        String userId = SPUtils.getInstance().getString("userId");
        mPresenter.requestUserQueryFollowFriends(userId, String.valueOf(page), "10");
    }

    @Override
    protected void initListener() {
        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                initData();
                mRefreshLayout.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                initData();
                mRefreshLayout.finishRefresh();
            }
        });
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

    @Override
    public void setFollowListData(List<FollowFriendsBean.MapListBean> listData) {
        switch (mRefreshLayout.getState()) {
            case Refreshing:
            case RefreshFinish:
                mFollowAdapter.setNewData(listData);
                break;
            case Loading:
            case LoadFinish:
                mFollowAdapter.addData(listData);
                break;
            default:
                break;
        }
    }
}
