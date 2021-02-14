package com.fashion.rest.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Categories {
    String name,name_local;
    @SerializedName("image")
    Flag flag;
    @SerializedName("sub_categories")
    ArrayList<Sub_Cat> sub_catArrayList ;

    public Categories(String name, String name_local, Flag flag, ArrayList<Sub_Cat> sub_catArrayList) {
        this.name = name;
        this.name_local = name_local;
        this.flag = flag;
        this.sub_catArrayList = sub_catArrayList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName_local() {
        return name_local;
    }

    public void setName_local(String name_local) {
        this.name_local = name_local;
    }

    public Flag getFlag() {
        return flag;
    }

    public void setFlag(Flag flag) {
        this.flag = flag;
    }

    public ArrayList<Sub_Cat> getSub_catArrayList() {
        return sub_catArrayList;
    }

    public void setSub_catArrayList(ArrayList<Sub_Cat> sub_catArrayList) {
        this.sub_catArrayList = sub_catArrayList;
    }
}
