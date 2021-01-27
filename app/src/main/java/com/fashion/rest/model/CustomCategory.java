package com.fashion.rest.model;

import java.util.ArrayList;

public class CustomCategory {
    ArrayList<Category> customCategory;

    public CustomCategory(ArrayList<Category> customCategory) {
        this.customCategory = customCategory;
    }

    public ArrayList<Category> getCustomCategory() {
        return customCategory;
    }

    public void setCustomCategory(ArrayList<Category> customCategory) {
        this.customCategory = customCategory;
    }
}
