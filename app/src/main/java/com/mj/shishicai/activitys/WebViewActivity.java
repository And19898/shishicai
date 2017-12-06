package com.mj.shishicai.activitys;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.mj.shishicai.R;
import com.mj.shishicai.databinding.ActivityWebviewBinding;
import com.mj.shishicai.iwebview.IWebChromeClient;
import com.mj.shishicai.iwebview.IWebViewClient;
import com.mj.shishicai.iwebview.IWebviewFragment;
import com.mj.shishicai.tools.AppManager;
import com.mj.shishicai.tools.ToastUtils;
import com.tencent.smtt.sdk.WebView;

/**
 * Created by xinru on 2017/12/2.
 */

public class WebViewActivity extends UIActivity<ActivityWebviewBinding> implements View.OnClickListener {
    private IWebviewFragment fragment;

    public static void load(Context context, String url, boolean isShowBack) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("isShowBack", isShowBack);
        context.startActivity(intent);
    }

    public static void load(Context context, String url) {
        load(context, url, false);
    }

    @Override
    protected int getConteneView() {
        return R.layout.activity_webview;
    }

    private boolean isShowBack;

    @Override
    protected void init() {
        tvBack.setVisibility(View.GONE);
        tvBack.setOnClickListener(this);
        String url = getIntent().getStringExtra("url");
        isShowBack = getIntent().getBooleanExtra("isShowBack", false);
        if (isShowBack) {
            tvBack.setVisibility(View.VISIBLE);
        }
        fragment = (IWebviewFragment) getSupportFragmentManager().findFragmentById(R.id.webfragment);
        fragment.getWebView().loadUrl(url);
        fragment.setWebChromeClient(new IWebChromeClient(fragment) {
            @Override
            public void onReceivedTitle(WebView webView, String s) {
                super.onReceivedTitle(webView, s);
                tvTitle.setText(s);
                checkCangoback();
            }
        });
        fragment.setWebViewClient(new IWebViewClient(fragment) {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                checkCangoback();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String s) {
                checkCangoback();
                return super.shouldOverrideUrlLoading(webView, s);
            }
        });
    }

    private void checkCangoback() {
        if (fragment.getWebView().canGoBack()) {
            tvBack.setVisibility(View.VISIBLE);
        } else {
            if (!isShowBack)
                tvBack.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        if (fragment.getWebView().canGoBack()) {
            fragment.getWebView().goBack();
        }
        if(isShowBack)finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater in = getMenuInflater();
        in.inflate(R.menu.menu_webview, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.refresh) {
            fragment.getWebView().reload();
        }
        return super.onOptionsItemSelected(item);
    }

    private long backTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (isShowBack) return super.onKeyDown(keyCode, event);
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (fragment.getWebView().canGoBack()) {
                fragment.getWebView().goBack();
                return true;
            }
            if (backTime == 0) {
                backTime = System.currentTimeMillis();
                ToastUtils.toastWarn(this, getString(R.string.hybrid_exit_app));
                return true;
            }
            if ((System.currentTimeMillis() - backTime) >= 2000) {
                backTime = System.currentTimeMillis();
                ToastUtils.toastWarn(this, getString(R.string.hybrid_exit_app));
                return true;
            }
            AppManager.exitApp();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
