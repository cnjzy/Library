package com.mynews.app.launcher.ui.defact;

import com.mynews.app.launcher.bean.NewsBean;
import com.mynews.app.launcher.bean.req.NewsListReq;
import com.base.ui.presenter.AbsBasePresenter;
import com.common.http.callback.HttpCallback;
import com.common.http.load.HttpLoader;
import com.common.http.download.DownloadManager;
import com.common.http.download.IOnDownloadListener;

import java.util.List;

/**
 * Created by jzy on 2018/3/23.
 */

public class DefPresenter extends AbsBasePresenter<DefContract.IDefView> implements DefContract.IDefPresenter {

    private final String TAG = "DefPresenter";

    @Override
    public void loadData() {
        loadNewsList();
        loadNewsListResultString();
    }

    @Override
    public void loadNewsList() {
        // 按照对象返回
        HttpLoader.getInstance().postWithForm(new NewsListReq(), new HttpCallback<List<NewsBean>>() {
            @Override
            public void onError(int code, Throwable e) {
                if (getView() != null) {
                    getView().showToast(e.toString());
                }
            }

            @Override
            public void onSuccess(List<NewsBean> newsList) {
                if (getView() != null) {
                    getView().showToast("item class " + (newsList.size() > 0 ? newsList.get(0).getClass().getName() : "") + "\n item count " + newsList.size());
                }
            }
        });

    }

    @Override
    public void loadNewsListResultString() {
        /**
         * 按照String返回
         */
        HttpLoader.getInstance().postWithForm(new NewsListReq(), new HttpCallback<String>() {
            @Override
            public void onError(int code, Throwable e) {
                if (getView() != null) {
                    getView().showToast(e.toString());
                }
            }

            @Override
            public void onSuccess(String str) {
                if (getView() != null) {
                    getView().showToast(str);
                }
            }
        });
    }

    @Override
    public void download(IOnDownloadListener listener) {
        final String url = "http://appdl.hicloud.com/dl/appdl/application/apk/27/27c6949bb3674684889840fb235caa9a/com.lovebizhi.wallpaper.1805231510.apk?sign=portal@portal1527501993027&source=portalsite";
        DownloadManager.getInstance().download(getView().getContext(), url, listener);
    }
}
