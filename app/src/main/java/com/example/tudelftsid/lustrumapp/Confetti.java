package com.example.tudelftsid.lustrumapp;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;


public class Confetti {

    private int x;
    private int y;
    private int speed;
    private Drawable img;

    public Confetti(int x, int y, int speed, Drawable img) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.img = img;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void draw(Canvas canvas) {
        img.setBounds(x, y, x + 100, y + 100);
        img.draw(canvas);
        y += speed;
    }

}
