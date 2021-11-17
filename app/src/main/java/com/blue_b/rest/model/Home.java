package com.blue_b.rest.model;

import com.google.gson.annotations.SerializedName;

public class Home {

    String sequence;
    @SerializedName("sub_category")
    Sub_Cat sub_cat;

    public Home(){}

    public Home(String sequence, Sub_Cat sub_cat) {
        this.sequence = sequence;
        this.sub_cat = sub_cat;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public Sub_Cat getSub_cat() {
        return sub_cat;
    }

    public void setSub_cat(Sub_Cat sub_cat) {
        this.sub_cat = sub_cat;
    }
}
