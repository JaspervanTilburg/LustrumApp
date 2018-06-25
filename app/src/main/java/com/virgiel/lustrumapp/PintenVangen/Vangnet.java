package com.virgiel.lustrumapp.PintenVangen;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

/**
 * Created by Jasper on 26-6-2016.
 */
public class Vangnet {

    public static final int IMG_WIDTH = 300;
    public static final int IMG_HEIGHT = 138;

    private int x;
    private Drawable lower;
    private Drawable upper;

    public Vangnet(int x, Drawable lower, Drawable upper) {
        this.x = x;
        this.lower = lower;
        this.upper = upper;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void drawUpper(Canvas canvas) {
        upper.setBounds(x, canvas.getClipBounds().height() - IMG_HEIGHT, x + IMG_WIDTH, canvas.getClipBounds().height());
        upper.draw(canvas);
    }

    public void drawLower(Canvas canvas) {
        lower.setBounds(x, canvas.getClipBounds().height() - IMG_HEIGHT, x + IMG_WIDTH, canvas.getClipBounds().height());
        lower.draw(canvas);
    }
}
