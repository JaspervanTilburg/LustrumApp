package com.example.tudelftsid.lustrumapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TUDelft SID on 10-11-2017.
 */


public class Profile {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("avatar")
    @Expose
    private String avatar;

    @SerializedName("gender")
    @Expose
    private String gender;

    @SerializedName("age")
    @Expose
    private Integer age;

    @SerializedName("club")
    @Expose
    private String club;

    @SerializedName("bolletjes")
    @Expose
    private Integer bolletjes;

    @SerializedName("huis")
    @Expose
    private String huis;

    @SerializedName("clubjaar")
    @Expose
    private Integer clubjaar;

    @SerializedName("verticale")
    @Expose
    private String verticale;

    @SerializedName("studie")
    @Expose
    private String studie;

    private String matchCreatedAt;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getBolletjes() {
        return bolletjes;
    }

    public void setBolletjes(int bolletjes) {
        this.bolletjes = bolletjes;
    }

    public String getHuis() {
        return huis;
    }

    public void setHuis(String huis) {
        this.huis = huis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getClubjaar() {
        return clubjaar;
    }

    public void setClubjaar(int clubjaar) {
        this.clubjaar = clubjaar;
    }

    public String getVerticale() {
        return verticale;
    }

    public void setVerticale(String verticale) {
        this.verticale = verticale;
    }

    public String getStudie() {
        return studie;
    }

    public void setStudie(String studie) {
        this.studie = studie;
    }

    public String getMatchCreatedAt() {
        return matchCreatedAt;
    }

    public void setMatchCreatedAt(String matchCreatedAt) {
        this.matchCreatedAt = matchCreatedAt;
    }
}
