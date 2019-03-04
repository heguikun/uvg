package com.uvgouapp.ui.user;


import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.SPUtils;
import com.uvgouapp.R;
import com.uvgouapp.common.base.BaseActivity;
import com.uvgouapp.common.util.QRCodeUtil;
import com.uvgouapp.common.util.WxShareAndLoginUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * - @Author:  ying
 * - @Time:  2019/2/15
 * - @Description:  我的邀请
 */
public class MyInvitationActivity extends BaseActivity {

    @BindView(R.id.iv_qr_code)
    ImageView mIvQrCode;//二维码

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_invitation;
    }

    @Override
    protected void initView() {
        QRCodeUtil.createQRCode("666666", mIvQrCode);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @OnClick({R.id.iv_back, R.id.rl_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back://返回
                finish();
                break;
            case R.id.rl_share://分享
                WxShareAndLoginUtil.WxUrlShare(this, "www.baidu.com", "分享人:" + SPUtils.getInstance().getString("name"), "这里有你想要的", WxShareAndLoginUtil.WECHAT_FRIEND);
                break;
            default:
                break;
        }
    }
}
