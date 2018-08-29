package com.mynews.app.launcher.ui.deflist.adapter;

import android.content.Context;
import android.view.View;

import com.mynews.app.launcher.bean.NewsBean;
import com.base.ui.adapter.recycler.AbsViewHolder;
import com.base.ui.adapter.recycler.BaseRecyclerViewAdapter;
import com.mynews.app.R;

/**
 * Created by jzy on 2018/3/26.
 */

public class DefRecyclerAdapter extends BaseRecyclerViewAdapter<NewsBean> {

    public DefRecyclerAdapter(Context context) {
        super(context);
    }

    @Override
    public AbsViewHolder createViewHoler(View parent, int viewType) {
        return new ViewHolder(parent);
    }

    @Override
    public int getConvertViewRsId(int viewType) {
        return R.layout.activity_def_list_item;
    }

    class ViewHolder extends AbsViewHolder<NewsBean> {

        public ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bindData(NewsBean NewsBean, int position, Object... args) {
            if (NewsBean == null) {
                return;
            }
            setText(R.id.item_name_tv, NewsBean.getTitle());
        }
    }
}
