package com.rm.oscatalog.model;

import android.os.Parcel;

public class Video implements PageData {

    public static final String EXTRA = "Вконтакте";

    public final String name;
    public final String link;
    public final String preview;
    public final int duration;

    protected Video(Parcel in) {
        name = in.readString();
        link = in.readString();
        preview = in.readString();
        duration = in.readInt();
    }

    public static final Creator<Video> CREATOR = new Creator<Video>() {
        @Override
        public Video createFromParcel(Parcel in) {
            return new Video(in);
        }

        @Override
        public Video[] newArray(int size) {
            return new Video[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(link);
        dest.writeString(preview);
        dest.writeInt(duration);
    }
}
