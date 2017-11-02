package com.example.tudelftsid.lustrumapp;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;
import android.widget.TableLayout;

public class WeightAnimation extends Animation {
    private View view;
    private float fromWeight;
    private float toWeight;

    public WeightAnimation(View view, float fromWeight, float toWeight, long duration) {
        this.view = view;
        this.fromWeight = fromWeight;
        this.toWeight = toWeight;
        setDuration(duration);
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        float weight = (toWeight - fromWeight) * interpolatedTime + fromWeight;
        ((LinearLayout.LayoutParams) view.getLayoutParams()).weight = weight;
        view.requestLayout();
    }
}