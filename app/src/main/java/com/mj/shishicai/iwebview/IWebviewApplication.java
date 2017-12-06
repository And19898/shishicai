package com.mj.shishicai.iwebview;

import android.app.Application;

import com.mj.shishicai.tools.LogUtil;
import com.tencent.smtt.sdk.QbSdk;

/**
 * author: Rea.X
 * date: 2017/8/8.
 */

public abstract class IWebviewApplication extends Application {
    private static Application application;

    private static WebConfig webConfig;
    @Override
    public void onCreate() {
        super.onCreate();
        init(this, configWebView());
    }

    public static void init(Application app, WebConfig config) {
        application = app;
        webConfig = config;
        QbSdk.setDownloadWithoutWifi(true);
        QbSdk.initX5Environment(app, new QbSdk.PreInitCallback() {
            public void onCoreInitFinished() {
                LogUtil.d("onCoreInitFinished x5 core load success--------");
            }

            public void onViewInitFinished(boolean b) {
                LogUtil.d("onViewInitFinished b--------" + b);
            }
        });
    }

    public abstract WebConfig configWebView();

    public static Application get(){
        return application;
    }

    public static WebConfig getWebConfig(){
        return webConfig;
    }
}
