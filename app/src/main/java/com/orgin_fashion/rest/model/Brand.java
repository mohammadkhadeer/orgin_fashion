package com.orgin_fashion.rest.model;

import com.google.gson.annotations.SerializedName;

public class Brand {
    @SerializedName("photo")
    Flag flag;
    String id;

    public Brand(Flag flag, String id) {
        this.flag = flag;
        this.id = id;
    }

    public Flag getFlag() {
        return flag;
    }

    public void setFlag(Flag flag) {
        this.flag = flag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
