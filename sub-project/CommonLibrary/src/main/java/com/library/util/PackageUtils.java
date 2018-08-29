package com.library.util;


import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.WallpaperInfo;
import android.app.WallpaperManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Looper;
import android.text.TextUtils;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class PackageUtils {
//    public static void uninstall(Context context, final String pkgName) {
//        // 通过程序的包名创建URL
//        Uri packageURI = Uri.parse("package:" + pkgName);
//        // 创建Intent意图
//        Intent intent = new Intent(Intent.ACTION_DELETE);
//        // 设置Uri
//        intent.setData(packageURI);
//        // 卸载程序
//        ComponentUtils.startActivity(context, intent);
//    }
//
//    public static long safeGetFirstInstallTime(final PackageInfo packageInfo) {
//        long time = 0;
//        try {
//            Field firstInstallTime = packageInfo.getClass().getField("firstInstallTime");
//            firstInstallTime.setAccessible(true);
//            time = firstInstallTime.getLong(packageInfo);
//        } catch (SecurityException e1) {
//            e1.printStackTrace();
//        } catch (NoSuchFieldException e1) {
//            e1.printStackTrace();
//        } catch (IllegalArgumentException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        return time;
//    }
//
    public static boolean isPkgInstalled(Context ctx, String pkgName) {
        return (getApplicationInfo(ctx, pkgName) != null);
    }

    public static ApplicationInfo getApplicationInfo(Context ctx, String pkgName) {
        if (TextUtils.isEmpty(pkgName) || ctx == null) return null;
        try {
            PackageInfo pkgInfo = ctx.getPackageManager().getPackageInfo(pkgName, 0);
            if (pkgInfo != null) {
                return pkgInfo.applicationInfo;
            }
        } catch (Exception e) {
        }
        return null;
    }
//
//    public static void openApp(Context context, String packageName) {
//        Intent intent = getLaunchIntentForPackage(context, packageName);
//        if (intent != null) {
//            if (!(context instanceof Activity)) {
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            }
//            ComponentUtils.startActivity(context, intent);
//        }
//    }
//
//    public static Intent getLaunchIntentForPackage(Context context, String pkgname) {
//        PackageManager pm = context.getPackageManager();
//        Intent intent = null;
//        try {
//            intent = pm.getLaunchIntentForPackage(pkgname);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return intent;
//    }
//
//    public static boolean isSystemApp(Context context, String pkgName) {
//        ApplicationInfo ai = getApplicationInfo(context, pkgName);
//        return ai != null && (ai.flags & ApplicationInfo.FLAG_SYSTEM) != 0;
//    }
//
//    public static String getPkgNameFromPid(Context context, int pid) {
//        ActivityManagerHelper amh = new ActivityManagerHelper();
//        amh.setSkeyclient(RTApiClient.getInst());
//        List<RunningAppProcessInfo> apps = amh.getRunningAppProcesses(context);
//
//        if (apps != null) { // fixed dumpkey=3400109938空指针问题
//            for (RunningAppProcessInfo app : apps) {
//                if (app.pid == pid) {
//                    if (null != app.pkgList)
//                        return app.pkgList[0];
//
//                    return null;
//                }
//            }
//        }
//        return null;
//    }
//
//    public static Drawable getAppIcon(Context context, String packageName) {
//        if (context == null) return null;
//        PackageManager pm = context.getPackageManager();
//        try {
//            return pm.getApplicationIcon(packageName);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public static boolean isHasPackage(Context c, String packageName) {
        if (null == c || null == packageName) return false;

        boolean bHas = true;
        try {
            c.getPackageManager().getPackageInfo(packageName, PackageManager.GET_GIDS);
        } catch (/* NameNotFoundException */Exception e) {
            // 抛出找不到的异常，说明该程序已经被卸载
            bHas = false;
        }
        return bHas;
    }

//    public static PackageInfo getPackageInfo(Context ctx, String pkgName) {
//        if (TextUtils.isEmpty(pkgName) || ctx == null) return null;
//        try {
//            return ctx.getPackageManager().getPackageInfo(pkgName, 0);
//        } catch (Exception e) {
//            return null;
//        }
//    }
//
//    public static int getPidFromPkgName(Context context, String pkgName) {
//        if (pkgName == null) return 0;
//
//        ActivityManagerHelper amh = new ActivityManagerHelper();
//        amh.setSkeyclient(RTApiClient.getInst());
//        List<RunningAppProcessInfo> apps = amh.getRunningAppProcesses(context);
//
//        if (apps != null) {
//            for (RunningAppProcessInfo app : apps) {
//                if (null != app.pkgList) {
//                    for (int i = 0; i < app.pkgList.length; i++) {
//                        if (pkgName.equals(app.pkgList[i])) {
//                            return app.pid;
//                        }
//                    }
//                }
//            }
//        }
//
//        return 0;
//    }
//
//    /**
//     * @param pkg 应用包名
//     * @return 判断应用包名是否存在
//     */
//    public static boolean isPackageExist(String pkg) {
//        try {
//            PackageManager pm = HostHelper.getInstance().getAppContext().getPackageManager();
//            PackageInfo info = pm.getPackageInfo(pkg, 0);
//            if (null != info) {
//                return true;
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//            return false;
//        }
//        return false;
//    }
//
//    public static int getVersionCode(Context context, String pkgName) {
//        if (context == null || null == pkgName || pkgName.length() <= 0) {
//            return -1;
//        }
//
//        PackageManager localPackageManager = context.getPackageManager();
//        try {
//            return localPackageManager.getPackageInfo(pkgName, 0).versionCode;
//        } catch (/* NameNotFoundException */Exception e) {
//            e.printStackTrace();
//            return -1;
//        }
//    }
//
//    public static int getVersionCode() {
//        final Context context = HostHelper.getInstance().getAppContext();
//        final String pkgName = context.getPackageName();
//        return getVersionCode(context, pkgName);
//    }
//
//    public static boolean isGPAvailable(Context ctx) {
//        if (!isHasPackage(ctx, "com.android.vending")) {
//            return false;
//        }
//
//        // 判断GP服务包
//        PackageInfo gsfInfo = getPackageInfo(ctx, "com.google.android.gsf");
//        if (null == gsfInfo || null == gsfInfo.applicationInfo || ((gsfInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 1)) {
//            return false;
//        }
//
//		/*PackageInfo gsfLoginInfo = getPackageInfo(ctx, "com.google.android.gsf.login");
//        if (null == gsfLoginInfo || ((gsfLoginInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 1)) {
//			return false;
//		}*/
//
//        return true;
//    }
//
//    public static long getFirstInstallTime(PackageInfo pkgInfo) {
//        return pkgInfo.firstInstallTime;
//    }
//
//    public static String getAuthority(Context context, String pkgName) {
//        ProviderInfo[] providers = getProviderInfo(context, pkgName);
//        if (providers != null && providers.length > 0) {
//            return providers[0].authority;
//        }
//        return null;
//    }
//
//    public static ProviderInfo[] getProviderInfo(Context context, String packagename) {
//        PackageManager pm = context.getPackageManager();
//        try {
//            PackageInfo pi = pm.getPackageInfo(packagename, PackageManager.GET_PROVIDERS);
//            return pi.providers;
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public static void showInstalledAppDetails(Context context, String packageName) {
//        if (packageName == null) return;
//        final int apiLevel = Build.VERSION.SDK_INT;
//        Intent intent = new Intent();
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        if (PhoneModelUtils.isEMUI() && PackageUtils.isHasPackage(HostHelper.getInstance().getAppContext(), "com.huawei.systemmanager")) {
//            intent.setAction("Intent.ACTION_VIEW");
//            intent.setClassName("com.huawei.systemmanager", "com.huawei.notificationmanager.ui.NotificationManagmentActivity");
//        } else if (apiLevel >= 9) {
//            // 如果是MIUIV6，还得判断下这个app是否存在。
//            if ((PhoneModelUtils.isSingleMiuiV6() || PhoneModelUtils.isSingleMiuiV7() || PhoneModelUtils.isSingleMiuiV8())
//                    && PackageUtils.isHasPackage(HostHelper.getInstance().getAppContext(), "com.miui.securitycenter")) {
//                intent.setAction("miui.intent.action.APP_PERM_EDITOR");
//                intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
//                intent.putExtra("extra_pkgname", packageName);
//            } else {
//                intent.setAction(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//                intent.setData(Uri.parse("package:" + packageName));
//            }
//        } else {
//            final String appPkgName = (apiLevel == 8 ? "pkg" : "com.android.settings.ApplicationPkgName");
//            intent.setAction(Intent.ACTION_VIEW);
//            intent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
//            intent.putExtra(appPkgName, packageName);
//        }
//        Commons.startActivity(context, intent);
//    }
//
//    public static String getAppNameByPackageName(Context context, String packageName) {
//        if (context == null) return null;
//
//        ApplicationInfo info = PackageUtils.getApplicationInfo(context, packageName);
//        if (info != null) {
//            PackageManager pm = context.getPackageManager();
//            return pm.getApplicationLabel(info).toString();
//        } else {
//            return packageName;
//        }
//    }
//
//    /**
//     * 获取当前动态壁纸的包名
//     *
//     * @param context
//     *            the context
//     * @return 动态壁纸的包名，或者""
//     */
//    public final static String getPackageNameOfCurrentLiveWallpaper(Context context) {
//        try {
//            final WallpaperManager wallpaperManager = WallpaperManager.getInstance(context);
//            if (wallpaperManager == null) {
//                return "";
//            }
//
//            WallpaperInfo wi = wallpaperManager.getWallpaperInfo();
//            if (wi == null) {
//                return "";
//            }
//
//            String pkgname = wallpaperManager.getWallpaperInfo().getPackageName();
//            return pkgname == null ? "" : pkgname;
//        } catch (SecurityException e) {
//            e.printStackTrace();
//        } catch (Exception e){
//            CMLogUtils.e(PackageUtils.class.getSimpleName(), e.getMessage().toString());
//        }
//        return "";
//    }
//
//    /**
//     * 是否是Laucnher
//     *
//     * @param context
//     * @param packageInfo
//     * @return
//     */
//    public static boolean isLauncher(Context context, final PackageInfo packageInfo) {
//        if (packageInfo == null || context == null){
//            return false;
//        }
//        Intent intent = new Intent(Intent.ACTION_MAIN);
//        intent.addCategory(Intent.CATEGORY_HOME);
//        intent.setPackage(packageInfo.packageName);
//        List<ResolveInfo> homes = new PackageManagerWrapper(context.getPackageManager()).queryIntentActivities(intent, 0);
//        return (homes != null && homes.size() > 0);
//    }
//
//    public static String getForegroundAppPackageName(Context context){
//        try {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                return getForegroundAppAfterL(context);
//            } else {
//                return getForegroundAppBeforeL(context);
//            }
//        } catch (Exception e){
//            e.printStackTrace();
//            return "";
//        }
//    }
//
//    private static String getForegroundAppBeforeL(Context context) {
//        ActivityManager mActivityManager =
//                (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
//        if (mActivityManager.getRunningTasks(1) == null) {
//            return "";
//        }
//        ActivityManager.RunningTaskInfo mRunningTask =
//                mActivityManager.getRunningTasks(1).get(0);
//        if (mRunningTask == null) {
//            return "";
//        }
//
//        String pkgName = mRunningTask.topActivity.getPackageName();
//        return pkgName;
//    }
//
//    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//    public static String getForegroundAppAfterL(Context context) {
//        class RecentUseComparator implements Comparator<UsageStats> {
//            @Override
//            public int compare(UsageStats lhs, UsageStats rhs) {
//                return (lhs.getLastTimeUsed() > rhs.getLastTimeUsed()) ? -1 : (lhs.getLastTimeUsed() == rhs.getLastTimeUsed()) ? 0 : 1;
//            }
//        }
//        RecentUseComparator mRecentComp = new RecentUseComparator();
//        long ts = System.currentTimeMillis();
//        UsageStatsManager mUsageStatsManager = (UsageStatsManager) context.getSystemService("usagestats");
//        List<UsageStats> usageStats = mUsageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_BEST, ts - 1000 * 10, ts);
//        if (usageStats == null || usageStats.size() == 0) {
//            return "";
//        }
//        Collections.sort(usageStats, mRecentComp);
//        String currentTopPackage = usageStats.get(0).getPackageName();
//        return currentTopPackage;
//    }
//
//    private static final ReentrantLock sAppInfoLock = new ReentrantLock();
//
//    public static ApplicationInfo getAppApplication(Context context, String packageName) {
//        if (Looper.myLooper() != Looper.getMainLooper()) {
//            sAppInfoLock.lock();
//        }
//        try {
//            return getApplicationInfoImpl(context, packageName);
//        } finally {
//            if (Looper.myLooper() != Looper.getMainLooper()) {
//                sAppInfoLock.unlock();
//            }
//        }
//    }
//
//    private static ApplicationInfo getApplicationInfoImpl(Context context, String packageName) {
//        ApplicationInfo info = null;
//        if (context == null) {
//            return null;
//        }
//        PackageManager pm = context.getPackageManager();
//        try {
//            info = pm.getApplicationInfo(packageName, PackageManager.GET_META_DATA);
//            return info;
//        } catch (Throwable e) {
//            e.printStackTrace();
//        }
//        return info;
//    }
//
//    public static long getUpdateTimeFromSourceDir(final PackageInfo packageInfo) {
//        long time = 0;
//        try {
//            String sourceDir = packageInfo.applicationInfo.sourceDir;
//            time = new File(sourceDir).lastModified();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return time;
//    }

    public static void startAppByPkgName(String pkgName, Context context) {

        // 通过包名获取此APP详细信息，包括Activities、services、versioncode、name等等
        PackageInfo packageinfo = null;
        try {
            packageinfo = context.getPackageManager().getPackageInfo(pkgName, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (packageinfo == null) {
            return;
        }

        // 创建一个类别为CATEGORY_LAUNCHER的该包名的Intent
        Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
        resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        resolveIntent.setPackage(packageinfo.packageName);

        // 通过getPackageManager()的queryIntentActivities方法遍历
        List<ResolveInfo> resolveinfoList = context.getPackageManager().queryIntentActivities(resolveIntent, 0);
        if (resolveinfoList == null || resolveinfoList.size() == 0) {
            return;
        }

        ResolveInfo resolveinfo = resolveinfoList.iterator().next();
        if (resolveinfo != null) {
            // packagename = 参数packname
            String packageName = resolveinfo.activityInfo.packageName;
            // 这个就是我们要找的该APP的LAUNCHER的Activity[组织形式：packagename.mainActivityname]
            String className = resolveinfo.activityInfo.name;
            // LAUNCHER Intent
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
            // 设置ComponentName参数1:packagename参数2:MainActivity路径
            ComponentName cn = new ComponentName(packageName, className);
            intent.setComponent(cn);
            context.startActivity(intent);
        }
    }
}
