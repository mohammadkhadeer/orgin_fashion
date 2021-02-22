package com.fashion.rest.model;

import java.util.ArrayList;

public class CustomCategory {
    ArrayList<Categories> customCategory;

    public CustomCategory(ArrayList<Categories> customCategory) {
        this.customCategory = customCategory;
    }

    public ArrayList<Categories> getCustomCategory() {
        return customCategory;
    }

    public void setCustomCategory(ArrayList<Categories> customCategory) {
        this.customCategory = customCategory;
    }
}
