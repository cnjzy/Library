package com.mynews.app.launcher.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import com.mynews.app.R;
import com.jcodecraeer.xrecyclerview.AbsRefreshHeader;
import com.library.log.LogUtils;

/**
 * Created by jzy on 2018/3/29.
 */

public class CustomRecyclerViewRefreshHeader extends AbsRefreshHeader {

    private View mProgressPb;

    private View mRefreshInfoLy;
    private View mRefreshBgIv;
    private View mRefreshContentTv;

    public CustomRecyclerViewRefreshHeader(Context context) {
        super(context);
    }

    public CustomRecyclerViewRefreshHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRecyclerViewRefreshHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void destroy() {

    }

    @Override
    public void initView() {
        mProgressPb = findViewById(R.id.progress_pb);

        mRefreshInfoLy = findViewById(R.id.refresh_info_ly);
        mRefreshBgIv = findViewById(R.id.refresh_info_bg_iv);
        mRefreshContentTv = findViewById(R.id.refresh_info_content_tv);
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
            startRefreshBgAnimation();
        } else {
            notifyReset();
        }
        setState(STATE_DONE);
    }

    private void startRefreshBgAnimation() {
        mProgressPb.setVisibility(View.INVISIBLE);
        mRefreshInfoLy.setVisibility(View.VISIBLE);

        ObjectAnimator animator = ObjectAnimator.ofFloat(mRefreshBgIv, "scaleX", 0f, 1f, 1f);
        animator.setDuration(500);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                notifyReset();
            }
        });
        animator.start();
    }

    @Override
    protected void onScrollEnd() {
        super.onScrollEnd();
        if (mProgressPb.getVisibility() != View.VISIBLE) {
            mRefreshInfoLy.setVisibility(View.INVISIBLE);
            mProgressPb.setVisibility(View.VISIBLE);
        }
    }

    public void notifyReset() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                reset();
            }
        }, 200);
    }
}
