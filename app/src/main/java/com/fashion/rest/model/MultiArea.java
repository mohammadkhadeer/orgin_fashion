package com.fashion.rest.model;

public class MultiArea {
    String area_en,area_local,id;
    int isSelected;

    public MultiArea(String area_en, String area_local, String id, int isSelected) {
        this.area_en = area_en;
        this.area_local = area_local;
        this.id = id;
        this.isSelected = isSelected;
    }

    public String getArea_en() {
        return area_en;
    }

    public void setArea_en(String area_en) {
        this.area_en = area_en;
    }

    public String getArea_local() {
        return area_local;
    }

    public void setArea_local(String area_local) {
        this.area_local = area_local;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(int isSelected) {
        this.isSelected = isSelected;
    }
}
