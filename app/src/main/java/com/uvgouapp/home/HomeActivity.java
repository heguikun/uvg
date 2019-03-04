package com.uvgouapp.home;

import android.view.KeyEvent;

import com.blankj.utilcode.util.ToastUtils;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.uvgouapp.R;
import com.uvgouapp.common.base.BaseActivity;
import com.uvgouapp.common.base.BaseFragment;
import com.uvgouapp.common.constant.Constants;
import com.uvgouapp.view.NoScrollViewPager;

import java.util.ArrayList;

import butterknife.BindView;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.common_tab_layout)
    CommonTabLayout mCommonTabLayout;
    @BindView(R.id.viewpager)
    NoScrollViewPager mViewPager;

    private long exitTime = 0;

    private ArrayList<BaseFragment> mFragments = null;
    private ArrayList<CustomTabEntity> mTabEntities = null;

    private final String[] mTitles = {"商城", "秀场", "消息", "淘友圈", "我的"};
    private final int[] mIconUnselectIds = {
            R.drawable.tab_shop_unselect, R.drawable.tab_show_unselect,
            R.drawable.tab_news_unselect, R.drawable.tab_circle_unselect, R.drawable.tab_mine_unselect};
    private final int[] mIconSelectIds = {
            R.drawable.tab_shop_select, R.drawable.tab_show_select,
            R.drawable.tab_news_select, R.drawable.tab_circle_select, R.drawable.tab_mine_select};

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        mFragments = new ArrayList<>();
        mTabEntities = new ArrayList<>();
    }

    @Override
    protected void initData() {
        //添加 商城  秀场  消息  淘友圈  我的 fragment
        mFragments.add(FragmentFactory.getInstance().createShopFragment());
        mFragments.add(FragmentFactory.getInstance().createShowFragment());
        mFragments.add(FragmentFactory.getInstance().createNewsFragment());
        mFragments.add(FragmentFactory.getInstance().createCircleTwoFragment());
        mFragments.add(FragmentFactory.getInstance().createUserFragment());
        for (int i = Constants.CURRENT_ITEM; i < mTitles.length; i++) {
            mTabEntities.add(new TabBean(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }

        mViewPager.setAdapter(new HomePagerAdapter(getSupportFragmentManager(), mFragments, mTitles));
        mCommonTabLayout.setTabData(mTabEntities);
        mViewPager.setScroll(false);//禁止滑动
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setCurrentItem(Constants.CURRENT_ITEM,false);//默认选中第一项

    }

    @Override
    protected void initListener() {
        //点击事件
        mCommonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position,false);

            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    /*
     * 重写onKeyDown方法
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            //2s之内按返回键就会退出
            if ((System.currentTimeMillis() - exitTime) > Constants.EXIT_TIME) {
                ToastUtils.showShort("再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }

    /**
     * @return 淘友圈进入秀场页面  需要该对象进行切换
     */
    public NoScrollViewPager getViewPager() {
        return mViewPager;
    }

    /**
     * @return 淘友圈进入秀场页面  需要该对象进行切换
     */
    public CommonTabLayout getCommonTabLayout() {
        return mCommonTabLayout;
    }
}
