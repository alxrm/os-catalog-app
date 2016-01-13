package com.rm.oscatalog.ui.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import com.rm.oscatalog.ui.PageContentFragment;

import java.util.ArrayList;

// класс создающий представления страниц в слайдере
public class CatalogPagerAdapter extends FragmentPagerAdapter {

    // динамический массив с представлениями(фрагментами) страниц
    private ArrayList<PageContentFragment> mFragments;

    /*
    конструктор, инициализирующий массив представлений
    класс FragmentManager нужен для того, чтобы менять фрагменты,
    отображаемые в данный момент на экране
    */
    public CatalogPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragments = new ArrayList<>();
    }

    // метод для добавления представления страницы
    public void addPage(PageContentFragment fragment) {
        mFragments.add(fragment); // добавление
        notifyDataSetChanged(); // уведомление других компонентов о добавлении
    }

    // метод получения нужного фрагмента по индексу из массива
    // (нужен классу родителю)
    @Override // пометка, что метод отнаследован
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    // метод для получения количества страниц (нужен классу родителю)
    @Override // пометка, что метод отнаследован
    public int getCount() {
        return mFragments.size();
    }
}
