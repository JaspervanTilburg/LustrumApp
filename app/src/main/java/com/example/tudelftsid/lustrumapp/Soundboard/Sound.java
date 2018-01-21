package com.example.tudelftsid.lustrumapp.Soundboard;

/**
 * Created by Jip on 24-11-2017.
 * Taken from: https://github.com/meonwax/soundboard/blob/master/app/src/main/java/de/meonwax/soundboard/sound/Sound.java
 */
public class Sound {

    private String mName;
    private int mResourceId;
    private int mImageId;

    public Sound(String name, int resourceId, int imageId) {
        this.mName = name;
        this.mResourceId = resourceId;
        this.mImageId = imageId;
    }

    public int getResourceId() {
        return mResourceId;
    }

    public void setResourceId(int resourceId) {
        this.mResourceId = resourceId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public int getmImageId() {
        return mImageId;
    }

    public void setmImageId(int mImageId) {
        this.mImageId = mImageId;
    }

    @Override
    public String toString() {
        return mName;
    }
}

