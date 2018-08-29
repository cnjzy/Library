package com.mynews.app.launcher.ui.deffragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.mynews.app.launcher.ui.deffragment.fragment.DefFragment;
import com.mynews.app.launcher.ui.deffragment.fragment.DefListFragment;
import com.mynews.app.launcher.ui.deffragment.fragment.DefRecyclerViewFragment;
import com.base.ui.activity.BaseActivity;
import com.base.ui.fragment.BaseFragment;
import com.mynews.app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jzy on 2018/3/29.
 */

public class DefFragmentActivity extends BaseActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private String[] mTitles = {
            "普通Fragment",
            "ListFragment",
            "XRecyclerViewFragment"
    };

    @Override
    public int getContentViewRsId() {
        return R.layout.activity_def_fragment;
    }

    @Override
    public void init() {

    }

    @Override
    public void initView() {
        mTabLayout = (TabLayout) findViewById(R.id.tab_ly);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
    }

    @Override
    public void initListener() {
    }

    @Override
    public void setViewsValue() {
        initTabLayoutData();

        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onClickDispatch(View v) {

    }

    private void initTabLayoutData() {
        for (String title : mTitles) {
            mTabLayout.addTab(mTabLayout.newTab().setText(title));
        }
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> mFragmentList = new ArrayList<>();

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
            initFragments();
        }

        private void initFragments() {
            mFragmentList.add(BaseFragment.getInstance(DefFragment.class));
            mFragmentList.add(BaseFragment.getInstance(DefListFragment.class));
            mFragmentList.add(BaseFragment.getInstance(DefRecyclerViewFragment.class));
        }


        @Override
        public Fragment getItem(int position) {
            Fragment fragment = mFragmentList.get(position);
            return fragment;
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }
    }

}
