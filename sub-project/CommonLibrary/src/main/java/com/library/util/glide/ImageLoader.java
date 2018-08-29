package com.library.util.glide;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.library.host.HostHelper;
import com.library.log.LogUtils;

/**
 * Created by Jzy on 2016/11/21.
 */
public class ImageLoader {

    private static final String TAG = "ImageLoader";

    public static void loadImage(ImageView imageView, String url) {
        if (imageView == null) {
            return;
        }
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    public static void loadImage(ImageView imageView, String url, int defResId) {
        if (imageView == null) {
            return;
        }
        RequestOptions options = new RequestOptions();
        options.placeholder(defResId);
        Glide.with(imageView.getContext()).load(url).apply(options).into(imageView);
    }

    public static void loadCircle(String url, ImageView view) {
        if (view == null) {
            return;
        }
//        RequestOptions requestOptions = RequestOptions.placeholderOf(R.drawable.ic_head_default).bitmapTransform(new CircleCrop());
        Glide.with(HostHelper.getInstance().getAppContext()).load(url)
//                .apply(requestOptions)
                .into(view);

    }

    public static void loadCircle(int url, ImageView view) {
        if (view == null) {
            return;
        }
//        RequestOptions requestOptions = RequestOptions.placeholderOf(R.drawable.ic_head_default).bitmapTransform(new CircleCrop());
        Glide.with(HostHelper.getInstance().getAppContext()).load(url)
//                .apply(requestOptions)
                .into(view);
    }

    public static void pause(Context context) {
        if (context == null) {
            return;
        }
        LogUtils.e(TAG, "=================pause");
        Glide.with(context).pauseRequests();
    }

    public static void resume(Context context) {
        if (context == null) {
            return;
        }
        LogUtils.e(TAG, "=================resume");
        Glide.with(context).resumeRequests();
    }

    public static boolean isPaused(Context context) {
        if (context == null) {
            return false;
        }
        return Glide.with(context).isPaused();
    }
}
