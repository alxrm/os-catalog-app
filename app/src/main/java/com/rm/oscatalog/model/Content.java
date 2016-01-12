package com.rm.oscatalog.model;

import android.os.Parcel;
import android.os.Parcelable;

public abstract class Content implements Parcelable {

    public static final String  TYPE_VIDEO = "VIDEO";
    public static final String TYPE_DOC = "DOC";

    protected final String name;
    protected final String link;

    public Content(Parcel in) {
        name = in.readString();
        link = in.readString();
    }

    public abstract String getName();

    public abstract String getLink();

    public static final Creator<Content> CREATOR = new Creator<Content>() {
        @Override
        public Content createFromParcel(Parcel in) {
            return Content.findContentType(in);
        }

        @Override
        public Content[] newArray(int size) {
            return new Content[size];
        }
    };

    private static Content findContentType(Parcel in) {
        switch (in.readString()) {
            case TYPE_VIDEO: return new Video(in);
            case TYPE_DOC: return new Document(in);
            default: throw new IllegalArgumentException("Cannot find content contentType");
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(link);
    }
}
