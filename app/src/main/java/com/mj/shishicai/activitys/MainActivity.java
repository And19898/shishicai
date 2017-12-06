package com.mj.shishicai.activitys;

import android.support.design.widget.TabLayout;
import android.view.KeyEvent;
import android.view.View;

import com.mj.shishicai.R;
import com.mj.shishicai.adapters.MainPagerAdapter;
import com.mj.shishicai.databinding.ActivityMainBinding;
import com.mj.shishicai.tools.AppManager;
import com.mj.shishicai.tools.ToastUtils;

public class MainActivity extends UIActivity<ActivityMainBinding>{
    private MainPagerAdapter adapter;
    @Override
    protected int getConteneView() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        tvBack.setVisibility(View.GONE);
        tvTitle.setText(R.string.app_name);
        adapter = new MainPagerAdapter(this);
        databinding.viewpager.setAdapter(adapter);
        databinding.viewpager.setOffscreenPageLimit(4);
        databinding.tablayout.setupWithViewPager(databinding.viewpager);
        databinding.tablayout.setTabMode(TabLayout.MODE_FIXED);
        adapter.addView(databinding.tablayout);
    }

    public void changePosition(int position){
        databinding.tablayout.getTabAt(position).select();
    }


    private long backTime;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
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
