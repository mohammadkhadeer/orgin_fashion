package com.orgin_fashion.rest.model;

public class Price {
    double price,newPrice,oldPrice;

    public Price(double price, double newPrice, double oldPrice) {
        this.price = price;
        this.newPrice = newPrice;
        this.oldPrice = oldPrice;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(double newPrice) {
        this.newPrice = newPrice;
    }

    public double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(double oldPrice) {
        this.oldPrice = oldPrice;
    }
}
