package com.blue_b.rest.model;

public class ListItem {
    String listType;

    public ListItem() {
    }

    public ListItem(String listType) {
        this.listType = listType;
    }

    public String getListType() {
        return listType;
    }

    public void setListType(String listType) {
        this.listType = listType;
    }
}
