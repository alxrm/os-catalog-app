package com.rm.oscatalog.ui.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.rm.oscatalog.ui.adapter.OnItemClickListener;

// базовый класс, хранящий в себе представление и выполняющий связывание его с данными
public abstract class BaseViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {

    private View mRoot; // представление элемента списка
    private OnItemClickListener mClickListener; // слушатель нажатия

    // конструктор, принимающий представление
    public BaseViewHolder(View itemView) {
        super(itemView);

        mRoot = itemView; // сохранение представления элемента списка
        createView(itemView); // ициализация внутрениих представлений элемента списка
        itemView.setOnClickListener(this); // привязка слушателя нажатия к представлению
    }

    // реакция на нажатие на представление
    @Override // пометка, что метод отнаследован
    public void onClick(View v) {
        if (mClickListener != null)
            // передача данных о событии нажатия выше, в случае если слушатель задан
            this.mClickListener.onItemClick(v, getAdapterPosition());
    }

    // привязка заданного слушателя нажатия к слушателю элемента списка
    public void setOnItemClickListener(OnItemClickListener listener) {
        mClickListener = listener;
    }

    // обёртка над неудобной конструкцией,
    // метод для поиска внутренних представлений у элемента списка
    protected View findViewById(int id) {
        return mRoot.findViewById(id);
    }

    // метод инициализации внутрениих представлений элемента списка
    // инициализация представлений должна быть реализована у дочерних классов
    public abstract void createView(View root);

    // привязка данных должна быть реализована у дочерних классов
    public abstract <T> void bind(T model);
}