package com.fashion.rest.model;

public class OffersGradientsWithTextColor {
    String gradient ;
    int fontColor;

    public OffersGradientsWithTextColor(String gradient, int fontColor) {
        this.gradient = gradient;
        this.fontColor = fontColor;
    }

    public String getGradient() {
        return gradient;
    }

    public void setGradient(String gradient) {
        this.gradient = gradient;
    }

    public int getFontColor() {
        return fontColor;
    }

    public void setFontColor(int fontColor) {
        this.fontColor = fontColor;
    }
}
