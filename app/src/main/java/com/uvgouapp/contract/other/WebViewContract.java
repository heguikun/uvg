package com.uvgouapp.contract.other;


import android.view.ViewGroup;

import com.tencent.smtt.sdk.WebView;
import com.uvgouapp.common.base.BaseView;

/**
 * - @Author:  ying
 * - @Time:  2019/1/25
 * - @Description:
 */
public interface WebViewContract {

    interface Presenter {

        void setWebViewAttribute(WebView webView, String url);

        void onPause();

        void onResume();

        void onDestroy(ViewGroup webParentView);

    }

    interface View extends BaseView {

        void goback();

        void goback(String url);

        void goHome();

        void displayErrorView(boolean show);

        void progressBarView(boolean show);

        void showTitle(String title);

        void goUserDetails(String userId);

    }

}
