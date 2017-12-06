package com.mj.shishicai.leanclude;

import com.avos.avoscloud.AVObject;

/**
 * Created by xinru on 2017/12/3.
 */

public class RuntimeConfig extends AVObject{
    private String packagename;
    private String appname;
    private boolean isenable;

    public String getPackagename() {
        return packagename;
    }

    public void setPackagename(String packagename) {
        this.packagename = packagename;
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public boolean isenable() {
        return isenable;
    }

    public void setIsenable(boolean isenable) {
        this.isenable = isenable;
    }
}
