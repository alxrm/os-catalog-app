package com.rm.oscatalog;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.rm.oscatalog.model.Page;
import com.rm.oscatalog.ui.PageContentFragment;
import com.rm.oscatalog.ui.adapter.CatalogPagerAdapter;
import com.rm.oscatalog.utils.FileUtils;
import com.rm.oscatalog.utils.Pages;
import com.rm.oscatalog.utils.Prefs;

// главное окно приложения
public class MainActivity extends AppCompatActivity {

    private Page[] mPages; // массив страниц
    private View mOnBoarding; // представление с подсказкой

    // метод в котором инициализируется приложение
    @Override // пометка, что метод отнаследован
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // задание представления окна

        // инициализация утил классов
        FileUtils.init(this);
        Prefs.init(this);

        // показать подсказку, если ещё не показывали
        showOnBoardingIfNeeded();

        // инициализация верхней панели
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // получение информации обо всех страницах
        mPages = Pages.get();

        // задание заголовка из первой страницы
        setTitle(mPages[0].title);

        // инициализация слайдера и табов для переключения страниц слайдера
        ViewPager sectionPager = (ViewPager) findViewById(R.id.container);
        TabLayout sectionTabs = (TabLayout) findViewById(R.id.tab_layout);

        // инициализация класса для смены фрагментов в слайдере
        CatalogPagerAdapter pagerAdapter = new CatalogPagerAdapter(getFragmentManager());

        // заполнение слайдера страницами
        fillPagerAdapter(pagerAdapter);

        // привязка слайдера к страницам
        sectionPager.setAdapter(pagerAdapter);

        // загрузка всех четырёх страниц сразу
        sectionPager.setOffscreenPageLimit(4);

        // добавление слушателя смены страницы
        sectionPager.addOnPageChangeListener(getTitleChangeListener());

        // подключение верхних табов к слайдеру
        sectionTabs.setupWithViewPager(sectionPager);

        // отрисовка иконок на табах
        fillTabIcons(sectionTabs);
    }

    /* метод для отрисовки подсказки в случае первого захода в приложение */
    private void showOnBoardingIfNeeded() {
        // проверка настроек, если подказка уже показывалась, условие не выполнится
        if (!Prefs.get().getBoolean(Prefs.KEY_IS_HINT_SHOWN, false)) {

            // инициализация представления подсказки
            Button proceed = (Button) findViewById(R.id.onboarding_proceed);
            mOnBoarding = findViewById(R.id.onboarding);
            mOnBoarding.setVisibility(View.VISIBLE);

            // на последних версиях Android нужно следить за цветом статусбара
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(Color.BLACK); // красим статусбар в чёрный
            }

            // привязка слушателя нажатия к кнопке
            proceed.setOnClickListener(new View.OnClickListener() {

                @Override // пометка, что метод отнаследован
                public void onClick(View v) {
                    mOnBoarding.setVisibility(View.GONE); // убирается подсказка
                    Prefs.put(Prefs.KEY_IS_HINT_SHOWN, true); // сохранение настроек

                    // на последних версиях Android нужно следить за цветом статусбара
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                        // получаем цвет из ресурсов
                        int statusBarColor = getResources().getColor(R.color.colorPrimaryDark);

                        // красим статусбар в обычный цвет
                        getWindow().setStatusBarColor(statusBarColor);
                    }
                }
            });
        }
    }

    /* метод для создания фрагментов для каждой из страниц */
    private void fillPagerAdapter(CatalogPagerAdapter pagerAdapter) {

        // пробегаемся по массиву страниц и создаём новый фрагмент, передавая туда нужные данные
        for (Page page : mPages)
            pagerAdapter.addPage(PageContentFragment.newInstance(page.data, page.contentType));
    }

    /* метод для отрисовки иконок в табах */
    private void fillTabIcons(TabLayout tabs) {
        TabLayout.Tab tab;
        for (int i = 0; i < tabs.getTabCount(); i++) {
            tab = tabs.getTabAt(i); // получаем нужный таб
            if (tab != null) tab.setIcon(mPages[i].icon); // если он существует добавляем туда иконку
        }
    }

    /* метод получения слушателя смены страницы */
    private ViewPager.OnPageChangeListener getTitleChangeListener() {
        return new ViewPager.SimpleOnPageChangeListener() {

            @Override // пометка, что метод отнаследован
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                // в случае смены страницы, мы меняем заголовок в верхней панели
                setTitle(mPages[position].title);
            }
        };
    }
}
