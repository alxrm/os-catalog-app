package com.rm.oscatalog.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rm.oscatalog.R;
import com.rm.oscatalog.model.Content;
import com.rm.oscatalog.model.Document;
import com.rm.oscatalog.model.Page;
import com.rm.oscatalog.model.Video;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static com.rm.oscatalog.model.Content.TYPE_DOC;
import static com.rm.oscatalog.model.Content.TYPE_VIDEO;

// утил класс для работы со страницами
public class Pages {

    // константы с ключами страниц
    // (они же являются названиями файлов с описанием страниц в JSON)
    public static final String LECTURES = "lectures"; // лекции
    public static final String LABS = "labs"; // лабораторные
    public static final String BOOKS = "books"; // полезные материалы
    public static final String MOVIES = "movies"; // видео

    // константы с названиями страниц
    public static final String TITLE_LECTURES = "Лекции";
    public static final String TITLE_LABS = "Лабораторные работы";
    public static final String TITLE_BOOKS = "Полезные материалы";
    public static final String TITLE_MOVIES = "Видео";

    // специальный инструмент для чтения и записи JSON
    private static Gson sGson = new Gson();

    // получение массива объектов с описанием всех страниц
    public static Page[] get() {
        return new Page[] {
                make(LECTURES),
                make(LABS),
                make(BOOKS),
                make(MOVIES)
        };
    }

    // получение данных, содержащихся на странице по имени файла
    private static ArrayList<Content> getPageData(String name) {
        // динамический массив, куда будет записан результат
        ArrayList<Content> result = new ArrayList<>();

        /*
        рефлексивное получение типа данных,
        рефлексия здесь для того, чтобы автоматически
        заполнить одноимённые поля, которые будут в JSON файле
        */
        Type type = name.equals(MOVIES) ?
                new TypeToken<ArrayList<Video>>() {}.getType() : /* возвращаем тип видео данных */
                new TypeToken<ArrayList<Document>>() {}.getType(); /* возвращаем тип документов */

        try {
            /* запись объектов данных страницы в динамический массив */
            result = sGson.fromJson(FileUtils.readJsonFromFile(name), type);
        } catch (Throwable e) {
            /* обработка ошибки */
            e.printStackTrace();
        }

        // возвращение результата
        return result;
    }

    // создание страницы в зависимости от ключа
    private static Page make(String key) {
        switch (key) {
            case LECTURES:
                /* создание страницы с лекциями */
                return new Page(R.drawable.tab_lectures, TITLE_LECTURES, getPageData(key), TYPE_DOC);
            case LABS:
                /* создание страницы с лабораторными */
                return new Page(R.drawable.tab_labs, TITLE_LABS, getPageData(key), TYPE_DOC);
            case BOOKS:
                /* создание страницы с полезными материалами */
                return new Page(R.drawable.tab_books, TITLE_BOOKS, getPageData(key), TYPE_DOC);
            case MOVIES:
                /* создание страницы с видео */
                return new Page(R.drawable.tab_movies, TITLE_MOVIES, getPageData(key), TYPE_VIDEO);
            default:
                // вызывается ошибка в случае, мы не обрабатываем такую страницу
                throw new IllegalArgumentException("Cannot make page from given key");
        }
    }
}
