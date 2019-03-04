package com.uvgouapp.common.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.trello.rxlifecycle3.components.support.RxFragment;
import com.uvgouapp.common.dialog.LoadingDialog;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * - @Author:  ying
 * - @Time:  2018/12/22
 * - @Description:  fragment基类
 */
public abstract class BaseFragment<T extends BasePresenter> extends RxFragment implements BaseView {

    public FragmentActivity mBaseActivity = null;
    private Unbinder mUnbinder = null;
    private CompositeDisposable compositeDisposable = null;
    private LoadingDialog mLoadingDialog = null;
    protected T mPresenter = null;

    //Fragment的View加载完毕的标记
    private boolean isViewCreated = false;
    //Fragment对用户可见的标记
    private boolean isUIVisible = false;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //isVisibleToUser这个boolean值表示:该Fragment的UI 用户是否可见
        if (isVisibleToUser) {
            isUIVisible = true;
            lazyLoad();
        } else {
            isUIVisible = false;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mBaseActivity = getActivity();
        mLoadingDialog = new LoadingDialog();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView;
        if (setLayout() instanceof Integer) {
            rootView = inflater.inflate((Integer) setLayout(), container, false);
        } else if (setLayout() instanceof View) {
            rootView = (View) setLayout();
        } else {
            throw new ClassCastException("setLayout() type must be int or View");
        }

        mUnbinder = ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewCreated = true;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getUserVisibleHint()) {
            lazyLoad();
        }
    }

    /**
     * 懒加载
     */
    private void lazyLoad() {
        //这里进行双重标记判断,是因为setUserVisibleHint会多次回调,并且会在onCreateView执行前回调,必须确保onCreateView加载完毕且页面可见,才加载数据
        if (isViewCreated && isUIVisible) {
            initData();
            //数据加载完毕,恢复标记,防止重复加载
            isViewCreated = false;
            isUIVisible = false;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        initListener();
    }

    @Override
    public void onPause() {
        super.onPause();
        KeyboardUtils.hideSoftInput(mBaseActivity);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //页面销毁,恢复标记
        isViewCreated = false;
        isUIVisible = false;
    }

    @Override
    public void onDestroy() {
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
        dispose();
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
        mLoadingDialog.show(mBaseActivity);
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
    protected abstract Object setLayout();  //设置布局

    protected abstract void initView();//初始化视图

    protected abstract void initData();//初始化数据

    protected abstract void initListener();//初始化事件

}
