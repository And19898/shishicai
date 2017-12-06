package com.mj.shishicai;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.lzy.okgo.model.Progress;
import com.mj.shishicai.update.DownloadAppService;
import com.mj.shishicai.update.UpdateProgressDialog;
import com.tencent.smtt.sdk.DownloadListener;


public class IDownloadListener implements DownloadListener {
    private AppCompatActivity context;

    public IDownloadListener(Context context) {
        this.context = (AppCompatActivity) context;
    }

    @Override
    public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimeType, long length) {
        DownloadAppService.downloadAppAndInstall(context, url, true);
        UpdateProgressDialog dialog = new UpdateProgressDialog();
        dialog.show(context.getSupportFragmentManager(), "update");

    }


}
