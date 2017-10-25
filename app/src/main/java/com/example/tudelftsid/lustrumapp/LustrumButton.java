package com.example.tudelftsid.lustrumapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.AppCompatImageButton;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AnimationSet;
import android.widget.ImageButton;

import java.util.ArrayList;

    /**
     * Created by TUDelft SID on 24-10-2017.
     */
    public class LustrumButton extends AppCompatImageButton {

        public static final int EXPANDED_BUTTON_SIZE = 236;
        public static final int COLLAPSED_BUTTON_SIZE = 1;
        public static final int EXPAND_DURATION = 300;

        public static final int SELECT_BUTTON_SIZE = 314;
        public static final int DESELECT_BUTTON_SIZE = 157;
        public static final int SELECT_DURATION = 100;

        private int[] XLocations = {200, 300, 600};
        private int[] YLocations = {200, 1000, 300};

        private int color;
        private LustrumButton next;
        private LustrumButton previous;
        private ArrayList<LustrumButton> childButtons;

        public LustrumButton(Context context) {
            super(context);
            childButtons = new ArrayList<>();
        }

        public LustrumButton(Context context, AttributeSet attrs) {
            super(context, attrs);
            childButtons = new ArrayList<>();
        }

        public LustrumButton(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
            childButtons = new ArrayList<>();
        }

    public void addChildButton(LustrumButton button) {
        childButtons.add(button);
    }

    public ResizeAnimation animateScaleDown() {
        ResizeAnimation scaleUp = new ResizeAnimation(this,
                SELECT_BUTTON_SIZE, SELECT_BUTTON_SIZE, DESELECT_BUTTON_SIZE, DESELECT_BUTTON_SIZE, SELECT_DURATION);
        this.startAnimation(scaleUp);
        return scaleUp;
    }

    public ResizeAnimation animateScaleUp() {
        ResizeAnimation scaleDown = new ResizeAnimation(this,
                DESELECT_BUTTON_SIZE, DESELECT_BUTTON_SIZE, SELECT_BUTTON_SIZE, SELECT_BUTTON_SIZE, SELECT_DURATION);
        this.startAnimation(scaleDown);
        return scaleDown;
    }

    public void animateExpand() {
        for (int i = 0; i < childButtons.size(); i++) {
            LustrumButton button = childButtons.get(i);
            ResizeAnimation expandAnimation = new ResizeAnimation(button,
                    COLLAPSED_BUTTON_SIZE, COLLAPSED_BUTTON_SIZE, EXPANDED_BUTTON_SIZE, EXPANDED_BUTTON_SIZE, EXPAND_DURATION);
            TranslateAnimation translateAnimation = new TranslateAnimation(button,
                    this.getX() + 100, this.getY() + 100, XLocations[i], YLocations[i]);
            AnimationSet animationSet = new AnimationSet(true);
            animationSet.addAnimation(expandAnimation);
            animationSet.addAnimation(translateAnimation);
            animationSet.setDuration(EXPAND_DURATION);

            button.setVisibility(View.VISIBLE);
            button.setClickable(true);
            button.startAnimation(animationSet);
        }
    }

        public void animateCollapse() {
            for (int i = 0; i < childButtons.size(); i++) {
                LustrumButton button = childButtons.get(i);
                ResizeAnimation collapseAnimation = new ResizeAnimation(button,
                        EXPANDED_BUTTON_SIZE, EXPANDED_BUTTON_SIZE, COLLAPSED_BUTTON_SIZE, COLLAPSED_BUTTON_SIZE, EXPAND_DURATION);
                TranslateAnimation translateAnimation = new TranslateAnimation(button,
                        XLocations[i], YLocations[i], this.getX() + 100, this.getY() + 100);
                AnimationSet animationSet = new AnimationSet(true);
                animationSet.addAnimation(collapseAnimation);
                animationSet.addAnimation(translateAnimation);
                animationSet.setDuration(EXPAND_DURATION);

                button.setVisibility(View.INVISIBLE);
                button.setClickable(false);
                button.startAnimation(animationSet);
            }
        }

    public void setBackgroundColor(Activity activity) {
        System.out.println(color);
        activity.findViewById(R.id.activiteitenBackground).setBackgroundColor(color);
    }

    public LustrumButton getNext() {
        return next;
    }

    public void setNext(LustrumButton next) {
        this.next = next;
    }

    public LustrumButton getPrevious() {
        return previous;
    }

    public void setPrevious(LustrumButton previous) {
        this.previous = previous;
    }

    public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }
    }
