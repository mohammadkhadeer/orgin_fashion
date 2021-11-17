package com.blue_b.rest.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ItemFavorite implements Parcelable {
    @SerializedName("Photo")
    ArrayList<Flag> flagArrayL;
    @SerializedName("store")
    Store store;
    @SerializedName("sub_category")
    Sub_Cat sub_cat;
    String name,name_local,description,description_local,price,discountPrice,id;
    boolean inStock,deleted;


    public ItemFavorite(){}

    public ItemFavorite(ArrayList<Flag> flagArrayL, Store store, Sub_Cat sub_cat, String name, String name_local, String description, String description_local, String price, String discountPrice, String id, boolean inStock, boolean deleted) {
        this.flagArrayL = flagArrayL;
        this.store = store;
        this.sub_cat = sub_cat;
        this.name = name;
        this.name_local = name_local;
        this.description = description;
        this.description_local = description_local;
        this.price = price;
        this.discountPrice = discountPrice;
        this.id = id;
        this.inStock = inStock;
        this.deleted = deleted;
    }

    protected ItemFavorite(Parcel in) {
        flagArrayL = in.createTypedArrayList(Flag.CREATOR);
        store = in.readParcelable(Store.class.getClassLoader());
        sub_cat = in.readParcelable(Sub_Cat.class.getClassLoader());
        name = in.readString();
        name_local = in.readString();
        description = in.readString();
        description_local = in.readString();
        price = in.readString();
        discountPrice = in.readString();
        id = in.readString();
        inStock = in.readByte() != 0;
        deleted = in.readByte() != 0;
    }

    public static final Creator<ItemFavorite> CREATOR = new Creator<ItemFavorite>() {
        @Override
        public ItemFavorite createFromParcel(Parcel in) {
            return new ItemFavorite(in);
        }

        @Override
        public ItemFavorite[] newArray(int size) {
            return new ItemFavorite[size];
        }
    };

    public ArrayList<Flag> getFlagArrayL() {
        return flagArrayL;
    }

    public void setFlagArrayL(ArrayList<Flag> flagArrayL) {
        this.flagArrayL = flagArrayL;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Sub_Cat getSub_cat() {
        return sub_cat;
    }

    public void setSub_cat(Sub_Cat sub_cat) {
        this.sub_cat = sub_cat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName_local() {
        return name_local;
    }

    public void setName_local(String name_local) {
        this.name_local = name_local;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription_local() {
        return description_local;
    }

    public void setDescription_local(String description_local) {
        this.description_local = description_local;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(String discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(flagArrayL);
        dest.writeParcelable(store, flags);
        dest.writeParcelable(sub_cat, flags);
        dest.writeString(name);
        dest.writeString(name_local);
        dest.writeString(description);
        dest.writeString(description_local);
        dest.writeString(price);
        dest.writeString(discountPrice);
        dest.writeString(id);
        dest.writeByte((byte) (inStock ? 1 : 0));
        dest.writeByte((byte) (deleted ? 1 : 0));
    }
}
