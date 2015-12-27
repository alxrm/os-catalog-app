package com.rm.oscatalog.model;

import android.os.Parcel;

public class Video implements PageData {

    public final String readableName;
    public final String realName;
    public final String downloadLink;
    public final String youtubeLink;
    public final int size;
    public final int duration;

    public Video(String readableName,
                 String realName,
                 String downloadLink,
                 String youTubeLink,
                 int size,
                 int duration) {
        this.readableName = readableName;
        this.realName = realName;
        this.size = size;
        this.downloadLink = downloadLink;
        this.youtubeLink = youTubeLink;
        this.duration = duration;
    }

    protected Video(Parcel in) {
        readableName = in.readString();
        realName = in.readString();
        downloadLink = in.readString();
        youtubeLink = in.readString();
        size = in.readInt();
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
        dest.writeString(readableName);
        dest.writeString(realName);
        dest.writeString(downloadLink);
        dest.writeString(youtubeLink);
        dest.writeInt(size);
        dest.writeInt(duration);
    }
}
