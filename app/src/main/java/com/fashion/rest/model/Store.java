package com.fashion.rest.model;

import com.google.gson.annotations.SerializedName;

public class Store {
    @SerializedName("flag")
    Flag flag;
    String name,id;

    public Store(Flag flag, String name, String id) {
        this.flag = flag;
        this.name = name;
        this.id = id;
    }

    public Flag getFlag() {
        return flag;
    }

    public void setFlag(Flag flag) {
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
