package com.rm.oscatalog;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.rm.oscatalog.model.Page;
import com.rm.oscatalog.model.PageData;
import com.rm.oscatalog.ui.CollectionFragment;
import com.rm.oscatalog.ui.adapter.CatalogPagerAdapter;
import com.rm.oscatalog.utils.Pages;

import java.util.ArrayList;

import static com.rm.oscatalog.utils.AssetsUtil.getPageDataByKey;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Page<? extends PageData>> mPages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(Pages.TITLE_LECTURES);
        setupPages();

        ViewPager sectionPager = (ViewPager) findViewById(R.id.container);
        TabLayout sectionTabs = (TabLayout) findViewById(R.id.tab_layout);

        CatalogPagerAdapter pagerAdapter = new CatalogPagerAdapter(getFragmentManager());
        fillPagerAdapter(pagerAdapter);

        sectionPager.setAdapter(pagerAdapter);
        sectionPager.addOnPageChangeListener(getTitleChangeListener());

        sectionTabs.setupWithViewPager(sectionPager);
        fillTabIcons(sectionTabs);
    }

    private void fillPagerAdapter(CatalogPagerAdapter pagerAdapter) {
        for (Page<? extends PageData> page : mPages)
            pagerAdapter.addPage(CollectionFragment.newInstance(page.Data));
    }

    private void fillTabIcons(TabLayout tabs) {
        TabLayout.Tab tab;
        for (int i = 0; i < tabs.getTabCount(); i++) {
            tab = tabs.getTabAt(i);
            if (tab != null) tab.setIcon(mPages.get(i).Icon);
        }
    }

    private ViewPager.OnPageChangeListener getTitleChangeListener() {
        return new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setTitle(mPages.get(position).Title);
            }
        };
    }

    private void setupPages() {
        mPages.add(Page.create(R.drawable.lectures, Pages.TITLE_LECTURES,
                getPageDataByKey(this, Pages.LECTURES)));

        mPages.add(Page.create(R.drawable.labs, Pages.TITLE_LABS,
                getPageDataByKey(this, Pages.LABS)));

        mPages.add(Page.create(R.drawable.books, Pages.TITLE_BOOKS,
                getPageDataByKey(this, Pages.BOOKS)));

        mPages.add(Page.create(R.drawable.movies, Pages.TITLE_MOVIES,
                getPageDataByKey(this, Pages.MOVIES)));
    }
}
