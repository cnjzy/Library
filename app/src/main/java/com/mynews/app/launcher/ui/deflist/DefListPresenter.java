package com.mynews.app.launcher.ui.deflist;

import android.os.Handler;

import com.mynews.app.launcher.bean.NewsBean;
import com.base.ui.presenter.DataListPresenter;
import com.library.log.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jzy on 2018/3/23.
 */

public class DefListPresenter extends DataListPresenter<DefListContract.IDefListView> implements DefListContract.IDefPresenter {

    private List<NewsBean> mDataList = new ArrayList<>();
    private int mIndex = 0;
    private Handler mHandler = new Handler();


    @Override
    public void loadMore() {
        synchronized (mIsLoadMore) {
            mIsLoadMore = false;
        }
        LogUtils.e("=====================loadMore");
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshTestDataList();
                loadMoreSuccess(mDataList);
            }
        }, 2000);

    }

    @Override
    public void initData(boolean isShowLoading) {
        super.initData(isShowLoading);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshTestDataList();
                initDataSuccess(mDataList, false, true);
            }
        }, 2000);
    }

    private void refreshTestDataList() {
        mDataList.clear();
        for (int i = mIndex; i < mIndex + 10; i++) {
            NewsBean NewsBean = new NewsBean();
            NewsBean.setTitle("name " + i);
            mDataList.add(NewsBean);
        }
        mIndex += 10;
    }

    @Override
    public void loadData() {
        if (getView() != null) {
            getView().showToast("load data");
        }
    }
}
