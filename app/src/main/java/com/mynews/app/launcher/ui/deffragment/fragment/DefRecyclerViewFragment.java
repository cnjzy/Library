package com.mynews.app.launcher.ui.deffragment.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;

import com.mynews.app.launcher.ui.deffragment.DefFragmentListPresenter;
import com.mynews.app.launcher.ui.deflist.adapter.DefRecyclerAdapter;
import com.mynews.app.launcher.widget.CustomRecyclerViewRefreshHeader2;
import com.base.ui.adapter.recycler.BaseRecyclerViewAdapter;
import com.base.ui.fragment.BaseRecyclerViewFragment;
import com.base.ui.presenter.Presenter;
import com.mynews.app.R;

/**
 * Created by jzy on 2018/3/29.
 */
@Presenter(DefFragmentListPresenter.class)
public class DefRecyclerViewFragment extends BaseRecyclerViewFragment implements CustomRecyclerViewRefreshHeader2.OnRefreshListener {

    private CustomRecyclerViewRefreshHeader2 mCustomRecyclerViewRefreshHeader = null;

    private View mRefreshInfoLy;
    private View mRefreshBgIv;
    private View mRefreshContentTv;

    @Override
    public int getContentViewRsId() {
        return R.layout.fragment_def_recycler;
    }

    @Override
    public void init() {

    }

    @Override
    public void initView() {
        mCustomRecyclerViewRefreshHeader = new CustomRecyclerViewRefreshHeader2(getContext());

        mRefreshInfoLy = findViewById(R.id.refresh_info_ly);
        mRefreshBgIv = findViewById(R.id.refresh_info_bg_iv);
        mRefreshContentTv = findViewById(R.id.refresh_info_content_tv);
    }

    @Override
    public void initListener() {
        mCustomRecyclerViewRefreshHeader.setOnRefreshListener(this);
    }

    @Override
    public void setViewsValue() {
        getRecyclerView().setRefreshHeader(mCustomRecyclerViewRefreshHeader);
        setAdapter();
        initData(true);
    }

    @Override
    public void onClickDispatch(View v) {

    }

    @Override
    protected void lazyLoad() {
    }

    @Override
    public int getRecyclerId() {
        return R.id.list_view;
    }


    @Override
    public BaseRecyclerViewAdapter getAdapter() {
        return new DefRecyclerAdapter(getContext());
    }

    private void startRefreshBgAnimation() {
        mRefreshInfoLy.setTranslationY(0);
        getRecyclerView().setTranslationY(mRefreshInfoLy.getHeight());
        mRefreshInfoLy.setVisibility(View.VISIBLE);

        ObjectAnimator animator = ObjectAnimator.ofFloat(mRefreshBgIv, "scaleX", 0f, 1f);
        animator.setDuration(300);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                startRefreshInfoHideAnimation();
            }
        });
        animator.start();
    }

    private void startRefreshInfoHideAnimation() {
        ValueAnimator animator = ValueAnimator.ofInt(0, -mRefreshInfoLy.getHeight());
        animator.setDuration(500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (int) valueAnimator.getAnimatedValue();
                mRefreshInfoLy.setTranslationY(value);
                getRecyclerView().setTranslationY(mRefreshInfoLy.getHeight() + value);
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                getRecyclerView().setTranslationY(0);
                mRefreshInfoLy.setVisibility(View.GONE);
                mCustomRecyclerViewRefreshHeader.notifyReset();
            }
        });
        animator.start();
    }

    @Override
    public void onRefreshComplete() {
        startRefreshBgAnimation();
    }
}
