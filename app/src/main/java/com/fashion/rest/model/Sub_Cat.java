package com.fashion.rest.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class Sub_Cat implements Parcelable {
    String name_en,name_local,appearance;
    @SerializedName("image")
    Flag flag;

    public Sub_Cat(String name_en, String name_local, String appearance, Flag flag) {
        this.name_en = name_en;
        this.name_local = name_local;
        this.appearance = appearance;
        this.flag = flag;
    }

    protected Sub_Cat(Parcel in) {
        name_en = in.readString();
        name_local = in.readString();
        appearance = in.readString();
        flag =  in.readParcelable(Flag.class.getClassLoader());
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

    public Flag getFlag() {
        return flag;
    }

    public void setFlag(Flag flag) {
        this.flag = flag;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name_en);
        dest.writeString(name_local);
        dest.writeString(appearance);
        dest.writeParcelable(flag, flags);
    }
}
