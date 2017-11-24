package com.example.tudelftsid.lustrumapp.Animation;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class ResizeAnimation extends Animation {
    private View view;
    private float fromHeight;
    private float fromWidth;
    private float toHeight;
    private float toWidth;

    public ResizeAnimation(View view, float fromHeight, float fromWidth, float toHeight, float toWidth, long duration) {
        this.view = view;
        this.fromHeight = fromHeight;
        this.fromWidth = fromWidth;
        this.toHeight = toHeight;
        this.toWidth = toWidth;
        setDuration(duration);

        ViewGroup.LayoutParams p = view.getLayoutParams();
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        ViewGroup.LayoutParams p = view.getLayoutParams();
        double height = (toHeight - fromHeight) * interpolatedTime + fromHeight;
        double width = (toWidth - fromWidth) * interpolatedTime + fromWidth;
        p.height = (int) height;
        p.width = (int) width;
        view.requestLayout();

    }
}