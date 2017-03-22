package com.shanguang.lesson.application;

import android.app.Activity;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.shanguang.lesson.utils.SingleOkHttpUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by luke on 2017/3/22.
 */

public class YALApplication extends MultiDexApplication {
    private long startTime = 0;
    public static YALApplication app;
    private boolean sdkInited = false;
    private List<Activity> activities = new ArrayList<Activity>();

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        startTime = System.currentTimeMillis();
        new Thread(() -> {
            OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).
                    readTimeout(10, TimeUnit.SECONDS).build();
            SingleOkHttpUtils.setInit(okHttpClient);
        }).start();
        Log.e("初始化消耗时间：", System.currentTimeMillis() - startTime + "");
    }

    /**
     * 获得实例
     * @return
     */
    public static YALApplication getInstance() {
        return app;
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public void Exit() {
        for (Activity activity : activities) {
            activity.finish();
        }
        System.exit(0);
    }
}
