package com.rm.oscatalog.ui.holder;

import android.view.View;

import com.rm.oscatalog.model.Content;

// фабрика для получения контейнера представления по типу данных
public final class ViewHolderFactory {

    // получения экземпляра класса, хранящего нужное предсталение, по типу данных
    public static BaseViewHolder build(String typeCode, View itemView) {
        switch (typeCode) {
            case Content.TYPE_VIDEO: return new VideoViewHolder(itemView);
            case Content.TYPE_DOC: return new DocumentViewHolder(itemView);
            /* если ключа не найдено, вызывается ошибка */
            default: throw new IllegalArgumentException("Cannot find content contentType");
        }
    }
}
