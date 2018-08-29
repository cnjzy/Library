package com.mynews.app.launcher.ui;

import android.content.Intent;
import android.view.View;

import com.mynews.app.launcher.ui.defact.DefActivity;
import com.mynews.app.launcher.ui.deffragment.DefFragmentActivity;
import com.mynews.app.launcher.ui.deflist.act.DefEmptyListActivity;
import com.mynews.app.launcher.ui.deflist.act.DefEmptyRecyclerActivity;
import com.mynews.app.launcher.ui.deflist.act.DefListActivity;
import com.mynews.app.launcher.ui.deflist.act.DefRecyclerViewActivity;
import com.mynews.app.launcher.ui.defweb.ScrollWebViewActivity;
import com.mynews.app.launcher.ui.defweb.WebViewActivity;
import com.base.ui.activity.BaseActivity;
import com.mynews.app.R;

public class SplashActivity extends BaseActivity {

    @Override
    public int getContentViewRsId() {
        return R.layout.activity_main;
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
        initStatusBar();
    }

    @Override
    public void onClickDispatch(View v) {
        switch (v.getId()) {
            case R.id.show_def_activity_btn:
                launchActivity(DefActivity.class);
                break;
            case R.id.show_def_list_btn:
                launchActivity(DefListActivity.class);
                break;
            case R.id.show_def_web_btn:
                launchActivity(WebViewActivity.class);
                break;
            case R.id.show_def_scroll_web_btn:
                launchActivity(ScrollWebViewActivity.class);
                break;
            case R.id.show_def_recycleview_btn:
                launchActivity(DefRecyclerViewActivity.class);
                break;
            case R.id.show_def_empty_listview_btn:
                launchActivity(DefEmptyListActivity.class);
                break;
            case R.id.show_def_empty_recycler_btn:
                launchActivity(DefEmptyRecyclerActivity.class);
                break;
            case R.id.show_def_fragment_btn:
                launchActivity(DefFragmentActivity.class);
                break;
        }
    }

    private void launchActivity(Class c) {
        try {
            Intent intent = new Intent(this, c);
            startActivity(intent);
        } catch (Throwable tr) {
            tr.printStackTrace();//
        }
    }
}
