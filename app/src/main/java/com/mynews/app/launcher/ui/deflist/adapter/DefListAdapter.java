package com.mynews.app.launcher.ui.deflist.adapter;

import android.content.Context;
import android.view.View;

import com.mynews.app.launcher.bean.NewsBean;
import com.base.ui.adapter.listview.BaseListViewAdapter;
import com.base.ui.adapter.listview.AbsViewHolder;
import com.base.ui.view.IBaseViewHolder;
import com.mynews.app.R;

/**
 * Created by jzy on 2018/3/26.
 */

public class DefListAdapter extends BaseListViewAdapter<NewsBean> {

    public DefListAdapter(Context context) {
        super(context);
    }

    @Override
    public int getConvertViewRsId(int viewType) {
        return R.layout.activity_def_list_item;
    }

    @Override
    public IBaseViewHolder createViewHoler(View parent, int viewType) {
        return new ViewHolder(parent);
    }

    class ViewHolder extends AbsViewHolder<NewsBean> {

        public ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bindData(NewsBean NewsBean, int position, Object... arg) {
            if (NewsBean == null) {
                return;
            }
            setText(R.id.item_name_tv, NewsBean.getTitle());
        }

        @Override
        public void bindView() {
        }
    }
}
