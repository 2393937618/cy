package com.example.lxc.cy.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class search_result_FragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;
    private List<String> title;
    private Context context;

    public search_result_FragmentAdapter(FragmentManager fm, List<Fragment> list, List<String> title) {
        super(fm);
        this.list = list;
        this.title = title;

    }


    @Override
    public Fragment getItem(int i) {
        return list.get(i);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }
}
