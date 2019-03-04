package com.uvgouapp.ui.circle;

import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.uvgouapp.R;
import com.uvgouapp.adapter.circle.CircleLookAllAdapter;
import com.uvgouapp.common.base.BaseActivity;
import com.uvgouapp.common.util.ImageLoaderUtil;
import com.uvgouapp.contract.circle.CircleLookOtherInfoContract;
import com.uvgouapp.dialog.circle.CircleCameraDialog;
import com.uvgouapp.model.circle.CircleLookAllBean;
import com.uvgouapp.presenter.circle.CircleLookOtherInfoPresenter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class CircleLookOtherInfoActivity extends BaseActivity<CircleLookOtherInfoPresenter> implements CircleLookOtherInfoContract.View, View.OnClickListener {

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.fl_title)
    FrameLayout mFrameLayout;//标题布局
    @BindView(R.id.iv_back)
    ImageView mIvBack;//返回
    @BindView(R.id.iv_photo)
    ImageView mIvPhoto;//发布
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    //------------------头部-----------------
    private ImageView mIvHeadBg;//背景图片
    private ImageView mIvHeadPortrait;//头像
    private TextView mTvName;//用户名
    private SegmentTabLayout mSegmentTabLayout;

    private final String[] mTitles = {"全部", "商品", "生活"};

    private CircleLookAllAdapter mCircleLookAllAdapter = null;
    private int page = 1;//页数
    private String userId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_look_all;
    }

    @Override
    protected void initView() {
        ArrayList<CircleLookAllBean> list = new ArrayList<>();
        View view = View.inflate(this, R.layout.item_circle_head, null);
        mPresenter = new CircleLookOtherInfoPresenter(this);
        mPresenter.attachView(this);

        mCircleLookAllAdapter = new CircleLookAllAdapter(list);
        mCircleLookAllAdapter.addHeaderView(view);//添加头部

        mIvHeadBg = view.findViewById(R.id.iv_head_bg);
        mIvHeadPortrait = view.findViewById(R.id.iv_head_portrait);
        mTvName = view.findViewById(R.id.tv_name);
        mSegmentTabLayout = view.findViewById(R.id.segment_tab_layout);
        view.findViewById(R.id.tv_address).setVisibility(View.INVISIBLE);//隐藏定位不需要定位

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mCircleLookAllAdapter);
        mSegmentTabLayout.setTabData(mTitles);
        mSegmentTabLayout.setCurrentTab(2);//默认选中生活
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            userId = intent.getStringExtra("userId");
            mPresenter.requestQueryUserInfo(userId);
            loadData();
        }
    }

    @Override
    protected void initListener() {
        //--------------发布,顶部-----------------
        mIvPhoto.setOnClickListener(this);
        mIvBack.setOnClickListener(this);

        mSegmentTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                page = 1;
                loadData();
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                loadData();
                mRefreshLayout.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                initData();
                mRefreshLayout.finishRefresh();
            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {//静止
                    ImageLoaderUtil.resumeRequests(mContext);
                } else {
                    ImageLoaderUtil.pauseRequests(mContext);
                }
            }
        });

        mCircleLookAllAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CircleLookAllBean circleLookAllBean = mCircleLookAllAdapter.getData().get(position);
                Intent intent = new Intent(mContext, CircleDetailsActivity.class);
                intent.putExtra("CircleLookAllBean", circleLookAllBean);
                startActivity(intent);
            }
        });

    }

    private void loadData() {
        switch (mSegmentTabLayout.getCurrentTab()) {
            case 0://全部
                mPresenter.requestCircleLookAll(userId, String.valueOf(page), "10");
                break;
            case 1://商品
                mPresenter.requestCircleLookShop(userId, String.valueOf(page), "10");
                break;
            case 2://生活
                mPresenter.requestCircleLookLive(userId, String.valueOf(page), "10");
                break;
            default:
                break;
        }
    }

    @Override
    public void showBgImg(String bgImg) {
        ImageLoaderUtil.into(mContext, bgImg, mIvHeadBg);
    }

    @Override
    public void showDefaultImg() {
        ImageLoaderUtil.into(mContext, R.drawable.tyq_bg, mIvHeadBg);
    }

    @Override
    public void showHeadImg(String headImg) {
        ImageLoaderUtil.into(mContext, headImg, mIvHeadPortrait);
    }

    @Override
    public void showUsername(String username) {
        mTvName.setText(username);
    }

    @Override
    public void setCircleLookAllDataList(List<CircleLookAllBean> dataList) {
        if (page == 1) {
            mCircleLookAllAdapter.setNewData(dataList);
        } else {
            mCircleLookAllAdapter.addData(dataList);
            mCircleLookAllAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_photo://发布商品  分享生活
                CircleCameraDialog circleCameraDialog = new CircleCameraDialog(this);
                circleCameraDialog.showPopupWindow();
                break;
            case R.id.iv_back://返回
                finish();
                break;
            default:
                break;
        }
    }
}
