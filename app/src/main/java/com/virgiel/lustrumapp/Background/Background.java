package com.virgiel.lustrumapp.Background;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.virgiel.lustrumapp.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by TUDelft SID on 2-11-2017.
 */

public class Background extends View {

    public static final int CHANCE = 100;
    public static final int SPEED = 2;
    public static final int MAX_CONFETTIS = 10;

    private int count = CHANCE;
    private int locCount = 0;
    private int[] locations = {100, 600, 350, 850};
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
        spawnNewConfetti(canvas);
        for (Confetti c : confettis) {
            c.draw(canvas);
        }
        if (confettis.size() > MAX_CONFETTIS) {
            confettis.remove(0);
        }
        invalidate();
    }

    public void spawnNewConfetti(Canvas canvas) {
        if (count == CHANCE) {
            if (locCount == locations.length) {
                locCount = 0;
            }
            Drawable d = randomImg();
            Confetti c = new Confetti(locations[locCount], -100, SPEED, d);
            confettis.add(c);
            locCount++;
            count = 0;
        }
        count++;
    }

    public Drawable randomImg() {
        int[] images = {R.mipmap.asset_1, R.mipmap.asset_1_blue, R.mipmap.asset_1_light,
                R.mipmap.asset_1_pink, R.mipmap.asset_2, R.mipmap.asset_2_blue, R.mipmap.asset_2_light,
                R.mipmap.asset_2_pink, R.mipmap.asset_3, R.mipmap.confetti_1_2d, R.mipmap.confetti_1_2d_blue,
                R.mipmap.confetti_1_2d_light, R.mipmap.confetti_1_2d_pink, R.mipmap.confetti_2d,
                R.mipmap.confetti_2d_blue, R.mipmap.confetti_2d_light, R.mipmap.confetti_2d_pink,
                R.mipmap.confetti_3d, R.mipmap.confetti_3d_blue, R.mipmap.confetti_3d_light,
                R.mipmap.confetti_3d_pink, R.mipmap.tekengebied_12, R.mipmap.tekengebied_12_blue,
                R.mipmap.tekengebied_12_light, R.mipmap.tekengebied_12_pink};
        Random r = new Random();
        int val = r.nextInt(images.length);
        return getResources().getDrawable(images[val]);
    }

}
