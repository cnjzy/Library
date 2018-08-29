package com.mynews.app.launcher.ui.defweb;

import android.view.View;

import com.base.ui.activity.BaseWebActivity;
import com.mynews.app.R;

/**
 * Created by jzy on 2018/3/26.
 */

public class WebViewActivity extends BaseWebActivity {

    private final String URL = "http://www.baidu.com";

    @Override
    public int getContentViewRsId() {
        return R.layout.activity_def_web;
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
        if (getWebView() != null) {
            getWebView().loadUrl(URL);
        }

    }

    @Override
    public int getWebViewId() {
        return R.id.webview;
    }

    @Override
    public void onClickDispatch(View v) {

    }
}
