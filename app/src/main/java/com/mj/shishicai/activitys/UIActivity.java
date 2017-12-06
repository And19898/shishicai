package com.mj.shishicai.activitys;

import android.app.ProgressDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.mj.shishicai.R;
import com.mj.shishicai.tools.SoftKeyboardUtils;

public abstract class UIActivity<T extends ViewDataBinding> extends TimeOutActivity {

    protected Toolbar toolbar;
    protected TextView tvBack;
    protected TextView tvTitle;
    protected T databinding;
    protected Context context;
    protected ImmersionBar immersionBar;
    protected ProgressDialog dialog;

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int viewid = getConteneView();
        databinding = DataBindingUtil.setContentView(this, viewid);
        immersionBar = ImmersionBar.with(this)
                .statusBarColor(R.color.colorPrimaryDark)
                .fitsSystemWindows(true)
                .keyboardEnable(true);
        dialog = new ProgressDialog(this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setMessage("加载中...");
        immersionBar.init();
        context = this;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvBack = (TextView) findViewById(R.id.tv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        if(toolbar != null){
            toolbar.setTitle("");
            setSupportActionBar(toolbar);
            tvBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
        init();
    }

    protected abstract
    @LayoutRes
    int getConteneView();

    protected abstract void init();

    @Override
    protected void onStop() {
        super.onStop();
        SoftKeyboardUtils.hideSoftKeyboard(this);
    }
}
