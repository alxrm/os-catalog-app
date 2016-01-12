package com.rm.oscatalog.model;

import java.util.ArrayList;

public class Page {

    public final int icon;
    public final String title;
    public final ArrayList<Content> data;
    public final String contentType;

    public Page(int icon, String title, ArrayList<Content> data, String contentType) {
        this.icon = icon;
        this.title = title;
        this.data = data;
        this.contentType = contentType;
    }
}
