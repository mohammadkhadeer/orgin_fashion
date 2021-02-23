package com.fashion.rest.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Offer {
    @SerializedName("Photo")
    ArrayList<Flag> flagArrayL;
    String name,name_local,description,description_local,price,discountPrice,promo_code,valide,color,secondaryColor;
    @SerializedName("id")
    String item_id;

    public Offer(){}

    public Offer(ArrayList<Flag> flagArrayL, String name, String name_local, String description, String description_local, String price, String discountPrice, String promo_code, String valide, String start_color, String end_color, String item_id) {
        this.flagArrayL = flagArrayL;
        this.name = name;
        this.name_local = name_local;
        this.description = description;
        this.description_local = description_local;
        this.price = price;
        this.discountPrice = discountPrice;
        this.promo_code = promo_code;
        this.valide = valide;
        this.color = start_color;
        this.secondaryColor = end_color;
        this.item_id = item_id;
    }

    public ArrayList<Flag> getFlagArrayL() {
        return flagArrayL;
    }

    public void setFlagArrayL(ArrayList<Flag> flagArrayL) {
        this.flagArrayL = flagArrayL;
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
        return promo_code;
    }

    public void setPromo_code(String promo_code) {
        this.promo_code = promo_code;
    }

    public String getValide() {
        return valide;
    }

    public void setValide(String valide) {
        this.valide = valide;
    }

    public String getStart_color() {
        return color;
    }

    public void setStart_color(String start_color) {
        this.color = start_color;
    }

    public String getEnd_color() {
        return secondaryColor;
    }

    public void setEnd_color(String end_color) {
        this.secondaryColor = end_color;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }
}
