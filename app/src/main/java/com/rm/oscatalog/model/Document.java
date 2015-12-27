package com.rm.oscatalog.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Document implements Parcelable {

    public final String ext;
    public final String readableName;
    public final String realName;
    public final String link;
    public final long size;

    public Document(String extension, String name, String realName, String link, long size) {
        this.ext = extension;
        this.readableName = name;
        this.realName = realName;
        this.link = link;
        this.size = size;
    }

    protected Document(Parcel in) {
        ext = in.readString();
        readableName = in.readString();
        realName = in.readString();
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
        dest.writeString(readableName);
        dest.writeString(realName);
        dest.writeString(link);
        dest.writeLong(size);
    }
}
