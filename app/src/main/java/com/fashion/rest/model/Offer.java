package com.fashion.rest.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Offer {
    @SerializedName("Photo")
    ArrayList<Flag> flagArrayL;
    @SerializedName("store")
    Store store;
    String name,name_local,description,description_local,price,discountPrice,promoCode,valide,color,secondaryColor,brand;
    @SerializedName("id")
    String item_id;
    boolean isBlack;

    public Offer(){}

    public Offer(ArrayList<Flag> flagArrayL, Store store, String name, String name_local, String description, String description_local, String price, String discountPrice, String promo_code, String valide, String color, String secondaryColor, String brand, String item_id, boolean isBlack) {
        this.flagArrayL = flagArrayL;
        this.store = store;
        this.name = name;
        this.name_local = name_local;
        this.description = description;
        this.description_local = description_local;
        this.price = price;
        this.discountPrice = discountPrice;
        this.promoCode = promo_code;
        this.valide = valide;
        this.color = color;
        this.secondaryColor = secondaryColor;
        this.brand = brand;
        this.item_id = item_id;
        this.isBlack = isBlack;
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

    public String getPromo_code() {
        return promoCode;
    }

    public void setPromo_code(String promo_code) {
        this.promoCode = promo_code;
    }

    public String getValide() {
        return valide;
    }

    public void setValide(String valide) {
        this.valide = valide;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSecondaryColor() {
        return secondaryColor;
    }

    public void setSecondaryColor(String secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public boolean isBlack() {
        return isBlack;
    }

    public void setBlack(boolean black) {
        isBlack = black;
    }
}
