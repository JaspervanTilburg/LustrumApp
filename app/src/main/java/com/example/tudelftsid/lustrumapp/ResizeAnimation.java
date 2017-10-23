package com.example.tudelftsid.lustrumapp;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class ResizeAnimation extends Animation {
    private View view;
    private int toHeight;
    private int toWidth;

    public ResizeAnimation(View view, double scaleFactor) {
        this.view = view;
        ViewGroup.LayoutParams p = view.getLayoutParams();
        this.toHeight = (int) (p.height * scaleFactor);
        this.toWidth = (int) (p.width * scaleFactor);
        setDuration(100);
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        ViewGroup.LayoutParams p = view.getLayoutParams();
        double height = (toHeight - p.height) * interpolatedTime + p.height;
        double width = (toWidth - p.width) * interpolatedTime + p.width;
        p.height = (int) height;
        p.width = (int) width;
        view.requestLayout();
    }
}