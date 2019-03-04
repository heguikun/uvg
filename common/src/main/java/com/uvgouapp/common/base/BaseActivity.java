package com.uvgouapp.common.base;

import android.content.Context;
import android.os.Bundle;

import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jaeger.library.StatusBarUtil;
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;
import com.uvgouapp.common.R;
import com.uvgouapp.common.dialog.LoadingDialog;

import androidx.annotation.Nullable;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * - @Author:  ying
 * - @Time:  2018/12/22
 * - @Description:  activity基类
 */
public abstract class BaseActivity<T extends BasePresenter> extends RxAppCompatActivity implements BaseView {

    public Context mContext = null;
    private CompositeDisposable compositeDisposable = null;
    private Unbinder mUnbinder = null;
    private LoadingDialog mLoadingDialog = null;
    protected T mPresenter = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doBeforeSetContentView();
        setContentView(getLayoutId());
        mUnbinder = ButterKnife.bind(this);
        mContext = this;
        mLoadingDialog = new LoadingDialog();

        initView();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initListener();
    }

    @Override
    protected void onPause() {
        super.onPause();
        KeyboardUtils.hideSoftInput(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        if (mLoadingDialog != null) {
            mLoadingDialog = null;
        }
        //Activity销毁时，取消网络请求
        dispose();
    }

    /**
     * 在布局之前相关配置
     */
    private void doBeforeSetContentView() {
        //设置状态栏颜色
        StatusBarUtil.setColor(this, getResources().getColor(R.color.app_color));
    }

    /**
     * @param disposable 添加
     */
    public void addDisposable(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    /**
     * 取消
     */
    private void dispose() {
        if (compositeDisposable != null)
            compositeDisposable.dispose();
    }

    /**
     * 判断网络是否连接以及视图是否绑定
     */
    public boolean isConnectNetWorkAndBindView() {
        //判断网络是否连接  View是否绑定 如果没有绑定,就不执行网络请求
        if (!NetworkUtils.isConnected() || !mPresenter.isViewAttached()) {
            ToastUtils.showShort("网络异常");
            return false;
        }
        return true;
    }

    @Override
    public void showLoading() {
        mLoadingDialog.show(this);
    }

    @Override
    public void hideLoading() {
        mLoadingDialog.hide();
    }

    @Override
    public void onFailError(Throwable throwable) {
        throwable.printStackTrace();
        ToastUtils.showShort("请求失败");
    }

    /*********************子类实现*****************************/
    protected abstract int getLayoutId(); //获取布局

    protected abstract void initView();//初始化视图

    protected abstract void initData();//初始化数据

    protected abstract void initListener();//初始化事件
}
