package com.rm.oscatalog.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rm.oscatalog.R;
import com.rm.oscatalog.model.Content;
import com.rm.oscatalog.model.Document;
import com.rm.oscatalog.model.Page;
import com.rm.oscatalog.model.Video;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import static com.rm.oscatalog.model.Content.TYPE_DOC;
import static com.rm.oscatalog.model.Content.TYPE_VIDEO;

public class Pages {

    public static final String LECTURES = "lectures";
    public static final String LABS = "labs";
    public static final String BOOKS = "books";
    public static final String MOVIES = "movies";

    public static final String TITLE_LECTURES = "Лекции";
    public static final String TITLE_LABS = "Лабораторные работы";
    public static final String TITLE_BOOKS = "Книги";
    public static final String TITLE_MOVIES = "Видео";

    private static Gson sGson = new Gson();

    public static ArrayList<Page> get() {
        ArrayList<Page> pages = new ArrayList<>();

        pages.add(Pages.make(Pages.LECTURES));
        pages.add(Pages.make(Pages.LABS));
        pages.add(Pages.make(Pages.BOOKS));
        pages.add(Pages.make(Pages.MOVIES));

        return pages;
    }

    private static ArrayList<Content> getPageData(String name) {
        ArrayList<Content> result = new ArrayList<>();
        Type type = name.equals(MOVIES) ?
                new TypeToken<ArrayList<Video>>() {}.getType() :
                new TypeToken<ArrayList<Document>>() {}.getType();

        try {
            result = sGson.fromJson(AssetsUtil.readPageFromFile(name), type);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private static Page make(String key) {
        switch (key) {
            case LECTURES:
                return new Page(R.drawable.lectures, TITLE_LECTURES, getPageData(key), TYPE_DOC);
            case LABS:
                return new Page(R.drawable.labs, TITLE_LABS, getPageData(key), TYPE_DOC);
            case BOOKS:
                return new Page(R.drawable.books, TITLE_BOOKS, getPageData(key), TYPE_DOC);
            case MOVIES:
                return new Page(R.drawable.movies, TITLE_MOVIES, getPageData(key), TYPE_VIDEO);
            default:
                throw new IllegalArgumentException("Cannot make page from given key");
        }
    }
}
