package com.rm.oscatalog.ui;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rm.oscatalog.R;
import com.rm.oscatalog.model.Content;
import com.rm.oscatalog.ui.adapter.ContentAdapter;
import com.rm.oscatalog.ui.adapter.OnItemClickListener;

import java.util.ArrayList;

import static com.rm.oscatalog.model.Content.TYPE_DOC;
import static com.rm.oscatalog.model.Content.TYPE_VIDEO;

/* представление страницы - фрагмент */
public class PageContentFragment extends BaseFragment {

    // константы с ключами для параметров, передаваемых при создании фрагмента
    private static final String KEY_DATA_SET = "dataSet";
    private static final String KEY_CONTENT_TYPE = "contentType";

    // константы с названиями пакетов приложений
    private static final String PACKAGE_VK = "com.vkontakte.android";
    private static final String PACKAGE_GOOGLE_CHROME = "com.android.chrome";

    private ArrayList<Content> mPageContent; // массив данных, которые должны быть отрисованы на странице
    private ContentAdapter mContentAdapter; // класс, осуществляющий привязку данных к представлению
    private String mContentType; // тип данных, отображаемых на этой странице

    // по правилам у фрагмента должен быть пустой конструктор
    public PageContentFragment() {
        // Required empty public constructor
    }

    // метод создания нового фрагмента страницы,
    // получающий на вход массив данных и тип отображаемых данных
    public static PageContentFragment newInstance(ArrayList<Content> dataSet, String contentKey) {
        Bundle args = new Bundle(); // специальный класс для упаковки посылаемых типов данных
        args.putParcelableArrayList(KEY_DATA_SET, dataSet); // упаковываем массив данных
        args.putString(KEY_CONTENT_TYPE, contentKey); // упаковываем тип

        // создание экземпляра фрагмента
        PageContentFragment fragment = new PageContentFragment();
        fragment.setArguments(args); // передача упакованных параметров
        return fragment;
    }

    /* метод вызываемый при создании фрагмента */
    @Override // пометка, что метод отнаследован
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // распаковка параметров
        mPageContent = getArguments().getParcelableArrayList(KEY_DATA_SET); // массив
        mContentType = getArguments().getString(KEY_CONTENT_TYPE); // тип
    }

    /* метод вызываемый при создании корневого представления фрагмента */
    @Override // пометка, что метод отнаследован
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /* создание корневого представления фрагмента */
        return inflater.inflate(R.layout.fragment_collection, container, false);
    }

    /* метод вызываемый после создания корневого представления фрагмента */
    @Override // пометка, что метод отнаследован
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // инициализация представления списка
        RecyclerView content = (RecyclerView) findViewById(R.id.page_content);
        content.setLayoutManager(new LinearLayoutManager(getActivity()));

        // инициализация класса привязки в случае, если он не был инициализирован
        if (mContentAdapter == null)
            mContentAdapter = new ContentAdapter(mPageContent, mContentType);

        // подключение слушателя нажатий
        mContentAdapter.setOnItemClickListener(getItemClickListener());

        // подключения класса привязки к списку
        content.setAdapter(mContentAdapter);
    }

    /* метод получения слушателя нажатия */
    private OnItemClickListener getItemClickListener() {
        return new OnItemClickListener() {

            /* метод обработки события нажатия, переданного от элемента списка */
            @Override // пометка, что метод отнаследован
            public void onItemClick(View v, int position) {

                // получения данных из массива
                Content item = mPageContent.get(position);

                // получение доступа к информации об установленных приложениях
                PackageManager manager = getActivity().getPackageManager();

                // получения нужного намерения для открытия ссылки по типу данных
                Intent viewIntent = getIntentForType(manager, mContentType);

                // задаём в качестве данных интенту ссылку
                viewIntent.setData(Uri.parse(item.getLink()));

                // выставляем флаг открытия нужного приложения в новом окне
                viewIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                // запуск намерения
                if (viewIntent.resolveActivity(manager) != null)
                    startActivity(viewIntent);
            }
        };
    }

    /* метод получения намерения по ключу типа данных (одна из констант) */
    private Intent getIntentForType(PackageManager manager, String type) {

        // проверка наличия браузера
        boolean hasChrome = isInstalledPackage(manager, PACKAGE_GOOGLE_CHROME);

        // проверка наличия клиента VK
        boolean hasVk = isInstalledPackage(manager, PACKAGE_VK);

        // инициализация намерения
        Intent viewIntent = new Intent(Intent.ACTION_VIEW);

        // настройка фрагмента для открытия документа
        if (type.equals(TYPE_DOC) && hasChrome)
            viewIntent.setPackage(PACKAGE_GOOGLE_CHROME);

        // настройка фрагмента для открытия видео
        if (type.equals(TYPE_VIDEO)) {
            if (hasVk) viewIntent.setPackage(PACKAGE_VK);
            else if (hasChrome) viewIntent.setPackage(PACKAGE_GOOGLE_CHROME);
        }

        return viewIntent;
    }

    // метод, осуществляющий проверку, установлено ли приложение,
    // которое должно обработать намерение
    private boolean isInstalledPackage(PackageManager manager, String packageName) {
        try {
            PackageInfo info = manager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return info != null; // если ошибки нет и данные о приложении не пустые, ответ положителен
        } catch (PackageManager.NameNotFoundException e) {
            return false; // в случае ошибки при поиске имени возвращается негативный ответ
        }
    }
}
