package com.mynews.app;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.library.host.HostHelper;
import com.library.interfaces.IHostInfo;
import com.library.util.PreferencesUtils;
import com.library.util.res.ResHelper;

/**
 * Created by jzy on 2017/5/27.
 */

public class MyApp extends MultiDexApplication{

    private static Context mApp;
    private IHostInfo mAppInfo = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = getApplicationContext();
        init();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public void init() {
        initAppInfo();
        // 初始化资源管理类
        ResHelper.getInstance().init(mApp);
        // 初始化preferences工具文件
        PreferencesUtils.getInstance().init(mApp);
    }

    public static Context getAppContext() {
        return mApp;
    }

    private void initAppInfo() {
        mAppInfo = new HostInfo();
        HostHelper.getInstance().init(mAppInfo);
    }
}
