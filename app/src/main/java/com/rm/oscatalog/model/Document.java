package com.rm.oscatalog.model;

import android.os.Parcel;

public class Document implements PageData {

    public final String ext;
    public final String name;
    public final String link;
    public final long size;

    protected Document(Parcel in) {
        ext = in.readString();
        name = in.readString();
        link = in.readString();
        size = in.readLong();
    }

    public static final Creator<Document> CREATOR = new Creator<Document>() {
        @Override
        public Document createFromParcel(Parcel in) {
            return new Document(in);
        }

        @Override
        public Document[] newArray(int size) {
            return new Document[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ext);
        dest.writeString(name);
        dest.writeString(link);
        dest.writeLong(size);
    }
}
