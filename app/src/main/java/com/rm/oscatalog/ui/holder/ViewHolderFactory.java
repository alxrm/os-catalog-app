package com.rm.oscatalog.ui.holder;

import android.view.View;

import com.rm.oscatalog.model.Content;

public final class ViewHolderFactory {

    public static BaseViewHolder build(String typeCode, View itemView) {
        switch (typeCode) {
            case Content.TYPE_VIDEO: return new VideoViewHolder(itemView);
            case Content.TYPE_DOC: return new DocumentViewHolder(itemView);
            default: throw new IllegalArgumentException("Cannot find content contentType");
        }
    }
}
