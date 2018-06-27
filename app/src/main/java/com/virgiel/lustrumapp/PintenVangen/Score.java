package com.virgiel.lustrumapp.PintenVangen;

/**
 * Created by TUDelft SID on 27-6-2018.
 */

public class Score {

    private String user;
    private int score;
    private long timeStamp;

    public Score() {

    }

    public Score(String user, int score, long timeStamp) {
        this.user = user;
        this.score = score;
        this.timeStamp = timeStamp;
    }

    public String getUser() {
        return user;
    }

    public int getScore() {
        return score;
    }

    public long getTimeStamp() {
        return timeStamp;
    }
}
