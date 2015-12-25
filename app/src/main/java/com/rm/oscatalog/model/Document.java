package com.rm.oscatalog.model;

import android.os.Parcel;

public class Document implements PageData {

    public final String Extension;
    public final String Name;
    public final String Link;
    public final float Size;

    public Document(String extension, String name, String link, float size) {
        this.Extension = extension;
        this.Name = name;
        this.Link = link;
        this.Size = size;
    }

    protected Document(Parcel in) {
        this.Extension = in.readString();
        this.Name = in.readString();
        this.Link = in.readString();
        this.Size = in.readFloat();
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
        dest.writeString(Extension);
        dest.writeString(Name);
        dest.writeString(Link);
        dest.writeFloat(Size);
    }

    @Override
    public void inflate(String json) {

    }
}
