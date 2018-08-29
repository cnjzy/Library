package com.mynews.app.launcher.ui.deflist;

import com.common.http.bean.BaseBean;
import com.base.contract.DataListContract;

/**
 * Created by jzy on 2018/3/23.
 */

public class DefListContract {
    public interface IDefListView<B extends BaseBean> extends DataListContract.IDataListView<B> {
        void showToast(String content);
    }

    public interface IDefPresenter extends DataListContract.Presenter {
        void loadData();
    }
}
