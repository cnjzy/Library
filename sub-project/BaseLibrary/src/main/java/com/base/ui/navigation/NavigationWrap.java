package com.base.ui.navigation;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.base.R;
import com.base.ui.view.IViewHolderWrap;
import com.base.ui.view.ViewHolderWrap;
import com.library.util.res.ResHelper;

/**
 * @author jzy
 * created at 2018/8/30
 */
public class NavigationWrap {

    private View mNavigationView = null;
    private ViewHolderWrap mViewHolderWrap = null;

    public NavigationWrap(Context context, int resLayoutId) {
        if (context == null || resLayoutId <= 0) {
            throw new NullPointerException("navigation bar context or resLayout is empty");
        }
        mNavigationView = View.inflate(context, resLayoutId, null);
        mViewHolderWrap = new ViewHolderWrap(context, mNavigationView);
    }

    public ViewHolderWrap getViewHolder() {
        return mViewHolderWrap;
    }

    public IViewHolderWrap setTitle(int resId) {
        return mViewHolderWrap.setText(R.id.navigation_title_tv, resId);
    }

    public IViewHolderWrap setTitle(String content) {
        return mViewHolderWrap.setText(R.id.navigation_title_tv, content);
    }

    public View build() {
        return mNavigationView;
    }

    public void initBaseNavigation(Activity activity, int resId) {
        initBaseNavigation(activity, ResHelper.getInstance().getString(resId));
    }

    public void initBaseNavigation(final Activity activity, String title) {
        setTitle(title).setOnClickListener(R.id.navigation_back_btn, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (activity != null) {
                    activity.finish();
                }
            }
        });
    }
}
