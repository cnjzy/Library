package com.mynews.app.launcher.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import com.mynews.app.R;
import com.jcodecraeer.xrecyclerview.AbsRefreshHeader;

/**
 * Created by jzy on 2018/3/29.
 */

public class CustomRecyclerViewRefreshHeader2 extends AbsRefreshHeader {

    private OnRefreshListener mOnRefreshListener;

    public CustomRecyclerViewRefreshHeader2(Context context) {
        super(context);
    }

    public CustomRecyclerViewRefreshHeader2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRecyclerViewRefreshHeader2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void destroy() {

    }

    @Override
    public void initView() {
    }

    @Override
    protected int getRefreshLayoutId() {
        return R.layout.view_recycleview_refresh;
    }

    @Override
    public void setState(int state) {
        super.setState(state);
        switch (state) {
            case STATE_NORMAL:
                // 默认状态
                break;
            case STATE_RELEASE_TO_REFRESH:
                // 释放到刷新
                break;
            case STATE_REFRESHING:
                // 刷新中
                break;
            case STATE_DONE:
                // 完成
                break;
        }
        // 必须
        mState = state;
    }

    @Override
    public void refreshComplete() {
        if (mState == STATE_REFRESHING) {
            cancelSmoothScrollAnim();
            setVisibleHeight(0);
            if (mOnRefreshListener != null) {
                mOnRefreshListener.onRefreshComplete();
            }
        } else {
            notifyReset();
        }
        setState(STATE_DONE);
    }

    public void notifyReset() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                reset();
            }
        }, 200);
    }

    public void setOnRefreshListener(OnRefreshListener onRefreshListener) {
        this.mOnRefreshListener = onRefreshListener;
    }

    public interface OnRefreshListener {
        void onRefreshComplete();
    }
}
