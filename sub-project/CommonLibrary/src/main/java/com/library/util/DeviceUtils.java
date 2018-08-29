package com.library.util;


import android.app.Activity;
import android.app.ActivityManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 机型工具类
 *
 * @author jzy
 */
public class DeviceUtils {
    ///////////////////////////////////////////////////////

    /**
     * 移动端Android ID
     */
    public static String getAndroidID(Context context) {
        if (null == context) {
            return "";
        }
        try {
            ContentResolver cr = context.getContentResolver();
            return Settings.Secure.getString(cr, Settings.Secure.ANDROID_ID);
        } catch (Exception e) {
            return "";
        }
    }


    /**
     * 根据资源文件名称获得资源
     *
     * @param cx
     * @param defType
     * @param file_name
     * @return
     */
    public static int getResIdFromFileName(Context cx, String defType,
                                           String file_name) {
        // TODO Auto-generated method stub
        Resources rs = cx.getResources();
        String packageName = getMyPackageName(cx);
        return rs.getIdentifier(file_name, defType, packageName);
    }

    /**
     * 获得当前程序包名
     *
     * @param cx
     * @return
     */
    public static String getMyPackageName(Context cx) {
        try {
            return cx.getPackageManager()
                    .getPackageInfo(cx.getPackageName(), 0).packageName;
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前程序的版本号
     *
     * @param cx
     * @return
     */
    public static String getVersionName(Context cx) {
        return getPackageInfo(cx).versionName;
    }

    /**
     * 获取当前程序的内部版本号
     *
     * @param cx
     * @return
     */
    public static int getVersionCode(Context cx) {
        return getPackageInfo(cx).versionCode;
    }

    /**
     * 或得程序名称
     *
     * @param cx
     * @return
     */
    public static String getAppName(Context cx) {
        PackageInfo pi = getPackageInfo(cx);
        PackageManager packageManager = cx.getPackageManager();
        return packageManager.getApplicationLabel(pi.applicationInfo)
                .toString();
    }

    /**
     * 获得包信息
     *
     * @param c
     * @return
     */
    private static PackageInfo getPackageInfo(Context c) {
        try {
            return c.getPackageManager().getPackageInfo(c.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 是否联网
     *
     * @param cx
     * @return
     */
    public static boolean isNetworkActive(Context cx) {
        ConnectivityManager cManager = (ConnectivityManager) cx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cManager.getActiveNetworkInfo();
        return (info != null && info.isAvailable()) ? true : false;
    }

    /**
     * 判断wifi是否可用
     *
     * @param inContext
     * @return
     */
    public static boolean isWiFiActive(Context inContext) {
        Context context = inContext.getApplicationContext();
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getTypeName().equals("WIFI") && info[i].isConnected()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // 判断渠道号
    public static String getChannelName(Context cx, String meta_date) {
        return getAppMetaDate(cx).get(meta_date).toString();
    }

    /**
     * 获取可用存储空间大小
     * 若存在SD卡则返回SD卡剩余控件大小
     * 否则返回手机内存剩余空间大小
     *
     * @return
     */
    public static long getAvailableStorageSpace() {
        long externalSpace = getExternalStorageSpace();
        if (externalSpace == -1L) {
            return getInternalStorageSpace();
        }

        return externalSpace;
    }

    /**
     * 获取SD卡可用空间
     *
     * @return availableSDCardSpace 可用空间(MB)。-1L:没有SD卡
     */
    public static long getExternalStorageSpace() {
        long availableSDCardSpace = -1L;
        //存在SD卡
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            StatFs sf = new StatFs(Environment.getExternalStorageDirectory().getPath());
            long blockSize = sf.getBlockSize();//块大小,单位byte
            long availCount = sf.getAvailableBlocks();//可用块数量
//			long blockCount = sf.getBlockCount();//块总数量
//			Log.d("", "block大小:"+ blockSize+",block数目:"+ blockCount+",总大小:"+blockSize*blockCount/1024+"KB");
//			Log.d("", "可用的block数目：:"+ availCount+",剩余空间:"+ availCount*blockSize/1024+"KB");

            availableSDCardSpace = availCount * blockSize / 1024 / 1024;//可用SD卡空间，单位MB
        }

        return availableSDCardSpace;
    }

    /**
     * 获取机器内部可用空间
     *
     * @return availableSDCardSpace 可用空间(MB)。-1L:没有SD卡
     */
    public static long getInternalStorageSpace() {
        long availableInternalSpace = -1L;

        StatFs sf = new StatFs(Environment.getDataDirectory().getPath());
        long blockSize = sf.getBlockSize();//块大小,单位byte
        long availCount = sf.getAvailableBlocks();//可用块数量
//			long blockCount = sf.getBlockCount();//块总数量
//			Log.d("", "block大小:"+ blockSize+",block数目:"+ blockCount+",总大小:"+blockSize*blockCount/1024+"KB");
//			Log.d("", "可用的block数目：:"+ availCount+",剩余空间:"+ availCount*blockSize/1024+"KB");

        availableInternalSpace = availCount * blockSize / 1024 / 1024;//可用SD卡空间，单位MB

        return availableInternalSpace;
    }

    /**
     * @param cx
     * @return
     */
    public static Bundle getAppMetaDate(Context cx) {
        try {
            ApplicationInfo appInfo = cx.getPackageManager()
                    .getApplicationInfo(cx.getPackageName(),
                            PackageManager.GET_META_DATA);
            return appInfo.metaData;
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询包名是否存在
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean checkApkExist(Context context, String packageName) {
        if (packageName == null || "".equals(packageName))
            return false;
        try {
            context.getPackageManager().getApplicationInfo(packageName,
                    PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    /**
     * 获得IMEI
     *
     * @param cx
     * @return
     */
    public static String getImei(Context cx) {
        try {
            TelephonyManager tm = (TelephonyManager) cx
                    .getSystemService(Context.TELEPHONY_SERVICE);
            return tm.getDeviceId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "1";

    }

    // 获取网卡地址
    public static String getMacAddress(Context cx) {
        WifiManager wifi = (WifiManager) cx
                .getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        return info.getMacAddress();
    }

    // 获取手机类型
    public static String getModel(Context cx) {
        return Build.MODEL;
    }

    /**
     * 打开浏览器
     *
     * @param act
     * @param url
     */
    public static void openWebBrower(Activity act, String url) {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        act.startActivity(i);
    }

    /**
     * 打开拨打电话
     *
     * @param act
     * @param tel
     */
    public static void openCallPhone(Activity act, String tel) {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(tel));
        act.startActivity(i);
    }

    /**
     * 打开邮件
     *
     * @param act
     * @param emailUrl
     * @param emailTitle
     * @param emailBody
     */
    public static void openEmail(Activity act, String[] emailUrl,
                                 String emailTitle, String emailBody) {
        Intent email = new Intent(android.content.Intent.ACTION_SEND);
        email.setType("plain/text");

        // 设置邮件默认地址 参数必须是string[] 否则抛异常
        email.putExtra(android.content.Intent.EXTRA_EMAIL, emailUrl);
        // 设置邮件默认标题
        email.putExtra(android.content.Intent.EXTRA_SUBJECT, emailTitle);
        // 设置要默认发送的内容
        email.putExtra(android.content.Intent.EXTRA_TEXT, emailBody);
        // 调用系统的邮件系统
        act.startActivity(Intent.createChooser(email, "请选择发送邮件的应用"));
    }

    /**
     * 获取状态栏高度
     *
     * @param act
     * @return
     */
    public static int getStatusBarHeight(Activity act) {
        Rect frame = new Rect();
        act.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        return frame.top;
    }

    // 获取屏幕高度和宽度
    public static Rect getScreenPx(Context context) {
        return new Rect(0, 0, context.getResources().getDisplayMetrics().widthPixels, context.getResources().getDisplayMetrics().heightPixels);
    }

    /**
     * 获得view当前位置
     *
     * @param act
     * @param v
     * @return
     */
    public static int[] getViewLocation(Activity act, View v) {
        int[] location = new int[2];
        if (v != null)
            v.getLocationOnScreen(location);
        location[1] -= getStatusBarHeight(act);
        return location;
    }

    /**
     * 获得View截图
     *
     * @param view
     * @return
     */
    public static Bitmap getShotScreenByView(View view) {
        view.setDrawingCacheEnabled(true);
        Bitmap bimtap = view.getDrawingCache();
        return bimtap;
    }

    /**
     * 隐藏键盘
     *
     * @param context
     * @param view
     */
    public static void hideIMM(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null && view != null)
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * 显示键盘
     *
     * @param context
     * @param view
     */
    public static void showIMM(Context context, View view) {
        view.requestFocus();
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }

    /**
     * 获得键盘状态(显示、未显示)
     *
     * @param context
     * @return
     */
    public static boolean getIMMStatus(Activity context) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        boolean isOpen = imm.isActive();
        return isOpen;
    }

    /**
     * 显示键盘，无焦点
     *
     * @param context
     */
    public static void showIMM(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 获得国际移动用户识别码
     *
     * @param context
     * @return
     */
    public static String getIMSI(Context context) {
        return ((TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE)).getSubscriberId();
    }

    /**
     * 获得设备屏幕密度
     */
    public static float getScreenDensity(Context context) {
        DisplayMetrics metrics = context.getApplicationContext().getResources()
                .getDisplayMetrics();
        return metrics.density;
    }

    /**
     * 获取 AndroidId
     *
     * @param context Context
     * @return
     */
    public static String getAndroidId(Context context) {
        String androidID = "";
        try {
            try {
                androidID = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return androidID;
    }

    /**
     * 手机品牌(Brand)
     *
     * @return
     */
    public static String getBrand() {
        return Build.BRAND;
    }

    /**
     * 获取SimMCC
     *
     * @param context Context
     * @return SimMCC
     */
    public static String getSimMCC(Context context) {
        String _MCC = "";
        String _networkOperator = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getSimOperator();
        if (!TextUtils.isEmpty(_networkOperator)) {
            if (_networkOperator.length() >= 5) {
                _MCC = _networkOperator.substring(0, 3);
            }
        }
        return _MCC;
    }

    public static String getSimMNC(Context pContext) {
        String _MNC = "";
        String _networkOperator = ((TelephonyManager) pContext.getSystemService(Context.TELEPHONY_SERVICE)).getSimOperator();
        if (!TextUtils.isEmpty(_networkOperator)) {
            if (_networkOperator.length() >= 5) {
                _MNC = _networkOperator.substring(3);
            }
        }
        return _MNC;
    }

    public static String getMCC(Context pContext) {
        String mcc = "";
        String networkOperator = ((TelephonyManager) pContext.getSystemService(Context.TELEPHONY_SERVICE)).getNetworkOperator();
        if (!TextUtils.isEmpty(networkOperator)) {
            if (networkOperator.length() >= 5) {
                mcc = networkOperator.substring(0, 3);
            }
        }
        return mcc;
    }

    /**
     * 获取MNC
     *
     * @param pContext Context
     * @return MNC
     */
    public static String getMNC(Context pContext) {
        String _MNC = "";
        String _networkOperator = ((TelephonyManager) pContext.getSystemService(Context.TELEPHONY_SERVICE)).getNetworkOperator();
        if (!TextUtils.isEmpty(_networkOperator)) {
            if (_networkOperator.length() >= 5) {
                _MNC = _networkOperator.substring(3);
            }
        }
        return _MNC;
    }


    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param dipValue
     * @param context
     * @return
     */
    public static float dip2px(Context context, float dipValue) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, context.getResources().getDisplayMetrics());
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     * @param context
     * @return
     */
    public static float sp2px(Context context, float pxValue) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, pxValue, context.getResources().getDisplayMetrics());
    }

    /**
     * 通过进程名获取当前app进程ID
     *
     * @param context
     * @param processName
     * @return
     */
    public static int getMyAppProcessIdByProcessName(Context context, String processName) {
        if (context == null || TextUtils.isEmpty(processName)) {
            return -1;
        }

        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> list = activityManager.getRunningAppProcesses();
        if (list == null) {
            return -1;
        }

        for (ActivityManager.RunningAppProcessInfo info : list) {
            if (processName.equalsIgnoreCase(info.processName)) {
                return info.pid;
            }
        }
        return -1;
    }

    /**
     * 魅族手机smartbar相关判断
     */
    private static boolean m_bDetected = false;
    private static boolean m_bHasSmartBar = false;

    public static boolean hasSmartBar() {
        //已检测过了，直接返回保存的检测结果
        if (m_bDetected) {
            return m_bHasSmartBar;
        } else {
            //未检测过，先判断手机厂商是否为魅族，如果是,判断是否有smartbar，否则直接返回false。
            if (Build.MANUFACTURER.equals("Meizu") && Build.VERSION.SDK_INT >= 14) {
                try {
                    // 新型号可用反射调用Build.hasSmartBar()
                    Method method = Class.forName("android.os.Build").getMethod("hasSmartBar");
                    m_bHasSmartBar = (Boolean) method.invoke(null);
                    m_bDetected = true;
                    return m_bHasSmartBar;
                } catch (Exception e) {
                }

                // 反射不到Build.hasSmartBar()，则用Build.DEVICE判断
                if (!Build.DEVICE.equals("mx") && Build.DEVICE.contains("mx")) {
                    m_bHasSmartBar = true;
                    m_bDetected = true;
                    return m_bHasSmartBar;
                } else //if (Build.DEVICE.equals("mx") || Build.DEVICE.equals("m9"))
                {
                    m_bHasSmartBar = false;
                    m_bDetected = true;
                    return m_bHasSmartBar;
                }
            } else {
                m_bHasSmartBar = false;
                m_bDetected = true;
                return m_bHasSmartBar;
            }
        }
    }

    public static boolean isActivityDestroy(Context context) {
        if (context == null) {
            return true;
        }
        if (Build.VERSION_CODES.JELLY_BEAN_MR1 <= Build.VERSION.SDK_INT) {
            return (context instanceof Activity) && ((Activity) context).isDestroyed();
        } else {
            return (context instanceof Activity) && ((Activity) context).isFinishing();
        }
    }
}
