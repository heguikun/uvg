package com.uvgouapp.dialog.show;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.uvgouapp.R;
import com.uvgouapp.ui.show.ShowRocketTopActivity;

import razerdp.basepopup.BasePopupWindow;

/**
 * - @Author:  ying
 * - @Time:  2019/1/10
 * - @Description:  火箭
 */
public class ShowRocketDialog extends BasePopupWindow implements View.OnClickListener {

    private TextView mTvBuy, mTvRelease, mTvTask, mTvCancel, mTvBuyRocket, mTvGoRelease;

    public ShowRocketDialog(Context context) {
        super(context);
        initView();
    }

    private void initView() {

        mTvBuy = findViewById(R.id.tv_buy);//购买内容
        mTvRelease = findViewById(R.id.tv_release);//发布内容

        mTvTask = findViewById(R.id.tv_task);
        mTvCancel = findViewById(R.id.tv_cancel);

        mTvBuyRocket = findViewById(R.id.tv_buy_rocket);
        mTvGoRelease = findViewById(R.id.tv_go_release);

        showBuy();

        //------------------点击事件----------------------
        mTvTask.setOnClickListener(this);
        mTvCancel.setOnClickListener(this);
        mTvBuyRocket.setOnClickListener(this);
        mTvGoRelease.setOnClickListener(this);
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.dialog_show_online);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_task://做任务
                dismiss();
                ToastUtils.showShort("敬请期待");
                break;
            case R.id.tv_buy_rocket://购买火箭
                dismiss();
                getContext().startActivity(new Intent(getContext(), ShowRocketTopActivity.class));
                break;
            case R.id.tv_cancel://取消
                dismiss();
                break;
            case R.id.tv_go_release://去发布
                ToastUtils.showShort("去发布");
                break;
            default:
                break;
        }
    }

    /**
     * 显示新人第一次购买需要发布   隐藏购买火箭
     */
    private void showRelease() {
        mTvRelease.setVisibility(View.VISIBLE);
        mTvCancel.setVisibility(View.VISIBLE);
        mTvGoRelease.setVisibility(View.VISIBLE);

        mTvBuy.setVisibility(View.GONE);
        mTvTask.setVisibility(View.GONE);
        mTvBuyRocket.setVisibility(View.GONE);
    }

    /**
     * 显示购买火箭  隐藏新人发布
     */
    private void showBuy() {
        mTvBuy.setVisibility(View.VISIBLE);
        mTvTask.setVisibility(View.VISIBLE);
        mTvBuyRocket.setVisibility(View.VISIBLE);

        mTvRelease.setVisibility(View.GONE);
        mTvCancel.setVisibility(View.GONE);
        mTvGoRelease.setVisibility(View.GONE);
    }

}
