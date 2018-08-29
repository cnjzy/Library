package com.mynews.app.launcher.ui.defact;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.base.ui.activity.BaseActivity;
import com.base.ui.presenter.Presenter;
import com.mynews.app.R;
import com.common.http.download.IOnDownloadListener;

/**
 * Created by jzy on 2018/3/23.
 */

@Presenter(DefPresenter.class)
public class DefActivity extends BaseActivity<DefContract.IDefPresenter> implements DefContract.IDefView {

    @Override
    public int getContentViewRsId() {
        return R.layout.activity_def;
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
        setText(R.id.download_btn, "开始下载");
    }

    @Override
    public void onClickDispatch(View v) {
        switch (v.getId()) {
            case R.id.req_news_list:
                showLoadingDialog();
                if (getPresenter() != null) {
                    getPresenter().loadNewsList();
                }
                break;
            case R.id.req_news_list_string:
                if (getPresenter() != null) {
                    getPresenter().loadNewsListResultString();
                }
                break;
            case R.id.download_btn:
                if (getPresenter() != null) {
                    getPresenter().download(new IOnDownloadListener() {
                        @Override
                        public void onDownloadSuccess(String filePath) {
                            Toast.makeText(mContext, filePath, Toast.LENGTH_SHORT).show();
                            setText(R.id.download_btn, "下载成功");
                        }

                        @Override
                        public void onDownloadProgress(long progress, long total, int percentage) {
                            setText(R.id.download_btn, percentage + "%");
                        }

                        @Override
                        public void onDownloadFailed(Throwable e) {
                            setText(R.id.download_btn, "重新下载");
                        }
                    });
                }
                break;
        }
    }

    @Override
    public void showToast(String content) {
        showToast(content, true);
    }

    @Override
    public Context getContext() {
        return this;
    }
}
