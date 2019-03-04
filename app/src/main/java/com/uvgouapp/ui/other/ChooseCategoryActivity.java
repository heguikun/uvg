package com.uvgouapp.ui.other;


import android.content.Intent;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.uvgouapp.R;
import com.uvgouapp.adapter.other.ChooseCategoryAdapter;
import com.uvgouapp.common.base.BaseActivity;
import com.uvgouapp.contract.other.ChooseCategoryContract;
import com.uvgouapp.model.other.ChooseCategoryBean;
import com.uvgouapp.presenter.other.ChooseCategoryPresenter;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * - @Author:  ying
 * - @Time:  2019/2/14
 * - @Description:  选择类目
 */
public class ChooseCategoryActivity extends BaseActivity<ChooseCategoryPresenter> implements ChooseCategoryContract.View {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private ChooseCategoryAdapter mChooseCategoryAdapter = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_choose_category;
    }

    @Override
    protected void initView() {
        mPresenter = new ChooseCategoryPresenter(this);
        mPresenter.attachView(this);
        mChooseCategoryAdapter = new ChooseCategoryAdapter(R.layout.item_choose_category);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mChooseCategoryAdapter);
    }

    @Override
    protected void initData() {
        mPresenter.requestShopCategory();
    }

    @Override
    protected void initListener() {
        mChooseCategoryAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String name = mChooseCategoryAdapter.getData().get(position).getName();
                //--------------------结果回传---------------
                Intent intent = new Intent();
                intent.putExtra("result", name);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
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

    @Override
    public void setShopCategoryList(List<ChooseCategoryBean.DataBean> listData) {
        mChooseCategoryAdapter.addData(listData);
        mChooseCategoryAdapter.notifyDataSetChanged();
    }
}
