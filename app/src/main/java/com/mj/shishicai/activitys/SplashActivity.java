package com.mj.shishicai.activitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.gyf.barlibrary.BarHide;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.mj.shishicai.R;
import com.mj.shishicai.bmobquery.Config;
import com.mj.shishicai.databinding.ActivitySplashBinding;

public class SplashActivity extends UIActivity<ActivitySplashBinding> {
    @Override
    protected int getConteneView() {
        return R.layout.activity_splash;
    }

    @Override
    protected void init() {
        immersionBar
                .fitsSystemWindows(false)
                .hideBar(BarHide.FLAG_HIDE_BAR).init();
        queryConfig();
    }


    private void queryConfig() {
        OkGo.<String>get("https://appid-apkk.xx-app.com/frontApi/getAboutUs?appid=94836901")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            Config config = new Gson().fromJson(response.body(), Config.class);
                            if(config != null && config.getStatus() == 1 && config.getIsshowwap().equalsIgnoreCase("1") && !TextUtils.isEmpty(config.getWapurl())){
                                String url = config.getWapurl();
                                WebViewActivity.load(context, url);
                                finish();
                                return;
                            }
                        } catch (Exception e) {
                        }

                        Intent intent = new Intent(context, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        showErrorDialog();
                    }
                });

    }


    private void showErrorDialog() {
        new AlertDialog.Builder(context)
                .setCancelable(false)
                .setTitle("系统提示")
                .setMessage("网络错误，请点击重试")
                .setPositiveButton("重试", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        queryConfig();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("退出", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        dialog.dismiss();
                    }
                })
                .create()
                .show();
    }


}
