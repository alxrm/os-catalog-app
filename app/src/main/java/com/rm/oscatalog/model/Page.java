package com.rm.oscatalog.model;

import android.support.annotation.NonNull;

import java.util.ArrayList;

public class Page<T extends PageData> {

    public final int Icon;
    public final String Title;
    public final ArrayList<T> Data;

    public static Page<? extends PageData> create(int icon,
                                                  @NonNull String title,
                                                  @NonNull ArrayList<? extends PageData> data) {
        return new Page<>(icon, title, data);
    }

    private Page(int icon, String title, ArrayList<T> data) {
        this.Icon = icon;
        this.Title = title;
        this.Data = data;
    }
}
