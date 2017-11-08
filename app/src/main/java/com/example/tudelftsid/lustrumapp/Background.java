package com.example.tudelftsid.lustrumapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by TUDelft SID on 2-11-2017.
 */

public class Background extends View {

    public static final int CHANCE = 100;
    public static final int SPEED = 1;

    private ArrayList<Confetti> confettis;
    private Random r;

    public Background(Context context) {
        super(context);
        initialize();
    }

    public Background(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initialize();
    }

    public Background(Context context, AttributeSet attributeSet, int defStyleAttr) {
        super(context, attributeSet, defStyleAttr);
        initialize();
    }

    public void initialize() {
        confettis = new ArrayList<>();
        r = new Random();
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //spawnNewConfetti(canvas);
        System.out.println();
        for (Confetti c : confettis) {
            c.draw(canvas);
        }
        invalidate();
    }

    public void spawnNewConfetti(Canvas canvas) {
        if (r.nextInt(CHANCE) == 0) {
            Drawable d = getResources().getDrawable(R.mipmap.confetti_2d);
            int x = (int) (Math.random() * canvas.getWidth());
            Confetti c = new Confetti(x, -100, SPEED, d);
            confettis.add(c);
        }
    }
}
