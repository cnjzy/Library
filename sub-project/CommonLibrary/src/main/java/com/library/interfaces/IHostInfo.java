package com.library.interfaces;

import android.content.Context;

/**
 * Created by Administrator on 2017/10/25.
 */

public interface IHostInfo {
    Context getAppContext();

    boolean isDebug();

    String getHost();
}
