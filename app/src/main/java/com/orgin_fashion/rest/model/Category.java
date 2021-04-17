package com.orgin_fashion.rest.model;

public class Category {
    String category_en, category_local
	, category_image , category_icon
    , category_sort_num ,category_type ;

    public Category(String category_en, String category_local, String category_image, String category_icon, String category_sort_num, String category_type) {
        this.category_en = category_en;
        this.category_local = category_local;
        this.category_image = category_image;
        this.category_icon = category_icon;
        this.category_sort_num = category_sort_num;
        this.category_type = category_type;
    }

    public String getCategory_en() {
        return category_en;
    }

    public void setCategory_en(String category_en) {
        this.category_en = category_en;
    }

    public String getCategory_local() {
        return category_local;
    }

    public void setCategory_local(String category_local) {
        this.category_local = category_local;
    }

    public String getCategory_image() {
        return category_image;
    }

    public void setCategory_image(String category_image) {
        this.category_image = category_image;
    }

    public String getCategory_icon() {
        return category_icon;
    }

    public void setCategory_icon(String category_icon) {
        this.category_icon = category_icon;
    }

    public String getCategory_sort_num() {
        return category_sort_num;
    }

    public void setCategory_sort_num(String category_sort_num) {
        this.category_sort_num = category_sort_num;
    }

    public String getCategory_type() {
        return category_type;
    }

    public void setCategory_type(String category_type) {
        this.category_type = category_type;
    }
}
