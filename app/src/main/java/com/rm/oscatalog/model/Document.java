package com.rm.oscatalog.model;

import android.os.Parcel;

public class Document extends Content {

    private final String ext;
    private final long size;

    public Document(Parcel in) {
        super(in);
        this.ext = in.readString();
        this.size = in.readLong();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getLink() {
        return this.link;
    }

    public String getExt() {
        return this.ext;
    }

    public long getSize() {
        return this.size;
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
        dest.writeString(TYPE_DOC);
        super.writeToParcel(dest, flags);
        dest.writeString(ext);
        dest.writeLong(size);
    }
}
