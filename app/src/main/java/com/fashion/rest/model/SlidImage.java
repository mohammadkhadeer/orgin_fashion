package com.fashion.rest.model;

public class SlidImage {
    String imagePath;
    int imageNumber,totalImage;

    public SlidImage(String imagePath, int imageNumber, int totalImage) {
        this.imagePath = imagePath;
        this.imageNumber = imageNumber;
        this.totalImage = totalImage;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getImageNumber() {
        return imageNumber;
    }

    public void setImageNumber(int imageNumber) {
        this.imageNumber = imageNumber;
    }

    public int getTotalImage() {
        return totalImage;
    }

    public void setTotalImage(int totalImage) {
        this.totalImage = totalImage;
    }
}
