package com.example.tudelftsid.lustrumapp.DateSpel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TUDelft SID on 10-11-2017.
 */


public class DateQuestion {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("question")
    @Expose
    private String question;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
