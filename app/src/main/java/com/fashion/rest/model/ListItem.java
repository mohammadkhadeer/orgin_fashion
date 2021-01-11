package com.fashion.rest.model;

public class ListItem {
    String listType;

    public ListItem(String listType) {
        this.listType = listType;
    }

    public ListItem() {
    }

    public String getListType() {
        return listType;
    }

    public void setListType(String listType) {
        this.listType = listType;
    }
}
