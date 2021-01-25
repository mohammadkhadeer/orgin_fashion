package com.fashion.rest.model;

import com.google.gson.annotations.SerializedName;

public class Areas {
    String name_en,name_local;
    @SerializedName("city")
    City city;

    public Areas(String name_en, String name_local, City city) {
        this.name_en = name_en;
        this.name_local = name_local;
        this.city = city;
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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
