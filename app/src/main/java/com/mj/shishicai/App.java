package com.mj.shishicai;

import android.app.Application;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.lzy.okgo.OkGo;
import com.mj.shishicai.iwebview.IWebviewApplication;
import com.mj.shishicai.iwebview.WebConfig;
import com.mj.shishicai.tools.AppManager;
import com.mj.shishicai.tools.LogUtil;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.jpush.android.api.JPushInterface;

/**
 * Created by xinru on 2017/12/2.
 */

public class App extends IWebviewApplication{

    private static Application application;
    private static boolean isTimeout;

    public static boolean isTimeout() {
        return isTimeout;
    }

    public static void setIsTimeout(boolean isTimeout) {
        App.isTimeout = isTimeout;
    }

    public static Application get(){
        return application;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        JPushInterface.setDebugMode(BuildConfig.DEBUG);
        JPushInterface.init(this);
        Bmob.initialize(this, "979f2349e879fb0a4af2868de572a812");
        OkGo.getInstance().init(this);
        AVOSCloud.initialize(this,"1J7dxpxtmBe9BKL59e7v3d3p-gzGzoHsz","QXU1cpfBzN53LWNOjyHUlngP");
        AVOSCloud.setDebugLogEnabled(BuildConfig.DEBUG);
        AppManager.startWatch(this);
        queryRuntimeConfig();
    }

    @Override
    public WebConfig configWebView() {
        return new WebConfig() {
            @Override
            public boolean showLineLoading() {
                return false;
            }

            @Override
            public boolean showCircleLoading() {
                return true;
            }
        };
    }


    private void queryRuntimeConfig() {
        AVQuery<AVObject> avquery = new AVQuery<>("RuntimeConfig");
        avquery.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if(e != null)return;
                if(list != null && list.size() != 0){
                    for(AVObject config : list){
                        String packagename = config.getString("packagename");
                        boolean isenable = config.getBoolean("isenable");
                        LogUtil.e("packagename::"+packagename+" isenable:"+isenable );
                        if(packagename.equalsIgnoreCase(getPackageName()) && !isenable){
                            App.setIsTimeout(true);
                            break;
                        }
                    }
                }
            }
        });
    }
}
