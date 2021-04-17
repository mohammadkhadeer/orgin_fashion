package com.orgin_fashion.rest.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class Sub_Cat implements Parcelable {
    String name_en,name_local,appearance,id,brand;
    @SerializedName("category")
    String category_id;
    @SerializedName("image")
    Flag flag;
    public  Sub_Cat(){}

    public Sub_Cat(String name_en, String name_local, String appearance, String id, String brand, String category_id, Flag flag) {
        this.name_en = name_en;
        this.name_local = name_local;
        this.appearance = appearance;
        this.id = id;
        this.brand = brand;
        this.category_id = category_id;
        this.flag = flag;
    }

    protected Sub_Cat(Parcel in) {
        name_en = in.readString();
        name_local = in.readString();
        appearance = in.readString();
        id = in.readString();
        brand = in.readString();
        category_id = in.readString();
        flag = in.readParcelable(Flag.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name_en);
        dest.writeString(name_local);
        dest.writeString(appearance);
        dest.writeString(id);
        dest.writeString(brand);
        dest.writeString(category_id);
        dest.writeParcelable(flag, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Sub_Cat> CREATOR = new Creator<Sub_Cat>() {
        @Override
        public Sub_Cat createFromParcel(Parcel in) {
            return new Sub_Cat(in);
        }

        @Override
        public Sub_Cat[] newArray(int size) {
            return new Sub_Cat[size];
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

    public String getAppearance() {
        return appearance;
    }

    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public Flag getFlag() {
        return flag;
    }

    public void setFlag(Flag flag) {
        this.flag = flag;
    }
}