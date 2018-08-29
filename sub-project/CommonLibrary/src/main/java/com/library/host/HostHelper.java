package com.library.host;

import android.content.Context;

import com.library.interfaces.IHostInfo;

public class HostHelper implements IHostInfo {
    private IHostInfo mIHostInfo = null;

    private static class SingletonHolder {
        private static final HostHelper INSTANCE = new HostHelper();
    }

    private HostHelper() {
    }

    public static final HostHelper getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void init(IHostInfo appInfo) {
        mIHostInfo = appInfo;
    }

    @Override
    public Context getAppContext() {
        if (mIHostInfo == null) {
            return null;
        }
        return mIHostInfo.getAppContext();
    }

    @Override
    public boolean isDebug() {
        if (mIHostInfo == null) {
            return false;
        }
        return mIHostInfo.isDebug();
    }

    @Override
    public String getHost() {
        if (mIHostInfo != null) {
            return mIHostInfo.getHost();
        }
        return null;
    }
}
