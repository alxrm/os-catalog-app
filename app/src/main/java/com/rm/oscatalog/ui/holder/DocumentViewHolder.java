package com.rm.oscatalog.ui.holder;

import android.view.View;
import android.widget.TextView;

import com.rm.oscatalog.R;
import com.rm.oscatalog.model.Document;
import com.rm.oscatalog.utils.FormatUtil;

// класс, хранящий в себе внутренние представления для отрисовки документа
// плюс здесь происходит привязка данных о документе в представлению
public class DocumentViewHolder extends BaseViewHolder {

    private TextView mName; // представление с именем
    private TextView mExt; // расширение
    private TextView mSize; // размер

    // конструктор, принимающий представление
    public DocumentViewHolder(View itemView) {
        super(itemView); // переиспользование логики из родительского конструктора
    }

    // инициализация внутренних представлений элемента списка
    @Override // пометка, что метод отнаследован
    public void createView(View root) {

        // получение представлений из вёрстки
        mName = (TextView) findViewById(R.id.item_name);
        mExt = (TextView) findViewById(R.id.item_ext);
        mSize = (TextView) findViewById(R.id.item_info_extra);
    }

    // привязка данных к представлению
    @Override // пометка, что метод отнаследован
    public <T> void bind(T model) {
        Document document = (Document) model;

        mName.setText(document.getName()); // привязка имени
        mSize.setText(FormatUtil.formatBytes(document.getSize())); // привязка размера

        // делаем видимой строку с расширением(мы её прячем, если элемент это видео)
        mExt.setVisibility(View.VISIBLE);
        mExt.setText(document.getExt()); // привязка расширения
    }
}
