package com.rm.oscatalog.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rm.oscatalog.R;
import com.rm.oscatalog.ui.holder.BaseViewHolder;
import com.rm.oscatalog.ui.holder.ViewHolderFactory;

import java.util.ArrayList;

// класс, создающий представления контента страницы (видео, документы) в списке
public class ContentAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private final ArrayList<?> mDataSet; // массив данных для отрисовки
    private final String mContentDataType; // ключ типа данных (одна из констант)

    private OnItemClickListener mListener; // слушатель нажатий

    // конструктор, в котором сохраняется массив данных и их тип
    public ContentAdapter(ArrayList<?> dataSet, String contentDataType) {
        this.mDataSet = dataSet;
        this.mContentDataType = contentDataType;
    }

    // метод, в котором создаётся объект для хранения представления элемента списка
    @Override // пометка, что метод отнаследован
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // создание представления элемента списка
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_page_content, parent, false);

        // получения экземпляра класса, хранящего нужное предсталение, по типу данных
        BaseViewHolder holder = ViewHolderFactory.build(mContentDataType, itemView);

        // задаём слушателя нажатий, если он есть
        if (mListener != null) holder.setOnItemClickListener(mListener);

        // возвращаем объект с представлением
        return holder;
    }

    // метод для привязки данных элемента массива к представлению элемента списка
    @Override // пометка, что метод отнаследован
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.bind(mDataSet.get(position));
    }

    // получение количества элементов списка (нужно классу родителю)
    @Override // пометка, что метод отнаследован
    public int getItemCount() {
        return mDataSet.size();
    }

    // метод для задания слушателя нажатий, передаваемого ниже
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }
}