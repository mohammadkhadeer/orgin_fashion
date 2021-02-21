package com.fashion.rest.model;

import com.google.gson.annotations.SerializedName;

public class City {
    String name_en,name_local;
    @SerializedName("id")
    String idServer;

    public City(String name_en, String name_local, String idServer) {
        this.name_en = name_en;
        this.name_local = name_local;
        this.idServer = idServer;
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

    public String getIdServer() {
        return idServer;
    }

    public void setIdServer(String idServer) {
        this.idServer = idServer;
    }
}
