package com.rm.oscatalog.model;

import android.os.Parcelable;

import java.util.Collection;

public class Page<T extends Parcelable> {

    public final int Icon;
    public final String Title;
    public final Collection<T> Data;

    public Page(int icon, String title, Collection<T> data) {
        this.Icon = icon;
        this.Title = title;
        this.Data = data;
    }
}
