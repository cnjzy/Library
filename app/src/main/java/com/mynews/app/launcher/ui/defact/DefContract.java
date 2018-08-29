package com.mynews.app.launcher.ui.defact;

import android.content.Context;

import com.base.ui.presenter.IBaseMvpPresenter;
import com.base.ui.view.IBaseMvpView;
import com.common.http.download.IOnDownloadListener;

/**
 * Created by jzy on 2018/3/23.
 */

public class DefContract {
    public interface IDefView extends IBaseMvpView<IDefPresenter> {
        void showToast(String content);

        Context getContext();
    }

    public interface IDefPresenter extends IBaseMvpPresenter<IDefView> {
        void loadData();

        void loadNewsList();

        void loadNewsListResultString();

        void download(IOnDownloadListener listener);
    }
}
