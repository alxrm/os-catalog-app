package com.rm.oscatalog.ui;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rm.oscatalog.R;
import com.rm.oscatalog.model.Content;
import com.rm.oscatalog.model.Document;
import com.rm.oscatalog.model.Video;
import com.rm.oscatalog.ui.adapter.ContentAdapter;
import com.rm.oscatalog.ui.adapter.OnItemClickListener;
import com.rm.oscatalog.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;

import static android.app.DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED;
import static android.content.Context.DOWNLOAD_SERVICE;

/* представление страницы - фрагмент */
public class PageContentFragment extends BaseFragment {

    // константы с ключами для параметров, передаваемых при создании фрагмента
    private static final String KEY_DATA_SET = "dataSet";
    private static final String KEY_CONTENT_TYPE = "contentType";

    private ArrayList<Content> mPageContent; // массив данных, которые должны быть отрисованы на странице
    private ContentAdapter mContentAdapter; // класс, осуществляющий привязку данных к представлению
    private String mContentType; // тип данных, отображаемых на этой странице

    private DownloadManager mDownloadManager; // сервис для скачивания файлов через сеть
    private long mCurrentDownloadId; // число с идентификационным номером текущего скачивания

    // фильтр намерений для приёмника сигналов,
    // вызываемых при скачивании файлов с помощью сервиса (DownloadManager)
    // он нужен для того, чтобы фильтровать сигналы и получать только нужные нам
    private IntentFilter mDownloaderFilter;

    private BroadcastReceiver mDownloadReceiver = new BroadcastReceiver() {

        // метод, который вызывается при сигналах, которые должен получать приёмник
        @Override // пометка, что метод отнаследован
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction(); // получаем тип сигнала

            // проверка, соответствует ли тип сигнала тому, который нам нужен (окончание загрузки)
            if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)) {

                // получаем ID загрузки
                long downloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, 0);

                // если мы получили сигнал не о последней загрузке, то ничего не делаем
                if (downloadId != mCurrentDownloadId) return;

                // создаём запрос в базу данных Загрузчика
                DownloadManager.Query query = new DownloadManager.Query();

                // задаём в запросе по какому ID загрузки искать
                query.setFilterById(downloadId);

                // получаем виртуальную таблицу с результатом
                Cursor c = mDownloadManager.query(query);

                // проверка, на то, что результат не пустой
                if (c.moveToFirst()) {

                    // получаем статус загрузки по ID
                    int status = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));

                    // проверка на то, что статус загрузки – успешно завершена
                    if (DownloadManager.STATUS_SUCCESSFUL == status) {

                        // получаем путь, куда сохранился наш файл
                        String uriString =
                                c.getString(c.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));

                        // открываем по этому пути файл
                        showContent(Uri.parse(uriString), false);
                    }

                    // закрываем виртуальную таблицу с результатом
                    c.close();
                }

            }
        }
    };

    // по правилам у фрагмента должен быть пустой конструктор с модификатором public,
    // потому что внутренние механизмы обращаются к фрагменту именно по нему
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

        // инициализация фильтра намерений с передачей константы
        // с названием сигнала, по которому он будет фильтровать
        mDownloaderFilter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);

        // инициализация сервиса для скачивания файлов
        mDownloadManager = (DownloadManager) getActivity().getSystemService(DOWNLOAD_SERVICE);

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

    @Override // пометка, что метод отнаследован
    public void onStart() {
        super.onStart();

        // если приложение начинает работу, включаем приёмник сигналов,
        // чтобы после скачивания автоматически открывался нужный файл
        if (getActivity() != null) {
            getActivity().registerReceiver(mDownloadReceiver, mDownloaderFilter);
        }
    }

    @Override // пометка, что метод отнаследован
    public void onStop() {
        super.onStop();

        // если приложение останавливается, отключаем приёмник сигналов,
        // чтобы после закрытия приложения не открылся скачанный файл
        if (getActivity() != null) {
            getActivity().unregisterReceiver(mDownloadReceiver);
        }
    }

    /* метод получения слушателя нажатия */
    private OnItemClickListener getItemClickListener() {
        return new OnItemClickListener() {

            /* метод обработки события нажатия, переданного от элемента списка */
            @Override // пометка, что метод отнаследован
            public void onItemClick(View v, int position) {

                // получения данных из массива
                Content item = mPageContent.get(position);

                // проверка типа, если это видео, запускаем его проигрываться
                if (item instanceof Video) {
                    showContent(Uri.parse(item.getLink()), true);
                }

                // если документ, получаем файл
                if (item instanceof Document) {

                    // приводим тип, чтобы избежать лишней нагрузки (распаковка может быть дорогой)
                    Document doc = (Document) item;

                    // создаём объект файла документа
                    File documentFile = FileUtils.getFileForDocument(doc);

                    // если файл документа уже скачан – просто открываем
                    // если не скачан, скачиваем
                    if (documentFile.exists()) showContent(Uri.fromFile(documentFile), false);
                    else downloadFile(doc.getLink(), documentFile);
                }
            }
        };
    }

    // метод для открытия контента, принимающий URI с данными,
    // которые нужно показать и условие, является ли этот URI ссылкой
    private void showContent(Uri data, boolean isLink) {
        Intent viewer = new Intent(Intent.ACTION_VIEW); // создаём намерение

        // если URI не является ссылкой, указываем MIME тип,
        // чтобы система знала через какое приложение открывать файл
        if (!isLink) {
            viewer.setDataAndType(data, FileUtils.getMimeType(data)); // ставим путь и тип
        } else {
            viewer.setData(data); // ставим только путь(ссылку)
        }

        if (viewer.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(viewer); // запускаем намерение
        }
    }

    // метод для запуска скачивания документа, принимает в себя
    // ссылку на скачивание и объект файла,
    // в который будет записан результат скачивания
    private void downloadFile(String link, File dest) {

        // создаём запрос на скачивание по ссылке
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(link));

        // запрет скачивания через роуминг
        request.setAllowedOverRoaming(false);

        // выставляем название уведомления(имя файла)
        request.setTitle(dest.getName());

        // показываем уведомление на всех стадия скачивания
        request.setNotificationVisibility(VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

        // выставляем URI с путем, куда будет скачан файл
        request.setDestinationUri(Uri.fromFile(dest));

        // добавляем запрос в очередь и сохраняем последний ID
        mCurrentDownloadId = mDownloadManager.enqueue(request);
    }
}
