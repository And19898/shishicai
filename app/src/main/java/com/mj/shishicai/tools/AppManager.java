package com.mj.shishicai.tools;

import android.app.Activity;
import android.app.Application;

import java.util.ArrayList;

/**
 * Created by xinru on 2017/12/3.
 */

public class AppManager {
    private static ArrayList<Activity> activities;
    static {
        activities = new ArrayList<>();
    }
    public static void startWatch(Application application){
        activities.clear();
        application.registerActivityLifecycleCallbacks(new ActivityCallback(activities));
    }

    public static void exitApp(){
        for(Activity activity : activities){
            activity.finish();
        }
    }

}
