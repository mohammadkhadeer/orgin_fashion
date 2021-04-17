package com.orgin_fashion.rest.model;

import java.util.ArrayList;

public class CustomItems {
    ArrayList<ItemTest> customItems;

    public CustomItems(){}

    public CustomItems(ArrayList<ItemTest> customItems) {
        this.customItems = customItems;
    }

    public ArrayList<ItemTest> getCustomItems() {
        return customItems;
    }

    public void setCustomItems(ArrayList<ItemTest> customItems) {
        this.customItems = customItems;
    }
}
