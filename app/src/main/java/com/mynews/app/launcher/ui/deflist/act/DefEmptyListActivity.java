package com.mynews.app.launcher.ui.deflist.act;

import android.view.View;

import com.mynews.app.launcher.bean.NewsBean;
import com.mynews.app.launcher.ui.deflist.DefListContract;
import com.mynews.app.launcher.ui.deflist.DefListPresenter;
import com.mynews.app.launcher.ui.deflist.adapter.DefListAdapter;
import com.base.ui.activity.BaseListActivity;
import com.base.ui.adapter.listview.BaseListViewAdapter;
import com.base.ui.presenter.Presenter;
import com.mynews.app.R;

/**
 * Created by jzy on 2018/3/23.
 */

@Presenter(DefListPresenter.class)
public class DefEmptyListActivity extends BaseListActivity<NewsBean, DefListPresenter> implements DefListContract.IDefListView<NewsBean> {

    @Override
    public int getContentViewRsId() {
        return R.layout.activity_def_list;
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
                .setListView(getListView())
                .setLoadingViewResId(R.layout.item_loading)
                .setText(R.id.loading_btn, "不要点我，我只是loading");
        mAdapter
                .setListView(getListView())
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
    public int getListViewId() {
        return R.id.list_view;
    }

    @Override
    public int getSwipeLayoutId() {
        return R.id.swipe_ly;
    }

    @Override
    public BaseListViewAdapter getAdapter() {
        return new DefListAdapter(getApplicationContext());
    }

    @Override
    public void showToast(String content) {
        showToast(content, true);
    }
}
