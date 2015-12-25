package com.rm.oscatalog.model;

import android.os.Parcelable;

/**
 * Created by alex
 */
public interface PageData extends Parcelable {
    void inflate(String json);
}
