package com.virgiel.lustrumapp.Animation;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class TranslateAnimation extends Animation {
    private View view;
    private float fromX;
    private float fromY;
    private float toX;
    private float toY;

    public TranslateAnimation(View view, float fromX, float fromY, float toX, float toY) {
        this.view = view;
        this.fromX = fromX;
        this.fromY = fromY;
        this.toX = toX;
        this.toY = toY;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        float x = (toX - fromX) * interpolatedTime + fromX;
        float y = (toY - fromY) * interpolatedTime + fromY;
        view.setX(x);
        view.setY(y);
        view.requestLayout();
    }
}