package com.mynews.app;

import android.content.Context;

import com.library.interfaces.IHostInfo;

/**
 * Created by Administrator on 2017/10/25.
 */

public class HostInfo implements IHostInfo {
    @Override
    public Context getAppContext() {
        return MyApp.getAppContext();
    }

    @Override
    public boolean isDebug() {
        return false;
    }

    @Override
    public String getHost() {
        return BuildConfig.HOST_URL;
    }
}
