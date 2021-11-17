package com.blue_b.rest.model;

public class Notification {
    String openOrNotYet,notificationTitle,notificationDes
            ,imagePath,timeStamp,itemServerID
            ,date,cat;

    public Notification(String openOrNotYet, String notificationTitle, String notificationDes, String imagePath, String timeStamp, String itemServerID, String date, String cat) {
        this.openOrNotYet = openOrNotYet;
        this.notificationTitle = notificationTitle;
        this.notificationDes = notificationDes;
        this.imagePath = imagePath;
        this.timeStamp = timeStamp;
        this.itemServerID = itemServerID;
        this.date = date;
        this.cat = cat;
    }

    public String getOpenOrNotYet() {
        return openOrNotYet;
    }

    public void setOpenOrNotYet(String openOrNotYet) {
        this.openOrNotYet = openOrNotYet;
    }

    public String getNotificationTitle() {
        return notificationTitle;
    }

    public void setNotificationTitle(String notificationTitle) {
        this.notificationTitle = notificationTitle;
    }

    public String getNotificationDes() {
        return notificationDes;
    }

    public void setNotificationDes(String notificationDes) {
        this.notificationDes = notificationDes;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getItemServerID() {
        return itemServerID;
    }

    public void setItemServerID(String itemServerID) {
        this.itemServerID = itemServerID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }
}
