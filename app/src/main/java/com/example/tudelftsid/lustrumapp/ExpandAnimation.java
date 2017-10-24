package com.example.tudelftsid.lustrumapp;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class ExpandAnimation extends Animation {
    private View view;
    private int fromHeight;
    private int fromWidth;
    private int toHeight;
    private int toWidth;

    public ExpandAnimation(View view) {
        this.view = view;
        ViewGroup.LayoutParams p = view.getLayoutParams();
        this.fromHeight = 0;
        this.fromWidth = 0;
        this.toHeight = p.height;
        this.toWidth = p.width;
        //setDuration(100);
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