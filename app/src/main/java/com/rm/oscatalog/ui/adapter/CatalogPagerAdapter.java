package com.rm.oscatalog.ui.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import com.rm.oscatalog.ui.CollectionFragment;

import java.util.ArrayList;

public class CatalogPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<CollectionFragment> mFragments;

    public CatalogPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragments = new ArrayList<>();
    }

    public void addPage(CollectionFragment fragment) {
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
