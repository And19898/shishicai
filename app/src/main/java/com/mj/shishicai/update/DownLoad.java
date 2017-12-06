package com.mj.shishicai.update;

/**
 * Created by Fish.C on 2017/11/8.
 */

import android.net.Uri;
import android.text.TextUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Progress;
import com.mj.shishicai.App;
import com.tencent.smtt.sdk.WebView;

import java.io.File;
import java.io.IOException;
import java.util.List;

import okhttp3.Response;
import okhttp3.internal.Util;
import okio.BufferedSink;
import okio.Okio;

public enum DownLoad {
    INSTANT;



    public File downloadSync(String downloadUrl, File path, WebView webView) throws IOException {
        Response response = OkGo.<File>get(downloadUrl).execute();
        if (response == null || response.code() != 200) return null;
        BufferedSink sink = Okio.buffer(Okio.sink(path));
        sink.writeAll(response.body().source());
        Util.closeQuietly(sink);
        return path;
    }

    /**
     * 下载服务，暂时不支持下载到指定位置，因为没有权限
     *
     * @param downloadUrl 下载地址
     * @param callBack    下载监听
     */
    public void download(final String downloadUrl, final DownloadCallback callBack, final boolean isShowNotify) {
        download(downloadUrl, getNameFromUrl(downloadUrl), callBack, isShowNotify);
    }

    public static String getNameFromUrl(String url) {
        Uri uri = Uri.parse(url);
        List<String> paths = uri.getPathSegments();
        if (paths != null && paths.size() > 0) {
            String filename = paths.get(paths.size() - 1);
            if (!TextUtils.isEmpty(filename) && filename.contains(".")) {
                String[] names = filename.split("\\.");
                if (names != null && names.length > 1) {
                    String expend = names[names.length - 1];
                    return MD5.md5(url) + "." + expend;
                }
            }
        }
        return url;
    }

    public void download(final String downloadUrl, final String path, final String filename, final DownloadCallback callBack, final boolean isShowNotify) {
        OkGo.<File>get(downloadUrl).execute(new FileCallback(path, filename) {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<File> response) {
                File file = response.body();
                if (callBack != null)
                    callBack.requestSuccess(file);
            }

            @Override
            public void downloadProgress(Progress progress) {
                super.downloadProgress(progress);
                if (callBack != null)
                    callBack.downProgress(progress.currentSize, progress.totalSize, (int) (progress.fraction * 100));
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<File> response) {
                super.onError(response);
                if (callBack != null)
                    callBack.requestFial();
            }
        });
    }

    public void download(final String downloadUrl, final String filename, final DownloadCallback callBack, final boolean isShowNotify) {
        download(downloadUrl, CommonUtils.getCacheDir(App.get()), filename, callBack, isShowNotify);
    }

}
