package com.orgin_fashion.rest.model;

import com.google.gson.annotations.SerializedName;

public class Comment {
    int postId,id;
    String name,email;

    @SerializedName("body")
    String text;

    public Comment(int postId, int id, String name, String email, String text) {
        this.postId = postId;
        this.id = id;
        this.name = name;
        this.email = email;
        this.text = text;
    }

    public int getPostId() {
        return postId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getText() {
        return text;
    }
}
