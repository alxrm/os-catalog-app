package com.rm.oscatalog.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Video implements Parcelable {

    public final String Name;
    public final String DownloadLink;
    public final String YouTubeLink;
    public final float Size;
    public final int Duration;

    public Video(String name, String downloadLink, String youTubeLink, float size, int duration) {
        this.Name = name;
        this.Size = size;
        this.DownloadLink = downloadLink;
        this.YouTubeLink = youTubeLink;
        this.Duration = duration;
    }

    protected Video(Parcel in) {
        this.Name = in.readString();
        this.DownloadLink = in.readString();
        this.YouTubeLink = in.readString();
        this.Size = in.readFloat();
        this.Duration = in.readInt();
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
        dest.writeString(Name);
        dest.writeString(DownloadLink);
        dest.writeString(YouTubeLink);
        dest.writeFloat(Size);
        dest.writeInt(Duration);
    }
}
