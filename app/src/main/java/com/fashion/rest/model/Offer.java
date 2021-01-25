package com.fashion.rest.model;

public class Offer {
    //must to set delivery area
    String imagePathStr,categoryStr,mailTypeStr;
    int offerOrNotInt,newPriceInt;
    String timeStampStr;
    int priceInt;
    String arNameStr,enNameStr;
    String arDesStr,enDesStr;
    String subCategoryStr,restaurantsImageStr,restaurantsNameStr,restaurantsOpenAtStr,restaurantsCloseAtStr
            ,restaurantsCityStr,neighborhoodStr,restaurantsAddressLinkStr,restaurantsIDStr;

    public Offer(){}

    public Offer(String imagePathStr, String categoryStr, String mailTypeStr, int offerOrNotInt, int newPriceInt, String timeStampStr, int priceInt, String arNameStr, String enNameStr, String arDesStr, String enDesStr, String subCategoryStr,String restaurantsImageStr, String restaurantsNameStr, String restaurantsOpenAtStr, String restaurantsCloseAtStr, String restaurantsCityStr, String neighborhoodStr, String restaurantsAddressLinkStr, String restaurantsIDStr) {
        this.imagePathStr = imagePathStr;
        this.categoryStr = categoryStr;
        this.mailTypeStr = mailTypeStr;
        this.offerOrNotInt = offerOrNotInt;
        this.newPriceInt = newPriceInt;
        this.timeStampStr = timeStampStr;
        this.priceInt = priceInt;
        this.arNameStr = arNameStr;
        this.enNameStr = enNameStr;
        this.arDesStr = arDesStr;
        this.enDesStr = enDesStr;
        this.subCategoryStr = subCategoryStr;
        this.restaurantsNameStr = restaurantsNameStr;
        this.restaurantsImageStr = restaurantsImageStr;
        this.restaurantsOpenAtStr = restaurantsOpenAtStr;
        this.restaurantsCloseAtStr = restaurantsCloseAtStr;
        this.restaurantsCityStr = restaurantsCityStr;
        this.neighborhoodStr = neighborhoodStr;
        this.restaurantsAddressLinkStr = restaurantsAddressLinkStr;
        this.restaurantsIDStr = restaurantsIDStr;
    }

    public String getRestaurantsImageStr() {
        return restaurantsImageStr;
    }

    public void setRestaurantsImageStr(String restaurantsImageStr) {
        this.restaurantsImageStr = restaurantsImageStr;
    }

    public String getImagePathStr() {
        return imagePathStr;
    }

    public void setImagePathStr(String imagePathStr) {
        this.imagePathStr = imagePathStr;
    }

    public String getCategoryStr() {
        return categoryStr;
    }

    public void setCategoryStr(String categoryStr) {
        this.categoryStr = categoryStr;
    }

    public String getMailTypeStr() {
        return mailTypeStr;
    }

    public void setMailTypeStr(String mailTypeStr) {
        this.mailTypeStr = mailTypeStr;
    }

    public int getOfferOrNotInt() {
        return offerOrNotInt;
    }

    public void setOfferOrNotInt(int offerOrNotInt) {
        this.offerOrNotInt = offerOrNotInt;
    }

    public int getNewPriceInt() {
        return newPriceInt;
    }

    public void setNewPriceInt(int newPriceInt) {
        this.newPriceInt = newPriceInt;
    }

    public String getTimeStampStr() {
        return timeStampStr;
    }

    public void setTimeStampStr(String timeStampStr) {
        this.timeStampStr = timeStampStr;
    }

    public int getPriceInt() {
        return priceInt;
    }

    public void setPriceInt(int priceInt) {
        this.priceInt = priceInt;
    }

    public String getArNameStr() {
        return arNameStr;
    }

    public void setArNameStr(String arNameStr) {
        this.arNameStr = arNameStr;
    }

    public String getEnNameStr() {
        return enNameStr;
    }

    public void setEnNameStr(String enNameStr) {
        this.enNameStr = enNameStr;
    }

    public String getArDesStr() {
        return arDesStr;
    }

    public void setArDesStr(String arDesStr) {
        this.arDesStr = arDesStr;
    }

    public String getEnDesStr() {
        return enDesStr;
    }

    public void setEnDesStr(String enDesStr) {
        this.enDesStr = enDesStr;
    }

    public String getSubCategoryStr() {
        return subCategoryStr;
    }

    public void setSubCategoryStr(String subCategoryStr) {
        this.subCategoryStr = subCategoryStr;
    }

    public String getRestaurantsNameStr() {
        return restaurantsNameStr;
    }

    public void setRestaurantsNameStr(String restaurantsNameStr) {
        this.restaurantsNameStr = restaurantsNameStr;
    }

    public String getRestaurantsOpenAtStr() {
        return restaurantsOpenAtStr;
    }

    public void setRestaurantsOpenAtStr(String restaurantsOpenAtStr) {
        this.restaurantsOpenAtStr = restaurantsOpenAtStr;
    }

    public String getRestaurantsCloseAtStr() {
        return restaurantsCloseAtStr;
    }

    public void setRestaurantsCloseAtStr(String restaurantsCloseAtStr) {
        this.restaurantsCloseAtStr = restaurantsCloseAtStr;
    }

    public String getRestaurantsCityStr() {
        return restaurantsCityStr;
    }

    public void setRestaurantsCityStr(String restaurantsCityStr) {
        this.restaurantsCityStr = restaurantsCityStr;
    }

    public String getNeighborhoodStr() {
        return neighborhoodStr;
    }

    public void setNeighborhoodStr(String neighborhoodStr) {
        this.neighborhoodStr = neighborhoodStr;
    }

    public String getRestaurantsAddressLinkStr() {
        return restaurantsAddressLinkStr;
    }

    public void setRestaurantsAddressLinkStr(String restaurantsAddressLinkStr) {
        this.restaurantsAddressLinkStr = restaurantsAddressLinkStr;
    }

    public String getRestaurantsIDStr() {
        return restaurantsIDStr;
    }

    public void setRestaurantsIDStr(String restaurantsIDStr) {
        this.restaurantsIDStr = restaurantsIDStr;
    }
}
