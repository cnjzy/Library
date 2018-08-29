package com.library.util;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;

import com.library.R;

/**
 * Created by jzy on 2017/6/13.
 */

public class DownloadManagerUtils {
    private static long reference;

    public static long getReference() {
        return reference;
    }

    public static void downloadApk(Context context, String url) {
        if (TextUtils.isEmpty(url) || !url.endsWith(".apk"))
            return;
        String fileName = url.substring(url.lastIndexOf("/") + 1, url.length());
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setTitle(context.getString(R.string.app_name));
        request.setDescription(context.getString(R.string.app_name));
        request.setMimeType("application/vnd.android.package-archive");
        request.setDestinationInExternalPublicDir("down", fileName);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        reference = downloadManager.enqueue(request);
    }
}
