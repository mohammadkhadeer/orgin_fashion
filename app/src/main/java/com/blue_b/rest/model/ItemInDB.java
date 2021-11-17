package com.blue_b.rest.model;

public class ItemInDB {
    int id,imageID;
    String image,name,des;
    Double price,priceN,priceO;
    int numberOfItemNow;

    public ItemInDB(int id, int imageID, String image, String name, String des, Double price, Double priceN, Double priceO, int numberOfItemNow) {
        this.id = id;
        this.imageID = imageID;
        this.image = image;
        this.name = name;
        this.des = des;
        this.price = price;
        this.priceN = priceN;
        this.priceO = priceO;
        this.numberOfItemNow = numberOfItemNow;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPriceN() {
        return priceN;
    }

    public void setPriceN(Double priceN) {
        this.priceN = priceN;
    }

    public Double getPriceO() {
        return priceO;
    }

    public void setPriceO(Double priceO) {
        this.priceO = priceO;
    }

    public int getNumberOfItemNow() {
        return numberOfItemNow;
    }

    public void setNumberOfItemNow(int numberOfItemNow) {
        this.numberOfItemNow = numberOfItemNow;
    }
}
