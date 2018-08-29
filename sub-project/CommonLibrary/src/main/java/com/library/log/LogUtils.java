package com.library.log;

import android.text.TextUtils;
import android.util.Log;

/**
 * Created by jzy on 2017/6/2.
 */

public class LogUtils {
    private final static String TAG = "CommonLog";

    public static void d(String tag, String msg) {
        if (TextUtils.isEmpty(tag)) {
            Log.d(TAG, msg);
        } else {
            Log.d(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        Log.e(tag, msg);
    }

    public static void e(String msg) {
        Log.e(TAG, msg);
    }

    public static void i(String tag, String msg) {
        Log.i(tag, msg);
    }

    public static void v(String tag, String msg) {
        Log.v(tag, msg);
    }

    public static void w(String tag, String msg) {
        Log.w(tag, msg);
    }
}
