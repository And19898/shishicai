package com.mj.shishicai.iwebview;

import android.content.Intent;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.mj.shishicai.IDownloadListener;
import com.mj.shishicai.R;
import com.mj.shishicai.databinding.FragmentWebviewBinding;
import com.tencent.smtt.sdk.WebView;

/**
 * author: Rea.X
 * date: 2017/8/8.
 */

public class IWebviewFragment extends UIBaseFragment<FragmentWebviewBinding> {

    private IWebChromeClient chromeClient;
    private IWebViewClient webViewClient;

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_webview;
    }

    @Override
    protected void init() {
        chromeClient = new IWebChromeClient(this);
        webViewClient = new IWebViewClient(this);
        IWebSetting.init(databinding.webview);
        databinding.webview.setWebChromeClient(chromeClient);
        databinding.webview.setWebViewClient(webViewClient);
        databinding.webview.setDownloadListener(new IDownloadListener(getActivity()));
    }

    public void setWebChromeClient(IWebChromeClient chromeClient) {
        databinding.webview.setWebChromeClient(chromeClient);
    }

    public void setWebViewClient(IWebViewClient webViewClient){
        databinding.webview.setWebViewClient(webViewClient);
    }

    public WebView getWebView() {
        return databinding.webview;
    }

    public ProgressBar getlineProgressbar() {
        return databinding.pbloading;
    }

    public ProgressBar getcircleProgressbar() {
        return databinding.pbCircleLoading;
    }

    public FrameLayout getfullFramlayout() {
        return databinding.fullFramlayout;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        chromeClient.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
