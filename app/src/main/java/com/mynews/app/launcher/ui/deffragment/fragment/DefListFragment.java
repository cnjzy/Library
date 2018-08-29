package com.mynews.app.launcher.ui.deffragment.fragment;

import android.view.View;

import com.mynews.app.launcher.ui.deffragment.DefFragmentListPresenter;
import com.mynews.app.launcher.ui.deflist.DefListContract;
import com.mynews.app.launcher.ui.deflist.adapter.DefListAdapter;
import com.base.ui.adapter.listview.BaseListViewAdapter;
import com.base.ui.fragment.BaseListFragment;
import com.base.ui.presenter.Presenter;
import com.mynews.app.R;

/**
 * Created by jzy on 2018/3/29.
 */

@Presenter(DefFragmentListPresenter.class)
public class DefListFragment extends BaseListFragment implements DefListContract.IDefListView {
    @Override
    public int getContentViewRsId() {
        return R.layout.fragment_def_list;
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
    public int getListViewId() {
        return R.id.list_view;
    }

    @Override
    public int getSwipeLayoutId() {
        return R.id.swipe_ly;
    }

    @Override
    public BaseListViewAdapter getAdapter() {
        return new DefListAdapter(getContext());
    }

    @Override
    public void showToast(String content) {
        showToast("load data", true);
    }
}
