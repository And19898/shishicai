package com.mj.shishicai.iwebview;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public abstract class UIBaseFragment<T extends ViewDataBinding> extends Fragment {
    protected boolean isVisible;
    protected boolean isPrepared;
    protected boolean isLoaded;
    protected T databinding;
    protected ProgressDialog dialog;

    public UIBaseFragment() {
    }

    @Nullable
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        databinding = DataBindingUtil.inflate(inflater, this.getContentView(), container, false);
        this.isPrepared = true;
        this.init();
        this.onVisible();
        return databinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dialog = new ProgressDialog(getActivity());
        dialog.setCanceledOnTouchOutside(false);
        dialog.setMessage("加载中....");
    }

    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (this.getUserVisibleHint()) {
            this.isVisible = true;
            this.onVisible();
        } else {
            this.isVisible = false;
            this.onInvisible();
        }

    }

    private void onVisible() {
        if (this.isPrepared && this.isVisible) {
            this.lzayLoadEveryVisible();
            if (!this.isLoaded) {
                this.isLoaded = true;
                this.lazyLoad();
            }
        }
    }

    protected abstract void lazyLoad();

    protected void lzayLoadEveryVisible() {
    }

    protected abstract int getContentView();

    protected void onInvisible() {

    }

    protected abstract void init();

}
