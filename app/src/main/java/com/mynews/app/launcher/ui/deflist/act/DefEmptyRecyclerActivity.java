package com.mynews.app.launcher.ui.deflist.act;

import android.view.View;

import com.mynews.app.launcher.bean.NewsBean;
import com.mynews.app.launcher.ui.deflist.DefListContract;
import com.mynews.app.launcher.ui.deflist.DefListPresenter;
import com.mynews.app.launcher.ui.deflist.adapter.DefRecyclerAdapter;
import com.base.ui.activity.BaseRecyclerViewActivity;
import com.base.ui.adapter.recycler.BaseRecyclerViewAdapter;
import com.base.ui.presenter.Presenter;
import com.mynews.app.R;

/**
 * Created by jzy on 2018/3/23.
 */

@Presenter(DefListPresenter.class)
public class DefEmptyRecyclerActivity extends BaseRecyclerViewActivity<NewsBean, DefListPresenter> implements DefListContract.IDefListView<NewsBean> {

    @Override
    public int getContentViewRsId() {
        return R.layout.activity_def_recycler;
    }

    @Override
    public void init() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void setViewsValue() {
        mAdapter
                .setRecyclerView(getRecyclerView())
                .setLoadingViewResId(R.layout.item_loading)
                .setText(R.id.loading_btn, "不要点我，我只是loading");
        mAdapter
                .setRecyclerView(getRecyclerView())
                .setEmptyViewResId(R.layout.item_empty)
                .setText(R.id.empty_btn, "点我啊，我是一个空页面");

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                onInitSuccess(null, false);
            }
        }, 5000);

        setAdapter();

    }

    @Override
    public void onClickDispatch(View v) {

    }

    @Override
    public int getRecyclerId() {
        return R.id.list_view;
    }

    @Override
    public BaseRecyclerViewAdapter getAdapter() {
        return new DefRecyclerAdapter(getApplicationContext());
    }

    @Override
    public void showToast(String content) {
        showToast(content, true);
    }
}
