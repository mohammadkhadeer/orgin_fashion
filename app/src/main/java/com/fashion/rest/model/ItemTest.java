package com.fashion.rest.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ItemTest {
    @SerializedName("Photo")
    ArrayList<Flag> flagArrayL;
    @SerializedName("store")
    Store store;
    String name,name_local,description,description_local,price,discountPrice;


    public ItemTest(){}

    public ItemTest(ArrayList<Flag> flagArrayL, Store store, String name, String name_local, String description, String description_local, String price, String discountPrice) {
        this.flagArrayL = flagArrayL;
        this.name = name;
        this.store = store;
        this.name_local = name_local;
        this.description = description;
        this.description_local = description_local;
        this.price = price;
        this.discountPrice = discountPrice;
    }

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
}
