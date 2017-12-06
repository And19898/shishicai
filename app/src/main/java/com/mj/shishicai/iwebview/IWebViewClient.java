package com.mj.shishicai.iwebview;

/**
 * author: Rea.X
 * date: 2017/4/7.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ProgressBar;

import com.mj.shishicai.iwebview.cache.CacheResourceClient;
import com.mj.shishicai.tools.LogUtil;
import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.sdk.WebView;

public class IWebViewClient extends CacheResourceClient {
    protected IWebviewFragment fragment;
    protected Context context;
    protected ProgressBar lineProgressBar;
    protected ProgressBar circleProgressbar;
    protected boolean isShowLineProgress, isShowCircleProgress;
    private WebConfig webConfig;

    public IWebViewClient(IWebviewFragment fragment) {
        this.fragment = fragment;
        this.context = fragment.getContext();
        this.lineProgressBar = fragment.getlineProgressbar();
        this.circleProgressbar = fragment.getcircleProgressbar();
        this.webConfig = IWebviewApplication.getWebConfig();
        this.isShowLineProgress = this.webConfig.showLineLoading();
        this.isShowCircleProgress = this.webConfig.showCircleLoading();
    }

    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (isShowLineProgress) {
            this.lineProgressBar.setVisibility(View.VISIBLE);
            this.lineProgressBar.setProgress(0);
        }
        if (isShowCircleProgress)
            this.circleProgressbar.setVisibility(View.VISIBLE);
        LogUtil.e("onPageStarted->" + url);

    }

    @Override
    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
//        super.onReceivedSslError(webView, sslErrorHandler, sslError);
        sslErrorHandler.proceed();
    }


    @Override
    public boolean shouldOverrideUrlLoading(WebView webView, String s) {
        if (s.contains("javascript: void(0)") || s.contains("javascript:void(0)")) return false;
        if (isShowLineProgress) {
            this.lineProgressBar.setVisibility(View.VISIBLE);
            this.lineProgressBar.setProgress(0);
        }
        if (isShowCircleProgress)
            this.circleProgressbar.setVisibility(View.VISIBLE);
//        if (s.startsWith("http://") || s.startsWith("https://") || s.startsWith("file:///"))
//            webView.loadUrl(s);
        return false;
    }

    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        this.circleProgressbar.setVisibility(View.GONE);
        this.lineProgressBar.setVisibility(View.GONE);
    }


//    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
//        super.onReceivedError(view, request, error);
//        if (VERSION.SDK_INT >= 23) {
//            LogUtil.e("onReceivedError1>> " + request.getUrl() + "    " + error.getErrorCode() + "  d>>>" + error.getDescription());
//            this.fragment.showFailView(this.getErrorText(error.getErrorCode()));
//        } else {
//            this.fragment.showFailView(this.context.getString(R.string.webview_error));
//        }
//
//        this.circleProgressbar.setVisibility(View.GONE);
//        this.progressBar.setVisibility(View.GONE);
//    }
//
//    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
//        super.onReceivedError(view, errorCode, description, failingUrl);
//        LogUtil.e("onReceivedError2>>>errorCode=" + errorCode + "  description=" + description + "    failingUrl=" + failingUrl);
//        this.fragment.showFailView(this.getErrorText(errorCode));
//        this.circleProgressbar.setVisibility(View.GONE);
//        this.progressBar.setVisibility(View.GONE);
//    }

//    private String getErrorText(int errorCode) {
//        switch (errorCode) {
//            case -10:
//            case -2:
//                return this.context.getString(R.string.webview_webAddError);
//            default:
//                return "";
//        }
//    }

}
