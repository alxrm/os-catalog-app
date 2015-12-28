package com.rm.oscatalog.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rm.oscatalog.R;
import com.rm.oscatalog.model.Document;
import com.rm.oscatalog.model.PageData;
import com.rm.oscatalog.model.Video;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

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

    public static Class<? extends PageData> getPageContentTypeByKey(String key) {
        return key.equals(MOVIES) ? Video.class : Document.class;
    }

    public static ArrayList<PageData> getPageData(String key) {

        ArrayList<PageData> result = new ArrayList<>();
        Type t = key.equals(MOVIES) ?
                new TypeToken<ArrayList<Video>>() {}.getType() :
                new TypeToken<ArrayList<Document>>() {}.getType();

        try {
            result = sGson.fromJson(AssetsUtil.readPageFromFile(key), t);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String getPageTitle(String key) {
        switch (key) {
            case LECTURES: return TITLE_LECTURES;
            case LABS: return TITLE_LABS;
            case BOOKS: return TITLE_BOOKS;
            case MOVIES: return TITLE_MOVIES;
            default: return null;
        }
    }

    public static int getPageIcon(String key) {
        switch (key) {
            case LECTURES: return R.drawable.lectures;
            case LABS: return R.drawable.labs;
            case BOOKS: return R.drawable.books;
            case MOVIES: return R.drawable.movies;
            default: return -1;
        }
    }
}
