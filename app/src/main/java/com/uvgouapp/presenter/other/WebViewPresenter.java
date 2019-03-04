package com.uvgouapp.presenter.other;


import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;

import com.lzy.okgo.OkGo;
import com.tencent.smtt.export.external.interfaces.GeolocationPermissionsCallback;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.export.external.interfaces.WebResourceError;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.sdk.CookieManager;
import com.tencent.smtt.sdk.CookieSyncManager;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.uvgouapp.common.base.BasePresenter;
import com.uvgouapp.common.util.ClipboardUtil;
import com.uvgouapp.common.util.WxShareAndLoginUtil;
import com.uvgouapp.contract.other.WebViewContract;
import com.uvgouapp.model.other.OnlinePayManager;
import com.uvgouapp.ui.other.WebViewActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.Cookie;


/**
 * - @Author:  ying
 * - @Time:  2019/1/25
 * - @Description:  网页
 */
public class WebViewPresenter extends BasePresenter<WebViewContract.View> implements WebViewContract.Presenter {

    private WebViewActivity mWebViewActivity;

    private WebView mWebView;

    public WebViewPresenter(WebViewActivity webViewActivity) {
        this.mWebViewActivity = webViewActivity;
    }

    @Override
    public void setWebViewAttribute(WebView webView, String url) {
        this.mWebView = webView;
        WebSettings settings = mWebView.getSettings();
        settings.setDisplayZoomControls(false);
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setGeolocationEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setAppCacheEnabled(true);
        settings.setAllowContentAccess(true);
        settings.setAllowUniversalAccessFromFileURLs(true);
        settings.setDatabaseEnabled(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setMediaPlaybackRequiresUserGesture(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            mWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else {
            mWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        //设置播放网页视频的属性
        mWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mWebView.setDrawingCacheEnabled(true);
        mWebView.clearCache(true);
        mWebView.removeJavascriptInterface("accessibility");
        mWebView.removeJavascriptInterface("accessibilityTraversal");
        mWebView.removeJavascriptInterface("searchBoxJavaBridge_");

        mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        mWebView.setInitialScale(1);
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView webView, int i) {
                //                if (i == 100) {
                // mView.progressBarView(false);
                //                } else {
                //mView.progressBarView(true);
                // mWebViewActivity.mProgressbar.setProgress(i);
                //                }
                super.onProgressChanged(webView, i);
            }

            @Override
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissionsCallback callback) {
                callback.invoke(origin, true, false);
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }

            /**
             * @param webView  网页
             * @param title    标题
             */
            @Override
            public void onReceivedTitle(WebView webView, String title) {
                super.onReceivedTitle(webView, title);
                Log.i("UWE_JS", title);
                mView.showTitle(title);
            }
        });

        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                mView.displayErrorView(false);
                mView.showLoading();//页面开始加载
            }

            @Override
            public void onPageFinished(WebView webView, String s) {
                super.onPageFinished(webView, s);
                mView.hideLoading();//页面加载完成
            }

            @Override
            public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, com.tencent.smtt.export.external.interfaces.SslError sslError) {
                super.onReceivedSslError(webView, sslErrorHandler, sslError);
                Log.w("WebError", webView.getUrl());
                if (webView.getUrl().startsWith("https://open.163.com") || webView.getUrl().startsWith("https://m.open.163.com")) {
                    sslErrorHandler.proceed();
                }
            }

            /**
             * @param view   这里进行无网络或错误处理，具体可以根据errorCode的值进行判断，做跟详细的处理。
             */
            // 旧版本，会在新版本中也可能被调用，所以加上一个判断，防止重复显示
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                Log.e("onReceivedError", "onReceivedError: ----url:" + errorCode);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    return;
                }
                mView.displayErrorView(true);
            }

            // 新版本，只会在Android6及以上调用
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                if (request.isForMainFrame()) { // 或者： if(request.getUrl().toString() .equals(getUrl()))
                    // 在这里显示自定义错误页
                    Log.e("onReceivedError", "onReceivedError: ----url:" + error);
                    mView.displayErrorView(true);
                }
            }
        });

        mWebView.addJavascriptInterface(this, "andro");//Android与JS交互   andro就是JS反射出来的对象
        syncCookie(mWebViewActivity, url);// 设置cookie
        mWebView.loadUrl(url);
    }

    /**
     * 同步一下cookie
     */
    private void syncCookie(Context context, String url) {
        CookieSyncManager.createInstance(context);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        //获取cookie
        List<Cookie> allCookie = OkGo.getInstance().getCookieJar().getCookieStore().getAllCookie();
        int size = allCookie.size();
        for (int i = 0; i < size; i++) {
            Cookie cookie = allCookie.get(i);
            cookieManager.setCookie(url, cookie.toString());//cookies是在HttpClient中获得的cookie
        }
        CookieSyncManager.getInstance().sync();
    }

    @Override
    public void onPause() {
        mWebView.onPause();
    }

    @Override
    public void onResume() {
        mWebView.onResume();
    }

    @Override
    public void onDestroy(ViewGroup webParentView) {
        if (webParentView != null && mWebView != null) {
            webParentView.removeView(mWebView);
            mWebView.removeAllViews();
            mWebView.destroy();
        }
    }

    @JavascriptInterface
    public void goback(String url) {
        mWebView.post(new Runnable() {
            @Override
            public void run() {
                Log.i("UWE_JS", url);
                mView.goback(url);
            }
        });
    }

    @JavascriptInterface
    public void backtotop() {
        mWebView.post(new Runnable() {
            @Override
            public void run() {
                mView.goback();
            }
        });
    }

    @JavascriptInterface
    public void goHome() {
        mWebView.post(new Runnable() {
            @Override
            public void run() {
                Log.i("UWE_JS", "goHome handler");
                mView.goHome();
            }
        });
    }

    @JavascriptInterface
    public void wxpay(final String data) {
        Log.e("UWE_JS", "handler = weixinpay, data from web = " + data);
        try {
            OnlinePayManager.getInstance().startWXPay(mWebViewActivity, new JSONObject(data));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param data 微信支付
     */
    @JavascriptInterface
    public void paywxnum(String data) {
        Log.i("UWE_JS", data);
        try {
            OnlinePayManager.getInstance().toWXPay(mWebViewActivity, new JSONObject(data));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @JavascriptInterface
    public String alipay(String data) {
        Log.e("UWE_JS", "handler = alipay, data from web = " + data);
        return OnlinePayManager.getInstance().startZFBPay(mWebViewActivity, data);
    }

    /**
     * @param data 微信分享
     */
    @JavascriptInterface
    public void shareToWechat(String data) {
        Log.i("UWE_JS", data);
        try {
            JSONObject jsonObject = new JSONObject(data);
            String url = jsonObject.getString("url");
            String title = jsonObject.getString("title");
            String connect = jsonObject.getString("connect");
            WxShareAndLoginUtil.WxUrlShare(mWebViewActivity, url, title, connect, WxShareAndLoginUtil.WECHAT_FRIEND);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param data 复制
     */
    @JavascriptInterface
    public void copy(String data) {
        Log.i("UWE_JS", data);
        try {
            JSONObject jsonObject = new JSONObject(data);
            String url = jsonObject.getString("url");
            ClipboardUtil.copy(mWebViewActivity, url);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param data 店铺
     */
    @JavascriptInterface
    public void gotoShop(String data) {
        Log.i("UWE_JS", data);
        try {
            JSONObject jsonObject = new JSONObject(data);
            String userId = jsonObject.getString("userId");
            mWebView.post(new Runnable() {
                @Override
                public void run() {
                    mView.goUserDetails(userId);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
