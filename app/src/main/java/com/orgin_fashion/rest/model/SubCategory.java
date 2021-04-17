package com.orgin_fashion.rest.model;

public class SubCategory {
    String sub_category_en, sub_category_local
	, sub_category_image , sub_category_icon
    , sub_category_sort_num ,sub_category_type ;

    public SubCategory(String sub_category_en, String sub_category_local, String sub_category_image, String sub_category_icon, String sub_category_sort_num, String sub_category_type) {
        this.sub_category_en = sub_category_en;
        this.sub_category_local = sub_category_local;
        this.sub_category_image = sub_category_image;
        this.sub_category_icon = sub_category_icon;
        this.sub_category_sort_num = sub_category_sort_num;
        this.sub_category_type = sub_category_type;
    }

    public String getSub_category_en() {
        return sub_category_en;
    }

    public void setSub_category_en(String sub_category_en) {
        this.sub_category_en = sub_category_en;
    }

    public String getSub_category_local() {
        return sub_category_local;
    }

    public void setSub_category_local(String sub_category_local) {
        this.sub_category_local = sub_category_local;
    }

    public String getSub_category_image() {
        return sub_category_image;
    }

    public void setSub_category_image(String sub_category_image) {
        this.sub_category_image = sub_category_image;
    }

    public String getSub_category_icon() {
        return sub_category_icon;
    }

    public void setSub_category_icon(String sub_category_icon) {
        this.sub_category_icon = sub_category_icon;
    }

    public String getSub_category_sort_num() {
        return sub_category_sort_num;
    }

    public void setSub_category_sort_num(String sub_category_sort_num) {
        this.sub_category_sort_num = sub_category_sort_num;
    }

    public String getSub_category_type() {
        return sub_category_type;
    }

    public void setSub_category_type(String sub_category_type) {
        this.sub_category_type = sub_category_type;
    }
}
