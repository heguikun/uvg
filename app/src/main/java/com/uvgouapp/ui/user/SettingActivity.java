package com.uvgouapp.ui.user;

import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.uvgouapp.R;
import com.uvgouapp.common.base.BaseActivity;
import com.uvgouapp.dialog.user.UserCacheCleanDialog;
import com.uvgouapp.dialog.user.UserLoginOutDialog;

import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity {

    @BindView(R.id.tv_version)
    TextView mTvVersion;//版本号

    private UserLoginOutDialog mMineLoginOut = null;
    private UserCacheCleanDialog mMineCacheCleanDialog = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        mTvVersion.setText(String.format(Locale.ENGLISH, "V%s", AppUtils.getAppVersionName()));
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @OnClick({R.id.iv_back, R.id.rl_notice, R.id.rl_privacy, R.id.rl_clear_cache,
            R.id.rl_cancel_account, R.id.tv_login_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back://返回
                finish();
                break;
            case R.id.rl_notice://音效与通知
                ToastUtils.showShort("音效与通知");
                break;
            case R.id.rl_privacy://隐私
                ToastUtils.showShort("隐私");
                break;
            case R.id.rl_clear_cache://清理缓存
                mMineCacheCleanDialog = new UserCacheCleanDialog(mContext);
                mMineCacheCleanDialog.showPopupWindow();
                break;
            case R.id.rl_cancel_account://注销账户
                ToastUtils.showShort("注销账户");
                break;
            case R.id.tv_login_out://退出当前账户
                mMineLoginOut = new UserLoginOutDialog(mContext);
                mMineLoginOut.showPopupWindow();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //防止内存泄漏
        if (mMineLoginOut != null) {
            mMineLoginOut.dismiss();
            mMineLoginOut = null;
        }
        if (mMineCacheCleanDialog != null) {
            mMineCacheCleanDialog.dismiss();
            mMineCacheCleanDialog = null;
        }
    }
}
