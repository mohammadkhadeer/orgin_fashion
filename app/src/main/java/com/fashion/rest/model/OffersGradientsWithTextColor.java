package com.fashion.rest.model;

public class OffersGradientsWithTextColor {
    String gradient,fontColor ;

    public OffersGradientsWithTextColor(String gradient, String fontColor) {
        this.gradient = gradient;
        this.fontColor = fontColor;
    }

    public String getGradient() {
        return gradient;
    }

    public void setGradient(String gradient) {
        this.gradient = gradient;
    }

    public String getFontColor() {
        return fontColor;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }
}
