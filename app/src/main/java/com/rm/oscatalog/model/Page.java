package com.rm.oscatalog.model;

import com.rm.oscatalog.utils.Pages;

import java.util.ArrayList;

public class Page {

    public final int icon;
    public final String title;
    public final ArrayList<PageData> data;
    public final String key;

    public static Page create(String pageKey) {
        return new Page(pageKey);
    }

    private Page(String key) {
        this.key = key;
        this.icon = Pages.getPageIcon(key);
        this.title = Pages.getPageTitle(key);
        this.data = Pages.getPageData(key);
    }
}
