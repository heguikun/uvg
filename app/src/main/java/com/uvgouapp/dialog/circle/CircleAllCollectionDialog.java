package com.uvgouapp.dialog.circle;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.uvgouapp.R;
import com.uvgouapp.presenter.circle.CirclePresenter;

import razerdp.basepopup.BasePopupWindow;

/**
 * - @Author:  ying
 * - @Time:  2019/1/3
 * - @Description:  收藏
 */
public class CircleAllCollectionDialog extends BasePopupWindow implements View.OnClickListener {

    private RelativeLayout mRlAddCollection, mRlAddShop;

    private TextView mTvAddCollection, mTvAddShop;

    private CirclePresenter mPresenter;
    private String commodityId;
    private String commodityShopId;
    private Object collect;
    private int position;

    public CircleAllCollectionDialog(Context context, String commodityId, String commodityShopId, Object collect, int position, CirclePresenter presenter) {
        super(context);
        this.commodityId = commodityId;
        this.commodityShopId = commodityShopId;
        this.collect = collect;
        this.position = position;
        this.mPresenter = presenter;
        initView();
    }

    private void initView() {
        mRlAddCollection = findViewById(R.id.rl_add_collection);
        mRlAddCollection.setOnClickListener(this);
        mTvAddCollection = findViewById(R.id.tv_add_collection);
        mRlAddShop = findViewById(R.id.rl_add_shop);
        mRlAddShop.setOnClickListener(this);
        mTvAddShop = findViewById(R.id.tv_add_shop);

        if (collect != null) {
            mRlAddCollection.setSelected(true);
        } else {
            mRlAddCollection.setSelected(false);
        }

        findViewById(R.id.tv_ok).setOnClickListener(this);
        findViewById(R.id.tv_cancel).setOnClickListener(this);
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.dialog_circle_collection);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_add_collection://收藏夹   设置图片以及颜色
                if (mRlAddCollection.isSelected()) {
                    mRlAddCollection.setSelected(false);
                    mTvAddCollection.setTextColor(getContext().getResources().getColor(R.color.color_1a1a1a));
                    Drawable drawable = getContext().getResources().getDrawable(R.drawable.collection_un_stars);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    mTvAddCollection.setCompoundDrawables(drawable, null, null, null);
                } else {
                    mRlAddCollection.setSelected(true);
                    mTvAddCollection.setTextColor(getContext().getResources().getColor(R.color.color_white));
                    Drawable drawable = getContext().getResources().getDrawable(R.drawable.collection_stars);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    mTvAddCollection.setCompoundDrawables(drawable, null, null, null);
                }
                break;
            case R.id.rl_add_shop://商品库   设置图片以及颜色
                if (mRlAddShop.isSelected()) {
                    mRlAddShop.setSelected(false);
                    mTvAddShop.setTextColor(getContext().getResources().getColor(R.color.color_1a1a1a));
                    Drawable drawable = getContext().getResources().getDrawable(R.drawable.collection_un_plus);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    mTvAddShop.setCompoundDrawables(drawable, null, null, null);
                } else {
                    mRlAddShop.setSelected(true);
                    mTvAddShop.setTextColor(getContext().getResources().getColor(R.color.color_white));
                    Drawable drawable = getContext().getResources().getDrawable(R.drawable.collection_plus);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    mTvAddShop.setCompoundDrawables(drawable, null, null, null);
                }
                break;
            case R.id.tv_ok://确认
                int type = 0;
                if (mRlAddCollection.isSelected()) {
                    mPresenter.addCollect(commodityId, SPUtils.getInstance().getString("userId"), commodityShopId, position);
                    type = 1;
                }
                if (mRlAddShop.isSelected()) {
                    //添加商品库
                    addShopStock();
                    type = 2;
                }
                CircleAddCollectionDialog circleAddCollectionDialog = new CircleAddCollectionDialog(getContext(), type);
                circleAddCollectionDialog.showPopupWindow();
                dismiss();
                break;
            case R.id.tv_cancel://取消
                dismiss();
                break;
            default:
                break;
        }
    }

    /**
     * 删除商品库
     */
    private void deleteShopStock() {

    }

    /**
     * 添加商品库
     */
    private void addShopStock() {

    }

    /**
     * 添加收藏
     */
    private void addCollect() {

    }

    /**
     * 删除收藏
     */
    private void deleteCollect() {

    }
}
