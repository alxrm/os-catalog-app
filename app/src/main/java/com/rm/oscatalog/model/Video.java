package com.rm.oscatalog.model;

import android.os.Parcel;

public class Video extends Content {

    public static final String EXTRA = "Вконтакте";

    private final String preview;
    private final int duration;

    public Video(Parcel in) {
        super(in);
        preview = in.readString();
        duration = in.readInt();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getLink() {
        return this.link;
    }

    public String getPreview() {
        return this.preview;
    }

    public int getDuration() {
        return this.duration;
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
        dest.writeString(TYPE_VIDEO);
        super.writeToParcel(dest, flags);
        dest.writeString(preview);
        dest.writeInt(duration);
    }
}
