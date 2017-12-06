package com.mj.shishicai.fragments;

import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.mj.shishicai.R;
import com.mj.shishicai.activitys.ActivityLogin;
import com.mj.shishicai.activitys.AddTieziActivity;
import com.mj.shishicai.activitys.MyMessageActivity;
import com.mj.shishicai.databinding.FragmentMineBinding;
import com.mj.shishicai.iwebview.UIBaseFragment;
import com.mj.shishicai.tools.Cons;
import com.mj.shishicai.tools.PrefreshHelper;
import com.mj.shishicai.views.PullScrollView;

import java.util.Random;

/**
 * Created by xinru on 2017/12/3.
 */

public class MineFragment extends UIBaseFragment<FragmentMineBinding> implements PullScrollView.OnTurnListener, View.OnClickListener {
    public static MineFragment getInstant() {
        return new MineFragment();
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void init() {
        databinding.scrollView.setHeader(databinding.backgroundImg);
        databinding.scrollView.setOnTurnListener(this);
        databinding.tvLogin.setOnClickListener(this);
        databinding.tvLoginout.setOnClickListener(this);

        databinding.tvZiliao.setOnClickListener(this);
        databinding.tvGuanyu.setOnClickListener(this);
        databinding.tvGengxin.setOnClickListener(this);
        databinding.tvShezhi.setOnClickListener(this);
        databinding.tvFatie.setOnClickListener(this);
    }

    @Override
    public void onTurn() {

    }

    private void checklogin() {
        String username = PrefreshHelper.get(Cons.USERNAME);
        if (TextUtils.isEmpty(username)) {
            loginout();
        } else {
            databinding.layoutUserMsg.setVisibility(View.VISIBLE);
            databinding.tvLoginout.setVisibility(View.VISIBLE);
            databinding.layoutLogin.setVisibility(View.INVISIBLE);
            databinding.userName.setText(PrefreshHelper.get(Cons.USERNAME));
            databinding.tvFatie.setVisibility(View.VISIBLE);
            databinding.tvLine.setVisibility(View.VISIBLE);
        }
    }

    private void loginout() {
        PrefreshHelper.save(Cons.USERNAME, "");
        databinding.layoutUserMsg.setVisibility(View.INVISIBLE);
        databinding.tvLoginout.setVisibility(View.INVISIBLE);
        databinding.layoutLogin.setVisibility(View.VISIBLE);
        databinding.tvFatie.setVisibility(View.GONE);
        databinding.tvLine.setVisibility(View.GONE);
    }

    @Override
    public void onResume() {
        super.onResume();
        checklogin();
    }

    @Override
    protected void lzayLoadEveryVisible() {
        super.lzayLoadEveryVisible();
        checklogin();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_fatie:
                startActivity(new Intent(getContext(), AddTieziActivity.class));
                break;
            case R.id.tv_login:
                startActivity(new Intent(getContext(), ActivityLogin.class));
                break;
            case R.id.tv_loginout:
                loginout();
                break;
            case R.id.tv_ziliao:
                if(TextUtils.isEmpty(PrefreshHelper.get(Cons.USERNAME))){
                    startActivity(new Intent(getContext(), ActivityLogin.class));
                    return;
                }
                startActivity(new Intent(getContext(), MyMessageActivity.class));
                break;
            case R.id.tv_guanyu:
                break;
            case R.id.tv_gengxin:
                dialog.show();
                Random r = new Random();
                int time = r.nextInt(4000) - 1000;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                        Toast.makeText(getActivity(), "当前是最新版本！", Toast.LENGTH_LONG).show();
                    }
                }, time);
                break;
            case R.id.tv_shezhi:
                Toast.makeText(getActivity(), "缓存已清理！", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
