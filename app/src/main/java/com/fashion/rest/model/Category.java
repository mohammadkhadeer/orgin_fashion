package com.fashion.rest.model;

public class Category {
    String CategoryNameStr;
    int imageIDInt;

    public Category(String categoryNameStr, int imageIDInt) {
        CategoryNameStr = categoryNameStr;
        this.imageIDInt = imageIDInt;
    }

    public String getCategoryNameStr() {
        return CategoryNameStr;
    }

    public void setCategoryNameStr(String categoryNameStr) {
        CategoryNameStr = categoryNameStr;
    }

    public int getImageIDInt() {
        return imageIDInt;
    }

    public void setImageIDInt(int imageIDInt) {
        this.imageIDInt = imageIDInt;
    }
}
