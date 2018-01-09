package com.virgiel.lustrumapp.Soundboard;

/**
 * Created by Jip on 24-11-2017.
 * Taken from: https://github.com/meonwax/soundboard/blob/master/app/src/main/java/de/meonwax/soundboard/sound/Sound.java
 */
public class Sound {

    private String mName;
    private int mResourceId;

    public Sound(String name, int resourceId) {
        this.mName = name;
        this.mResourceId = resourceId;
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

    @Override
    public String toString() {
        return mName;
    }
}

