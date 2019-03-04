package com.uvgouapp.home;

import com.flyco.tablayout.listener.CustomTabEntity;

/**
 * - @Author:  ying
 * - @Time:  2018/12/23
 * - @Description:
 */
public class TabBean implements CustomTabEntity {

    private String title;
    private int selectedIcon;
    private int unSelectedIcon;

    public TabBean(String title, int selectedIcon, int unSelectedIcon) {
        this.title = title;
        this.selectedIcon = selectedIcon;
        this.unSelectedIcon = unSelectedIcon;
    }

    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public int getTabSelectedIcon() {
        return selectedIcon;
    }

    @Override
    public int getTabUnselectedIcon() {
        return unSelectedIcon;
    }
}
