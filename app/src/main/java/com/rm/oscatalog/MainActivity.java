package com.rm.oscatalog;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.rm.oscatalog.model.Page;
import com.rm.oscatalog.ui.PageContentFragment;
import com.rm.oscatalog.ui.adapter.CatalogPagerAdapter;
import com.rm.oscatalog.utils.AssetsUtil;
import com.rm.oscatalog.utils.Pages;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Page> mPages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(Pages.TITLE_LECTURES);

        AssetsUtil.init(this);
        setupPages();

        ViewPager sectionPager = (ViewPager) findViewById(R.id.container);
        TabLayout sectionTabs = (TabLayout) findViewById(R.id.tab_layout);

        CatalogPagerAdapter pagerAdapter = new CatalogPagerAdapter(getFragmentManager());
        fillPagerAdapter(pagerAdapter);

        sectionPager.setAdapter(pagerAdapter);
        sectionPager.setOffscreenPageLimit(4);
        sectionPager.addOnPageChangeListener(getTitleChangeListener());

        sectionTabs.setupWithViewPager(sectionPager);
        fillTabIcons(sectionTabs);
    }

    private void fillPagerAdapter(CatalogPagerAdapter pagerAdapter) {
        for (Page page : mPages)
            pagerAdapter.addPage(PageContentFragment.newInstance(page.data, page.key));
    }

    private void fillTabIcons(TabLayout tabs) {
        TabLayout.Tab tab;
        for (int i = 0; i < tabs.getTabCount(); i++) {
            tab = tabs.getTabAt(i);
            if (tab != null) tab.setIcon(mPages.get(i).icon);
        }
    }

    private ViewPager.OnPageChangeListener getTitleChangeListener() {
        return new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setTitle(mPages.get(position).title);
            }
        };
    }

    private void setupPages() {
        mPages.add(Page.create(Pages.LECTURES));
        mPages.add(Page.create(Pages.LABS));
        mPages.add(Page.create(Pages.BOOKS));
        mPages.add(Page.create(Pages.MOVIES));
    }
}
