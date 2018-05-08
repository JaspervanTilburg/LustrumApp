package com.virgiel.lustrumapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TUDelft SID on 10-11-2017.
 */


public class Selfie {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("created_at")
    @Expose
    private String createdAt;

    @SerializedName("image")
    @Expose
    private String imageURL;

    @SerializedName("likes")
    @Expose
    private Integer likes;

    @SerializedName("liked_by_me")
    @Expose
    private boolean likedByMe;

    @SerializedName("user")
    @Expose
    private String user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getImageURL() {
        return imageURL;
    }

    public Integer getLikes() {
        return likes;
    }

    public boolean isLikedByMe() {
        return likedByMe;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public void setLikedByMe(boolean likedByMe) {
        this.likedByMe = likedByMe;
    }

    public String getUser() {
        return user;
    }
}
