package com.orgin_fashion.rest.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Store implements Parcelable {
    @SerializedName("photo")
    Flag flag;
    @SerializedName("cover")
    Cover cover;
    String name,id,name_local,phone_number,address,maps_url,website_link,area;
    @SerializedName("location")
    ArrayList<Location> locationsArrayL;

    public Store(){}

    public Store(Flag flag, Cover cover, String name, String id, String name_local, String phone_number, String address, String maps_url, String website_link, String area, ArrayList<Location> locationsArrayL) {
        this.flag = flag;
        this.cover = cover;
        this.name = name;
        this.id = id;
        this.name_local = name_local;
        this.phone_number = phone_number;
        this.address = address;
        this.maps_url = maps_url;
        this.website_link = website_link;
        this.area = area;
        this.locationsArrayL = locationsArrayL;
    }

    protected Store(Parcel in) {
        flag = in.readParcelable(Flag.class.getClassLoader());
        cover = in.readParcelable(Cover.class.getClassLoader());
        name = in.readString();
        id = in.readString();
        name_local = in.readString();
        phone_number = in.readString();
        address = in.readString();
        maps_url = in.readString();
        website_link = in.readString();
        area = in.readString();
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

    public Cover getCover() {
        return cover;
    }

    public void setCover(Cover cover) {
        this.cover = cover;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMaps_url() {
        return maps_url;
    }

    public void setMaps_url(String maps_url) {
        this.maps_url = maps_url;
    }

    public String getWebsite_link() {
        return website_link;
    }

    public void setWebsite_link(String website_link) {
        this.website_link = website_link;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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
        dest.writeParcelable(cover, flags);
        dest.writeString(name);
        dest.writeString(id);
        dest.writeString(name_local);
        dest.writeString(phone_number);
        dest.writeString(address);
        dest.writeString(maps_url);
        dest.writeString(website_link);
        dest.writeString(area);
        dest.writeTypedList(locationsArrayL);
    }
}
