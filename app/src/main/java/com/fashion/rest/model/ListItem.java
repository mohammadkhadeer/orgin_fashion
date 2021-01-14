package com.fashion.rest.model;

import java.util.ArrayList;

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
