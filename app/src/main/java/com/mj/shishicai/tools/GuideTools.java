package com.mj.shishicai.tools;

import android.text.TextUtils;

import static com.mj.shishicai.tools.Cons.VERSION;

/**
 * Created by xinru on 2017/12/2.
 */

public class GuideTools {

    public static boolean isShowGuide() {
        String version = PrefreshHelper.get(VERSION);
        if (TextUtils.isEmpty(version)) return true;
        return !version.equalsIgnoreCase(CommonUtils.getAppVersionName());
    }

    public static void showdGuide() {
        PrefreshHelper.save(VERSION, CommonUtils.getAppVersionName());
    }
}
