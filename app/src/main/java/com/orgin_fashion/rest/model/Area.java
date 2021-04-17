package com.orgin_fashion.rest.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Area implements Parcelable {
    String name_en,name_local,id;

    public Area(String name_en, String name_local, String id) {
        this.name_en = name_en;
        this.name_local = name_local;
        this.id = id;
    }

    protected Area(Parcel in) {
        name_en = in.readString();
        name_local = in.readString();
        id = in.readString();
    }

    public static final Creator<Area> CREATOR = new Creator<Area>() {
        @Override
        public Area createFromParcel(Parcel in) {
            return new Area(in);
        }

        @Override
        public Area[] newArray(int size) {
            return new Area[size];
        }
    };

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public String getName_local() {
        return name_local;
    }

    public void setName_local(String name_local) {
        this.name_local = name_local;
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
        dest.writeString(name_en);
        dest.writeString(name_local);
        dest.writeString(id);
    }
}
