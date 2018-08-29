package com.library.util;

import android.content.Context;
import android.os.Build;
import android.os.SystemProperties;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.library.log.LogUtils;

import java.lang.reflect.Method;

public class PhoneOSUtil {
    public static final String TAG = "PhoneOSUtil";


    private PhoneOSUtil() {
    }

    public static class Data {
        private String os;
        private String ver;

        private Data(String os, String ver) {
            this.os = os;
            this.ver = ver;
        }

        public String getOs() {
            return os;
        }

        public String getVer() {
            return ver;
        }

        public String getVersionIncremental() {
            return SystemProperties.get("ro.build.version.incremental", "UNKNOWN");
        }
    }

    public static Data getData() {
        String ver = "";

        //XiaoMi - MIUI
        ver = SystemProperties.get("ro.miui.ui.version.name", "UNKNOWN");
        if (ver != null && !ver.equals("UNKNOWN")) {
            return new Data("MIUI", ver);
        }

        //HuaWei - EMUI
        ver = SystemProperties.get("ro.build.version.emui", "UNKNOWN");
        if (ver != null && !ver.equals("UNKNOWN")) {
            return new Data("EMUI", ver);
        }

        //Oppo - ColorOS
        ver = SystemProperties.get("ro.build.version.opporom", "UNKNOWN");
        if (ver != null && !ver.equals("UNKNOWN")) {
            return new Data("OPPO", ver);
        }

        //Yun - YunOS
        ver = SystemProperties.get("ro.yunos.version", "UNKNOWN");
        if (ver != null && !ver.equals("UNKNOWN")) {
            return new Data("YunOS", ver);
        }

        //VIVO - FuntouchOS
        ver = SystemProperties.get("ro.vivo.os.build.display.id", "UNKNOWN");
        if (ver != null && !ver.equals("UNKNOWN")) {
            return new Data("VIVO", ver);
        }

        //letv -
        ver = SystemProperties.get("ro.letv.release.version", "UNKNOWN");
        if (ver != null && !ver.equals("UNKNOWN")) {
            return new Data("letv", ver);
        }

        //Coolpad - UGOLD
        ver = SystemProperties.get("ro.coolpad.ui.theme", "UNKNOWN");
        if (ver != null && !ver.equals("UNKNOWN")) {
            return new Data("Coolpad", ver);
        }

        //nubia - nubia
        ver = SystemProperties.get("ro.build.nubia.rom.code", "UNKNOWN");
        if (ver != null && !ver.equals("UNKNOWN")) {
            return new Data("nubia", ver);
        }

        //GiONEE、Flyme - amigo、Flyme
        ver = SystemProperties.get("ro.build.display.id", "UNKNOWN");
        if (ver != null && !ver.equals("UNKNOWN")) {
            String str = ver.toLowerCase();
            if (str.contains("amigo")) {
                return new Data("GiONEE", ver);
            } else if (str.contains("flyme")) {
                return new Data("Flyme", ver);
            }
        }

        String fingerPrint = "";
        //MeiZu - FlyMe
        try {
            fingerPrint = Build.FINGERPRINT.toLowerCase();
            if (fingerPrint.contains("flyme")) {
                return new Data("FLYME", ver);
            }
        } catch (Exception e) {
        }

        if (!fingerPrint.equals("")) {
            int index = fingerPrint.indexOf("/");
            if (index == -1) {
                return new Data(ver, "");
            }
            String versionName = fingerPrint.substring(0, index);
            ver = SystemProperties.get("ro.build.version.incremental", "UNKNOWN");
            return new Data(versionName, ver);
        }

        return new Data(ver, "");
    }

    /***
     * 获取手机状态栏高度
     *
     * @return
     */
    public static int getStatusHeight(Context context) {
        if (context == null)
            return 0;
        int statusBaHeight = 0;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height").get(object).toString());
            statusBaHeight = context.getResources().getDimensionPixelSize(height);
            LogUtils.d(TAG, "getStatusHeight height:" + statusBaHeight);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusBaHeight;
    }

    public static boolean isKitKat() {
        return Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT
                || Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT_WATCH;
    }

    /**
     * 获得全屏高度，带有虚拟键高度
     *
     * @param context
     * @return
     */
    public static int getFullScreenHeight(Context context) {
        if (context == null)
            return 0;
        int height = 0;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        try {
            @SuppressWarnings("rawtypes")
            Class c = Class.forName("android.view.Display");
            @SuppressWarnings("unchecked")
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, dm);
            height = dm.heightPixels;
            LogUtils.d(TAG, "virtual height:" + height);
        } catch (Exception e) {
            dm = context.getResources().getDisplayMetrics();
            height = dm.heightPixels;
        }
        LogUtils.d(TAG, "getFullScreenHeight height:" + height);
        return height;
    }

    /**
     * 从系统拿到的标准高度，不一定带有虚拟机高度
     *
     * @param context
     * @return
     */
    public static int getStandardScreenHeight(Context context) {
        if (context == null)
            return 0;
        int height = 0;
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        height = dm.heightPixels;
        LogUtils.d(TAG, "getStandedScreenHeight height:" + height);
        return height;
    }

    /**
     * 获取虚拟功能键高度
     */
    public static int getVirtualBarHeight(Context context) {
        if (context == null)
            return 0;
        int vh = 0;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        try {
            @SuppressWarnings("rawtypes")
            Class c = Class.forName("android.view.Display");
            @SuppressWarnings("unchecked")
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, dm);
            vh = dm.heightPixels - windowManager.getDefaultDisplay().getHeight();
            LogUtils.d(TAG, "virtual height:" + vh);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vh;
    }

}
