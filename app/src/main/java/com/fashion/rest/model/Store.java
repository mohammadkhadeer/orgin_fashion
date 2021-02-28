package com.fashion.rest.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Store implements Parcelable {
    @SerializedName("photo")
    Flag flag;
    String name,id,name_local,phone_number;
    @SerializedName("location")
    ArrayList<Location> locationsArrayL;

    public Store(Flag flag, String name, String id, String name_local, String phone_number, ArrayList<Location> locationsArrayL) {
        this.flag = flag;
        this.name = name;
        this.id = id;
        this.name_local = name_local;
        this.phone_number = phone_number;
        this.locationsArrayL = locationsArrayL;
    }

    protected Store(Parcel in) {
        flag = in.readParcelable(Flag.class.getClassLoader());
        name = in.readString();
        id = in.readString();
        name_local = in.readString();
        phone_number = in.readString();
        locationsArrayL = in.createTypedArrayList(Location.CREATOR);
    }

    public static final Creator<Store> CREATOR = new Creator<Store>() {
        @Override
        public Store createFromParcel(Parcel in) {
            return new Store(in);
        }

        @Override
        public Store[] newArray(int size) {
            return new Store[size];
        }
    };

    public Flag getFlag() {
        return flag;
    }

    public void setFlag(Flag flag) {
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName_local() {
        return name_local;
    }

    public void setName_local(String name_local) {
        this.name_local = name_local;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public ArrayList<Location> getLocationsArrayL() {
        return locationsArrayL;
    }

    public void setLocationsArrayL(ArrayList<Location> locationsArrayL) {
        this.locationsArrayL = locationsArrayL;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(flag, flags);
        dest.writeString(name);
        dest.writeString(id);
        dest.writeString(name_local);
        dest.writeString(phone_number);
        dest.writeTypedList(locationsArrayL);
    }
}
