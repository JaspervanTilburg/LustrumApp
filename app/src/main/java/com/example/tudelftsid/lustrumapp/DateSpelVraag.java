package com.example.tudelftsid.lustrumapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Rolf on 15-11-2017.
 */

public class DateSpelVraag {

    @SerializedName("uppertext")
    @Expose
    private String upperText;

    @SerializedName("bottomtext")
    @Expose
    private String bottomText;

    public String getUpperText() {
        return upperText;
    }

    public String getBottomText() {
        return bottomText;
    }

    public void setUpperText(String upperText) {
        this.upperText = upperText;
    }

    public void setBottomText(String bottomText) {
        this.bottomText = bottomText;
    }
}
