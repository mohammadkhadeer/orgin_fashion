package com.blue_b.rest.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Location implements Parcelable {
    String longitude,latitude,id;

    public Location(String longitude, String latitude, String id) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.id = id;
    }

    protected Location(Parcel in) {
        longitude = in.readString();
        latitude = in.readString();
        id = in.readString();
    }

    public static final Creator<Location> CREATOR = new Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(longitude);
        dest.writeString(latitude);
        dest.writeString(id);
    }
}
