package com.fashion.rest.model;

public class Deal {
    int image1 ;
    String image,name,des;
    Price price;
    OffersGradientsWithTextColor offersGradientsWithTextColor;

    public Deal() {
    }

    public Deal(int image1, String image, String name, String des, Price price, OffersGradientsWithTextColor offersGradientsWithTextColor) {
        this.image1 = image1;
        this.image = image;
        this.name = name;
        this.des = des;
        this.price = price;
        this.offersGradientsWithTextColor = offersGradientsWithTextColor;
    }
    public int getImage1() {
        return image1;
    }

    public void setImage1(int image1) {
        this.image1 = image1;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public OffersGradientsWithTextColor getOffersGradientsWithTextColor() {
        return offersGradientsWithTextColor;
    }

    public void setOffersGradientsWithTextColor(OffersGradientsWithTextColor offersGradientsWithTextColor) {
        this.offersGradientsWithTextColor = offersGradientsWithTextColor;
    }
}
