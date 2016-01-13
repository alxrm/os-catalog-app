package com.rm.oscatalog.model;

import java.util.ArrayList;

// класс описывающий страницу
public class Page {

    public final int icon; // иконка страницы
    public final String title; // название страницы
    public final ArrayList<Content> data; // данные, отображаемые на странице
    public final String contentType; // тип данных, отображаемых на странице (одна из констант)

    // конструктор, записывающий все нужные поля
    public Page(int icon, String title, ArrayList<Content> data, String contentType) {
        this.icon = icon;
        this.title = title;
        this.data = data;
        this.contentType = contentType;
    }
}
