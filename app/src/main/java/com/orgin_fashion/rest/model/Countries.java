package com.orgin_fashion.rest.model;

import com.google.gson.annotations.SerializedName;

public class Countries {
    String name_en,name_local,id;
    @SerializedName("flag")
    Flag flag;

    public Countries(String name_en, String name_local, String id, Flag flag) {
        this.name_en = name_en;
        this.name_local = name_local;
        this.id = id;
        this.flag = flag;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public String getName_local() {
        return name_local;
    }

    public void setName_local(String name_local) {
        this.name_local = name_local;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Flag getFlag() {
        return flag;
    }

    public void setFlag(Flag flag) {
        this.flag = flag;
    }
}
