package com.blue_b.rest.model;

import com.google.gson.annotations.SerializedName;

public class Post {

    int userId,id;
    String title;
    @SerializedName("body")
    String text;

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
