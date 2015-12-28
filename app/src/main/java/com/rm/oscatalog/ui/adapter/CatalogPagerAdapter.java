package com.rm.oscatalog.ui.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import com.rm.oscatalog.ui.PageContentFragment;

import java.util.ArrayList;

public class CatalogPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<PageContentFragment> mFragments;

    public CatalogPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragments = new ArrayList<>();
    }

    public void addPage(PageContentFragment fragment) {
        mFragments.add(fragment);
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
