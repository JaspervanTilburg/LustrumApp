package com.example.tudelftsid.lustrumapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TUDelft SID on 10-11-2017.
 */


public class Profile {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("url")
    @Expose
    private String imageUrl;

    @SerializedName("age")
    @Expose
    private Integer age;

    @SerializedName("club")
    @Expose
    private String club;

    @SerializedName("bolletjes")
    @Expose
    private String bolletjes;

    @SerializedName("huis")
    @Expose
    private String huis;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String location) {
        this.club = location;
    }

    public String getBolletjes() {
        return bolletjes;
    }

    public void setBolletjes(String bolletjes) {
        this.bolletjes = bolletjes;
    }

    public String getHuis() {
        return huis;
    }

    public void setHuis(String huis) {
        this.huis = huis;
    }
}
