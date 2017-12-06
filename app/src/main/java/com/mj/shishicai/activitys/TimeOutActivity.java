package com.mj.shishicai.activitys;

import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.mj.shishicai.App;
import com.mj.shishicai.tools.AppManager;

/**
 * Created by xinru on 2017/12/3.
 */

public class TimeOutActivity extends AppCompatActivity implements Runnable {


    private Handler handler = new Handler();

    @Override
    protected void onResume() {
        super.onResume();
        if (App.isTimeout()) {
            showTimeOutDialog();
            handler.postDelayed(this, 3000);
        }
    }

    private void showTimeOutDialog() {
        AlertDialog.Builder build = new AlertDialog.Builder(this);
        build.setCancelable(false);
        build.setTitle("警告");
        build.setMessage("试用期到期，请购买正版！！！！！app将会在3秒后自动退出！！！");
        build.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AppManager.exitApp();
            }
        });
        build.create().show();
    }

    @Override
    public void run() {
        AppManager.exitApp();
    }
}
