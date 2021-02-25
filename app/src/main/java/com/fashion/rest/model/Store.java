package com.fashion.rest.model;

import com.google.gson.annotations.SerializedName;

public class Store {
    @SerializedName("photo")
    Flag flag;
    String name,id,name_local,phone_number;

    public Store(Flag flag, String name, String id, String name_local, String phone_number) {
        this.flag = flag;
        this.name = name;
        this.id = id;
        this.name_local = name_local;
        this.phone_number = phone_number;
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

    public String getName_local() {
        return name_local;
    }

    public void setName_local(String name_local) {
        this.name_local = name_local;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
