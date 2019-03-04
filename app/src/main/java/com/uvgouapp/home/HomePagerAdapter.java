package com.uvgouapp.home;


import com.uvgouapp.common.base.BaseFragment;

import java.util.ArrayList;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * - @Author:  ying
 * - @Time:  2018/12/23
 * - @Description:
 */
public class HomePagerAdapter extends FragmentPagerAdapter {
    private ArrayList<BaseFragment> mFragments;
    private String[] mTitles;

    public HomePagerAdapter(FragmentManager fm, ArrayList<BaseFragment> fragments, String[] titles) {
        super(fm);
        this.mFragments = fragments;
        this.mTitles = titles;
    }

    @Override
    public int getCount() {
        return mFragments.size() > 0 ? mFragments.size() : 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override
    public BaseFragment getItem(int position) {
        return mFragments.get(position);
    }
}
