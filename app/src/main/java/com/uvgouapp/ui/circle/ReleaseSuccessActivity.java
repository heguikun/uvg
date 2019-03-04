package com.uvgouapp.ui.circle;

import android.view.KeyEvent;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.uvgouapp.R;
import com.uvgouapp.common.base.BaseActivity;
import com.uvgouapp.model.other.MessageEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.OnClick;

/**
 * - @Author:  ying
 * - @Time:  2019/1/4
 * - @Description:  发布成功
 */
public class ReleaseSuccessActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_release_success;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @OnClick({R.id.tv_wechat, R.id.tv_friends, R.id.tv_qq, R.id.tv_qqzone, R.id.tv_microblog, R.id.iv_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_wechat://微信
                ToastUtils.showShort("微信");
                break;
            case R.id.tv_friends://朋友圈
                ToastUtils.showShort("朋友圈");
                break;
            case R.id.tv_qq://QQ
                ToastUtils.showShort("QQ");
                break;
            case R.id.tv_qqzone://QQ空间
                ToastUtils.showShort("QQ空间");
                break;
            case R.id.tv_microblog://微博
                ToastUtils.showShort("微博");
                break;
            case R.id.iv_close://关闭
                close();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            close();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 关闭当前页面 并且发送消息
     */
    private void close() {
        finish();
        //------------------发送消息给淘友圈刷新数据----------------
        MessageEvent messageEvent = new MessageEvent();
        messageEvent.refresh = 10;
        EventBus.getDefault().post(messageEvent);
    }
}
