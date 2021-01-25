package com.fashion.rest.model;

public class Area {
    String name_en,name_local;

    public Area(String name_en, String name_local) {
        this.name_en = name_en;
        this.name_local = name_local;
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
}
