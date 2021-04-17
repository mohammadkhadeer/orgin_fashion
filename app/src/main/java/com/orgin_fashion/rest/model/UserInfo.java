package com.orgin_fashion.rest.model;

import com.google.gson.annotations.SerializedName;

public class UserInfo {
    @SerializedName("id")
    String server_id;
    String token,fullName,picture;

    public UserInfo(String server_id, String token, String fullName, String picture) {
        this.server_id = server_id;
        this.token = token;
        this.fullName = fullName;
        this.picture = picture;
    }

    public String getServer_id() {
        return server_id;
    }

    public void setServer_id(String server_id) {
        this.server_id = server_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
