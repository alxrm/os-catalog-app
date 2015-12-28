package com.rm.oscatalog.model;

import android.os.Parcel;

public class Video implements PageData {

    public static final String EXTRA = "YouTube";

    public final String name;
    public final String youtubeLink;
    public final int duration;

    public Video(String name, String youTubeLink, int duration) {
        this.name = name;
        this.youtubeLink = youTubeLink;
        this.duration = duration;
    }

    protected Video(Parcel in) {
        name = in.readString();
        youtubeLink = in.readString();
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
        dest.writeString(youtubeLink);
        dest.writeInt(duration);
    }
}
