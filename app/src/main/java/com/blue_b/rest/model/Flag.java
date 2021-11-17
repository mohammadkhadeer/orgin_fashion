package com.blue_b.rest.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Flag implements Parcelable {
    String url;

    public Flag(String url) {
        this.url = url;
    }

    protected Flag(Parcel in) {
        url = in.readString();
    }

    public static final Creator<Flag> CREATOR = new Creator<Flag>() {
        @Override
        public Flag createFromParcel(Parcel in) {
            return new Flag(in);
        }

        @Override
        public Flag[] newArray(int size) {
            return new Flag[size];
        }
    };

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
    }
}
