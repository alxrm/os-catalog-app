package com.rm.oscatalog;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.rm.oscatalog.ui.CollectionFragment;
import com.rm.oscatalog.ui.adapter.CatalogPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private static final String TITLE_LECTURES = "Лекции";
    private static final String TITLE_LABS = "Лабораторные работы";
    private static final String TITLE_BOOKS = "Книги";
    private static final String TITLE_MOVIES = "Видео";

    private static final String[] TITLES = {
            TITLE_LECTURES, TITLE_LABS,
            TITLE_BOOKS, TITLE_MOVIES
    };

    private static final int[] TAB_ICONS = {
            R.drawable.lectures,
            R.drawable.labs,
            R.drawable.books,
            R.drawable.movies
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(TITLE_LECTURES);

        ViewPager sectionPager = (ViewPager) findViewById(R.id.container);
        TabLayout sectionTabs = (TabLayout) findViewById(R.id.tab_layout);

        CatalogPagerAdapter pagerAdapter = new CatalogPagerAdapter(getFragmentManager());
        pagerAdapter.addPage(new CollectionFragment());
        pagerAdapter.addPage(new CollectionFragment());
        pagerAdapter.addPage(new CollectionFragment());
        pagerAdapter.addPage(new CollectionFragment());

        sectionPager.setAdapter(pagerAdapter);
        sectionPager.addOnPageChangeListener(getTitleChangeListener());

        sectionTabs.setupWithViewPager(sectionPager);
        addTabIcons(sectionTabs);
    }

    @SuppressWarnings("ConstantConditions")
    private void addTabIcons(TabLayout tabs) {
        for (int i = 0; i < tabs.getTabCount(); i++) {
            tabs.getTabAt(i).setIcon(TAB_ICONS[i]);
        }
    }

    private ViewPager.OnPageChangeListener getTitleChangeListener() {
        return new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setTitle(TITLES[position]);
            }
        };
    }
}
