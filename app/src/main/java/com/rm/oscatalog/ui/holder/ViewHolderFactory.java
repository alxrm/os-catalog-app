package com.rm.oscatalog.ui.holder;

import android.view.View;

import com.rm.oscatalog.model.Document;
import com.rm.oscatalog.model.Video;

public final class ViewHolderFactory {
    public static BaseViewHolder build(Class<?> modelType, View itemView) {
        if (modelType.isAssignableFrom(Document.class)) return new DocumentViewHolder(itemView);
        if (modelType.isAssignableFrom(Video.class)) return new VideoViewHolder(itemView);
        throw new IllegalArgumentException("Unregistered view holder type");
    }
}
