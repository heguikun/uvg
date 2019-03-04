package com.uvgouapp.ui.other;

import android.content.Intent;
import android.os.Bundle;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.uvgouapp.common.util.DialogUtil;
import com.uvgouapp.common.util.DownloadUtil;
import com.uvgouapp.common.util.PermissionsUtil;
import com.uvgouapp.contract.other.SplashContract;
import com.uvgouapp.home.HomeActivity;
import com.uvgouapp.presenter.other.SplashPresenter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.functions.Consumer;

public class SplashActivity extends AppCompatActivity implements SplashContract.View {

    private SplashPresenter mPresenter = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new SplashPresenter();

        mPresenter.attachView(this);

        mPresenter.onCreate();
    }

    @Override
    public void showRootWarning() {
        DialogUtil.showDialog(this, null, "手机已被Root, 由于安全原因，程序无法访问。",
                "确定", null, new DialogUtil.OnCusDialogInterface() {

                    @Override
                    public void onConfirmClick() {
                        finish();
                    }

                    @Override
                    public void onCancelClick() {

                    }
                }, false);
    }

    @Override
    public void launchAfterPermission() {
        PermissionsUtil.checkPermissions(this, new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                mPresenter.requestVersionUpdate();
            }
        });
    }

    @Override
    public void onReceiveNewVersion(String msg, String url) {
        DialogUtil.showDialog(this, "提示", msg, "确定", null, new DialogUtil.OnCusDialogInterface() {
            @Override
            public void onConfirmClick() {
                DownloadUtil.startUpdate(SplashActivity.this, url);
                finish();
            }

            @Override
            public void onCancelClick() {

            }
        }, false);
    }

    @Override
    public void onSuccessCheckVersion() {
        //------------------用户id----------------
        String userId = SPUtils.getInstance().getString("userId", "");
        if (!StringUtils.isEmpty(userId)) {
            startActivity(new Intent(this, HomeActivity.class));
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }
        finish();
    }

    @Override
    public void onFailError(String errorMsg) {
        DialogUtil.showDialog(SplashActivity.this, null, errorMsg, "确定", null, new DialogUtil.OnCusDialogInterface() {
            @Override
            public void onConfirmClick() {
                finish();
            }

            @Override
            public void onCancelClick() {

            }
        }, false);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onFailError(Throwable throwable) {

    }
}
