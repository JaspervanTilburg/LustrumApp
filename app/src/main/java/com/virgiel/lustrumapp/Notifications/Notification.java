package com.virgiel.lustrumapp.Notifications;

/**
 * Created by TUDelft SID on 27-6-2018.
 */

public class Notification {

    private String title;
    private String body;
    private long time;

    public Notification() {

    }

    public Notification(String title, String body, long time) {
        this.title = title;
        this.body = body;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public long getTime() {
        return time;
    }
}
