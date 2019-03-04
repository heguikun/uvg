package com.uvgouapp.dialog.circle;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.uvgouapp.R;
import com.uvgouapp.api.Api;
import com.uvgouapp.common.constant.Constants;
import com.uvgouapp.common.util.GsonUtil;
import com.uvgouapp.model.user.StoreInfoBean;
import com.uvgouapp.ui.circle.ReleaseShopActivity;
import com.uvgouapp.ui.circle.ShareLiveActivity;
import com.uvgouapp.ui.user.ApplyStoreActivity;

import razerdp.basepopup.BasePopupWindow;

/**
 * - @Author:  ying
 * - @Time:  2019/1/3
 * - @Description:  发布商品  分享生活
 */
public class CircleCameraDialog extends BasePopupWindow implements View.OnClickListener {

    private LinearLayout mLlReleaseShop = null;
    private TextView mTvReleaseShop = null;

    public CircleCameraDialog(Context context) {
        super(context);
        initView();
    }

    private void initView() {

        TextView tvApplyShop = findViewById(R.id.tv_apply_shop);//申请店铺
        tvApplyShop.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//设置下划线

        mLlReleaseShop = findViewById(R.id.ll_release_shop);
        mTvReleaseShop = findViewById(R.id.tv_release_shop);

        //差群是否开店
        requestQueryShop(SPUtils.getInstance().getString("userId"));

        mLlReleaseShop.setOnClickListener(this);
        mTvReleaseShop.setOnClickListener(this);
        findViewById(R.id.tv_share_live).setOnClickListener(this);
        findViewById(R.id.tv_cancel).setOnClickListener(this);
    }

    private void requestQueryShop(String userId) {

        OkGo.<String>get(Api.QUERY_STORE)
                .params("userId", userId)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        if (!StringUtils.isEmpty(body)) {
                            StoreInfoBean storeInfoBean = GsonUtil.GsonToBean(body, StoreInfoBean.class);
                            if (storeInfoBean != null) {
                                int statusCode = storeInfoBean.getStatusCode();
                                if (statusCode == Constants.SUCCESS_CODE) {
                                    StoreInfoBean.DataBean data = storeInfoBean.getData();
                                    //判断是否为空   是   没有店铺   否  有店铺
                                    if (data != null) {
                                        mLlReleaseShop.setVisibility(View.GONE);
                                        mTvReleaseShop.setVisibility(View.VISIBLE);
                                    } else {
                                        mLlReleaseShop.setVisibility(View.VISIBLE);
                                        mTvReleaseShop.setVisibility(View.GONE);
                                    }
                                }
                            }
                        }
                    }
                });
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.dialog_circle_camera);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_release_shop://申请店铺
                dismiss();
                getContext().startActivity(new Intent(getContext(), ApplyStoreActivity.class));
                break;
            case R.id.tv_release_shop://发布商品
                dismiss();
                Intent intent = new Intent(getContext(), ReleaseShopActivity.class);
                intent.putExtra("releaseShop", 0);//0.快捷发布  1.标准发布
                getContext().startActivity(intent);
                break;
            case R.id.tv_share_live://分享生活
                dismiss();
                getContext().startActivity(new Intent(getContext(), ShareLiveActivity.class));
                break;
            case R.id.tv_cancel://取消
                dismiss();
                break;
            default:
                break;
        }
    }
}
