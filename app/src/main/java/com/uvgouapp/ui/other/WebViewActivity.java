package com.uvgouapp.ui.other;


import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tencent.smtt.sdk.WebView;
import com.uvgouapp.R;
import com.uvgouapp.common.base.BaseActivity;
import com.uvgouapp.contract.other.WebViewContract;
import com.uvgouapp.home.HomeActivity;
import com.uvgouapp.presenter.other.WebViewPresenter;
import com.uvgouapp.ui.circle.CircleInformationActivity;
import com.uvgouapp.ui.circle.ReleaseShopActivity;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * - @Author:  ying
 * - @Time:  2019/1/25
 * - @Description:  加载网页
 */
public class WebViewActivity extends BaseActivity<WebViewPresenter> implements WebViewContract.View {

    @BindView(R.id.rl_title)
    RelativeLayout mRelativeLayout;//标题布局
    @BindView(R.id.tv_title)
    TextView mTvTitle;//标题
    @BindView(R.id.iv_photo)
    ImageView mIvPhoto;//标准发布
    @BindView(R.id.fl_root)
    FrameLayout mViewGroup;
    //@BindView(R.id.progressbar)
    //public ProgressBar mProgressbar;
    @BindView(R.id.jswebview)
    WebView mWebView;
    @BindView(R.id.webview_error_txt)
    TextView mWebviewErrorTxt;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web_view;
    }

    @Override
    protected void initView() {
        mPresenter = new WebViewPresenter(this);
        mPresenter.attachView(this);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            String url = intent.getStringExtra("URL");
            mPresenter.setWebViewAttribute(mWebView, url);
        }
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void goback() {
        finish();//产品详情返回
    }

    @Override
    public void goback(String url) {

    }

    @Override
    public void goHome() {
        //回到首页
        startActivity(new Intent(this, HomeActivity.class));
    }

    @Override
    public void displayErrorView(boolean show) {
        mWebviewErrorTxt.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void progressBarView(boolean show) {
        //mProgressbar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showTitle(String title) {
        if (title.equals("商品列表")) {
            String titleShop = getIntent().getStringExtra("title");
            mTvTitle.setText(titleShop);
        } else {
            mTvTitle.setText(title);
        }
        //-----------判断是否显示标准发布--------------
        if (title.equals("产品管理")) {
            mIvPhoto.setVisibility(View.VISIBLE);
        } else {
            mIvPhoto.setVisibility(View.GONE);
        }
        if (title.equals("产品详情")) {//隐藏顶部标题
            mRelativeLayout.setVisibility(View.GONE);
        } else {
            mRelativeLayout.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void goUserDetails(String userId) {
        Intent intent = new Intent(mContext, CircleInformationActivity.class);
        intent.putExtra("userId", userId);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy(mViewGroup);
    }

    @OnClick({R.id.iv_photo, R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_photo:
                Intent intent = new Intent(mContext, ReleaseShopActivity.class);
                intent.putExtra("releaseShop", 1);//0.快捷发布  1.标准发布
                startActivity(intent);
                break;
            case R.id.iv_back:
                String title = mTvTitle.getText().toString().trim();
                if (title.equals("店铺") || title.equals("钱包管理") || title.equals("地址管理")
                        || title.equals("购物车") || title.equals("商品列表") || title.equals("积分管理")
                        || title.equals("我的收藏") || title.equals("订单管理") || title.equals("美妆护肤") || title.equals("服装")
                        || title.equals("鞋靴") || title.equals("母婴") || title.equals("家电") || title.equals("居家百货")
                        || title.equals("食品") || title.equals("配饰") || title.equals("手机数码") || title.equals("整车车品")) {
                    finish();
                } else {//一级一级回退
                    mWebView.goBack();
                }
                break;
            default:
                break;
        }
    }

    //使用Webview的时候，返回键没有重写的时候会直接关闭程序，这时候其实我们要其执行的知识回退到上一步的操作
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //这是一个监听用的按键的方法，keyCode 监听用户的动作，如果是按了返回键，同时Webview要返回的话，WebView执行回退操作，因为mWebView.canGoBack()返回的是一个Boolean类型，所以我们把它返回为true
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        } else {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
